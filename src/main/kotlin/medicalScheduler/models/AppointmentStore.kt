package medicalScheduler.models

interface AppointmentStore {
    fun findAll(): List<AppointmentModel>
    fun findOne(id: Long): AppointmentModel?
    fun create(appointment: AppointmentModel)
    fun update(appointment: AppointmentModel)
    fun delete(appointment: AppointmentModel)
}