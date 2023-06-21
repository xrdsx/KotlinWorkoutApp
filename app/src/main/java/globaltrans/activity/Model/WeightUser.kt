package globaltrans.activity.Model

import java.util.*

data class WeightUser(
    var weightId: Int,
    var userId: Int,
    var date: Date,
    var weight: Float
) {
    var previousWeight: Float? = null

    val weightDifference: Float?
        get() {
            return if (previousWeight != null) weight - previousWeight!! else null
        }
}