import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("-------MD Bank of India Welcomes you!-------");
    Scanner sc  = new Scanner(System.in);
    Bank b = new Bank();
    int option ;

    while(true){
        System.out.println("1.Create new Account\n2.Login\n3.Exit");
        System.out.print("choose one option: ");
        option = sc.nextInt();
        sc.nextLine();
        if(option == 1){
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your inital bal: ");
            double balance = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            System.out.print("Acc type(Savings/Current/Student): ");
            String acc_type = sc.nextLine();

            b.createAccount(name, balance, username, password, acc_type);
        }else if(option == 2){
            System.out.print("Enter Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            Accounts log_in = b.login(username, password);
            if(log_in!=null){
                while(true){
                    System.out.println("1.Deposit\n2.Withdraw\n3.Transfer\n4.Check Balance\n5.Transaction History\n6.Logout");
                    System.out.print("Choose one option: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    if(option ==1){
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        log_in.deposit(amount);
                    }else if(option  == 2) {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        log_in.withdraw(amount);
                    }else if(option == 3){
                        System.out.print("Enter the target Account number: ");
                        String otheracc_num = sc.nextLine();
                         Accounts other_acc = b.findbyaccnum(otheracc_num);
                         if(other_acc!=null) {
                             System.out.print("Enter amount: ");
                             double amount = sc.nextDouble();
                             log_in.transfer(other_acc, amount);
                         }else{
                             System.out.println("Account not found!");
                         }

                    }else if(option == 4){
                        System.out.println();
                        System.out.println( "Current Balance: " + log_in.getBalance());
                    }else if(option == 5){
                        log_in.printHistory();
                    }else if (option == 6){
                        System.out.println();
                        System.out.println("Logged out Successfully");
                        break;
                    }
                }
            }
        }else if(option == 3){
            break;
        }
    }
    }
}