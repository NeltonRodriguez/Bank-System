import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class AccountFrame extends JFrame {
    JLabel accountNumberLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accountNumberTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;
    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;
    JList<Account> accountsLST;
    JPanel p1,p2,p3,p4,p5;
    Set<Account> accountSet = new TreeSet<>();
    Account acc, x;
    boolean newRec = true;


    // ComboBox Data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountLSTMDL;

    // Table Data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    // Constructor


    public AccountFrame() {
        super("Account Operations");
        setLayout(null);
        setSize(600,400);

        // Adding components to the frame

        // 1st: Adding Labels
        accountNumberLBL = new JLabel("Account Number");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        // 2nd: Adding TextFields

        accountNumberTXT = new JTextField();
        accountNumberTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));

        // 3rd: ComboBox
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);

        citiesCMBMDL.addElement(new City("Brooklyn", "New York"));
        citiesCMBMDL.addElement(new City("Dothan", "Alabama"));
        citiesCMBMDL.addElement(new City("Panama", "Florida"));
        citiesCMBMDL.addElement(new City("Miami", "Florida"));

        // Adding data to JComboBox
        citiesCMB = new JComboBox<City>(citiesCMBMDL);

        // 4th: Adding Radio Buttons

        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");

        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        // 5th: Adding Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        // 6th: Adding Table
        accountLSTMDL = new DefaultListModel<>();
        accountsLST = new JList<>(accountLSTMDL);

        // 7th: Adding Panels
        p1 = new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305,5,300,190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());

        // 8th: Adding Components to Panel
        p1.add(accountNumberLBL); p1.add(accountNumberTXT); p1.add(ownerLBL);
        p1.add(ownerTXT); p1.add(balanceLBL); p1.add(balanceTXT);
        p1.add(cityLBL); p1.add(citiesCMB); p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN); p2.add(saveBTN); p2.add(showBTN); p2.add(quitBTN);

        p3.add(amountLBL); p3.add(amountTXT); p3.add(depositBTN); p3.add(withdrawBTN);

        p4.add(accountsLST);

        // 9th: Adding Panels to Frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        // 10th: Table creation
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Transaction Number");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Transaction Amount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);

        // 11th: Adding functionality to the buttons
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountNumberTXT.setText("");
                ownerTXT.setText("");
                citiesCMB.setSelectedIndex(0);
                maleRDB.setSelected(true);
                balanceTXT.setText("");
                amountTXT.setText("");
                newRec = true;

            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newRec){
                    // New record insertion
                    if (ownerTXT.getText().length() != 0){
                        // create a new variable
                        acc = new Account(
                                ownerTXT.getText(),
                                (City) citiesCMB.getSelectedItem(),
                                maleRDB.isSelected()? 'M' : 'F');

                        accountNumberTXT.setText(String.valueOf(acc.accountNumber));
                        accountSet.add(acc);
                        accountLSTMDL.addElement(acc);
                        newRec = false;
                    } else{
                        JOptionPane.showMessageDialog(null, "Please fill all field before saving...");
                    }
                } else {
                    // 12th : Updating
                    accountSet.remove(acc);
                    int a = Integer.parseInt(accountNumberTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) citiesCMB.getSelectedItem();

                    char g = maleRDB.isSelected()? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountLSTMDL.setElementAt(acc,accountsLST.getSelectedIndex());
                    newRec = false;
                }
            }
        });

        showBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Account> it = accountSet.iterator();
                while (it.hasNext()){
                    s += it.next().toString() + "\n";
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });

        depositBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newRec && amountTXT.getText().length() !=0){
                    // 13th: Adding transaction to table
                    Transaction t = new Transaction(acc, LocalDate.now(),
                            'D', Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionInTable(t);

                    // 14th: Perform deposit from account
                    acc.deposit(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });

        withdrawBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newRec && amountTXT.getText().length() !=0){
                    // Adding transaction to table
                    Transaction t = new Transaction(
                            acc, LocalDate.now(),
                            'W',
                            Double.parseDouble(amountTXT.getText()));

                    DisplayTransactionInTable(t);

                    // 15th: Change/perform withdraw on account
                    acc.withdraw(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));

                }
            }
        });

        accountsLST.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                acc = x = accountsLST.getSelectedValue();
                accountNumberTXT.setText(String.valueOf(acc.accountNumber));
                ownerTXT.setText(acc.owner);
                citiesCMB.setSelectedItem(acc.city);

                if (acc.gender == 'M') maleRDB.setSelected(true);
                else femaleRDB.setSelected(true);

                balanceTXT.setText(String.valueOf(acc.balance));
                amountTXT.setEnabled(true);
                depositBTN.setEnabled(true);
                newRec = false;

                // 16th: Clear table:
                for (int i = tableModel.getRowCount() -1; i>=0; i++){
                    tableModel.removeRow(i);
                }

                // 17th: Get transaction to selected account
                SearchTransactionList(acc.accountNumber);
            }
        });


    }

    private void SearchTransactionList(int accountNumber) {
        List<Transaction> filteredList = new ArrayList<>();

        // Iterate through the list
        for (Transaction t: translist){
            // Filter values that contains transactionNumber
            if (t.getAcc().accountNumber == accountNumber){
                filteredList.add(t);
            }
        }

        // Display the filtered list

        for (int i =0; i<filteredList.size(); i++){
            // Displaying data into table
            tableModel.addRow(new Object[]{
                    filteredList.get(i).getTransactionNumber(),
                    filteredList.get(i).getDate(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getAmount()
            });
        }

    }

    private void DisplayTransactionInTable(Transaction t) {
        // Displaying data into table
        tableModel.addRow(new Object[]{
                t.getTransactionNumber(),
                t.getDate(),
                t.getOperation(),
                t.getAmount()
        });

        // Adding object to ArrayList
        translist.add(t);

    }

    public static void main (String[] args){
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
