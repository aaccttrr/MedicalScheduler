package medicalScheduler.models

data class Prescription(
    var drug: String = "",
    var timesDaily: Int,
    var expiryDate: String = ""
)