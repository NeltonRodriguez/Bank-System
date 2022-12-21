import java.util.Date;

public class Account implements Comparable{

    // Variables
    static int nextAccountNumber = 10; // class variable
    int accountNumber;
    String owner;
    City city;
    char gender;
    double balance;
    Date openDate;

    // Constructor


    public Account() {
    }

    public Account(String owner, City city, char gender) {

        accountNumber = nextAccountNumber;
        nextAccountNumber +=10;
        this.owner = owner;
        this.city = city;
        this.gender = gender;

        balance = 0.0;
        openDate = null;
    }

    public Account(int accountNumber, String owner, City city, char gender, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        //this.balance = balance;
        setBalance(balance);
    }

    // Setter:
    public void setBalance(double balance) {
        balance = balance>0.0 ? balance: 0.0;
    }
    //


    //toString
    @Override
    public String toString() {
        return accountNumber + "  " + owner
                + "  " + city.cityName + "  " + gender
                + "  " + balance; }

    @Override
    public int compareTo(Object o) {
        return this.owner.compareTo(((Account) o).owner );
    }

    public void deposit(double amount){
        if (amount>0){
            setBalance(balance + amount);
        }
    }

    public double withdraw (double amount){
        if (amount>0){
            if (amount<balance){
                setBalance(balance-amount);
            }
            else {
                amount = balance;
                setBalance(0.0);
            }
            return amount;
        }
        return 0.0;
    }
}
