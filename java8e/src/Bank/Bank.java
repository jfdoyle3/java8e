package Bank;

import BankAccount.*;
import BankTools.UI;

import java.util.HashMap;

public class Bank {
    private String name;
    private int safeAmt;
    private HashMap<String, Client> clients;
    private HashMap<Integer, Account> accounts;
    private Integer clientCount = 0;
    private Integer accountCount = 0;

    public Bank(String name) {
        this.name = name;
        clients= new HashMap<>();
        accounts = new HashMap<>();
    }

    public void addClient(String firstName, String lastName) {
        clientCount += 1;
        clients.put(clientCount.toString(), new Client(firstName, lastName, clientCount.toString()));
    }

    public void addAccount(String clientId, String accountType, int startingBal, UI ui) {
        // TODO: get client from client collection
        Client client = clients.get(clientId);
        // TODO: create account giving it the client.
        Account newAccount = createAccount(accountType, client, ++accountCount, startingBal, ui);
        // TODO: attach to accounts collection
        client.addAccount(newAccount);
        accounts.put(accountCount, newAccount);
    }

    public Account createAccount(String accountType, Client client, int accountId, int startingBal, UI ui) {
        switch (accountType) {
            case "checking":
                return new CheckingAccount(accountId, startingBal, client);
            case "savings":
                int interest = ui.requestInt("Please enter interest rate:");
                return new SavingsAccount(accountId, startingBal, client, interest);
            case "CDInvestment" :
                int cdInterest = ui.requestInt("Please enter CD interest rate:");
               int period=ui.requestInt("What is the period");
                int timeframe=ui.requestInt("What is the timeframe");
                return new CDInvestment(startingBal,  accountId,client,cdInterest, period, timeframe);
            case "Investment":
                int interestRate = ui.requestInt("Please enter interest rate:");
                String periodType=ui.requestString("Enter period: (D)ay, (W)eek, (B)iweekly, (M)onthly,(Q)uarterly, (Y)early");
                int investPeriod=ui.requestInt("What is the period");
                return new InvestmentAccount( startingBal, accountId, client, interestRate, investPeriod,periodType);
            default:
                return null;
        }
    }

    public void displayClients() {
        for (var client : clients.keySet()){
            System.out.println(clients.get(client));
        }
    }

    public Account getAccount(int accountId) {
        return accounts.get(accountId);
    }

//    public SavingsAccount createSavingsAccount() {};
//    public CheckingAccount createCheckingAccount() {};
}
