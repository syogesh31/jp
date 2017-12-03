import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ CurrencyCalenderTest.class, TradeReportingEngineTest.class,
		TradeServiceTest.class, TradeValidatorTest.class })
public class AllTests {

}
