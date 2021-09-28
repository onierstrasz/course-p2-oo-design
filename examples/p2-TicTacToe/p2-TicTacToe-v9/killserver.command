#! /bin/sh

cd `dirname "$0"`

srv=.killserver
if test -f ${srv}
then	
	cat ${srv}
	sh ${srv}
	rm -f ${srv}
fi

reg=.killregistry
if test -f ${reg}
then	
	cat ${reg}
	sh ${reg}
	rm -f ${reg}
fi
