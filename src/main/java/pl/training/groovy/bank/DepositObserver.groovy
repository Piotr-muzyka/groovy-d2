package pl.training.groovy.bank

/**
 * Created by PMUZYKA on 2017-10-03.
 */
interface DepositObserver {

    void onBigDeposit(String accountNumber, Long funds)
}