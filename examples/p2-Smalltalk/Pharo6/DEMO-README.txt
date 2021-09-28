***
# Smalltalk Demo

- Get the latest Pharo image from <http://pharo-project.org/pharo-download>
- Drag the following code snippets to the desktop and file them in:
	- Beeper-beep.st (fixes beeping)
	- BouncingAtomsMorphDemo.st (loads this missing demo)
	- DemoWorkspace.st (sets big fonts and prepares 2 workspaces)

NB: <shift><opt><click> to obtain Morphic halos in Pharo 6.

---

# Demo Part 1 — first steps

## Open Pharo Image

---
## Playground — doit, printit etc

---

	3 + 4.  

---

- explain three-button mouse
- do it, print it, inspect it, explore it, do it and go
- browse its class
- find implementors of +

---

	SmallInteger maxVal

---

- inspect: SmallInteger maxVal
- add 1 and inspect result
- subtract 1 and inspect result

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
	(Integer>>#factorial) browse.

---

- evaluate and discuss
- inspect also intermediate results
- find implementors of factorial
- browse factorial method
- note syntax for
  - statements
  - comments vs strings
  - if/then/else
  - blocks
  - return
  - keywords

---

	OrderedCollection new.
	Array with: 1 with: 2.
	1/2.
	1@2.

---

- different ways to create objects
- note class-side methods Array class>>#with:with: and Point class>>#x:y:

---

## Tools

- show World menu
- open Transcript

---

	Transcript show: 'hello world'.  
	Transcript show: 'hello world' ; cr.  

---

- note cascade
- NB: Transcript is a global object, but not a class

---
## Everything is an Object

---

- Load BouncingAtomsMorph.st and 

---

	BouncingAtomsMorph new openInWorld.

---

- save image, close and open
- blue-click>explore
- select some submorphs>AtomMorph and inspect it
- self color: Color red
- self velocity: 0@0
- drag out the red AtomMorph
- make it larger
- copy it
- inspect it
- self velocity: 2@3
- drag it back to the BouncingAtomsMorph
- click on the red Menu handle and embed it
- self browse the AtomMorph
- note that velocity changes when bounced
- browse senders of velocity:
- modify to beep after bounce:

---

	bounced ifTrue: [self velocity: vx @ vy. Beeper beep ].  

---

NB: If `Beeper beep` does not work, try `Beeper primitiveBeep`

- very noisy, so add test for color red

---

	bounced ifTrue: [self velocity: vx @ vy.
		self color = Color red ifTrue: [Beeper beep] ].

***

# Demo Part 2 — defining classes and methods

---
## Test-driven PostOffice with one counter (don't model counters) — 10"

- *NB* Set change set to go to PostOffice
- Create TestCase subclass: PostOfficeTest ...
  - NB: a class is an object
  - create a class by sending msg to superclass

- Create PostOfficeTest>>testPostOffice

---

	testPostOffice
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

---

- Commit; introduce missing classes and ivars
  - add queue OrderedCollection ivar to model PostOffice
- Add the setUp method

- open the TestRunner
  - show how to run tests from runner or from browser
- Run the tests; iteratively implement missing methods
- Test fails — inspect values in debugger to see that Customer>>name is the default implementation; reimplement
- show the debugger: creating methods; stack view; 

- Test fails — can't remove from queue if empty

---

	serveCustomer
		^ queue ifNotEmpty: [queue removeFirst name] ifEmpty: [nil]

---

- file out change set!
- mention Monticello and git integration

***

# Demo Part 3 — Seaside

[TIMINGS?]

## Seaside intro

- Open Seaside3.1-OneClick.app
- open <http://localhost:8080/seaside>
	- NB: you might need to configure network settings to bypass proxies for 127.0.0.1 and localhost
- Navigate to examples>counter
	Click on ++ and --. Interleave with Back and Forward buttons.
- Explain toolbar.
	- Start a New Session.
	- Show the Configure page. Close by clicking "x".
- Explain components are objects inheriting from WAComponent
- Toggle halos
	- Show Browser, Inspector and Stylesheet
	- Toggle Rendered and Source views. Click active links.
- open http://localhost:8080/seaside/examples/multicounter
	- Explain components can be multiply instantiated
	- Interleave links and forward and back buttons
	- Browse implementation of WAMultiCounter (3 instance & 3 class methods)
- open http://localhost:8080/seaside/config [Hint: use a separate tab]
	- The default username/password is admin/seaside
	- Click on "examples"
	- Click on "Add"
	- Enter name: "counter2" / Type: "Application"
	- Select Root Class: "WACounter"
	- Save and Close
	- Try counter2
	- Delete it

- Define: WAComponent subclass: #WAHelloWorld ...
	- renderContentOn: html
		html text: 'hello world'
	- WAHelloWorld class>>canBeRoot
			^ true
	- Add entry point "hello" / Application
	- "Root Component" "WAHelloWorld"
	- Save and Close

## Post Office in Seaside

- Load the change set in 12SmallTalkDemos>PostOffice.2.cs
- browse PostOfficeView
- Start a local pier
- Add a new Application called PostOffice anywhere — and set it to PostOfficeView
- Navigate to http://localhost:8080/browse to run it

***

# ... OPTIONAL ...

## The Debugger is your Friend — 2"

---

	50 isPerfect  

---

- Create
^ self divisors sum = self

- Proceed
- Create

---

	^ (1 to: self -1 ) select: [ :each | (self rem: each) = 0]

---

- Try it out inside the debugger!

- Proceed

---

	(2 to: 1000) select: [:each| each isPerfect]

---
