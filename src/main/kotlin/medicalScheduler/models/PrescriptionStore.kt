package medicalScheduler.models

interface PrescriptionStore {
    fun findAll(): List<PrescriptionModel>
    fun findOne(id: Long): PrescriptionModel?
    fun create(prescription: PrescriptionModel)
    fun update(prescription: PrescriptionModel)
}