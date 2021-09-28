package annotations;

import java.lang.reflect.Method;

public class Debug {

	public static void print(Object object) {
		Debug debug = new Debug(object);
		debug.printAll();
	}
	
	private Object object;
	
	private Debug(Object object) {
		this.object = object;
	}
	
	/**
	 * Print all properties to System.out
	 *
	 */
	private void printAll() {
		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(GetProperty.class)) {
				this.printProperty(method);
			}
		}
	}
	
	/**
	 * Prints one property (name: value) to System.out
	 */
	private void printProperty(Method method) {
		try {
			GetProperty getter = method.getAnnotation(GetProperty.class);
			Object value = method.invoke(object);
			System.out.println(getter.value() + ": " + value);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
