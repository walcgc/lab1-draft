import java.sql.ResultSet;
import java.time.LocalDateTime;

public interface SmsManagerInterface {

    public boolean insertSms();
    public ResultSet retrieveSmsByDate(LocalDateTime dateTime);
    public ResultSet retrieveSmsByPromoCode(String promoCode);
    public ResultSet retrieveSmsByMsisdn(String msisdn);
    public ResultSet retrieveSmsByMsisdn(String[] msisdn);
    public ResultSet retrieveSmsBySent(String msisdn);
    public ResultSet retrieveSmsByReceive(String msisdn);


}
