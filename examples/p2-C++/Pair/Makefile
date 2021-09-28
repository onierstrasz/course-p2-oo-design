
ALL = testpair

all : $(ALL)

testpair : testpair.cpp pair.h
	c++ $@.cpp -o $@

run : $(ALL)
	./$(ALL)

test : testpair
	@../check testpair '6 sez I am not a number'

clean :
	rm -f $(ALL)
