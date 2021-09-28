'From Pharo1.2.1 of 2 April 2011 [Latest update: #12345] on 19 May 2011 at 4:02:38 pm'!TestCase subclass: #PostOfficeTest	instanceVariableNames: 'postoffice'	classVariableNames: ''	poolDictionaries: ''	category: 'PostOffice'!Object subclass: #Customer	instanceVariableNames: 'name'	classVariableNames: ''	poolDictionaries: ''	category: 'PostOffice'!Object subclass: #PostOffice	instanceVariableNames: 'queue'	classVariableNames: ''	poolDictionaries: ''	category: 'PostOffice'!!PostOfficeTest methodsFor: 'as yet unclassified' stamp: 'onierstrasz 5/7/2009 09:40'!setUp	postoffice := PostOffice new.! !!PostOfficeTest methodsFor: 'as yet unclassified' stamp: 'onierstrasz 5/7/2009 10:34'!testPostOffice	self assert: postoffice isEmpty.	(Customer named: 'jack') enters: postoffice.	self assert: postoffice waiting equals: 1.	(Customer named: 'jane') enters: postoffice.	self assert: postoffice waiting equals: 2.	(Customer named: 'jill') enters: postoffice.	self assert: postoffice waiting equals: 3.	self assert: postoffice serveCustomer equals: 'jack'.	self assert: postoffice waiting equals: 2.	self assert: postoffice serveCustomer equals: 'jane'.	self assert: postoffice waiting equals: 1.	self assert: postoffice serveCustomer equals: 'jill'.	self assert: postoffice waiting equals: 0.	self assert: postoffice serveCustomer isNil.! !!Customer methodsFor: 'creation' stamp: 'onierstrasz 5/7/2009 09:46'!name	^ name! !!Customer methodsFor: 'creation' stamp: 'onierstrasz 5/7/2009 09:42'!named: aByteString 	name := aByteString ! !!Customer methodsFor: 'evaluating' stamp: 'onierstrasz 5/7/2009 09:43'!enters: aPostOffice 	aPostOffice enqueues: self! !!Customer class methodsFor: 'instance creation' stamp: 'onierstrasz 5/7/2009 09:42'!named: aByteString 	^ self new named:aByteString ! !!PostOffice methodsFor: 'initialize-release' stamp: 'onierstrasz 5/7/2009 09:41'!initialize	queue := OrderedCollection new! !!PostOffice methodsFor: 'testing' stamp: 'onierstrasz 5/7/2009 09:41'!isEmpty	^ queue isEmpty! !!PostOffice methodsFor: 'evaluating' stamp: 'onierstrasz 5/7/2009 09:44'!enqueues: aCustomer 	queue addLast: aCustomer ! !!PostOffice methodsFor: 'evaluating' stamp: 'onierstrasz 5/19/2011 16:02'!serveCustomer	^ queue ifNotEmpty: [queue removeFirst name]			ifEmpty: [nil]! !!PostOffice methodsFor: 'evaluating' stamp: 'onierstrasz 5/7/2009 09:44'!waiting	^ queue size! !