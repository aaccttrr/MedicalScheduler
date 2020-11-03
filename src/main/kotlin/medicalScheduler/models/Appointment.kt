package medicalScheduler.models

data class Appointment(
    var type: String = "",
    var date: String = "",
    var doctorName: String = "",
    var location: String = ""
)