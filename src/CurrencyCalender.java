import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class CurrencyCalender {
	
	private static final List<String> middleEastCurrencies = Arrays.asList("SAR", "AED");
	
	private String currency;
	private LocalDate date;
	
	public CurrencyCalender(String currency, LocalDate date) {
		this.currency  = currency.toUpperCase();
		this.date  = date;
	}

	public  boolean isWorkingDay(){
		DayOfWeek day = date.getDayOfWeek();
		
		if(middleEastCurrencies.contains(currency)){
			if(DayOfWeek.FRIDAY.equals(day) || DayOfWeek.SATURDAY.equals(day))  return false;
		}else{
			if(DayOfWeek.SUNDAY.equals(day) || DayOfWeek.SATURDAY.equals(day))  return false;
		}
		
		return  true;
	}
	
	public LocalDate getNextWorkingDate(){
		DayOfWeek day = date.getDayOfWeek();
		int  daysToAdd = 0;
		
		if(middleEastCurrencies.contains(currency)){
			 daysToAdd  =  DayOfWeek.THURSDAY.equals(day) ?  3 : DayOfWeek.FRIDAY.equals(day)?2:1 ;
		}else{
			daysToAdd  = DayOfWeek.FRIDAY.equals(day) ?  3 : DayOfWeek.SATURDAY.equals(day)?2:1 ;
		}
		
		return date.plusDays(daysToAdd);
	}
	

}
