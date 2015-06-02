#include <stdlib.h>

void main(){
    int a = 9;
    
    for(int i =0; i < 10; i++){
        a= abs(i * (i%3-1));
        a= abs(i * (i%2-1));
        a= abs(i * 0);
    }
}
