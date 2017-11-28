import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TradeReportingEngine {

	TradeService ts = new TradeService();

	public static void main(String[] args) {

		List<Trade> trades = Arrays.asList(
				new Trade("X", "B", LocalDate.parse("2017-11-24"), "USD",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Z", "S", LocalDate.parse("2017-11-24"), "GBP",
						BigDecimal.valueOf(5), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Y", "B", LocalDate.parse("2017-11-24"), "AED",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")),
				new Trade("Y", "B", LocalDate.parse("2017-11-24"), "SAR",
						BigDecimal.valueOf(10), 4, BigDecimal.valueOf(5),
						LocalDate.parse("2017-11-24")));

		TradeService ts = new TradeService();

		Map<String, Map<String, BigDecimal>> s = trades
				.stream()
				.collect(
						Collectors.groupingBy(Trade::getEntity, Collectors.groupingBy(Trade::getTransactionType,Collectors.mapping(t->ts.calculateTradeAmount(t), Collectors.reducing(BigDecimal.ZERO,(t,r)->t.add(r))))));

		
		System.out.println(s);

	}

	public Map<LocalDate, BigDecimal> amountSettledEveryDay(List<Trade> trades,
			String transactionType) {

		if (transactionType == null
				|| (!transactionType.equals("B") && !transactionType
						.equals("S"))) {
			throw new IllegalArgumentException(
					"Incorrect Transaction Type, Engin can process only B(Buy) or S(Sell) values");
		}

		if (trades == null || trades.isEmpty()) {
			throw new IllegalArgumentException("Trades are empty");
		}

		Map<LocalDate, BigDecimal> s = trades
				.stream()
				.filter(b -> b.getTransactionType().equals(transactionType))
				.collect(
						Collectors.groupingBy((t) -> ts
								.calculateSettlementDate(t), Collectors
								.mapping(t -> ts.calculateTradeAmount(t),
										Collectors.reducing(BigDecimal.ZERO, (
												t, r) -> t.add(r)))));

		return s;

	}

	void rankEntities(List<Trade> trades) {

	}
}
