CIVL=civl
VERIFY=$(CIVL) verify

all: bug1 fib_bench_false 

bug1: bug1.cvl
	$(VERIFY) bug1.cvl -inputNUM_THREADS=6 -inputTCOUNT=10 -inputCOUNT_LIMIT=12

fib_bench_false: fib_bench_false.cvl
	$(VERIFY) fib_bench_false.cvl -inputNUM=5

