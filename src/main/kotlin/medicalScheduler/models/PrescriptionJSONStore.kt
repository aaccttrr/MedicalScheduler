package medicalScheduler.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import medicalScheduler.helpers.*
import java.util.*

val JSON_FILE2 = "prescription.json"
val gsonBuilder2 = GsonBuilder().setPrettyPrinting().create()
val listType2 = object : TypeToken<java.util.ArrayList<PrescriptionModel>>() {}.type

class PrescriptionJSONStore: PrescriptionStore{

    var prescriptions = mutableListOf<PrescriptionModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PrescriptionModel> {
        return prescriptions
    }

    override fun findOne(id: Long): PrescriptionModel? {
        var foundPrescription: PrescriptionModel? = prescriptions.find{p -> p.id == id}
        return foundPrescription
    }

    override fun create(prescription: PrescriptionModel) {
        prescription.id = generateRandomId()
        prescriptions.add(prescription)
    }

    override fun update(prescription: PrescriptionModel) {
        var foundPrescription = findOne(prescription.id!!)
        if(foundPrescription != null){
            foundPrescription.drug = prescription.drug
            foundPrescription.expiryDate = prescription.expiryDate
            foundPrescription.timesDaily = prescription.timesDaily
        }
        serialize()
    }

    internal fun logAll(){
        prescriptions.forEach{println("${it}")}
        println()
    }

    private fun serialize() {
        val jsonString = gsonBuilder2.toJson(prescriptions, listType2)
        write(JSON_FILE2, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        prescriptions = Gson().fromJson(jsonString, listType2)
    }
}