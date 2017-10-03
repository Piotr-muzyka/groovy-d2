package scripts


/*
Why to use abstract class ? As a blueprint for the inheriting classes.
abstract class - not all of the methods have to be defined , can be abstract and implemented in the subclass. You cannot create
a new object from abstract class (e.g. like new Employee since the class is not fully implemented/ unfinished.

Interface vs Abstract class with only abstract methods - abstract class can contain fields, when Interface cannot.
 */

abstract class Employee {
    Integer salary = 0

    def getInfo() {
        "Salary: ${salary}"
    }

    abstract String report()
}

/*
Tester is also an Employee. It inherits from Employee class, treating it as a blueprint it implements some of the methods.
In Java it is possible only to inherit from one parent, however it can be overcome using Interfaces.
extends keyword - says that Tester inherits from Employee  class. @Override - we are overriding method definition from parent.

implementing an interface is the same mechanism. We are also inheriting from the interface. Interfaces can also inherit from other interfaces
what is IMPORTANT they can inherit from many interfaces since we don't have an implementation there. BUT there are default methods
which can be included in Java.
 */
class Tester extends Employee {

    private testingFrameworks = []

    void runTests() {
        println "Testing..."
    }

    @Override
    String getInfo() {
        super.getInfo() + ", frameworks: ${testingFrameworks}"
    }

    @Override
    String report() {
        return null
    }
}

class Developer extends Employee {

    void makeCoffe() {
        println "Please..."
    }

    @Override
    String report() {
        return null
    }
}


void printInfo(Employee ... employees) {
    employees.each { println it.info }
}


Employee tester = new Tester()
Employee developer = new Developer()
Tester testr2 = (Employee) tester


println tester.getInfo()

println developer.getInfo()