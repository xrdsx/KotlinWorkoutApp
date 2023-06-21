package globaltrans.activity.Model

import java.util.*

data class TrainingHistoryEntry(
    val id: Int,
    val userId: Int,
    val date: Date,
    val training: Training
)