
all :
	@cd HelloWorld; make all
	@cd Hello; make all
	@cd Creation; make all
	@cd LazyList; make all
	@cd Pair; make all
	@cd Rev; make all
	@cd String; make all

test : $(ALL)
	@chmod 0755 check
	@cd HelloWorld; make test
	@cd Hello; make test
	@cd Creation; make test
	@cd LazyList; make test
	@cd Pair; make test
	@cd Rev; make test
	@cd String; make test
	@echo FINISHED all tests

clean :
	@cd HelloWorld; make clean
	@cd Hello; make clean
	@cd Creation; make clean
	@cd LazyList; make clean
	@cd Pair; make clean
	@cd Rev; make clean
	@cd String; make clean
