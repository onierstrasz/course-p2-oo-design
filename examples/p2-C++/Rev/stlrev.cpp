/*	
	File: stlTest.cpp
	Author: Oscar Nierstrasz 4.5.96
	revised 2002-03-06
	Line reverser implemented using STL
*/


#include <iostream>
#include <stack>
#include <string>
using namespace std;

void rev(void)
{
	typedef stack<string> IOStack;
	IOStack ioStack;
	string buf;

	while (getline(cin, buf)) {
		ioStack.push(buf);
	}
	while (ioStack.size() != 0) {
		cout << ioStack.top() << endl;
		ioStack.pop();
	}
}

int main(void)
{
	rev();	
	return 0;
}


