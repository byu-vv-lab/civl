/* diffusion2d_cb.c: parallel 2d-diffusion equation solver with constant boundaries 
 * slicing matrix as a checker board.
 * To execute: mpicc diffusion2d_cb.c ; mpiexec -n 4 ./a.out 2 2
 * To verify: civl verify diffusion2d_cb.c
 */
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>
#include<mpi.h>

/* Message tags */
#define FROMLEFT   0
#define FROMRIGHT  1
#define FROMTOP    2
#define FROMBOTTOM 3
#define DATAPASS   4
#define comm MPI_COMM_WORLD

#ifdef _CIVL

$input int NXB = 5;                // nx upper bound
$input int nx;                     // global number of columns in matrix
$assume 1 <= nx && nx <= NXB;
$input int NYB = 5;                // ny upper bound
$input int ny;                     // global number of rows of matrix
$assume 1 <= ny && ny <= NYB;
$input double u_init[ny+2][nx+2];  // initial value of temperatures, includes 4 
                                   // strings of constant boundries
$input double k;                   // constant coefficient  
$assume k > 0.0 && k < 0.5;
$input int NSTEPSB = 5;            // boundary of number of steps
$input int nsteps;                 // number of steps
$assume 1<=nsteps && nsteps<=NSTEPSB;
$input int wstep = 1;              // write frame every this many time steps
double oracle[nsteps][ny+2][nx+2]; // solution computed sequentially, done by proc 0 only
$input int XPROCSB;                // Bound number of components of columns
$input int NPROCSX;                 // Number of components of columns
$assume NPROCSX > 1 && NPROCSX <= XPROCSB;
$input int YPROCSB;                // Bound number of components of rows
$input int NPROCSY;                 // Number of components of rows
$assume NPROCSY > 1 && NPROCSY <= YPROCSB;
$input int _NPROCS;
#else

int nx, ny, nsteps, wstep;
int NPROCSX, NPROCSY;
double constTemp, initTemp;       // values of constant boundaries and 
                                  // initial tempretures
double k;

#endif

/* Global variables */
double ** u_curr;
double ** u_next;
int nprocs, rank, left, right, top, bottom;
int nxl, nyl, firstCol, firstRow;

/* Compute the global column index of cells owned by the process */
int firstColForProc(int rank) {
  return (rank - (rank / NPROCSX)*NPROCSX)*nx/NPROCSX;
}

/* Compute the global row index of cells owned by the process */
int firstRowForProc(int rank) {
  return ((rank / NPROCSX)*ny)/NPROCSY;
}

/* Computes the number of columns owned by the process */
int countColForProc(int rank) {
  int a = firstColForProc(rank);
  int b;

  if((rank / NPROCSX) == ((rank+1) / NPROCSX))
    b = firstColForProc(rank+1);
  else
    b = nx;
  return b - a;
}

/* Computes the number of rows owned by the process */
int countRowForProc(int rank) {
  int a = firstRowForProc(rank);
  int b = firstRowForProc(rank+NPROCSX);

  return b - a;
}

/* Get the owner process of the given cell */
int OWNER(int col, int row) {
  int procRow = ((NPROCSY * (row+1))-1) / ny;
  int procCol = ((col + 1) * NPROCSX - 1) / nx;

  return procRow * NPROCSX + procCol;
}


#ifdef _CIVL
void setConstBoundaries() {
  int i;

  // sets vertical constant boundaries
  if(left == MPI_PROC_NULL)
    for(i=0; i<nyl+2; i++) {
      u_curr[i][0] = u_init[i + firstRow][0];
      u_next[i][0] = u_curr[i][0];
    }
  if(right == MPI_PROC_NULL)
    for(i=0; i<nyl+2; i++) {
      u_curr[i][nxl+1] = u_init[i + firstRow][nx+1];
      u_next[i][nxl+1] = u_curr[i][nxl+1];
    }
  // sets horizontal constant boundaries
  if(top == MPI_PROC_NULL)
    for(i=0; i<nxl+2; i++) {
      u_curr[0][i] = u_init[0][i + firstCol];
      u_next[0][i] = u_curr[0][i];
    }
  if(bottom == MPI_PROC_NULL)
    for(i=0; i<nxl+2; i++) {
      u_curr[nyl+1][i] = u_init[ny+1][i + firstCol];
      u_next[nyl+1][i] = u_curr[nyl+1][i];
    }
}
#endif

