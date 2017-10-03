package pl.training.groovy.bank.accounts

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.DepositObserver
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.util.Subject

import java.text.NumberFormat

@TupleConstructor
class AccountsService implements Accounts, Subject<Account> {

    private static final Long DEPOSIT_LIMIT = 10_000

    private AccountsRepository accountsRepository
    private AccountNumberGenerator accountNumberGenerator
    private Set<DepositObserver> depositObservers = []

    Account createAccount() {
        String accountNumber = accountNumberGenerator.next
        Account account = new Account(number: accountNumber, balance: 0)
        accountsRepository.save(account)
    }

    void deposit(String accountNumber, Long funds) {
        process(accountNumber) { account ->
            account.deposit(funds)
            checkDeposit(accountNumber,funds)
        }
    }

    void addDepositObserver(DepositObserver observer){
        depositObservers += observer
    }

    private void checkDeposit(String accountNumber, Long funds){
        if (funds >= DEPOSIT_LIMIT){
            depositObservers.each { observer ->
                observer.onBigDeposit(accountNumber, funds)
            }
        }
    }

    void withdraw(String accountNumber, Long funds) {
        process(accountNumber) { account ->
            account.checkFunds(funds)
            account.withdraw(funds)
        }
    }

    private void process(String accountNumber, Closure<Void> operation) {
        Account account = accountsRepository.getByNumber(accountNumber)
        operation(account)
        accountsRepository.update(account)
    }

}
