
CIVL=civl
VERIFY=$(CIVL) verify

all: algebra assoc

algebra: algebra.cvl
	$(VERIFY) algebra.cvl

assoc: assoc.cvl
	$(VERIFY) -inputB=10 assoc.cvl

division: division.cvl
	$(VERIFY) division.cvl

divisionBad: divisionBad.cvl
	$(VERIFY) divisionBad.cvl
	$(CIVL) replay divisionBad.cvl

matmat: matmat.cvl
	$(VERIFY) matmat.cvl -inputBOUND=4



