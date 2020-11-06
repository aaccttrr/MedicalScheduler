package medicalScheduler.models

data class PrescriptionModel(
    var id: Long = 0,
    var drug: String = "",
    var timesDaily: String = "",
    var expiryDate: String = ""
)