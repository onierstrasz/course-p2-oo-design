package money.v4;

public interface IMoney {
	public IMoney add(IMoney aMoney);
	IMoney addMoney(Money aMoney);
	IMoney addMoneyBag(MoneyBag aMoneyBag);
}