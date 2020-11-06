package medicalScheduler.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AppointmentMemStore: AppointmentStore{

    val appointments = ArrayList<AppointmentModel>()

    override fun findAll(): List<AppointmentModel>{
        return appointments
    }

    //find appointment by ID
    override fun findOne(id: Long): AppointmentModel? {
        var foundAppointment: AppointmentModel? = appointments.find{a -> a.id == id}
        return foundAppointment
    }

    override fun create(appointment: AppointmentModel){
        appointment.id = getId()
        appointments.add(appointment)
    }

    override fun update(appointment: AppointmentModel) {
        var foundAppointment = findOne(appointment.id!!)
        if(foundAppointment != null){
            foundAppointment.type = appointment.type
            foundAppointment.date = appointment.date
            foundAppointment.doctorName = appointment.doctorName
            foundAppointment.location = appointment.location
        }
    }

    internal fun logAll(){
        appointments.forEach{println("${it}")}
        println()
    }
}