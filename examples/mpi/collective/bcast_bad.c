#include<mpi.h>

int main() 
{ 
    int argc;
    char** argv;
    int rank;
    int procs;
    int value;

    MPI_Init(&argc,&argv); 
    MPI_Comm_size(MPI_COMM_WORLD, &procs); 
    MPI_Comm_rank(MPI_COMM_WORLD, &rank); 

    if (rank == 0) 
      value = 123;

    if (rank != 5)
      MPI_Bcast(&value, 1, MPI_INT, 0, MPI_COMM_WORLD); 

    MPI_Finalize(); 
    return 0; 
}
