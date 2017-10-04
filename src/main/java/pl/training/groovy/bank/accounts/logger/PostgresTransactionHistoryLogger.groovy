package pl.training.groovy.bank.accounts.logger

import groovy.sql.Sql
import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.model.Operation

import javax.sql.DataSource

/**
 * Created by PMUZYKA on 2017-10-04.
 */
@TupleConstructor
class PostgresTransactionHistoryLogger implements Accounts{

    private Accounts accounts
    private Sql sql

    private static final String INSERT_TRANSACTION_INFO = "insert into transactions (number,balance,timestamp,type) values(:number,:balance,:timestamp,:type)"

    PostgresTransactionHistoryLogger(Accounts accounts, DataSource dataSource){
        this.accounts = accounts // this points to the current constructor accounts instance
        sql = new Sql(dataSource) // we are referencing a method
    }

    Account createAccount() {
        accounts.createAccount()
    }

    void deposit(String accountNumber, Long funds) {
        accounts.deposit(accountNumber, funds)
        process(accountNumber, funds, Operation.DEPOSIT.name())
    }

    void withdraw(String accountNumber, Long funds) {
        accounts.withdraw(accountNumber, funds)
        process(accountNumber, funds, Operation.WITHDRAW.name())
    }

    private void process(String accountNumber, Long funds, String operationType) {

        Integer date = System.currentTimeMillis()
        sql.executeInsert(INSERT_TRANSACTION_INFO, [number: accountNumber, balance: funds, timestamp: date, type: operationType])

    }
}
