import java.time.LocalDateTime;
public class TransactionHistory {
    private String account_num;
    private String action;
    private double amount;
    private LocalDateTime date;

    public TransactionHistory(String account_num, String action, double amount, LocalDateTime date){
        this.account_num = account_num;
        this.action = action;
        this.amount = amount;
        this.date = date;
    }


}


