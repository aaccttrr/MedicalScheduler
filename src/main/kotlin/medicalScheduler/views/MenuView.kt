package medicalScheduler.views

import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.main.option
import medicalScheduler.main.t
import medicalScheduler.main.title
import medicalScheduler.main.appointment
import medicalScheduler.main.appointments
import medicalScheduler.main.prescription
import medicalScheduler.main.prescriptions
import medicalScheduler.models.AppointmentModel
import medicalScheduler.models.AppointmentMemStore
import medicalScheduler.models.PrescriptionModel
import medicalScheduler.models.PrescriptionMemStore

val t = Terminal()

class MenuView {

    fun menu(): Int {

        var option: Int
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

    fun listAppointments(appointments: AppointmentMemStore) {
        t.println(green("Listing Appointments"))
        appointments.logAll()
        println()
    }

    fun listPrescriptions(prescriptions: PrescriptionMemStore) {
        t.println(green("Listing Prescriptions"))
        println()
        prescriptions.logAll()
        println()
    }

    fun showAppointment(appointment: AppointmentModel) {
        if (appointment != null)
            t.println(green("Appointment Details: [$appointment]"))
        else
            t.println(red("Appointment not found."))
    }

    fun showPrescription(prescription: PrescriptionModel) {
        if (prescription != null)
            t.println(green("Prescription Details: [$prescription]"))
        else
            t.println(red("Prescription not found."))
    }

    fun addAppointmentData(appointment: AppointmentModel): Boolean {
        t.println(title("Add Appointment"))
        t.print("Enter the type of appointment: ")
        appointment.type = readLine()!!
        t.print("Enter the appointment date: ")
        appointment.date = readLine()!!
        t.print("Enter the doctor's name: ")
        appointment.doctorName = readLine()!!
        t.print("Enter the location of the appointment: ")
        appointment.location = readLine()!!

        return appointment.type.isNotEmpty() && appointment.date.isNotEmpty() && appointment.doctorName.isNotEmpty() && appointment.location.isNotEmpty()

    }

    fun addPrescriptionData(prescription: PrescriptionModel): Boolean {
        t.println(title("Add Prescription"))
        t.print("Enter the name of the prescribed drug: ")
        prescription.drug = readLine()!!
        t.print("Enter the times it must be taken daily: ")
        prescription.timesDaily = readLine()!!
        t.print("Enter the expiry date: ")
        prescription.expiryDate = readLine()!!

        return prescription.drug.isNotEmpty() && prescription.timesDaily.isNotEmpty() && prescription.expiryDate.isNotEmpty()
    }

    fun updateAppointmentData(appointment: AppointmentModel): Boolean {
        var tempType: String?
        var tempDate: String?
        var tempDoc: String?
        var tempAdrs: String?

        if (appointment != null) {
            t.print(red("Enter a new type for " + appointment.type + " : "))
            tempType = readLine()!!
            t.print(red("Enter a new date for " + appointment.date + " : "))
            tempDate = readLine()!!
            t.print(red("Enter a new doctor name for " + appointment.doctorName + " : "))
            tempDoc = readLine()!!
            t.print(red("Enter a new location for " + appointment.location + " : "))
            tempAdrs = readLine()!!

            if (tempType.isNotEmpty() && tempDate.isNotEmpty() && tempDoc.isNotEmpty() && tempAdrs.isNotEmpty()) {
                appointment.type = tempType
                appointment.date = tempDate
                appointment.doctorName = tempDoc
                appointment.location = tempAdrs
                return true
            }
        }
        return false
    }

    fun updatePrescriptionData(prescription: PrescriptionModel): Boolean{
        var tempDrug : String?
        var tempTimesDaily : String?
        var tempExpiry : String?

        if(prescription != null) {
            t.print(red("Enter a new drug for " + prescription.drug + " : "))
            tempDrug = readLine()!!
            t.print(red("Enter a new amount to take daily instead of " + prescription.timesDaily + " : "))
            tempTimesDaily = readLine()!!
            t.print(red("Enter a new expiry date instead of " + prescription.expiryDate + " : "))
            tempExpiry = readLine()!!

            if(tempDrug.isNotEmpty() && tempTimesDaily.isNotEmpty() && tempExpiry.isNotEmpty()) {
                prescription.drug = tempDrug
                prescription.timesDaily = tempTimesDaily
                prescription.expiryDate = tempExpiry
                return true
            }
        }
        return false
    }

    fun getId(): Long{
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

}