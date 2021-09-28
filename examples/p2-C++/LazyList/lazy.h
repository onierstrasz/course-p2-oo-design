
/*
	lazy.h -- lazy list interface
	Oscar Nierstrasz -- 3.5.96

	A lazy list caches values f(0) to f(n) on demand for some
	integer function f().

	The function that actually computes the values f(n) should take
	a reference to the lazy list as a second argument so that it
	can use previously cached values in case f() is defined
	recursively.

	Can be used to implement a cacheing function by
	declaring the lazy list as a static local variable
	to a global function.
*/

#ifndef LAZY_H
#define LAZY_H

// Use STL templates
#include <vector>

template <class T>
class LazyList {
public:
	// constructor requires pointer to function that computes f():
	LazyList(T (*f) (int, LazyList<T>&)) : _f(f) { };

	~LazyList(void) { };

	// returns nth value:
	T operator() (int);
	T operator[] (int n) { return (*this)(n); };
private:
	vector<T> _cache;				// caches f(0) to f(n)
	T (*_f) (int, LazyList<T>&);	// function that computes f(n)
private:
	// hide default constructor etc.
	LazyList(void) { };
	LazyList(LazyList<T>&) { };
	LazyList& operator=(LazyList<T>&) { };
};


/*
	Returns f(n); ensures f(n) is cached
	If f(n) is not already cached, then all values f(i) to f(n)
	where i is the current size, are computed and cached
	Note that _f() takes a reference to the existing LazyList
	as an argument.
*/

template <class T>
T LazyList<T>::operator() (int n)
{
	// NB: if _cache.size() > n then _cache[n] is defined
	int i;
	for (i=_cache.size(); i<=n; i++) {
		_cache.push_back(_f(i,*this));
	}
	return _cache[n];
}

#endif // LAZY_H
