package scripts

/**
 *
 * Domain Specific Language example
 *
 */

class Test {

    def show = { println it } // show '123' - shows 123
    def power = { it**2 } ///


    // please returns a map of keys, therefore it is possible to pass values
    // please accepts show as an actions, 'the' is a connector (returns a block of code hidden under it),
    def please(action) {
        [the: { what ->
            [of: {
                action(what(it))
            }
            ]

        }]
    }

    void test(){
        //show '123'
        //show power(44)

        please show the power of 3
    }
    static void main() {


        /--------/

    }
}
new Test().test()