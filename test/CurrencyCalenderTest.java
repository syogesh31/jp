import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;


public class CurrencyCalenderTest {

	@Test
	public void testIsWorkingDateForMECurrencies() {
		CurrencyCalender ccFriday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-24"));
		CurrencyCalender ccSaturday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-25"));
		CurrencyCalender ccSunday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-26"));
		
		Assert.assertFalse(ccFriday.isWorkingDay());
		Assert.assertFalse(ccSaturday.isWorkingDay());
		Assert.assertTrue(ccSunday.isWorkingDay());
		
	}

	
	@Test
	public void testIsWorkingDateForNonMECurrencies() {
		CurrencyCalender ccFriday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-24"));
		CurrencyCalender ccSaturday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-25"));
		CurrencyCalender ccSunday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-26"));
		
		Assert.assertTrue(ccFriday.isWorkingDay());
		Assert.assertFalse(ccSaturday.isWorkingDay());
		Assert.assertFalse(ccSunday.isWorkingDay());
	}
	
	@Test
	public void testGetNextWorkingDateForMECurrencies() {
		LocalDate  sunday =  LocalDate.parse("2017-11-26");
		
		CurrencyCalender ccFriday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-24"));
		CurrencyCalender ccSaturday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-25"));
		CurrencyCalender ccThursday =  new  CurrencyCalender("AED", LocalDate.parse("2017-11-23"));
		
		Assert.assertEquals(sunday, ccFriday.getNextWorkingDate());
		Assert.assertEquals(sunday, ccSaturday.getNextWorkingDate());
		Assert.assertEquals(sunday, ccThursday.getNextWorkingDate());
		
	}
	
	@Test
	public void testGetNextWorkingDateForNonMECurrencies() {
		LocalDate  monday =  LocalDate.parse("2017-11-27");
		
		CurrencyCalender ccFriday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-24"));
		CurrencyCalender ccSaturday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-25"));
		CurrencyCalender ccSunday =  new  CurrencyCalender("SGP", LocalDate.parse("2017-11-26"));
		
		Assert.assertEquals(monday, ccFriday.getNextWorkingDate());
		Assert.assertEquals(monday, ccSaturday.getNextWorkingDate());
		Assert.assertEquals(monday, ccSunday.getNextWorkingDate());
	}

}
