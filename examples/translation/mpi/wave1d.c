#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <math.h>
#include <mpi.h>

#define SQR(x) ((x)*(x))
/* MPI message tag */
#define FROMLEFT     1
#define FROMRIGHT    2
#define DATAPASS     3

/* Input parameters */
#ifdef _CIVL

const int NXB = 5;
$input int nx;                    /* number of discrete points including endpoints */
$assume 2 < nx && nx <= NXB;      /* setting bounds */
$input double c;                  /* physical constant to do with string */
$assume c > 0.0;
$input int height_init;           /* max amplitude in initial state */
$input int width_init;            /* width of initial pulse */
$assume 1 < width_init && width_init < nx;
$assume width_init < nx;
const int NSTEPSB = 5;        
$input int nsteps;                /*  number of iterations */
$assume 0 < nsteps && nsteps <= NSTEPSB;
const int wstep = 1;
int _NPROCS_LOWER_BOUND = 1;
int _NPROCS_UPPER_BOUND = 4;
double oracle[nsteps][nx];       /* array stores the results of sequential run in every step */
$input double u[nx];             /* arbitraty input data */

#else

int nx, height_init, width_init;
int nsteps, wstep;
double c;
double * u;

#endif

/* Global varibales */
double *u_prev, *u_curr, *u_next;
double k;
int nprocs, nxl, rank;
int left, right;                 /* left neighbor and right neighbor */

/* Returns the global index of the first cell owned
 * by the process with given rank */
int firstForProc(int rank) {
  return (rank*nx)/nprocs;      
}

/* Returns the number of cells 
   the given process owns */
int countForProc(int rank) {
  int a = firstForProc(rank);
  int b = firstForProc(rank + 1);

  return b - a;
}

/* Initialize data array for running in MPI */
void init() {
  int i;
  double e = exp(1.0);

  for(i = 0; i < nx; i++) {
    if(i == 1 || i >= width_init)
      u[i] = 0.0;
    else
      u[i] = height_init * e *
	exp(-1.0/(1-SQR(2.0*(i-width_init/2.0)/width_init)));
  }
}

/* Update cells owned by processes */
void update() {
  int i;
  double *tmp;

  for (i = 1; i < nxl + 1; i++){
    u_next[i] = 2.0*u_curr[i] - u_prev[i] +
      k*(u_curr[i+1] + u_curr[i-1] -2.0*u_curr[i]);
  }
  //cycle pointers
  tmp = u_prev;
  u_prev = u_curr;
  u_curr = u_next;
  u_next = tmp;
}

/* Initialization function, initializes all parameters and data array
   process 0 is responsible run in sequential for computing data for
   comparison */
void initialization() {
  int i, j;
  int nxlLeft = 0; 
  int nxlRight = 0;
  int neighborRank;

#ifndef _CIVL

  nx = 50;
  c = 0.3;
  height_init = 10;
  width_init = 10;
  nsteps = 500;
  wstep = 5;
  u = (double *)malloc(nx * sizeof(double));
  init();
#endif

  printf("Wave1d with nx=%d, c=%f, height_init=%d, width_init=%d, \
nsteps=%d, wstep=%d\n", nx, c, height_init, width_init, nsteps, wstep);
  assert(nx >= 2);
  assert(width_init < nx);
  assert(c > 0);
  assert(nsteps >= 1);
  assert(wstep >= 1 && wstep <= nsteps);
  k = c * c;

#ifdef _CIVL

  // If in CIVL verification mode and rank is 0,
  // do a sequential run and store result in "oracle" 
  // for comparison later
  if(rank == 0) {
    double *seq_u_curr, *seq_u_prev, *seq_u_next;
    double * tmp;
 
    seq_u_prev = (double *)malloc((nx + 2) * sizeof(double));
    assert(seq_u_prev);
    seq_u_curr = (double *)malloc((nx + 2) * sizeof(double));
    assert(seq_u_curr);
    seq_u_next = (double *)malloc((nx + 2) * sizeof(double));
    assert(seq_u_next);
    //Initialize seq_u_curr and seq_u_prev
    memcpy(&seq_u_curr[1], u, sizeof(double) * nx);
    memcpy(&seq_u_prev[1], u, sizeof(double) * nx);
    // run in sequential.
    // wirte data in time 0.
    for(i = 0; i < nx; i++) 
      oracle[0][i] = seq_u_curr[i + 1];
    for(i = 1; i < nsteps; i++){
      // exchange between head cell and tail cell.
      seq_u_curr[0] = seq_u_curr[nx];
      seq_u_curr[nx+1] = seq_u_curr[1];
      // update
      for (j = 1; j < nx + 1; j++){
	seq_u_next[j] = 2.0*seq_u_curr[j] - seq_u_prev[j] +
	  k*(seq_u_curr[j+1] + seq_u_curr[j-1] -2.0*seq_u_curr[j]);
      }    
      tmp = seq_u_prev;
      seq_u_prev = seq_u_curr;
      seq_u_curr = seq_u_next;
      seq_u_next = tmp;
      for(j = 0; j < nx; j++)
	oracle[i][j] = seq_u_curr[j + 1];
    }
    free(seq_u_prev);
    free(seq_u_curr);
    free(seq_u_next);
  }

#endif

  nxl = countForProc(rank);
  u_prev = (double *)malloc((nxl + 2) * sizeof(double));
  assert(u_prev);
  u_curr = (double *)malloc((nxl + 2) * sizeof(double));
  assert(u_curr);
  u_next = (double *)malloc((nxl + 2) * sizeof(double));
  assert(u_next);
  // Skip processes with none assignment
  neighborRank = rank;
  while(nxlLeft == 0 && nxl != 0) {
    neighborRank = neighborRank > 0 ? neighborRank-1 : nprocs - 1;
    nxlLeft = countForProc(neighborRank);
  }
  left = neighborRank;
  neighborRank = rank;
  while(nxlRight == 0 && nxl != 0) {
    neighborRank = neighborRank < nprocs - 1 ? neighborRank+1 : 0;
    nxlRight = countForProc(neighborRank);
  }
  right = neighborRank;
}

