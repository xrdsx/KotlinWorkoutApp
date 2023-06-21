package globaltrans.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import globaltrans.activity.Model.Exercise
import globaltrans.activity.Model.Training
import globaltrans.activity.Model.TrainingExercise
import globaltrans.activity.R

class AddTrainingActivity : AppCompatActivity() {
    private lateinit var trainingNameEditText: EditText
    private lateinit var exerciseSpinner: Spinner
    private lateinit var setsEditText: EditText
    private lateinit var repsEditText: EditText
    private lateinit var addExerciseButton: Button
    private lateinit var submitTrainingButton: Button
    private lateinit var exercisesListView: ListView
    private lateinit var exercisesAdapter: ArrayAdapter<String>
    private val exercisesInTraining = mutableListOf<TrainingExercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_training)

        // Show back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        trainingNameEditText = findViewById(R.id.trainingNameEditText)
        exerciseSpinner = findViewById(R.id.exerciseSpinner)
        setsEditText = findViewById(R.id.setsEditText)
        repsEditText = findViewById(R.id.repsEditText)
        addExerciseButton = findViewById(R.id.addExerciseButton)
        submitTrainingButton = findViewById(R.id.submitTrainingButton)

        // Fill Spinner with exercise names
        val exerciseNames = Exercise.exerciseList.map { it.nameOfExercise }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, exerciseNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        exerciseSpinner.adapter = adapter
        exercisesListView = findViewById(R.id.exercisesListView)
        exercisesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        exercisesListView.adapter = exercisesAdapter

        addExerciseButton.setOnClickListener { addExerciseToTraining() }
        submitTrainingButton.setOnClickListener { submitTraining() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addExerciseToTraining() {
        val exerciseName = exerciseSpinner.selectedItem.toString()
        val sets = setsEditText.text.toString().toIntOrNull()
        val reps = repsEditText.text.toString().toIntOrNull()

        if (sets == null || reps == null) {
            Toast.makeText(this, "Please enter valid sets and reps", Toast.LENGTH_SHORT).show()
            return
        }

        val exercise = Exercise.exerciseList.find { it.nameOfExercise == exerciseName }
        if (exercise != null) {
            val trainingExercise = TrainingExercise(exercise, sets, reps)
            exercisesInTraining.add(trainingExercise)

            val exerciseDescription = "${exercise.nameOfExercise} - serie: $sets, powtórzenia: $reps"
            exercisesAdapter.add(exerciseDescription)
            exercisesAdapter.notifyDataSetChanged()
            Toast.makeText(this, "$exerciseName added to the training", Toast.LENGTH_SHORT).show()
            setsEditText.text.clear()
            repsEditText.text.clear()
        }
    }

    private fun submitTraining() {
        val nameOfTraining = trainingNameEditText.text.toString()
        if (nameOfTraining.isEmpty()) {
            Toast.makeText(this, "Please enter a training name", Toast.LENGTH_SHORT).show()
            return
        }

        val newTraining = Training(Training.trainingList.size + 1, nameOfTraining, exercisesInTraining)
        Training.trainingList.add(newTraining)

        Toast.makeText(this, "Training submitted", Toast.LENGTH_SHORT).show()
        finish()
    }
}