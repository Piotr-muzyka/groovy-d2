package pl.training.groovy.bank.accounts.factory

import com.zaxxer.hikari.HikariDataSource
import org.postgresql.Driver
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.PostgresAccountNumberGenerator
import pl.training.groovy.bank.accounts.logger.PostgresTransactionHistoryLogger
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.PostgresAccountsRepository
import pl.training.groovy.bank.accounts.service.AccountsService

import javax.sql.DataSource

class ProductionAccountsFactory implements AccountsFactory {

    private DataSource dataSource = {
        DataSource dataSource = new HikariDataSource()
        dataSource.jdbcUrl = 'jdbc:postgresql://localhost:5432/bank'
        dataSource.username = 'postgres'
        dataSource.password = 'admin'
        dataSource.driverClassName = Driver.class.name
        return dataSource
    }()
    private AccountsRepository accountsRepository = new PostgresAccountsRepository(dataSource)
    private AccountNumberGenerator accountNumberGenerator = new PostgresAccountNumberGenerator(dataSource)

    Accounts create() {
        AccountsService accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        accountsService.addObserver { println "Deposit limit on account: ${it.number}" }
        new PostgresTransactionHistoryLogger(accountsService, dataSource)
    }

}
