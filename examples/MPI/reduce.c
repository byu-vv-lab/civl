#include <mpi.h>
#include <stdio.h>

void main(int argc, char * argv[]){

  int nprocs, rank;
  int value;

  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &nprocs);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);

  if(nprocs < 2){
    printf("The program needs more than 2 processes.\n");
    MPI_Finalize();
    return;
  }else{
    MPI_Allreduce(&rank, &value, 1, MPI_INT, MPI_SUM, MPI_COMM_WORLD);
    printf("I'm process %d, my value is %d \n", rank, value);
    MPI_Finalize();
    return;
  }
}
