package pl.training.groovy.bank.util

/**
 * Created by PMUZYKA on 2017-10-03.
 */
interface Observer<E> {
    void onEvent(E event)
}
