package globaltrans.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import globaltrans.activity.Adapter.TrainingAdapter
import globaltrans.activity.Model.Training
import globaltrans.activity.Model.Training.Companion.trainingList
import globaltrans.activity.R

class TrainingActivity : AppCompatActivity() {

    private lateinit var trainingAdapter: TrainingAdapter
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        val userId = intent.getIntExtra("USER_ID", -1)   // Get userId from Intent
        val userTrainings = trainingList.filter { it.userId == userId }

        // initialize Button and ListView
        val addButton: Button = findViewById(R.id.addTrainingButton)
        listView = findViewById(R.id.trainingList)

        // create an TrainingAdapter to link your ListView with the Training data
        trainingAdapter = TrainingAdapter(this, userTrainings)
        // set adapter to the ListView
        listView.adapter = trainingAdapter

        // handle button click to open AddTrainingActivity
        addButton.setOnClickListener {
            val intent = Intent(this, AddTrainingActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        val userId = intent.getIntExtra("USER_ID", -1)   // Get userId from Intent
        val userTrainings = trainingList.filter { it.userId == userId }

        // create an TrainingAdapter to link your ListView with the Training data
        trainingAdapter = TrainingAdapter(this, userTrainings)
        // set adapter to the ListView
        listView.adapter = trainingAdapter

        // notify ListView to update its view because the data has changed
        trainingAdapter.notifyDataSetChanged()

    }
}