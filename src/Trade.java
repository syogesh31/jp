import java.math.BigDecimal;
import java.time.LocalDate;

public class Trade {
	
	private String entity;
	private String transactionType;
	private BigDecimal fxRate;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private long unit;
	private BigDecimal price;

	
	public Trade(String entity,String transactionType,LocalDate settlementDate, String  currency,BigDecimal price,long unit,BigDecimal fxRate,LocalDate instDate) {
		this.entity = entity;
		this.transactionType = transactionType;
		this.price =  price;
		this.settlementDate = settlementDate ;
		this.currency = currency;
		this.unit = unit;
		this.fxRate  = fxRate;
		this.instructionDate  = instDate;
	}
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getFxRate() {
		return fxRate;
	}

	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public long getUnit() {
		return unit;
	}

	public void setUnit(long unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}