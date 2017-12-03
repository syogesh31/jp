import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TradeValidatorTest {

	
	TradeValidator tv ;
	
	Trade nullTrade;
	Trade invalidTrade;
	
	@Before
	public void setUp() throws Exception {
		tv = new TradeValidator();
		nullTrade =  null;
		invalidTrade = new Trade("X", "B", LocalDate.parse("2017-11-24"), "SAR",
				BigDecimal.valueOf(-10), -4, BigDecimal.valueOf(-1.34),
				LocalDate.parse("2017-11-24"));
	}

	@Test
	public void testIsNull() {
		Assert.assertTrue("Trade can't be null ",tv.isNull(nullTrade));
	}

	@Test
	public void testIsPriceValid() {
		Assert.assertFalse("Trade price Invalid ",tv.isPriceValid(invalidTrade));
	}

	@Test
	public void testIsRateValid() {
		Assert.assertFalse("Trade rate Invalid ",tv.isRateValid(invalidTrade));
	}

	@Test
	public void testAreUnitsValid() {
		Assert.assertFalse("Trade units Invalid ",tv.areUnitsValid(invalidTrade));
	}

}
