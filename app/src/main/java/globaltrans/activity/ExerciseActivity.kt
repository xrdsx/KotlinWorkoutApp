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

        nameOfExerciseEditText = findViewById(R.id.etNameOfExercise)
        createExerciseButton = findViewById(R.id.btnCreateExercise)
        exerciseListRecyclerView = findViewById(R.id.rvExerciseList)

        exerciseAdapter = ExerciseAdapter(Exercise.exerciseList)
        exerciseListRecyclerView.layoutManager = LinearLayoutManager(this)
        exerciseListRecyclerView.adapter = exerciseAdapter

        createExerciseButton.setOnClickListener {
            val nameOfExercise = nameOfExerciseEditText.text.toString()

            // Przykładowe wykorzystanie enuma PartBody do tworzenia ćwiczenia
            Exercise.createExercise(nameOfExercise)

            exerciseAdapter.notifyDataSetChanged()
        }
    }
}