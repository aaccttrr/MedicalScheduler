import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*

val t = Terminal()
private val logger = KotlinLogging.logger {}

//Text styles for menu
val title = (bold+green+underline)
val option = (yellow)


//class Main {

    fun main(args: Array<String>){
        logger.info { "Launching Medical Scheduler App" }

        var input: Int
        input = menu()
    }

    fun menu(): Int {

        var option : Int
        var input: String? = null

        //Printing menu
        t.println(title("Medical Scheduler App"))
        t.println(option("1. Create an appointment"))
        t.println(option("2. Create a prescription"))
        t.println(option("3. List all appointments"))
        t.println(option("4. List all prescriptions"))
        t.println(option("5. Exit"))
        println()
        t.print(red("Enter a choice: "))
        //Reads user input
        input = readLine()!!
        //Validates input before converting to integer
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option

    }
//}