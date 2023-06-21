package globaltrans.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import globaltrans.activity.Model.User

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val tvTrainingProgress = findViewById<TextView>(R.id.tvTrainingProgress)
        val tvGoals = findViewById<TextView>(R.id.tvGoals)

        val loggedInUser = User.allUsers[0]  // get the logged in user, this is just a placeholder

        // Show training progress
        val completedTrainings = loggedInUser.trainingHistory.filter { it.training.completedCount > 0 }
        val trainingProgressStr = completedTrainings.joinToString("\n") {
            "Trening ${it.training.nameOfTraining}, został zrobiony ${it.training.completedCount} "
        }
        tvTrainingProgress.text = trainingProgressStr

        // Show goals
        val goalsStr = loggedInUser.goals.joinToString("\n") {
            "Goal: ${it.type.name}, Target: ${it.target}, Progress: ${it.progress}"
        }
        tvGoals.text = goalsStr
    }
}