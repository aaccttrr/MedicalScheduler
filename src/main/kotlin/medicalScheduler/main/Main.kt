package medicalScheduler.main

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.models.*
import medicalScheduler.models.getId
import medicalScheduler.views.MenuView


val t = Terminal()
private val logger = KotlinLogging.logger {}

//Text styles for medicalScheduler.main.menu
val title = (bold+green+underline)
val option = (yellow)
val error = (brightYellow on red)

var appointment = AppointmentModel()
val appointments = AppointmentMemStore()

var prescription = PrescriptionModel()
val prescriptions = PrescriptionMemStore()

val menuView = MenuView()


fun main(args: Array<String>){
    logger.info { "Launching Medical Scheduler App" }

    var input: Int

    do {
        input= menuView.menu()
        when(input){
            1 -> addAppointment()
            2 -> updateAppointment()
            3 -> addPrescription()
            4 -> updatePrescription()
            5 -> menuView.listAppointments(appointments)
            6 -> menuView.listPrescriptions(prescriptions)
            7 -> t.println(brightRed("Exiting..."))
            else -> t.println(error("Invalid option! Please enter a valid number."))
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

    var anAppointment = AppointmentModel()

    if(menuView.addAppointmentData(anAppointment))
        appointments.create(anAppointment)
    else
        t.println(error("Appointment not added"))

}

//Updating appointments function
fun updateAppointment(){

    menuView.listAppointments(appointments)
    var searchId = menuView.getId()
    val anAppointment = searchAppointments(searchId)

    if(anAppointment != null){
        if(menuView.updateAppointmentData(anAppointment)){
            appointments.update(anAppointment)
            menuView.showAppointment(anAppointment)
            t.println(green("Appointment updated to " + anAppointment.type + ", " + anAppointment.date + ", "
                            + anAppointment.doctorName + ", " + anAppointment.location + "."))
        }
        else
            t.println(error("Appointment not updated."))
    }
    else
        t.println(error("Appointment not updated."))

}

fun addPrescription(){

    var aPrescription = PrescriptionModel()

    if(menuView.addPrescriptionData(aPrescription))
        prescriptions.create(aPrescription)
    else
        t.println(error("Prescription not added"))

}

fun updatePrescription(){

    menuView.listPrescriptions(prescriptions)
    var searchId = menuView.getId()
    val aPrescription = searchPrescriptions(searchId)

    if(aPrescription != null){
        if(menuView.updatePrescriptionData(aPrescription)){
            prescriptions.update(aPrescription)
            menuView.showPrescription(aPrescription)
            t.println(green("Prescription updated to : "+aPrescription.drug+", taken " +aPrescription.timesDaily+" times daily, expires on "+aPrescription.expiryDate+"."))
        }
        else
            t.println(error("Prescription not updated."))
    }
    else
        t.println(error("Prescription not updated."))


}

fun searchAppointments(id: Long) : AppointmentModel?{
    return appointments.findOne(id)
}

fun searchPrescriptions(id: Long): PrescriptionModel?{
    return prescriptions.findOne(id)
}
