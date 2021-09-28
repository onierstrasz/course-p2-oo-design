
#include <iostream>
using namespace std;

#include "pair.h"

int main(void)
{
	typedef Pair<int, string> MyPair;
	MyPair myPair = MyPair(6, "I am not a number");
	cout << myPair.first << " sez " << myPair.second << endl;
	return 0;
}
