import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>, Serializable {

    // Variables
    private static int next = 1;
    int transactionNumber;
    Account acc;
    LocalDate date;
    char operation;
    double amount;

    // Constructor


    public Transaction(Account acc, LocalDate date, char operation, double amount) {
        this.acc = acc;
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        transactionNumber = next++;

    }

    @Override
    public int compareTo(Transaction o) {
        return this.transactionNumber - o.transactionNumber;
    }

    // toString


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionNumber=" + transactionNumber +
                ", acc=" + acc +
                ", date=" + date +
                ", operation=" + operation +
                ", amount=" + amount +
                '}';
    }

    // Getters

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public Account getAcc() {
        return acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public char getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }
}
