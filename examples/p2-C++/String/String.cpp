/*
	File: String.cpp
	Author: Oscar Nierstrasz 30.11.95
	Simple String class to illustrate OCF.
	Revised 2002-03-06
*/

#include <string.h>
#include "String.h"

// Comment out the following line to turn off debug msgs:
// #define DEBUG
#ifdef DEBUG
inline void
debug (const char * msg)
{
	cerr << "DEBUG> " << msg << endl;
}
#else
inline void debug (const char * msg) { ; }
#endif

// default constructor:
// NB: this is called automatically when
// an array of strings in declared: String sVec[10];
String::String (void)
{
	debug("Made a new empty string");
	_s = new char[1];
	_s[0] = '\0';
}

// destructor:
String::~String (void)
{
	debug("Destroyed string:");
	debug(_s);
	delete [] _s; // NB: an array, so not just "delete"!!!
}

// copy constructor:
String::String (const String& copy)
{
	debug("Made a new string = ");
	debug(copy._s);
	become(copy._s);
}

// char* constructor:
String::String (const char* s)
{
	debug("Made a new string = ");
	debug(s);
	become(s);
}

// assignment operator:
// Already initialized, so must delete rep
String&
String::operator=(const String& copy)
{
	if (this != &copy) {	// take care!
		delete [] _s;		// the whole array, not just 1st char
		become(copy._s);
	}
	return *this;			// NB: a reference, not a copy
}
	
char&
String::operator[] (const int n) throw(exception)
{
	if ((n<0) || (length()<=n)) {
		throw(logic_error("array index out of bounds"));
	}
	// non-const reference returned,
	// so can be used as an lvalue!
	return _s[n];
}

String&
String::operator+= (const String& tail) throw (exception)
{
	// NB: length() must be const, or compiler will
	// complain that we cannot take length() of tail!!!
	int newLen = length() + tail.length() + 1;
	char * old = _s;
	
	_s = new char[newLen];
	if (_s == 0) throw(logic_error("can't allocate string"));
	::strcpy(_s, old);
	::strcat(_s, tail._s);
	
	delete [] old;
	return *this;
}

// helper function for constructors and operator=():
void
String::become (const char* s) throw (exception)
{
	// caller must assure that _s is unassigned,
	// or that the previous value is deleted!
	_s = new char[::strlen(s) + 1];
	if (_s == 0) throw(logic_error("can't allocate string"));
	::strcpy(_s, s);
}


// friend:
ostream& operator<<(ostream& outStream, const String& s)
{
	return outStream << s._s; // only friends can access _s
}

int
String::getline(istream& in)
{
	char c;
	int curLen = 0, maxLen = length();

	while ((c = in.get()) != EOF) {
		if (curLen == maxLen) {
			_s[curLen] = '\0';
			maxLen = maxLen * 2;
			grow(maxLen);
		}
		if (c == '\n') {
			_s[curLen] = '\0';
			return 1;
		}
		_s[curLen++] = c;
	}
	return 0;
}

// helper function for getline():
void
String::grow (int newSize) throw (exception)
{
	char * old = _s;
	_s = new char[newSize];
	if (_s == 0) throw(logic_error("can't allocate string"));
	::strcpy(_s, old);
	delete [] old;
}

// concatenation:
String
operator+ (const String& s1, const String& s2)
{
	String cat = s1;
	return cat += s2;
}
