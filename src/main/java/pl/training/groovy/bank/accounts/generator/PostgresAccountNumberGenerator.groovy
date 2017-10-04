package pl.training.groovy.bank.accounts.generator

import groovy.sql.Sql

import javax.sql.DataSource
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by PMUZYKA on 2017-10-04.
 */
class PostgresAccountNumberGenerator extends FakeAccountNumberGenerator{

    private static final String SELECT_MAX_ACCOUNT_NUMBER = "select max(number) from accounts"

/*
    PostgresAccountNumberGenerator(DataSource dataSource){
        new Sql(dataSource).eachRow(SELECT_MAX_ACCOUNT_NUMBER){
            String lastAccountNumber = it(0) // it is a row , a list with values = on index 0 we have our number inside
            if (lastAccountNumber){
                counter = new AtomicLong(lastAccountNumber as Long) // we are casting to Long, AtomicLong - synchronized long
            }

        }

    }
*/

    PostgresAccountNumberGenerator(DataSource dataSource){
        new Sql(dataSource).eachRow(SELECT_MAX_ACCOUNT_NUMBER, updateCounter) // we are referencing a method
    }


    private Closure<Void> updateCounter = {
        String lastAccountNumber = it(0) // it is a row , a list with values = on index 0 we have our number inside
        if (lastAccountNumber){
            counter = new AtomicLong(lastAccountNumber as Long) // we are casting to Long, AtomicLong - synchronized long
        }

    }
*/
    String getNext() {
        return null
    }
}
