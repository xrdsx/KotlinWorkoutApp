package globaltrans.activity.Model

import java.util.Date

data class ScheduledTraining(
    val training: Training,
    val date: Date,
    var isStarted: Boolean = false,
    var isCompleted: Boolean = false
)