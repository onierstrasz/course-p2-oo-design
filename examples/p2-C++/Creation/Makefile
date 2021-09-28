
ALL = creation

all : $(ALL)

creation : creation.cpp
	c++ $@.cpp -o $@

run : $(ALL)
	./$(ALL)

test : $(ALL)
	@../check creation 'create d*create a*create b*destroy a*destroy b*destroy d'

clean :
	rm -f $(ALL)
