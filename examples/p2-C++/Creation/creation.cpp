/*
	creation.cpp -- demo object creation
	(c) 2002-02-07 oscar.nierstrasz@acm.org
*/

#include <iostream>
#include <string>
using namespace std;

class MyClass {
private:
	string _name;
public:
	MyClass(string name) : _name(name) { // constructor
		cout << "create " << _name << endl;
	}
	~MyClass() { // destructor
		cout << "destroy " << _name << endl;	
	}
};

MyClass& start() { // returns a reference
	MyClass a("a"); // automatic (stack) instantiation
	MyClass *b = new MyClass("b"); // dynamic (heap)
	return *b; // returns a reference (!) to b
} // a goes out of scope

void finish(MyClass& b) {
	delete &b; // need pointer to b
}

int main(void)
{
	MyClass aClass("d");
	finish(start());
	return 0;
}
