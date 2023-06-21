package globaltrans.activity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnRegisterUsers = findViewById<Button>(R.id.btnRegisterUsers)
        btnRegisterUsers.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnExerciseActivity = findViewById<Button>(R.id.btnExerciseActivity)
        btnExerciseActivity.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        val btnAddTraining = findViewById<Button>(R.id.btnAddTraining)
        btnAddTraining.setOnClickListener {
            val intent = Intent(this, TrainingActivity::class.java)
            startActivity(intent)
        }


        val btnTrackWeightActivity = findViewById<Button>(R.id.btnTrackWeightActivity)
        btnTrackWeightActivity.setOnClickListener {
            val intent = Intent(this, TrackWeightActivity::class.java)
            startActivity(intent)
        }

        val btnPlanTraining = findViewById<Button>(R.id.btnPlanTraining)
        btnPlanTraining.setOnClickListener {
            val intent = Intent(this, PlanTrainingActivity::class.java)
            startActivity(intent)
        }

        val btnShareProgress: Button = findViewById(R.id.btnShareProgress)

        btnShareProgress.setOnClickListener {
            val intent = Intent(this, ShareProgressActivity::class.java)
            startActivity(intent)
        }

        val btnTrackProgress = findViewById<Button>(R.id.btnTrackProgress)
        btnTrackProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }








    }
}