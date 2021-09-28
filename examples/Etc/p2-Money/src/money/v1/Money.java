package money.v1;

class Money {
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

	public Money add(Money other) {
		assert this.currency.equals(other.currency);
		return new Money(amount + other.amount, currency);
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
}