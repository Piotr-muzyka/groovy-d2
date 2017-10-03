package pl.training.groovy.bank

import com.sun.istack.internal.logging.Logger
import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.AccountsService
import pl.training.groovy.bank.accounts.TransactionLogger
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.FakeAccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.HashMapAccountsRepository

import java.text.NumberFormat
import java.util.logging.Level
import java.util.logging.Logger
class App {

    private createFormatter = { funds ->
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format(funds)
    }

    private createAccounts() {
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        Accounts accountsService = new AccountsService(
                accountsRepository: accountsRepository,
                accountNumberGenerator: accountNumberGenerator)
        new TransactionLogger(accounts: accountsService, currencyFormatter: createFormatter)
    }

    static void main(String[] args) {

        App app = new App()
        Logger.getLogger(TransactionLogger.class.name).setLevel(Level.INFO)

        /* in order to turn off logging use :
        Accounts accounts = accountService
          */
        Accounts accounts = app.createAccounts()
        //---------------------------------------------
        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 100)
        println account
    }

}
