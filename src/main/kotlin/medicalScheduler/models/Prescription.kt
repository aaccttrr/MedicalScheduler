package medicalScheduler.models

data class Prescription(
    var id: Long = 0,
    var drug: String = "",
    var timesDaily: Int = 0,
    var expiryDate: String = ""
)