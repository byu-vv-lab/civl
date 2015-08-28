VERIFY=civl verify -input_mpi_nprocs=3 -deadlock=potential -mpiContract 

all: cycle wildcard_bad wildcard_barrier reduce

cycle: cycle.c
	$(VERIFY) cycle.c

wildcard_bad: wildcard_coassert_bad.c
	$(VERIFY) wildcard_coassert_bad.c

wildcard_barrier: wildcard_coassert_barrier.c
	$(VERIFY) wildcard_coassert_barrier.c

reduce: reduce_coassert.c
	$(VERIFY) reduce_coassert.c

clean: 
	rm -r -f CIVLREP *~