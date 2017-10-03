package scripts
// Elvis operator

def value

println value

if (value != null) {
    println "Value: ${value}"
} else {
    println "Brak wartości"
}

println value != null ? "Value: ${value}" : "Brak wartości" // question mark separates two statements

println value ?: "Brak wartości" // Elvis operator ?:

// Safe navigation, direct field access, method reference

class Person {
    private String name
    String getName() {
        println "Get name"
        return name
    }
    void sayHello() {
        println "Hi!"
    }
}

def person = new Person()

println person.@name?.contains('A') // safe navigation, instead of writing checks we can use ? to be safe from null-pointer exception

def sayHelloMethod = person.&sayHello // reference to a method
sayHelloMethod()
sayHelloMethod.call()

println sayHelloMethod.class.name

// Spread operator

class Car {
    String make
    String model
}

def cars = [
        new Car(make: 'Ford', model: 'Focus'),
        new Car(make: 'Skoda')
]

println cars*.model // will print models for all objects [focus, null]

class MathUtils {

    Integer sum(Integer x, Integer y) {
        x + y
    }

}

def mathUtils = new MathUtils()

println mathUtils.sum(2, 3);

def numbers = [2]
// *numbers with reference to table we are able to use it inside of the method
println mathUtils.sum(*numbers, 4);

def otherNumbers = [2, 5, 7, *numbers]