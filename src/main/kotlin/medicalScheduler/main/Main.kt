package medicalScheduler.main

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.models.AppointmentMemStore
import medicalScheduler.models.AppointmentModel
import medicalScheduler.models.PrescriptionMemStore
import medicalScheduler.models.PrescriptionModel

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
        appointments.create(appointment.copy())
        t.println(green("Appointment added : "+ appointment.type +" on "+ appointment.date +" with "+ appointment.doctorName +" in "+ appointment.location +"."))
    } else {
        println("Appointment not added.")
    }
}

//Updating appointments function
fun updateAppointment(){

    t.println(title("Update Appointment"))
    println()
    listAppointments()
    var searchId = getId()
    val anAppointment = searchAppointments(searchId)
    var tempType : String?
    var tempDate : String?
    var tempDoc : String?
    var tempAdrs : String?

    if(anAppointment != null){
        t.print(red("Enter a new type for "+anAppointment.type+" : "))
        tempType = readLine()!!
        t.print(red("Enter a new date for "+anAppointment.date+" : "))
        tempDate = readLine()!!
        t.print(red("Enter a new doctor name for "+anAppointment.doctorName+" : "))
        tempDoc = readLine()!!
        t.print(red("Enter a new location for "+anAppointment.location+" : "))
        tempAdrs = readLine()!!

        if(tempType.isNotEmpty() && tempDate.isNotEmpty() && tempDoc.isNotEmpty() && tempAdrs.isNotEmpty()) {
            anAppointment.type = tempType
            anAppointment.date = tempDate
            anAppointment.doctorName = tempDoc
            anAppointment.location = tempAdrs
            t.println(green(
                    "Appointment updated to " + anAppointment.type + ", " + anAppointment.date + ", "
                            + anAppointment.doctorName + ", " + anAppointment.location + "."
            ))
        } else {
            t.println(error("Appointment not updated."))
        }

    } else {
        t.println(error("Appointment not updated."))
    }
}

fun addPrescription(){

    t.println(title("Add Prescription"))
    t.print("Enter the name of the prescribed drug: ")
    prescription.drug = readLine()!!
    t.print("Enter the times it must be taken daily: ")
    prescription.timesDaily = readLine()!!
    t.print("Enter the expiry date: ")
    prescription.expiryDate = readLine()!!

    if(prescription.drug.isNotEmpty() && prescription.timesDaily.isNotEmpty() && prescription.expiryDate.isNotEmpty()) {
        prescriptions.create(prescription.copy())
        t.println(green("Prescription added : "+ prescription.drug +" taken "+ prescription.timesDaily +" times daily. Expires on "+ prescription.expiryDate +"."))
    } else {
        println("Prescription not added.")
    }
}

fun updatePrescription(){

    t.println(title("Update Prescription"))
    println()
    listPrescriptions()
    var searchId = getId()
    val aPrescription = searchPrescriptions(searchId)
    var tempDrug : String?
    var tempTimesDaily : String?
    var tempExpiry : String?

    if(aPrescription != null) {
        t.print(red("Enter a new drug for " + aPrescription.drug + " : "))
        tempDrug = readLine()!!
        t.print(red("Enter a new amount to take daily instead of " + aPrescription.timesDaily + " : "))
        tempTimesDaily = readLine()!!
        t.print(red("Enter a new expiry date instead of " + aPrescription.expiryDate + " : "))
        tempExpiry = readLine()!!

        if(tempDrug.isNotEmpty() && tempTimesDaily.isNotEmpty() && tempExpiry.isNotEmpty()){
            aPrescription.drug = tempDrug
            aPrescription.timesDaily = tempTimesDaily
            aPrescription.expiryDate = tempExpiry
            t.println(green("Prescription updated to : "+aPrescription.drug+", taken "
                    +aPrescription.timesDaily+" times daily, expires on "+aPrescription.expiryDate+"."))
        } else {
            t.println(error("Prescription not updated."))
        }
    } else{
        t.println(error("Prescription not updated."))
    }


}

fun listAppointments(){
    println("Listing appointments...")
    println()
    println(appointments.findAll())
}

fun listPrescriptions(){

    println("Listing prescriptions...")
    println()
    println(prescriptions.findAll())
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

fun searchAppointments(id: Long) : AppointmentModel?{
    return appointments.findOne(id)
}

fun searchPrescriptions(id: Long): PrescriptionModel?{
    return prescriptions.findOne(id)
}