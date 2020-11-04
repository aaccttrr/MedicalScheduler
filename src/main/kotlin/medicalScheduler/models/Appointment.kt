package medicalScheduler.models

data class Appointment(
    var id: Long =0,
    var type: String = "",
    var date: String = "",
    var doctorName: String = "",
    var location: String = ""
)