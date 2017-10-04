package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.AccountsService
import pl.training.groovy.bank.accounts.logger.TransactionLogger
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.PostgresAccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import com.zaxxer.hikari.HikariDataSource
import org.postgresql.Driver as PostgreDriver
import pl.training.groovy.bank.accounts.repository.PostgresAccountsRepository

import javax.sql.DataSource
import java.text.NumberFormat
import java.util.logging.Level
import java.util.logging.Logger
class App {

    private static final Long CURRENCY_UNIT = 100

    private def currencyFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format((it as Double) / CURRENCY_UNIT)
    }
    private createAccounts() {
        DataSource dataSource = new HikariDataSource()
        dataSource.jdbcUrl = 'jdbc:postgresql://localhost:5432/bank'
        dataSource.username = 'postgres'
        dataSource.password = 'admin'
        dataSource.driverClassName = PostgreDriver.class.name

//        AccountsRepository accountsRepository = new HashMapAccountsRepository()
//        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        AccountsRepository accountsRepository = new PostgresAccountsRepository(dataSource)
        AccountNumberGenerator accountNumberGenerator = new PostgresAccountNumberGenerator(dataSource)
        Accounts accountsService = new AccountsService(
                accountsRepository: accountsRepository,
                accountNumberGenerator: accountNumberGenerator)
        accountsService.addObserver{
            println "Deposit limit on account: ${it.number}"
        }
        accountsService.addObserver{
            println "Deposit info: ${it.number}"
        }
        new TransactionLogger(accounts: accountsService, currencyFormatter: currencyFormatter)
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
        accounts.withdraw(account.number, 100_000)
        println account
    }

}
