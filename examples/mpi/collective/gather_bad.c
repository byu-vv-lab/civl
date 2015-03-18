/**
 * This example demonstrates the usage of MPI collective operations,
 * which should be called in the same orders for all MPI processes.
 * This example has an error when there are more than two MPI processes.
 */
#include<mpi.h>
#include<stdlib.h>

int main() 
{ 
    int argc;
    char** argv;
    int rank;
    int procs;
    int* values;

    MPI_Init(&argc,&argv); 
    MPI_Comm_size(MPI_COMM_WORLD, &procs); 
    MPI_Comm_rank(MPI_COMM_WORLD, &rank); 
    
    if (rank == 0) {
      values = (int*)malloc(sizeof(int)*procs);
    }else{
      values = (int*)malloc(sizeof(int));
      *values = procs + rank;
    }

    if (rank != 2)
      MPI_Gather(values, 1, MPI_INT, values, 1, MPI_INT, 0, MPI_COMM_WORLD);

    free(values);
    MPI_Finalize(); 
    return 0; 
}
