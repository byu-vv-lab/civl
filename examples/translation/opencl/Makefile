CIVL=civl
VERIFY=$(CIVL) verify

all: tCLGetDID
	
tCLGetDID: testclGetDeviceIDs.cvl
	$(VERIFY) testclGetDeviceIDs.cvl -inputDEVICE_ID=1 -inputDEVICE_ID_BOUND=4
	
