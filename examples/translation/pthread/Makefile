CIVL=civl
VERIFY=$(CIVL) verify

all: arrayloops bug1 bug1fix bug3 bug4 bug4fix bug5 bug6 bug6fix\
 condvar detached dotprod_mutex fib_bench_false join mpithreads_threads

arrayloops: arrayloops.cvl
	$(VERIFY) arrayloops.cvl -inputNTHREADS=4 -inputARRAYSIZE=1000000 

bug1: bug1.cvl
	$(VERIFY) bug1.cvl -inputNUM_THREADS=6 -inputTCOUNT=10 -inputCOUNT_LIMIT=12
	$(CIVL) replay bug1.cvl
	
bug1fix: bug1fix.cvl
	$(VERIFY) bug1fix.cvl -inputNUM_THREADS=6 -inputTCOUNT=10 -inputCOUNT_LIMIT=12
	
bug3: bug3.cvl
	$(VERIFY) bug3.cvl -INPUTNUM_THREADS=8
	$(CIVL) replay bug3.cvl
	
bug4: bug4.cvl
	$(VERIFY) bug4.cvl -inputNUM_THREADS=3 -inputITERATIONS=10 -inputTHRESHOLD=12
	$(CIVL) replay bug4.cvl

bug4fix: bug4fix.cvl
	$(VERIFY) bug4fix.cvl -inputNUM_THREADS=3 -inputITERATIONS=10 -inputTHRESHOLD=12
	
bug5: bug5.cvl
	$(VERIFY) bug5fix.cvl -inputNUM_THREADS=5
	$(CIVL) replay bug5.cvl
	
bug6: bug6.cvl
	$(VERIFY) bug6.cvl -inputNUM_THRDS=5 -inputVECLEN=1000
	$(CIVL) replay bug6.cvl

bug6fix: bug6fix.cvl
	$(VERIFY) bug6fix.cvl -inputNUM_THRDS=5 -inputVECLEN=1000

condvar: condvar.cvl
	$(VERIFY) condvar.cvl -inputNUM_THREADS=3 -inputTCOUNT=10 -inputCOUNT_LIMIT=12
	
detached: detached.cvl
	$(VERIFY) detached.cvl -inputNUM_THREADS=4
	
dotprod_mutex: dotprod_mutex.cvl
	$(VERIFY) dotprod_mutex.cvl -inputNUMTHRDS=4 -inputVECLEN=100000
	
fib_bench_false: fib_bench_false.cvl
	$(VERIFY) fib_bench_false.cvl -inputNUM=5
	$(CIVL) replay fib_bench_false.cvl
	
join: join.cvl
	$(VERIFY) join.cvl -inputNUM_THREADS=4

mpithreads_threads: mpithreads_threads.cvl
	$(VERIFY) mpithreads_threads.cvl -inputMAXTHRDS=8 -inputVECLEN=100

twostage_3_false: twostage_3_false.cvl
	$(VERIFY) twostage_3_false.cvl
	$(CIVL) replay twostate_3_false.cvl
	
clean:
	rm -rf CIVLREP *~
