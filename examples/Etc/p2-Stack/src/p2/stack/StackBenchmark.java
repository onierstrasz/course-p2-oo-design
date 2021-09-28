package p2.stack;

/**
 * Run timing benchmarks for various Stack implementations.
 * @author Oscar.Nierstrasz@acm.org
 * @version 1.0 1998-11-25
 */
public class StackBenchmark {
	
	static public void main(String args[]) {
		benchmark(new LinkStack());
		// benchmark(new BrokenArrayStack());
		benchmark(new ArrayStack());
		// benchmark(new SimpleWrappedStack());
		benchmark(new WrappedStack());
	}
	
	/**
	 * Times how long it takes to do a large number of pushes/pops.
	 */
	static public void benchmark(StackInterface stack) {
		int iterations = 100000; // 100K
		Object item = new Integer(0);
		Timer timer = new Timer();
		long pushTime, popTime;
		
		// System.out.println("Timing " + stack.getClass().getName());
		
		timer.reset();
		for (int i=0; i<iterations; i++) {
			stack.push(item);
		}
		pushTime = timer.timeElapsed();
		// System.out.println(pushTime + " milliseconds for " + iterations + " pushes");
		
		timer.reset();
		for (int i=0; i<iterations; i++) {
			stack.pop();
		}
		popTime = timer.timeElapsed();
		// System.out.println(popTime + " milliseconds for " + iterations + " pops");
		
		String run = "Apple MRJ";
		// String run = "Metrowerks";
		// String run = "Metrowerks JIT";
		System.out.println(run + '\t' + stack.getClass().getName() + '\t'
				+ pushTime + '\t' + popTime);
		
		
	}
	
}
