package globaltrans.activity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val userId = intent.getIntExtra("USER_ID", -1)



        val btnExerciseActivity = findViewById<Button>(R.id.btnExerciseActivity)
        btnExerciseActivity.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("USER_ID", userId)  // przekazujemy USER_ID do ExerciseActivity
            startActivity(intent)
        }

        val btnAddTraining = findViewById<Button>(R.id.btnAddTraining)
        btnAddTraining.setOnClickListener {
            val intent = Intent(this, TrainingActivity::class.java)
            intent.putExtra("USER_ID", userId)  // przekazujemy USER_ID do TrainingActivity
            startActivity(intent)
        }

        val btnTrackWeightActivity = findViewById<Button>(R.id.btnTrackWeightActivity)
        btnTrackWeightActivity.setOnClickListener {
            val intent = Intent(this, TrackWeightActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        val btnPlanTraining = findViewById<Button>(R.id.btnPlanTraining)
        btnPlanTraining.setOnClickListener {
            val intent = Intent(this, PlanTrainingActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        val btnShareProgress: Button = findViewById(R.id.btnShareProgress)

        btnShareProgress.setOnClickListener {
            val intent = Intent(this, ShareProgressActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        val btnTrackProgress = findViewById<Button>(R.id.btnTrackProgress)
        btnTrackProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // opcjonalnie, możemy usunąć wszystkie poprzednie aktywności ze stosu, aby użytkownik nie mógł wrócić do poprzedniej aktywności po wylogowaniu
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }








    }
}