CIVL=civl
VERIFY=${CIVL} verify

all: max abs

max: unreached.c
	${VERIFY} -showUnreached unreached.c

abs: abs.c
	${VERIFY} -analyze_abs abs.c

clean: 
	rm -f -r CIVLREP
	rm -r -f *~
