

import static java.lang.System.out;

public class Overloading {

	public static void main(String[] args) {
		out.println("Overloading is not double dispatch! Why?");
		out.println("Java binds method calls at compile time with their full signature (early binding).");
		out.println("The full signature includes all parameter types, hence when the actual type of an");
		out.println("argument differs from its declared type (polymorphism), overloading does not work");
		out.println("as you might expect!\n");
		case1();
		case2();
		case3();
		case4();
		out.println("\nSolution -- use double dispatch!");
	}

	static void case1() {
		Thing thing = new ThingToo();
		Object o = new Foo();
		out.println("expects add(Foo): fails because both thing's and o's actual type differ");
		thing.add(o);
	}
	
	static void case2() {
		Thing thing = new ThingToo();
		Foo o = new Foo();
		out.println("expects add(Bar): fails because thing's actual type differs");
		thing.add(o);
	}
	
	static void case3() {
		ThingToo thing = new ThingToo();
		Foo o = new Bar();
		out.println("expects add(Bar): fails because o's actual type differs");
		thing.add(o);
	}	

	static void case4() {
		ThingToo thing = new ThingToo();
		Bar o = new Bar();
		out.println("expects add(Bar): succeeds because actual and declared types match");
		thing.add(o);
	}	
	
}

class Foo { }

class Bar extends Foo { }

class Thing {
	void add(Object o) {
		System.out.println("add(Object)");
	}
}

class ThingToo extends Thing {
	void add(Foo foo) {
		out.println("add(Foo)");
	}
	void add(Bar bar) {
		out.println("add(Bar)");
	}
}