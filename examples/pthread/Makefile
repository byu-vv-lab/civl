CIVL=civl
VERIFY=$(CIVL) verify

all: arrayloops bug1 bug1fix bug3 bug4 bug4fix bug5 bug6 bug6fix condvar detached dotprod_mutex fib_bench_false join mpithreads_threads

arrayloops: arrayloops.cvl
	$(VERIFY) -inputNTHREADS=4 -inputARRAYSIZE=1000000 arrayloops.cvl

bug1: bug1.cvl
	$(VERIFY) -inputNUM_THREADS=6 -inputTCOUNT=10 -inputCOUNT_LIMIT=12 bug1.cvl
	
bug1fix: bug1fix.cvl
	$(VERIFY) -inputNUM_THREADS=6 -inputTCOUNT=10 -inputCOUNT_LIMIT=12 bug1fix.cvl
	
bug3: bug3.cvl
	$(VERIFY) -inputNUM_THREADS=8 bug3.cvl
	
bug4: bug4.cvl
	$(VERIFY) -inputNUM_THREADS=3 -inputITERATIONS=10 -inputTHRESHOLD=12 bug4.cvl

bug4fix: bug4fix.cvl
	$(VERIFY) -inputNUM_THREADS=3 -inputITERATIONS=10 -inputTHRESHOLD=12 bug4fix.cvl
	
bug5: bug5.cvl
	$(VERIFY) -inputNUM_THREADS=5 bug5fix.cvl
	
bug6: bug6.cvl
	$(VERIFY) -inputNUM_THRDS=5 -inputVECLEN=1000 bug6.cvl

bug6fix: bug6fix.cvl
	$(VERIFY) -inputNUM_THRDS=5 -inputVECLEN=1000 bug6fix.cvl

condvar: condvar.cvl
	$(VERIFY) -inputNUM_THREADS=3 -inputTCOUNT=10 -inputCOUNT_LIMIT=12 condvar.cvl
	
detached: detached.cvl
	$(VERIFY) -inputNUM_THREADS=4 detached.cvl
	
dotprod_mutex: dotprod_mutex.cvl
	$(VERIFY) -inputNUMTHRDS=4 -inputVECLEN=100000 dotprod_mutex.cvl
	
fib_bench_false: fib_bench_false.cvl
	$(VERIFY) -inputNUM=5 fib_bench_false.cvl
	
join: join.cvl
	$(VERIFY) -inputNUM_THREADS=4 join.cvl

mpithreads_threads: mpithreads_threads.cvl
	$(VERIFY) -inputMAXTHRDS=8 -inputVECLEN=100 mpithreads_threads.cvl

twostage_3_false: twostage_3_false.cvl
	$(VERIFY) twostage_3_false.cvl

