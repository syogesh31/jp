import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeReportingEngineTest {

	List<Trade> trades;
	TradeService ts;
	TradeReportingEngine reportingEngine;
	
	Map<String,BigDecimal> expectedBuyRanking;
	Map<String,BigDecimal> expectedSellRanking;
	
	Map<LocalDate,BigDecimal> expectedOutgoing;
	Map<LocalDate,BigDecimal> expectedIncoming;

	@Before
	public void setUp() throws Exception {
		ts = new TradeService();
		reportingEngine = new TradeReportingEngine(ts);
		trades = Arrays.asList(
				new Trade("X", "B", LocalDate.parse("2017-11-24"), "USD",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(1),
						LocalDate.parse("2017-11-24")),
				new Trade("Z", "S", LocalDate.parse("2017-11-24"), "GBP",
						BigDecimal.valueOf(5), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Z", "B", LocalDate.parse("2017-11-25"), "GBP",
						BigDecimal.valueOf(5), 6, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Y", "B", LocalDate.parse("2017-11-24"), "AED",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Y", "S", LocalDate.parse("2017-11-24"), "SAR",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")));
		
		expectedBuyRanking = new  LinkedHashMap<String, BigDecimal>();
		expectedBuyRanking.put("X", BigDecimal.valueOf(40));
		expectedBuyRanking.put("Z", BigDecimal.valueOf(150));
		expectedBuyRanking.put("Y", BigDecimal.valueOf(200));
		
		expectedBuyRanking = Collections.unmodifiableMap(expectedBuyRanking);
		
		expectedSellRanking =  new  LinkedHashMap<String, BigDecimal>();
		expectedSellRanking.put("Z", BigDecimal.valueOf(100));
		expectedSellRanking.put("Y", BigDecimal.valueOf(200));
		
		expectedSellRanking = Collections.unmodifiableMap(expectedSellRanking);
		
		
		expectedOutgoing = new LinkedHashMap<LocalDate, BigDecimal>();
		expectedIncoming = new LinkedHashMap<LocalDate, BigDecimal>();
		
		expectedOutgoing.put(LocalDate.parse("2017-11-24"), BigDecimal.valueOf(40));
		expectedOutgoing.put(LocalDate.parse("2017-11-26"), BigDecimal.valueOf(200));
		expectedOutgoing.put(LocalDate.parse("2017-11-27"), BigDecimal.valueOf(150));
		expectedOutgoing = Collections.unmodifiableMap(expectedOutgoing);
		
		expectedIncoming.put(LocalDate.parse("2017-11-24"), BigDecimal.valueOf(100));
		expectedIncoming.put(LocalDate.parse("2017-11-26"), BigDecimal.valueOf(200));
		expectedIncoming = Collections.unmodifiableMap(expectedIncoming);
		 
	}

	@Test
	public void testAmountSettledEveryDay() {
		Map<LocalDate, BigDecimal> outgoing = reportingEngine
				.amountSettledEveryDay(trades, "B");
		Map<LocalDate, BigDecimal> incoming = reportingEngine
				.amountSettledEveryDay(trades, "S");

		
		Assert.assertEquals(expectedOutgoing, outgoing);
		Assert.assertEquals(expectedIncoming, incoming);

	}

	@Test
	public void testRankEntities() {
		Map<String, BigDecimal> buyRanking = reportingEngine.rankEntities(trades, "B");
		Map<String, BigDecimal> sellRanking = reportingEngine.rankEntities(trades, "S");

		Assert.assertEquals(expectedBuyRanking,buyRanking);
		Assert.assertEquals(expectedSellRanking,sellRanking);
		
	}
	
	

}
