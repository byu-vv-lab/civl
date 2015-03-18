CIVL = civl
VERIFY = $(CIVL) verify
REPLAY = $(CIVL) replay

all: bcast_bad scatter_bad gather_bad scatterGather scatterGather_bad scatterAllgather

bcast_bad: bcast_bad.c
	${VERIFY} -input_NPROCS=5 bcast_bad.c

scatter_bad: scatter_bad.c
	${VERIFY} -input_NPROCS=5 scatter_bad.c

gather_bad: gather_bad.c
	${VERIFY} -input_NPROCS=5 gather_bad.c

scatterGather: scatterGather.c
	${VERIFY} -input_NPROCS=5 scatterGather.c

scatterGather_bad: scatterGather_bad.c
	${VERIFY} -input_NPROCS=5 scatterGather_bad.c
	${REPLAY}  scatterGather_bad.c

scatterAllgather: scatterAllgather.c
	${VERIFY} -input_NPROCS=5 scatterAllgather.c

scatterAllgather_bad: scatterAllgather_bad.c
	${VERIFY} -input_NPROCS=5 scatterAllgather_bad.c

clean: 
	rm -f -r CIVLREP
	rm -r -f *~
