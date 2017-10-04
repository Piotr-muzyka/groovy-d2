package pl.training.groovy.bank.accounts.logger

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.BankException
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts

import java.util.logging.Level
import java.util.logging.Logger

/**
 * Created by PMUZYKA on 2017-10-03.
 */
@TupleConstructor
class TransactionLogger implements Accounts{


    // design pattern - decorator, proxy

    private Accounts accounts
    private Closure<String> currencyFormatter
    private Logger logger = Logger.getLogger(getClass().name)


    /*
     using contract we can use TransactionLogger by delegating accountService object from other classes. Therefore we can also
     use other objects which are Accounts interfaces instead of accountService.
      */
    Account createAccount() {
        Account account = accounts.createAccount()
        logger.log(Level.INFO, "New account created ${account.number}")
        return account
    }

    void deposit(String accountNumber, Long funds) {
        process(accountNumber){ ->
            accounts.deposit(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} <== ${currencyFormatter(funds)}")
        }

    }

    void withdraw(String accountNumber, Long funds) {
        process(accountNumber){ ->
            accounts.withdraw(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} ==> ${currencyFormatter(funds)}")
        }

    }

    private void process(String accountNumber, Closure<Void> operation) {
        try {
            operation()
            logger.log(Level.INFO, "Status: Success")
        } catch (BankException ex){
            logger.log(Level.INFO, "Status: Failure ${ex.class.simpleName}")
        }
    }
}
