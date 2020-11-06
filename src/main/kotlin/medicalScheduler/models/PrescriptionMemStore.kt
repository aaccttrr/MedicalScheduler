package medicalScheduler.models

import medicalScheduler.main.appointment
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class PrescriptionMemStore: PrescriptionStore{

    val prescriptions = ArrayList<PrescriptionModel>()

    override fun findAll(): List<PrescriptionModel> {
        return prescriptions
    }

    override fun findOne(id: Long): PrescriptionModel? {
        var foundPrescription: PrescriptionModel? = prescriptions.find{p -> p.id == id}
        return foundPrescription
    }

    override fun create(prescription: PrescriptionModel) {
        prescription.id = getId()
        prescriptions.add(prescription)
    }

    override fun update(prescription: PrescriptionModel) {
        var foundPrescription = findOne(prescription.id!!)
        if(foundPrescription != null){
            foundPrescription.drug = prescription.drug
            foundPrescription.expiryDate = prescription.expiryDate
            foundPrescription.timesDaily = prescription.timesDaily
        }
    }

    internal fun logAll(){
        prescriptions.forEach{println("${it}")}
    }
}