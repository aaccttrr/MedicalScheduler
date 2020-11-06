package medicalScheduler.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import medicalScheduler.helpers.*
import java.util.*

val JSON_FILE = "appointments.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AppointmentModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AppointmentJSONStore: AppointmentStore{

    var appointments = mutableListOf<AppointmentModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AppointmentModel>{
        return appointments
    }

    //find appointment by ID
    override fun findOne(id: Long): AppointmentModel? {
        var foundAppointment: AppointmentModel? = appointments.find{a -> a.id == id}
        return foundAppointment
    }

    override fun create(appointment: AppointmentModel){
        appointment.id = generateRandomId()
        appointments.add(appointment)
        serialize()
    }

    override fun update(appointment: AppointmentModel) {
        var foundAppointment = findOne(appointment.id!!)
        if(foundAppointment != null){
            foundAppointment.type = appointment.type
            foundAppointment.date = appointment.date
            foundAppointment.doctorName = appointment.doctorName
            foundAppointment.location = appointment.location
        }
        serialize()
    }

    internal fun logAll(){
        appointments.forEach{println("${it}")}
        println()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(appointments, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        appointments = Gson().fromJson(jsonString, listType)
    }
}