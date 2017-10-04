package pl.training.groovy.bank

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.model.Operation

/**
 * Created by PMUZYKA on 2017-10-04.
 */
@TupleConstructor
class FileDataImporter {

    private static final String SEPARATOR = ';'
    private Accounts accounts

    // method which imports queries for SQL
    void importData(String path){
        new File(path).eachLine{
            def (accountNumber, funds, operationType) = it.split(SEPARATOR)
            switch (Operation.valueOf(operationType)){
                case Operation.DEPOSIT:
                    accounts.deposit(accountNumber, funds as Long)
                    break
                case Operation.WITHDRAW:
                    accounts.withdraw(accountNumber, funds as Long)
                    break
                default:
                    throw new IllegalArgumentException()
            }
        }
    }
}
