
/*
	File: String.h
	Author: Oscar Nierstrasz 30.11.95
	Simple String class to illustrate OCF.
	Revised 2002-03-06
*/

#ifndef STRING_H
#define STRING_H

#include <iostream>		// ostream
#include <exception>	// exception
#include <stdexcept>	// logic_error
using namespace std;

class String
{
	friend ostream& operator<<(ostream&, const String&);
public:
	String(void);						// default constructor
	~String(void);						// destructor
	String(const String& copy);		// copy constructor
	String(const char*s);				// char* constructor
	String& operator=(const String&);	// assignment

	inline int length (void) const { return ::strlen(_s); }
	char& operator[] (const int n) throw(exception);
	String& operator+= (const String&) throw(exception);	// concatenation
	int getline(istream&);
private:
	// invariant:
	// _s points to a null-terminated string on the heap
	char *_s;
	
	void become(const char*) throw(exception);	// internal copy function
	void grow(int) throw(exception);			// helper for getline()
};

// concatenation -- makes a new String
String operator+ (const String& s1, const String& s2);
// NB: not a member function

// Instead of making this function a friend, we just make
// it an inline function that calls the privileged getline().
inline istream& operator>> (istream& inStream, String& s)
{
	s.getline(inStream);
	return inStream;
}

#endif // STRING_H
