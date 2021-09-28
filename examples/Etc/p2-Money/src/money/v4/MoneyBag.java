package money.v4;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

class MoneyBag implements IMoney {
	private Hashtable<String,Money> monies = new Hashtable<String,Money>(5);

	MoneyBag(Money m1, Money m2) {
		appendMoney(m1);
		appendMoney(m2);
		assert invariant();
	}

	MoneyBag(Money bag[]) {
		for (Money money : bag) {
			appendMoney(money);
		}
		assert invariant();
	}

	protected boolean invariant() {
		return monies.size() > 1;
	}

	private void appendMoney(Money aMoney) {
		Money m = monies.get(aMoney.currency);
		if (m != null) {
			m = (Money) m.add(aMoney); // add downcast
		} else {
			m = aMoney;
		}
		monies.put(aMoney.currency, m);
	}

	public String toString() {
		return monies.toString();
	}

	MoneyBag(Money m, MoneyBag bag) {
		appendMoney(m);
		appendBag(bag);
		assert invariant();
	}

	MoneyBag(MoneyBag m1, MoneyBag m2) {
		appendBag(m1);
		appendBag(m2);
		assert invariant();
	}

	public IMoney add(IMoney m) {
		return m.addMoneyBag(this);
	}

	public IMoney addMoney(Money m) {
		return new MoneyBag(m, this);
	}

	public IMoney addMoneyBag(MoneyBag s) {
		return new MoneyBag(s, this);
	}

	private void appendBag(MoneyBag aBag) {
		for (Money m: monies.values()) {
			appendMoney(m);
		}
	}

	public boolean equals(Object anObject) {
		if (!(anObject instanceof MoneyBag)) {
			return false;
		}
		Set<Money> myMoneySet = new HashSet<Money>(monies.values());
		MoneyBag other = (MoneyBag) anObject;
		Set<Money> otherMoneySet = new HashSet<Money>(other.monies.values());
		return myMoneySet.equals(otherMoneySet);
	}
	
	public int hashCode() {
		return monies.hashCode();
	}
}
