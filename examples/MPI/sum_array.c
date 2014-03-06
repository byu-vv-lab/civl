/* Sum of an array - First parallel version */
 
 #include <stdio.h>
 #include <mpi.h>
 
 #define N 100000
 #define MSG_DATA 100
 #define MSG_RESULT 101
 
void master (void);
void slave (void);
 
int main (int argc, char **argv) {
  int myrank;
  //Initialization of MPI
  MPI_Init (&argc, &argv);
  //myrank will contain the rank of the process
  MPI_Comm_rank (MPI_COMM_WORLD, &myrank);
  //The part of the program which will be executed is decided
  if (myrank == 0)
    master ();
  else
    slave ();
  MPI_Finalize ();
  return 0;
}
 
void master (void) {
  float array[N];
  double mysum = 0, tmpsum;
  unsigned long long i;
  MPI_Status status;
  //Initialization of the array
  for (i = 0; i < N; i++)
    array[i] = i + 1;
  //The array is divided by two and each part is sent to the slaves
  MPI_Send (array, N / 2, MPI_FLOAT, 1, MSG_DATA, MPI_COMM_WORLD);
  MPI_Send (array + N / 2, N / 2, MPI_FLOAT, 2, MSG_DATA, MPI_COMM_WORLD);
  //The master receive the result from the slaves
  MPI_Recv (&tmpsum, 1, MPI_DOUBLE, 1, MSG_RESULT, MPI_COMM_WORLD, &status);
  mysum += tmpsum;
  MPI_Recv (&tmpsum, 1, MPI_DOUBLE, 2, MSG_RESULT, MPI_COMM_WORLD, &status);
  mysum += tmpsum;
  printf ("%lf\n", mysum);
}
 
void slave (void) {
  float array[N];
  double sum;
  unsigned long long i;
  MPI_Status status;
  //The slave receives the array from the master
  MPI_Recv (array, N / 2, MPI_FLOAT, 0, MSG_DATA, MPI_COMM_WORLD, &status);
  for (i = 0, sum = 0; i < N / 2; i++)
    sum += array[i];
  //The result is send to the master
  MPI_Send (&sum, 1, MPI_DOUBLE, 0, MSG_RESULT, MPI_COMM_WORLD);
}
