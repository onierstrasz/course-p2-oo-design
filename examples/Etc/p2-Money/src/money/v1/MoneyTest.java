package money.v1;

import static org.junit.Assert.*;
import org.junit.*;

public class MoneyTest {
	// fixtures
	private Money f12CHF;
	private Money f14CHF;

	@Before public void setUp() {
		f12CHF = new Money(12, "CHF");
		f14CHF = new Money(14, "CHF");
	}

	@Test public void testEquals() {
		assertNotNull(f12CHF);
		assertEquals(f12CHF, f12CHF);
		assertEquals(f12CHF, new Money(12, "CHF"));
		assertFalse(f12CHF.equals(f14CHF));
	}

	@Test public void testSimpleAdd() {
		Money expected = new Money(26, "CHF");
		Money result = f12CHF.add(f14CHF);
		assertEquals(expected, result);
	}
}
