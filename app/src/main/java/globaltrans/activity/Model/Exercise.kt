package globaltrans.activity.Model

data class Exercise(
    var id: Int = 0,

    val nameOfExercise: String
) {
    companion object {
        val exerciseList = mutableListOf<Exercise>().apply {
            add(Exercise(1, "Wyciskanie sztangi"))
            add(Exercise(2, "Martwy ciąg"))
            add(Exercise(3,  "Przysiady"))
            // Dodaj tu więcej ćwiczeń dla różnych części ciała
        }

        fun createExercise(nameOfExercise: String) {
            val newId = exerciseList.size + 1
            val newExercise = Exercise(newId, nameOfExercise)
            exerciseList.add(newExercise)
        }
    }
}