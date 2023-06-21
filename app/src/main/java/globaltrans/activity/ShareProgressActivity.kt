package globaltrans.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import globaltrans.activity.Model.User

class ShareProgressActivity : AppCompatActivity() {
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_progress)
        shareButton = findViewById(R.id.shareButton)

        shareButton.setOnClickListener {
            // Get the user ID from the intent extras
            val userId = intent.getIntExtra("USER_ID", -1)
            val loggedInUser = User.allUsers.find { it.id == userId }

            // Ensure that the logged in user is not null
            if (loggedInUser != null) {
                val lastWeight = loggedInUser.weightHistory.lastOrNull()?.weight
                val lastTraining = loggedInUser.trainingHistory.lastOrNull()?.training?.nameOfTraining

                val shareText = "Mój najnowszy postęp:\n" +
                        "Moja ostatnia waga: $lastWeight kg\n" +
                        "Mój ostatni trening: $lastTraining"

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Podziel się swoim postępem"))
            } else {
                // Handle case where logged in user is null
            }
        }
    }
}