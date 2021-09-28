***
# GT Smalltalk Demo

Get latest GT from https://gtoolkit.com

---

# Demo Part 1 — first steps

---
## Playground — doit, printit etc

- Start up Gt
- Point out main tools and tutorials
- Open a Playground

---

	'Hello world'

---

	3 + 4.  

---

- evaluate, inspect, debug
- inspect it
- slide up a new playground

---

	self +1

---

- See the tabs: Preview, Integer, Raw and Meta
- Meta tab of SmallInteger
- see instance and class methods

---

	self class maxVal

---

- inspect: self class maxVal
- inspect: self + 1
- inspect: self - 1

---
## Syntax, classes, methods

---

	3 + 4.
	5 factorial.
	2 raisedTo: 10.

	2 raisedTo: 1 + 3 factorial.
	1 + 2 * 3.
	1 + (2 * 3).

---

- message syntax and precedence
- note 

---

	1000 factorial / 999 factorial.
	1000 factorial printString size.

---

- evaluate and discuss
- inspect also intermediate results
- browse factorial method
- note syntax for
  - statements
  - comments vs strings
  - if/then/else
  - blocks
  - return
  - keywords
- find implementors of factorial
- navigate to class Integer and explore class hierarchy

---

	OrderedCollection new.
	Array with: 1 with: 2.
	1/2.
	1@2.

---

- different ways to create objects
- note class-side methods Array class>>#with:with: and Point class>>#x:y:
- inspect 1/2
- look at implementation of / (from |>)
- see how it constructs a Fraction

---
## OPTIONAL Tools

- show Gt tools menu
- open Transcript in a new window and clear it

---

	Transcript show: 'hello world'.  
	Transcript show: 'hello world' ; cr.  

---

- note cascade
- NB: Transcript is a global object, but not a class

---
## OPTIONAL Everything is an Object

Bouncing atoms demo. See that atoms are objects, not just images.
Inspect them. Change them. Change the behavior of a method at run time.

Inspect:

---

	BlBouncingAtomsElement demo

---

- Find an atom that doesn't move
- Drag it
- Click on it
- Spawn a new inspector and close the old pane

- Look at the panes: 
  - Overview: click on Background, Border ...
  - Children: none
  - Parents: BouncingAtomsElement
  - Raw: velocity
  - Meta: methods

- Change velocity to 10@10 and back to 0@0
- Change atom size to 10@10
- Change background to Color blue
- Look for senders of velocity:
- See that it is in the bounceIn: method

- Change it so the colors change when vx becomes orange or green when vx turns negative  or positive

		vx < 0
			ifTrue: [ self background: Color orange ]
			ifFalse: [ self background: Color green ]

- Change its velocity to 20@5
- See its colors change
- Optional: change bounceIn: to set the color to grey and the size to 10@10

***

# Demo Part 2 — defining classes and methods

NB: Complete PostOffice example is here

	Metacello new
		baseline: 'GtDemos';
		repository: 'github://onierstrasz/gt-demos/src';
		load.


---
## Test-driven PostOffice with one counter — TIME ?


- Open Coder
- Create class PostOfficeTestExamples in package PostOffice

emptyPostOffice
	<gtExample>
	| po |
	po := PostOffice new.
	self assert: po isEmpty.
	^ po

isEmpty
	^ queue isEmpty

- add queue slot

initialize
	queue := OrderedCollection new

- test passes

- add customer "Jack"

postOfficeWithJack
	<gtExample>
	| po |
	po := self emptyPostOffice.
	(Customer named: 'Jack') enters: po.
	self assert: po waiting equals: 1.
	^ po

- Create Customer class
- Create Customer class>>named:

named: aString
	^ self new name: aString

- Create Customer>>name:

name: aString
	name := aString

- Create slot name
- Test fails
- Browse Customer (highlight name and browse)
- Add Customer>>#enters:

enters: aPostOffice
	aPostOffice enqueue: self

- Add PostOffice>>#enqueue:

enqueue: aCustomer
	queue addLast: aCustomer


- Add PostOffice>>#waiting

waiting
	^ queue size

- fix printing

printOn: aStream
	super printOn: aStream.
	queue printElementsOn: aStream

- show Print tab of example

- Fix Customer printing

printOn: aStream
	self name printOn: aStream

name
	^ name


- Add Jill

postOfficeWithJackAndJill
	<gtExample>
	| po |
	po := self postOfficeWithJack.
	(Customer named: 'Jill') enters: po.
	self assert: po waiting equals: 2.
	^ po

- Serve Jack

postOfficeWithJackServed
	<gtExample>
	| po |
	po := self postOfficeWithJackAndJill.
	self assert: po serveCustomer name equals: 'Jack'.
	self assert: po waiting equals: 1.
	^ po


serveCustomer
	^ queue ifEmpty: [ nil ] ifNotEmpty: [ queue removeFirst ]


- Serve Jill


postOfficeWithJackAndJillServed
	<gtExample>
	| po |
	po := self postOfficeWithJackServed.
	self assert: po serveCustomer name equals: 'Jill'.
	self assert: po waiting equals: 0.
	self assert: po serveCustomer isNil.
	^ po

- Run all examples from Coder button
- See Examples map
- Break a test and see the others fail


- Inspect an empty postoffice
- view the queue as Boxes
- Add a customer

	(Customer named: 'Bob') enters: self

- nothing happens
- update initialize to 

initialize
	queue := OrderedCollection new asValueHolder

- Add customers and serve them while viewing the Boxes tab of the queue


***

# ... OPTIONAL ...

## Live programming in GT

---

	50 isPerfect

---

- Create method isPerfect in class SmallInteger

---
	
	^ self divisors sum = self

---

- Proceed
- Create method divisors in class SmallInteger

---

	self assert: self > 1.
	^ (1 to: self -1 ) select: [ :each | (self rem: each) = 0]

---

Try:

---

	(2 to: 1000) select: [:each| each isPerfect]

---
