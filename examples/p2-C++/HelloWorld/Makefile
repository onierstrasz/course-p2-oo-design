
ALL = helloWorld

all : $(ALL)

helloWorld : helloWorld.cpp
	c++ $@.cpp -o $@

run : $(ALL)
	./$(ALL)

test : $(ALL)
	@../check helloWorld 'hello world!'

clean :
	rm -f $(ALL)
