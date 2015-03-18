CIVL = civl
VERIFY = $(CIVL) verify
REPLAY = $(CIVL) replay

all: scatterGather scatterGather_bad

scatterGather: scatterGather.c
	${VERIFY} -input_NPROCS=5 scatterGather.c

scatterGather_bad: scatterGather_bad.c
	${VERIFY} -input_NPROCS=5 scatterGather_bad.c
	${REPLAY}  scatterGather_bad.c

clean: 
	rm -f -r CIVLREP
	rm -r -f *~
