
CIVL = ../civl

TARGETS = \
	minimal \
	spawn \
	locks \
	sideEffects

default: all

all: $(TARGETS)

minimal: minimal.cvl
	$(CIVL) minimal.cvl

spawn: spawn.cvl
	$(CIVL) spawn.cvl

locks: locks.cvl
	$(CIVL) locks.cvl

sideEffects: sideEffects.cvl
	$(CIVL) sideEffects.cvl
