package medicalScheduler.controllers

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.models.*
import medicalScheduler.views.MenuView

class MenuController {

    val title = (bold+green+underline)
    val option = (yellow)
    val error = (brightYellow on red)
    val t = Terminal()
    val appointments = AppointmentJSONStore()
    val prescriptions = PrescriptionJSONStore()
    val menuView = MenuView()
    val logger = KotlinLogging.logger{}

    fun start(){
        var input: Int

        do {
            input= menu()
            when(input){
                1 -> addAppointment()
                2 -> updateAppointment()
                3 -> addPrescription()
                4 -> updatePrescription()
                5 -> menuView.listAppointments(appointments)
                6 -> menuView.listPrescriptions(prescriptions)
                99 -> dummyData()
                7 -> t.println(brightRed("Exiting..."))
                else -> t.println(error("Invalid option! Please enter a valid number."))
            }
            println()
        } while(input != 7)
        //Print log and finish process
        logger.info { "Shutting Down Medical Scheduler..." }
    }

    fun menu(): Int{return menuView.menu()}

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

    fun dummyData(){
        appointments.create(AppointmentModel(type = "Dental", date = "12/03/2019", doctorName = "Joe Bloggs", location = "Waterford Regional Hospital"))
        appointments.create(AppointmentModel(type = "Physiotherapy", date = "7/05/2019", doctorName = "Lisa McDonald", location = "Lisa's Physio"))
        appointments.create(AppointmentModel(type = "Dermatologist's", date = "21/10/2019", doctorName = "Robert O' Neil", location = "Waterford Regional Hospital"))
        prescriptions.create(PrescriptionModel(drug = "Paracetamol", timesDaily = "3",expiryDate = "03/03/2016"))
        prescriptions.create(PrescriptionModel(drug = "Warfarin", timesDaily = "1",expiryDate = "20/12/2025"))
        prescriptions.create(PrescriptionModel(drug = "Nurofen", timesDaily = "4",expiryDate = "01/01/2022"))
    }
}