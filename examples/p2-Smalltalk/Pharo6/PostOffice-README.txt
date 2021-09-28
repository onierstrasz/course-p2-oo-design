
The first change set is the result of test-driven development of the example in DEMO-README.txt

The initial test set is:

PostOfficeTest>>testPostOffice
	self assert: postoffice isEmpty.
	(Customer named: 'jack') enters: postoffice.
	self assert: postoffice waiting equals: 1.
	(Customer named: 'jane') enters: postoffice.
	self assert: postoffice waiting equals: 2.
	(Customer named: 'jill') enters: postoffice.
	self assert: postoffice waiting equals: 3.
	self assert: postoffice serveCustomer equals: 'jack'.
	self assert: postoffice waiting equals: 2.
	self assert: postoffice serveCustomer equals: 'jane'.
	self assert: postoffice waiting equals: 1.
	self assert: postoffice serveCustomer equals: 'jill'.
	self assert: postoffice waiting equals: 0.
	self assert: postoffice serveCustomer isNil.

The second change set is a Seaside application that simply adds a trivial view to the post office, with forms to add new customers and serve them.

