package pl.training.groovy.bank.util

/**
 * Created by PMUZYKA on 2017-10-03.
 */
trait Subject<E>{

    protected Set<Observer> observers = []
    //protected Set observers = [] as Set

    void addObserver(Observer<E> observer){
        observers.add(observer)
        // /observers << observer // << adds observer
    }

    void removeObserver(Observer<E> observer){
        observers.remove(observer)
    }

    void notifyObservers(E event){
        //observers.each { observer -> observer.onEvent(event)
        observers.each { it.onEvent(event)}
    }
}
