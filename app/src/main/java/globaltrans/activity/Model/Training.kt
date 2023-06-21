package globaltrans.activity.Model

data class Training(
    var id: Int = 0,
    val nameOfTraining: String,
    val exercises: MutableList<TrainingExercise> = mutableListOf(),
    var completedCount: Int = 0,
    val totalExerciseCount: Int = exercises.size
){
    companion object {
        val trainingList = mutableListOf<Training>()

        init {
            val trainingExercise1 = TrainingExercise(Exercise.exerciseList[0], 3, 10)
            val trainingExercise2 = TrainingExercise(Exercise.exerciseList[1], 2, 15)
            val trainingExercise3 = TrainingExercise(Exercise.exerciseList[2], 4, 8)

            val training1 = Training(1, "Trening 1", mutableListOf(trainingExercise1, trainingExercise2))
            val training2 = Training(2, "Trening 2", mutableListOf(trainingExercise3))

            trainingList.add(training1)
            trainingList.add(training2)
        }

        fun createTraining(nameOfTraining: String) {
            val newId = trainingList.size + 1
            val newTraining = Training(newId, nameOfTraining)
            trainingList.add(newTraining)
        }

        fun addExerciseToTraining(trainingId: Int, exercise: Exercise, sets: Int, reps: Int) {
            val trainingExercise = TrainingExercise(exercise, sets, reps)
            trainingList.find { it.id == trainingId }?.exercises?.add(trainingExercise)
        }
    }
}