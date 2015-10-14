#include<pthread.h>
#include<assert.h>

int f(int x){

  assert(!x);
  return x!=0;
}

__thread int id=-1;

int isRoot(){
  assert(id >= 0);
  return id == 0;
}

void* thread(void *arg){
  id=(int)arg;
  isRoot();
}

int main(){
  pthread_t t0,t1;

  pthread_create(&t0, 0, thread, (void*)0);
  pthread_create(&t1, 0, thread, (void*)1);
  pthread_join(t0, 0);
  pthread_join(t1, 0);
  assert(id==-1);
  return 0;
}


