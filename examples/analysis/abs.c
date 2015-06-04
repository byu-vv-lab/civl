#include <stdlib.h>
#include <civlc.cvh>

$input int i;
$input int x;
$input int y;
$input int z;
$assume(x<0);
$assume(y>0);
void main(){
    int a = 9;
    
    a=abs(0); // 0
    a=abs(x); // -
    a=abs(y); // +
    a=abs(z); // *
    a=abs(x*y*z); // *
    
    a=abs(x*y); // -
    $assert(x*y<=0);
    
    a=abs(x%y); //+
    $assert(x%y>=0);
    
    a=abs((-x)%y); // +
    $assert((-x)%y>=0);
}
