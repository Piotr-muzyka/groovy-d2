package pl.training.groovy.bank.accounts.logger

import groovy.sql.Sql
import groovy.transform.TupleConstructor
import pl.training.groovy.bank.BankException
import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.repository.AccountNotFoundException

import javax.sql.DataSource
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Created by PMUZYKA on 2017-10-04.
 */
@TupleConstructor
class PostgresTransactionHistoryLogger implements Accounts{

    private Accounts accounts
    private Sql sql

    private static final String INSERT_TRANSACTION_INFO = "insert into transactions (id,number,balance,timestamp,type) values(:id,:number,:balance,:timestamp,:type)"

    PostgresTransactionHistoryLogger(Accounts accounts, DataSource dataSource){
        this.accounts = accounts // this points to the current constructor accounts instance
        sql = new Sql(dataSource) // we are referencing a method
    }

    Account createAccount() {
        accounts.createAccount()
    }

    void deposit(String accountNumber, Long funds) {
        accounts.deposit(accountNumber, funds)
        process(accountNumber, funds, "DEPOSIT")
    }

    void withdraw(String accountNumber, Long funds) {
        accounts.withdraw(accountNumber, funds)
        process(accountNumber, funds, "WITHDRAW")
    }

    private void process(String accountNumber, Long funds, String operationType) {

        Integer date = new Date().getDateTimeString().toInteger()
        sql.executeInsert(INSERT_TRANSACTION_INFO, [number: accountNumber, balance: funds, timestamp: date, type: operationType])

    }
}
