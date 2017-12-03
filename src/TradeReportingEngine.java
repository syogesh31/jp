import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TradeReportingEngine {

	public TradeReportingEngine(TradeService ts) {
		super();
		this.ts = ts;
	}

	private TradeService ts;

	/**
	 * Calculates the date wise volume based on the trade transaction type B or
	 * S
	 * 
	 * @param trades
	 *            - Collection of trades
	 * @param transactionType
	 *            - either B or S
	 * @return Map containing the volume date
	 */
	public Map<LocalDate, BigDecimal> amountSettledEveryDay(
			Collection<Trade> trades, String transactionType) {

		validateTransactionType(transactionType);

		if (trades == null || trades.isEmpty()) {
			throw new IllegalArgumentException("Trades are empty");
		}

		// Filter trades based on transaction types and grouped by trade
		// settlement date
		LinkedHashMap<LocalDate, BigDecimal> s = trades
				.stream()
				.filter(b -> b.getTransactionType().equals(transactionType))
				.collect(
						Collectors.groupingBy((t) -> ts
								.calculateSettlementDate(t), Collectors
								.mapping(t -> ts.calculateTradeAmount(t),
										Collectors.reducing(BigDecimal.ZERO, (
												t, r) -> t.add(r)))))
				.entrySet().stream()
				.sorted(Entry.comparingByKey())
				// SORTING BY DATE ASC
				.collect(
						Collectors.toMap(Entry::getKey, Entry::getValue,
								(e, f) -> e, LinkedHashMap::new));

		return Collections.unmodifiableMap(s);

	}

	private void validateTransactionType(String transactionType) {
		if (transactionType == null
				|| (!transactionType.equals("B") && !transactionType
						.equals("S"))) {
			throw new IllegalArgumentException(
					"Incorrect Transaction Type, Engin can process only B(Buy) or S(Sell) values");
		}
	}

	/**
	 * Ranks entity based on transaction volume in given trade data.
	 * 
	 * @param trades
	 *            : Collection of trade
	 * @param transactionType
	 *            : either B or S
	 * @return Map of trading entities in ascending order by their , based on
	 *         transaction type.
	 */
	public Map<String, BigDecimal> rankEntities(Collection<Trade> trades,
			String transactionType) {
		TradeService ts = new TradeService();
		validateTransactionType(transactionType);

		if (trades == null || trades.isEmpty()) {
			throw new IllegalArgumentException("Trades are empty");
		}

		// Filter trades based on transaction types and grouped by entity

		LinkedHashMap<String, BigDecimal> s = trades
				.stream()
				.filter(b -> b.getTransactionType().equals(transactionType))
				.collect(
						Collectors.groupingBy(Trade::getEntity, Collectors
								.mapping(t -> ts.calculateTradeAmount(t),
										Collectors.reducing(BigDecimal.ZERO, (
												t, r) -> t.add(r)))))
				.entrySet()
				// SORTING BY VOLUME ASC
				.stream()
				.sorted(Entry.comparingByValue())
				.collect(
						Collectors.toMap(Entry::getKey, Entry::getValue,
								(e, f) -> e, LinkedHashMap::new));

		return Collections.unmodifiableMap(s);

	}
}
