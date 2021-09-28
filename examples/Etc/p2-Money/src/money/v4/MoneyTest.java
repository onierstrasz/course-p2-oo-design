package money.v4;

import static org.junit.Assert.*;
import org.junit.*;

public class MoneyTest {
	// fixtures
	private Money f12CHF;
	private Money f14CHF;
	
	private Money f7USD;
	private Money f21USD;
	private MoneyBag fMB1;
	private MoneyBag fMB2;

	@Before public void setUp() {
		f12CHF = new Money(12, "CHF");
		f14CHF = new Money(14, "CHF");
		f7USD =	new Money( 7, "USD");
		f21USD = new Money(21, "USD");
		fMB1 = new MoneyBag(f12CHF, f7USD);
		fMB2 = new MoneyBag(f14CHF, f21USD);
	}

	@Test public void testEquals() {
		assertNotNull(f12CHF);
		assertEquals(f12CHF, f12CHF);
		assertEquals(f12CHF, new Money(12, "CHF"));
		assertFalse(f12CHF.equals(f14CHF));
	}

	@Test public void testSimpleAdd() {
		IMoney expected = new Money(26, "CHF");
		IMoney result = f12CHF.add(f14CHF);
		assertEquals(expected, result);
	}

	@Test public void testBagEquals() {
		assertNotNull(fMB1);
		assertEquals(fMB1, fMB1);
		assertFalse(fMB1.equals(f12CHF));
		assertFalse(f12CHF.equals(fMB1));
		assertFalse(fMB1.equals(fMB2));
	}

	@Test public void testMixedSimpleAdd() {
		// [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
		Money bag[] = { f12CHF, f7USD };
		MoneyBag expected = new MoneyBag(bag);
		assertEquals(expected, f12CHF.add(f7USD));
	}

}
