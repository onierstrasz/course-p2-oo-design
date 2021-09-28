
/*
	rfib.cpp -- recursive Fibonacci function
*/


#include <iostream>
using namespace std;

#include <stdlib.h> // for atoi

int rfib(int);
void showFib(int);

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

int rfib (int n)
{
	if (n<=1)
		return 1;
	else
		return rfib(n-1) + rfib(n-2);
}


void showFib(int n)
{
	cout << "rfib(" << n << ") = " << rfib(n) << endl;
}
