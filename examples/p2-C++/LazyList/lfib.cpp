
/*
	lfib.cpp -- a lazy Fibonacci function
*/

#include <iostream>
using namespace std;

#include <stdlib.h>	// for atoi()

#include "lazy.h"

typedef LazyList<int> LazyIntList;

int computeFib(int n, LazyIntList& fibList);
void showFib(int);

static LazyIntList lfib(computeFib); // NB: a function object

int main(int argc, char* argv[])
{
	int i;
	const int max=40;
	if (argc > 1) {
		for (i=1; i<argc; i++)
			showFib(atoi(argv[i]));
	} else {
		for (i=0;i<=max;i++)
			showFib(i);
	}
	return 0;
}

int computeFib(int n, LazyIntList& fibList)
{
	if (n<=1)
		return 1;
	else
		return fibList(n-1) + fibList(n-2);
}


void showFib(int n)
{
	cout << "lfib(" << n << ") = " << lfib(n) << endl;
}

