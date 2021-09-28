package money.v4;

class Money implements IMoney {
	final int amount; // NB: package scope
	final String currency;

	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
		assert invariant();
	}

	protected boolean invariant() {
		return amount > 0;
	}

	public boolean equals(Object anObject) {
		if (anObject instanceof Money) {
			Money other = (Money) anObject;
			return (this.amount == other.amount)
				&& this.currency.equals(other.currency);
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return currency.hashCode();
	}

	public String toString() {
		return Integer.toString(this.amount) + this.currency; 
	}

	public IMoney add(IMoney m) {
		return m.addMoney(this);
	}

	public IMoney addMoney(Money m) {
		if (m.currency.equals(currency) ) {
			return new Money(amount+m.amount, currency);
		}
		return new MoneyBag(this, m);
	}

	public IMoney addMoneyBag(MoneyBag s) {
		return s.addMoney(this);
	}

}