package medicalScheduler.main

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.models.Appointment
import medicalScheduler.models.Prescription

val t = Terminal()
private val logger = KotlinLogging.logger {}

//Text styles for medicalScheduler.main.menu
val title = (bold+green+underline)
val option = (yellow)
val error = (brightYellow on red)

var appointment = Appointment()
val appointments = ArrayList<Appointment>()

var prescription = Prescription()
val prescriptions = ArrayList<Prescription>()


fun main(args: Array<String>){
    logger.info { "Launching Medical Scheduler App" }

    var input: Int

    do {
        input= menu()
        when(input){
            1 -> addAppointment()
            2 -> updateAppointment()
            3 -> addPrescription()
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

//Adding appointments function
fun addAppointment(){

    appointment.id++
    t.println(title("Add Appointment"))
    t.print("Enter the type of appointment: ")
    appointment.type = readLine()!!
    t.print("Enter the appointment date: ")
    appointment.date = readLine()!!
    t.print("Enter the doctor's name: ")
    appointment.doctorName = readLine()!!
    t.print("Enter the location of the appointment: ")
    appointment.location = readLine()!!

    if(appointment.type.isNotEmpty() && appointment.date.isNotEmpty() && appointment.doctorName.isNotEmpty() && appointment.location.isNotEmpty()) {
        appointments.add(appointment.copy())
        println("Appointment added : [$appointment]")
    } else {
        println("Appointment not added.")
    }
}

//Updating appointments functions
fun updateAppointment(){

    t.println(title("Update Appointment"))
    println()
    listAppointments()
    var searchId = getId()
    val anAppointment = searchAppointments(searchId)

    if(anAppointment != null){
        t.print(red("Enter a new type for "+anAppointment.type+" : "))
        anAppointment.type = readLine()!!
        t.print(red("Enter a new date for "+anAppointment.date+" : "))
        anAppointment.date = readLine()!!
        t.print(red("Enter a new doctor name for "+anAppointment.doctorName+" : "))
        anAppointment.doctorName = readLine()!!
        t.print(red("Enter a new location for "+anAppointment.location+" : "))
        anAppointment.location = readLine()!!
        t.println(green(
                "Appointment updated to "+anAppointment.type+", "+anAppointment.date+", "
                        +anAppointment.doctorName+", "+anAppointment.location+"."
        ))
    } else {
        t.println(error("Appointment not added."))
    }
}

fun addPrescription(){

    println("Create prescriptions to be implemented!")
}

fun updatePrescription(){

    println("Update prescriptions to be implemented!")
}

fun listAppointments(){
    println("Listing appointments...")
    println()
    appointments.forEach{t.println(green("$it"))}
}

fun listPrescriptions(){

    println("List prescriptions to be implemented!")
}

fun getId() : Long {

    var strId : String?
    var searchId : Long
    print("Enter the ID of the appointment/ prescription: ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun searchAppointments(id: Long) : Appointment?{
    return appointments.find { a -> a.id == id }
}