CIVL=civl
VERIFY=$(CIVL) verify

all: dotProduct dotProduct_critical
	
dotProduct: dotProduct.cvl
	$(VERIFY) -inputNTHREADS=3 -inputN=8 dotProduct.cvl
	
dotProduct_critical: dotProduct_critical.cvl
	$(VERIFY) -inputNTHREADS=2 -inputN=8 dotProduct_critical.cvl
	
