/*
	File: testString.cpp
*/

#include "String.h"

#include <cassert>

// missing tests for + and >>

void testString(void) {
	// cout << String("TESTING String ... ");

	String empty;
	assert(empty.length() == 0);

	String howdy("howdy!");
	assert(howdy.length() == 6);

	String howdy2(howdy);
	assert(howdy2.length() == 6);
	
	
	String copy;
	assert(copy.length() == 0);
	copy = howdy;
	assert(copy.length() == 6);

	howdy = "hello world!";
	assert(howdy.length() == 12);
	assert(howdy2.length() == 6);
	assert(copy.length() == 6);

	copy = copy;
	assert(copy.length() == 6);
	
	howdy2 += " to you";
	assert(howdy2.length() == 13);
	
	howdy[11] = '?';
	assert(howdy[10] == 'd');
	
	try {
		howdy[12] = 'a';
		assert(0);
	} catch(exception& e) { }

	try {
		empty[0] = 'a';
		assert(0);
	} catch(exception& e) { }

	cout << String("PASSED String tests") << endl;
	
}

int main(void)
{
	testString();
	return 0;
}
