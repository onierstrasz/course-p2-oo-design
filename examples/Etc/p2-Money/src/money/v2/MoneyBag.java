package money.v2;

import java.util.Hashtable;

class MoneyBag {
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
			m = m.add(aMoney);
		} else {
			m = aMoney;
		}
		monies.put(aMoney.currency, m);
	}

	public String toString() {
		return monies.toString();
	}
}
