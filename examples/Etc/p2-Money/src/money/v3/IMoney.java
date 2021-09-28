package money.v3;

public interface IMoney {
	public IMoney add(IMoney aMoney);
	// NB: the following have package scope only
	IMoney addMoney(Money aMoney);
	IMoney addMoneyBag(MoneyBag aMoneyBag);
}