/* Print out the value of data cells; 
   Do comparison in CIVL mode */
void printData (int time, int first, int length, double * buf) {
  int i;

  for(i = 0; i < length; i++){
    printf("u_curr[%d]=%8.8f   ", first + i, buf[i]);
#ifdef _CIVL

    $assert (oracle[time][first + i] == buf[i]):			\
    "Error: disagreement at time %d position %d: saw %lf, expected %lf", \
    time, first + i, buf[i], oracle[time][first + i];

#endif
    if(i%2 == 0) printf("\n");
  }
}

/* receives data from other processes and wirte frames */
void write_frame (int time, int * displs, int * counts) {
  if(rank == 0) {
    double  buf[nx + 2];

    printf("======= Time %d =======\n", time);
    printData(time, displs[0], counts[0], &u_curr[1]);
    for(int i=1; i < nprocs; i++) {
      MPI_Recv(buf, counts[i], MPI_DOUBLE, i, DATAPASS, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
      printData(time, displs[i], counts[i], buf);
    }
    printf("\n");
  } else 
    MPI_Send(&u_curr[1], nxl, MPI_DOUBLE, 0, DATAPASS, MPI_COMM_WORLD);
}

/* Exchanging ghost cells */
void communicate(){
  MPI_Sendrecv(&u_curr[1], 1, MPI_DOUBLE, left, FROMRIGHT, &u_curr[nxl+1], 1, MPI_DOUBLE, 
	       right, FROMRIGHT, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
  MPI_Sendrecv(&u_curr[nxl], 1, MPI_DOUBLE, right, FROMLEFT, &u_curr[0], 1, 
	       MPI_DOUBLE, left, FROMLEFT, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
}

int main(int argc, char * argv[]) {
  int iter;
  int * nxls;           // array stores counts of data of all processes
  int * displs;         // array stores start points of data of all processes

  // elaborate nx to concrete value...
  for(int i=0; i<nx; i++);
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &nprocs); 
  initialization();
  displs = (int *)malloc(nprocs * sizeof(int));
  assert(displs);
  nxls = (int *)malloc(nprocs * sizeof(int));
  assert(nxls);
  if(rank == 0) {
    for(int i=0; i < nprocs; i++) {
      displs[i] = firstForProc(i);
      nxls[i] = countForProc(i);
    }
    // Send every process their cells
    for(int i=1; i < nprocs; i++) {
      int first = displs[i];
      int count = nxls[i];

      MPI_Send(&u[first], count, MPI_DOUBLE, i, DATAPASS, MPI_COMM_WORLD);
    }
    memcpy(&u_prev[1], u, sizeof(double) * nxl);
    memcpy(&u_curr[1], u, sizeof(double) * nxl);
  } else {
    MPI_Recv(&u_curr[1], nxl, MPI_DOUBLE, 0, DATAPASS, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    memcpy(&u_prev[1], &u_curr[1], sizeof(double) * nxl);
  }
  for(iter = 0; iter < nsteps; iter++) {
    if(iter % wstep == 0)
      write_frame(iter, displs, nxls);
    communicate();
    update();
  }
  free(u_curr);
  free(u_prev);
  free(u_next);
  free(displs);
  free(nxls);
  MPI_Finalize(); 
  return 0;
}
