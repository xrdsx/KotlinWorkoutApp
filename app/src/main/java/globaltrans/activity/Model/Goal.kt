package globaltrans.activity.Model

data class Goal(
    val id: Int,
    val userId: Int,
    val type: GoalType,
    val target: Int,
    var progress: Int = 0
)