/* Initialize all global variables */
void initialization(int argc, char * argv[]) {
  int i,j;

#ifndef _CIVL

  nsteps = 300;
  wstep = 5;
  nx = 15;
  ny = 15;
  if(argc < 3) {
    printf("Program needs 2 arguments to specify the number of sliced components in x axis and y axis:\n"
	   "It should go with the format: mpiexec -n [nprocs] "
	   "[filename] [#components in x axis] [#components in y axis]\n");
    assert(0);
  }
  NPROCSX = atoi(argv[1]); 
  NPROCSY = atoi(argv[2]);
  assert(0 < NPROCSX * NPROCSY <=nprocs);
  nprocs = (nprocs > NPROCSX * NPROCSY)?NPROCSX*NPROCSY:nprocs;
  constTemp = 0.0;
  initTemp = 100.0;
  k = 0.13;
  printf("Diffusion2d with k=%f, nx=%d, ny=%d, nsteps=%d, wstep=%d\n",
	 k, nx, ny, nsteps, wstep);

#endif

  nxl = countColForProc(rank);
  nyl = countRowForProc(rank);
  u_curr = (double **)malloc((nyl + 2) * sizeof(double *));
  assert(u_curr);
  u_next = (double **)malloc((nyl + 2) * sizeof(double *));
  assert(u_next);
  for(i=0; i < nyl + 2; i++){
    u_curr[i] = (double *)malloc((nxl + 2) * sizeof(double));
    assert(u_curr[i]);
    u_next[i] = (double *)malloc((nxl + 2) * sizeof(double));
    assert(u_next[i]);
  }
  firstCol = firstColForProc(rank);
  firstRow = firstRowForProc(rank);
  // computes neighbors
  if(firstCol != 0)
    left = OWNER(firstCol - 1, firstRow);
  else
    left = MPI_PROC_NULL;
  if(firstRow != 0)
    top = OWNER(firstCol, firstRow - 1);
  else
    top = MPI_PROC_NULL;
  if(firstCol + nxl < nx)
    right = OWNER(firstCol + nxl, firstRow);
  else
    right = MPI_PROC_NULL;
  if(firstRow + nyl < ny)
    bottom = OWNER(firstCol, firstRow + nyl);
  else
    bottom = MPI_PROC_NULL;
#ifdef _CIVL
  setConstBoundaries();
  // In CIVL mode process with rank 0 will be responsible for computing the diffusion2d equation 
  // sequentially such that the results can be used to compare with the ones of parallel run.
  if(rank == 0) {
    for(i = 0; i < ny + 2; i++)
      for(j = 0; j < nx + 2; j++)
	oracle[0][i][j] = u_init[i][j];
    for(int t=1; t < nsteps; t++)
      for(i = 0; i < ny + 2; i++)
	for(j = 0; j < nx + 2; j++)
	  if(i==0 || j==0 || i == ny + 1 || j == nx + 1)
	    oracle[t][i][j] = oracle[t-1][i][j];
	  else
	    oracle[t][i][j] = oracle[t-1][i][j] +
	      k*(oracle[t-1][i+1][j] + oracle[t-1][i-1][j] + 
		 oracle[t-1][i][j+1] + oracle[t-1][i][j-1] - 4*oracle[t-1][i][j]);
  }

#endif
}

void update() {
  int i, j;
  double **tmp;

  for(i = 1; i < nyl + 1; i++)
    for(j = 1; j < nxl + 1; j++) {
      u_next[i][j] = u_curr[i][j] +
	k*(u_curr[i+1][j] + u_curr[i-1][j] + 
	   u_curr[i][j+1] + u_curr[i][j-1] - 4*u_curr[i][j]);
    }
  //swap two pointers
  tmp = u_curr;
  u_curr = u_next;
  u_next = tmp;
}

