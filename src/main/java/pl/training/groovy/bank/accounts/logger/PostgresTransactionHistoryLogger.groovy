package pl.training.groovy.bank.accounts.logger

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts

import java.util.logging.Level
import java.util.logging.Logger

/**
 * Created by PMUZYKA on 2017-10-04.
 */
@TupleConstructor
class PostgresTransactionHistoryLogger implements Accounts{

    private Accounts accounts
    private Closure<String> currencyFormatter
    private Logger logger = Logger.getLogger(getClass().name)

    Account createAccount() {
        Account account = accounts.createAccount()
        logger.log(Level.INFO, "New account created ${account.number}")
        return account
    }

    void deposit(String accountNumber, Long funds) {

    }

    void withdraw(String accountNumber, Long funds) {

    }
}
