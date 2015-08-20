#include <civlc.cvh>
#include <assert.h>

$input int in;
$assume(in > 0);

int f1(int a, int b) 
  $requires $collective(in) {a > 0}
$requires {b > 1}
$ensures {$result > 0}
$ensures {b >= 2} {
  
  if(a == 1) 
    return a + b;
  else
    return a - b;
}

int f2(int a, int b) 
$ensures {a > 0}{
  int x;

  x = a + b;
}



int main() {
  int i = 0;

  i = f1(in, 2);
  i = f2(1, 2);
  return 0;
}

