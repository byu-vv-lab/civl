CIVL = civl
COMPARE = $(CIVL) compare

all: sumN

sumN: sumNimpl.cvl sumNspec.cvl
	$(COMPARE) sumNspec.cvl sumNimpl.cvl -inputN=10

clean: 
	rm -f *~
	rm -f -r CIVLREP