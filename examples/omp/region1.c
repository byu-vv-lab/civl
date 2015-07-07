#include <omp.h>
#define N 10

int main (int argc, char * argv[]){
  int i;
  int a[N];

#pragma omp parallel 
{
  int max = N;

#pragma omp for
  for(i=0; i<max; i++)
      a[i] = 0;

  max = 0;
}
}
