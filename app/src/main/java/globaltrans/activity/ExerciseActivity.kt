package globaltrans.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import globaltrans.activity.Model.Exercise
import globaltrans.activity.Adapter.ExerciseAdapter

class ExerciseActivity : AppCompatActivity() {

    private lateinit var nameOfExerciseEditText: EditText
    private lateinit var createExerciseButton: Button
    private lateinit var exerciseListRecyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val userId = intent.getIntExtra("USER_ID", -1)   // Get userId from Intent
        val userExercises = Exercise.exerciseList.filter { it.userId == userId }.toMutableList()

        nameOfExerciseEditText = findViewById(R.id.etNameOfExercise)
        createExerciseButton = findViewById(R.id.btnCreateExercise)
        exerciseListRecyclerView = findViewById(R.id.rvExerciseList)

        exerciseAdapter = ExerciseAdapter(userExercises)
        exerciseListRecyclerView.layoutManager = LinearLayoutManager(this)
        exerciseListRecyclerView.adapter = exerciseAdapter

        createExerciseButton.setOnClickListener {
            val nameOfExercise = nameOfExerciseEditText.text.toString()

            // Create exercise for this user
            Exercise.createExercise(userId, nameOfExercise)

            // Update the list of exercises for this user
            exerciseAdapter.exerciseList = Exercise.exerciseList.filter { it.userId == userId }.toMutableList()
            exerciseAdapter.notifyDataSetChanged()
        }
    }
}