
ALL = rfib lfib

CPP = c++

.SUFFIXES: .cpp

.cpp.o:
	$(CPP) $(CFLAGS) -c $<

all : $(ALL)

lfib : lfib.cpp lazy.h
	$(CPP) -o $@ $@.cpp

run : lfib
	./lfib 30

rfib : rfib.cpp
	$(CPP) -o $@ $@.cpp

test : $(ALL)
	@# skip rfib -- v e r y   s l o w ! ! !
	@# ../check rfib '*rfib(40) = 165580141'
	@../check lfib '*lfib(40) = 165580141'

clean :
	rm -f *.o $(ALL)
