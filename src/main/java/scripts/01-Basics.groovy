package scripts
// One line comment

/*
 Multiline
 comment
 */

// Variable definition
String text = "hello"
println text

// Static vs. dynamic typing
def variable = 5
print variable.class.name
variable = "text"
print variable.class.name

String message = "Hello"
message = 3.4
print message.class.name

//Class definition

/**
 * Represents app user
 */
class User {
    /**
     *  User first name
     */
   String firstName
    /**
     *  User last name
     */
   String lastName
    /**
     *  Prints hello message
     */
   void sayHello() {
       println "Hi my name is ${firstName} ${lastName}"
   }


/*    equals - checks if two Objects are equal based on their state (values).
      We are not talking about references - we are not checking if that is the same object.
 */

    boolean equals(o) {
        if (this.is(o)) return true // compare to itself - must return true since the state will be the same
        if (getClass() != o.class) return false

        User user = (User) o

        if (firstName != user.firstName) return false
        if (lastName != user.lastName) return false

        return true
    }

/*    hashCode - state of the object (field values ) is translated into one value - hashCode.
      It can be used in collections, allowing fast access to the elements in a collection - like index.
      especially useful when working on large collections
 */



    int hashCode() {
        int result
        result = (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        return result
    }
}

User userOne = new User()
userOne.firstName = 'Jan'
userOne.lastName = 'Kowalski'
userOne.sayHello()

User userTwo = new User()
userTwo.firstName = 'Maria'
userTwo.lastName = 'Nowak'
userTwo.sayHello()

User testUser = new User()
testUser.firstName = 'Jan'
testUser.lastName = 'Kowalski'
testUser.sayHello()

User userThree = userTwo // we are referencing userTwo  , we are changing the name of userTwo
userThree.lastName = 'Kowalska'
userThree.sayHello()
userTwo.sayHello()

int someValue = 7
int otherValue = someValue // since it is a primitive type we are just copying the value

otherValue++
println someValue
println otherValue

byte byteValue = 2

def name = "Groovy"
name = 4

println name.getClass()


println userOne == testUser // in Groovy ==  means "equals"