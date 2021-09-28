
DOT_O = testString.o String.o

CXX		= c++
CFLAGS		= -O

.SUFFIXES: .cpp .C
.cpp.o:
	$(CXX) $(CFLAGS) -c $<

.C.o:
	$(CXX) $(CFLAGS) -c $<

ALL = testString

all : $(ALL)

testString : $(DOT_O)
	$(CXX) $(DOT_O) $(LFLAGS) -o $@

testString.o : String.h
String.o : String.h

run : $(ALL)
	./$(ALL)

test : testString
	@./testString

clean :
	rm -rf *.o $(ALL)

