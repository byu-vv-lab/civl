#ifdef __MPI__
#else
#define __MPI__
#include<civlc.cvh>
typedef enum {
    MPIX_NO_OP = CIVL_NO_OP,
    MPI_MAX = CIVL_MAX, 
    MPI_MIN = CIVL_MIN,     
    MPI_SUM = CIVL_SUM,
    MPI_PROD = CIVL_PROD,
    MPI_LAND = CIVL_LAND,   
    MPI_BAND = CIVL_BAND,   
    MPI_LOR = CIVL_LOR,     
    MPI_BOR = CIVL_BOR,     
    MPI_LXOR = CIVL_LXOR,    
    MPI_BXOR = CIVL_BXOR,    
    MPI_MINLOC = CIVL_MINLOC,  
    MPI_MAXLOC = CIVL_MAXLOC,  
    MPI_REPLACE = CIVL_REPLACE
}MPI_Op;
#include<mpi-common.h>
#endif
