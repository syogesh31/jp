import java.math.BigDecimal;
import java.time.LocalDate;

public class TradeService {
	
	private TradeValidator tv = new TradeValidator();

	public BigDecimal calculateTradeAmount(Trade t) {

		if (tv.isNull(t))
			throw new IllegalArgumentException("Trade Object is null");
		;

		if (!tv.isRateValid(t)) {
			throw new IllegalArgumentException(
					"Trade Exchange rate is null  or less than 0");
		}

		if (!tv.isPriceValid(t)) {
			throw new IllegalArgumentException(
					"Trade Unit Price is null or  less than 0");
		}

		if (!tv.areUnitsValid(t)) {
			throw new IllegalArgumentException(
					"Number of  units in trade are 0 or less");
		}

		return t.getFxRate().multiply(t.getPrice())
				.multiply(BigDecimal.valueOf(t.getUnit()));
	}

	public LocalDate calculateSettlementDate(Trade t) {
		CurrencyCalender cc = new CurrencyCalender(t.getCurrency(),	t.getSettlementDate());
		if (cc.isWorkingDay()) {
			return t.getSettlementDate();
		}

		return cc.getNextWorkingDate();
	}
}
