
CPP		= c++

ALL = stlrev

all : $(ALL)

stlrev : stlrev.cpp
	$(CPP) $@.cpp -o $@

run : $(ALL)
	./$(ALL) < Makefile

test: stlrev
	@./stlrev < stlrev.cpp > tmp
	@./stlrev < tmp > tmp2
	@diff stlrev.cpp tmp2
	@rm -f  tmp tmp2
	@echo "PASSED stlrev test"

clean :
	rm -rf *.o $(ALL)
