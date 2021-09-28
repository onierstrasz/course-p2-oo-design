
# Basic Makefile
# A Basic Makefile consists of comments, macros, dependency lines and commands:

# These are the files make should recognize
.SUFFIXES: .cpp

# Version of the C++ compiler; link and compile options:

CPP		= c++
CPPFLAGS	= -O

# Generic rules -- how to make .o files from .cpp files:
.cpp.o:
	$(CPP) $(CPPFLAGS) -c $<

# What to make or remove:
ALL = prog

# The first rule defines what to make by default
# (Could be many things)
all : $(ALL)

# prog is made by linking together the object files:
PROGO = prog.o hello.o

prog : $(PROGO)
	$(CPP) $(CPPFLAGS) $(PROGO) -o $@

# prog.o and hello.o each depend on a source file and a header file:

prog.o : prog.cpp hello.h
hello.o : hello.cpp hello.h

run : $(ALL)
	./$(ALL)

test : prog
	@../check prog 'hello world'

clean :
	rm -rf *.o $(ALL)
