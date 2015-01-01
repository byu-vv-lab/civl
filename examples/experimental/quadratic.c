#include <stdio.h>
#include <assert.h>

$input int x;
$assume 1 < x && x <= 3;
$input int y;
$assume 1 < y && y <= 3;
$input int n = x * y;

int main(){
  elaborate(n);
  printf("%d\n", n);
}
