#include <assert.h>

int main(int argc, char * argv[]) {
  int a[2][2][2] = {{{0,1}, {2,3}}, {{4,5}, {6,7}}};
#ifdef ADDONOFFSET
  void *p = (&a + 1);
#else
  void *p = (&a + 2);
#endif
  p += 0;
  int *t = (int *)&a + 0 ;

  t = (int *)(p + 2);
  return 0;
}
