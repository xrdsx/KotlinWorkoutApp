package globaltrans.activity.Model

data class Exercise(
    var id: Int = 0,
    val userId: Int,
    val nameOfExercise: String
) {
    companion object {
        val exerciseList = mutableListOf<Exercise>().apply {
            // Początkowa lista ćwiczeń dla testowania (każdy użytkownik ma swoje ćwiczenia)
            add(Exercise(1, 1, "Wyciskanie sztangi"))
            add(Exercise(2, 1, "Martwy ciąg"))
            add(Exercise(3, 2, "Przysiady"))
            // Dodaj tu więcej ćwiczeń dla różnych części ciała
        }

        fun createExercise(userId: Int, nameOfExercise: String) {
            val newId = exerciseList.size + 1
            val newExercise = Exercise(newId, userId, nameOfExercise)
            exerciseList.add(newExercise)
        }
    }
}