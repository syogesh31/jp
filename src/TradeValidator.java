
public class  TradeValidator {
	
	public boolean isNull(Trade t) {
		return  t==null;
	}

	public  boolean isPriceValid(Trade t){
		return  t.getPrice() != null || t.getPrice().signum() == 1;
	}
	
	public  boolean isRateValid(Trade t){
		return t.getFxRate() != null && t.getFxRate().signum()  == 1;
	}

	public boolean areUnitsValid(Trade t){
		return  t.getUnit() > 0;
	}
	
}
