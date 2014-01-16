
# CIVL = java -jar ../civl.jar
CIVL = civl

TARGETS = \
	collective \
	contract \
	count \
	floatsum \
	floatsum2 \
	pathfinder_cuda
	
default: all

all: $(TARGETS)

collective: collective.cvl
	$(CIVL) collective.cvl
	
contract: contract.cvl
	$(CIVL) contract.cvl
	
count: count.cvl
	$(CIVL) count.cvl
	
floatsum: floatsum.cvl
	$(CIVL) floatsum.cvl
	
floatsum2: floatsum2.cvl
	$(CIVL) floatsum2.cvl
	
pathfinder_cuda: pathfinder_cuda.cvl
	$(CIVL) pathfinder_cuda.cvl