void exchange() {
  double sendbuf[nyl];
  double recvbuf[nyl];

  // sends top string, receives bottom string
  MPI_Sendrecv(&u_curr[1][1], nxl, MPI_DOUBLE, top, FROMBOTTOM, &u_curr[nyl+1][1], nxl, 
	       MPI_DOUBLE, bottom, FROMBOTTOM, comm, MPI_STATUS_IGNORE);
  // sends bottom string, receives top string
  MPI_Sendrecv(&u_curr[nyl][1], nxl, MPI_DOUBLE, bottom, FROMTOP, &u_curr[0][1], nxl, 
	       MPI_DOUBLE, top, FROMTOP, comm, MPI_STATUS_IGNORE);
  // sends left most string, receives right most string
  for(int i = 0; i < nyl; i++) sendbuf[i] = u_curr[i+1][1];
  MPI_Sendrecv(sendbuf, nyl, MPI_DOUBLE, left, FROMRIGHT, recvbuf, nyl, 
	       MPI_DOUBLE, right, FROMRIGHT, comm, MPI_STATUS_IGNORE);
  if(right != MPI_PROC_NULL)
    for(int i = 0; i < nyl; i++) u_curr[i+1][nxl+1] = recvbuf[i];
  // sends right most string, receives left most string
  for(int i = 0; i < nyl; i++) sendbuf[i] = u_curr[i+1][nxl];
  MPI_Sendrecv(sendbuf, nyl, MPI_DOUBLE, right, FROMLEFT, recvbuf, nyl, 
	       MPI_DOUBLE, left, FROMLEFT, comm, MPI_STATUS_IGNORE);
  if(left != MPI_PROC_NULL)
    for(int i = 0; i < nyl; i++) u_curr[i+1][0] = recvbuf[i];
}

void printData(int time, int firstCol, int nxl, int firstRow, int nyl, double * buf) {

  for(int i=0; i<nyl; i++) {
    for(int j=0; j<nxl; j++) {
      printf("%8.2f ", *(buf + i*nxl + j));
#ifdef _CIVL
    $assert(*(buf + i*nxl + j) == oracle[time][firstRow + i + 1][firstCol + j + 1]) : \
    "Error: disagreement at time %d position [%d][%d]: saw %lf, expected %lf", \
      time, firstRow + i, firstCol + j, 
      *(buf + i*nxl + j), oracle[time][firstRow + i + 1][firstCol + j + 1];
#endif
    }
    printf("\n");
  }
}

void write_frame(int time) {
  double * buf; // buffer of data to print
  int i, j;

  buf = (double *)malloc(nxl * nyl * sizeof(double));
  // writes data into buffer array
  for(i = 0; i < nyl; i++)
    for(j = 0; j < nxl; j++) {
      buf[i*nxl + j] = u_curr[i+1][j+1];
    }
  if(rank == 0) {
    printf("\n-------------------- time step:%d --------------------\n", time);
    printData(time, firstCol, nxl, firstRow, nyl, buf);
    free(buf);
    for(i=1; i<nprocs; i++){
      double * recvbuf;
      int senderx, sendery;
      int senderNyl, senderNxl;

      senderNxl = countColForProc(i);
      senderNyl = countRowForProc(i);
      if(senderNxl != 0 && senderNyl != 0) {
	recvbuf = (double *)malloc(senderNxl * senderNyl * sizeof(double));
	senderx = firstColForProc(i);
	sendery = firstRowForProc(i);
	MPI_Recv(recvbuf, senderNyl*senderNxl, MPI_DOUBLE, i, 
		 DATAPASS, comm, MPI_STATUS_IGNORE);
	printData(time, senderx, senderNxl, sendery, senderNyl, recvbuf);
	free(recvbuf);
      }
    }
  } else {
    MPI_Send(buf, nyl*nxl, MPI_DOUBLE, 0, DATAPASS, comm);
    free(buf);
  }
}

int main(int argc, char * argv[]) {
  int i,j;

#ifdef _CIVL

  // elaborating nx, ny, NPROCSX and NPROCSY...
  elaborate(nx);
  elaborate(ny);
  elaborate(NPROCSX);
  elaborate(NPROCSY);

#endif
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(comm, &rank);
  MPI_Comm_size(comm, &nprocs);
  initialization(argc, argv);
#ifdef _CIVL

  for(i=1; i<nyl+1; i++)
    memcpy(&u_curr[i][1], &u_init[firstRow + i][firstCol + 1], nxl * sizeof(double));

#else

  for(int i=0; i < nyl+2; i++)
    for(int j=0; j < nxl+2; j++)
      if(i == 0 || j == 0 || i == nyl+1 || j == nxl+1)
	u_next[i][j] = u_curr[i][j] = constTemp;
      else
	u_curr[i][j] = initTemp;

#endif
  for(i=0; i<nsteps; i++) {
    if(nxl != 0 && nyl != 0) {
      if(i%wstep == 0)
	write_frame(i);
      exchange();
      update();
    }
  }
  for(i=0; i<nyl+2; i++) {
    free(u_curr[i]);
    free(u_next[i]);
  }
  free(u_curr);
  free(u_next);
  return 0;
}
