package globaltrans.activity.Model

data class Training(
    var id: Int = 0,
    val userId: Int,   // New field
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

            // Adding a userId value to the trainings
            val training1 = Training(1, 1, "Trening 1", mutableListOf(trainingExercise1, trainingExercise2))
            val training2 = Training(2, 2, "Trening 2", mutableListOf(trainingExercise3))

            trainingList.add(training1)
            trainingList.add(training2)
        }


        fun addExerciseToTraining(trainingId: Int, exercise: Exercise, sets: Int, reps: Int) {
            val trainingExercise = TrainingExercise(exercise, sets, reps)
            trainingList.find { it.id == trainingId }?.exercises?.add(trainingExercise)
        }
    }
}