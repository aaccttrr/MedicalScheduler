package medicalScheduler.main

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*

val t = Terminal()
private val logger = KotlinLogging.logger {}

//Text styles for medicalScheduler.main.menu
val title = (bold+green+underline)
val option = (yellow)
val error = (brightYellow on red)



fun main(args: Array<String>){
    logger.info { "Launching Medical Scheduler App" }

    var input: Int

    do {
        input= menu()
        when(input){
            1 -> addAppointment()
            2 -> addPrescription()
            3 -> updateAppointment()
            4 -> updatePrescription()
            5 -> listAppointments()
            6 -> listPrescriptions()
            7 -> t.println(brightRed("Exiting..."))
            else -> t.println(error("\n Invalid option! Please enter a valid number."))
        }
        println()
    } while(input != 7)
    //Print log and finish process
    logger.info { "Shutting Down Medical Scheduler..." }

}

fun menu(): Int {

    var option : Int
    var input: String? = null

    //Printing medicalScheduler.main.menu
    t.println(title("Medical Scheduler App"))
    t.println(option("1. Create an appointment"))
    t.println(option("2. Update an appointment"))
    t.println(option("3. Create a prescription"))
    t.println(option("4. Update a prescription"))
    t.println(option("5. List all appointments"))
    t.println(option("6. List all prescriptions"))
    t.println(option("7. Exit"))
    println()
    t.print(red("Enter a choice: "))
    //Reads user input
    input = readLine()!!
    //Validates input before converting to integer
    option = if (input.toIntOrNull() != null && !input.isEmpty)
        input.toInt()
    else
        -9
    return option

}

fun addAppointment(){

    println("Create appointments to be implemented!")
}

fun updateAppointment(){

    println("Update appointments to be implemented!")
}

fun addPrescription(){

    println("Create prescriptions to be implemented!")
}

fun updatePrescription(){

    println("Update prescriptions to be implemented!")
}

fun listAppointments(){

    println("List appointments to be implemented!")
}

fun listPrescriptions(){

    println("List prescriptions to be implemented!")
}

