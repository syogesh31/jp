import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeServiceTest {

	TradeService ts;
	Trade sarTrade;
	Trade gbpTrade;

	@Before
	public void setUp() throws Exception {
		ts = new TradeService();
		sarTrade = new Trade("X", "B", LocalDate.parse("2017-11-24"), "SAR",
				BigDecimal.valueOf(10), 4, BigDecimal.valueOf(1.34),
				LocalDate.parse("2017-11-24"));
		gbpTrade = new Trade("X", "B", LocalDate.parse("2017-11-25"), "gbp",
				BigDecimal.valueOf(10), 4, BigDecimal.valueOf(0.96),
				LocalDate.parse("2017-11-24"));

	}

	@Test
	public void testCalculateTradeAmount() {
		BigDecimal amt = ts.calculateTradeAmount(sarTrade);
		Assert.assertEquals(BigDecimal.valueOf(53.60).stripTrailingZeros(),
				amt.stripTrailingZeros());

	}

	@Test
	public void testCalculateSettlementDate() {
		LocalDate sarDate = ts.calculateSettlementDate(sarTrade);
		LocalDate gbpDate = ts.calculateSettlementDate(gbpTrade);

		Assert.assertEquals(LocalDate.parse("2017-11-26"), sarDate);
		Assert.assertEquals(LocalDate.parse("2017-11-27"), gbpDate);
	}

}
