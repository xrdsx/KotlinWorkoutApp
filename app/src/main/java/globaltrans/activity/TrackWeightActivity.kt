package globaltrans.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import globaltrans.activity.Adapter.WeightHistoryAdapter
import globaltrans.activity.Model.User
import globaltrans.activity.Model.WeightUser
import java.text.SimpleDateFormat
import java.util.*

class TrackWeightActivity : AppCompatActivity() {

    private lateinit var weightHistoryAdapter: WeightHistoryAdapter
    private lateinit var weightHistoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_weight)

        val currentUserTextView = findViewById<TextView>(R.id.currentUserTextView)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val submitWeightButton = findViewById<Button>(R.id.submitWeightButton)
        val pickDateButton = findViewById<Button>(R.id.pickDateButton)
        weightHistoryRecyclerView = findViewById(R.id.weightHistoryRecyclerView)
        val selectedDate = Calendar.getInstance()  // dodajemy to, żeby przechować wybraną datę

        // Tymczasowo używamy pierwszego użytkownika jako zalogowanego użytkownika
        val loggedInUser = User.allUsers[0]
        currentUserTextView.text = "Zalogowany jako: ${loggedInUser.name}"

        weightHistoryAdapter = WeightHistoryAdapter(loggedInUser.weightHistory)
        weightHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
        weightHistoryRecyclerView.adapter = weightHistoryAdapter

        pickDateButton.setOnClickListener {
            // Wywołaj DatePickerDialog tutaj
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                // Aktualizacja etykiety przycisku
                pickDateButton.text = SimpleDateFormat("dd.MM.yyyy").format(selectedDate.time)
            }
            DatePickerDialog(this, dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        submitWeightButton.setOnClickListener {
            val weight = weightInput.text.toString().toFloatOrNull()
            if (weight != null) {
                val newWeightEntry = WeightUser(loggedInUser.weightHistory.size + 1, loggedInUser.id, selectedDate.time, weight)
                newWeightEntry.previousWeight = loggedInUser.weightHistory.lastOrNull()?.weight
                loggedInUser.weightHistory.add(newWeightEntry)
                weightHistoryAdapter.notifyItemInserted(loggedInUser.weightHistory.size - 1)
                Toast.makeText(this, "Dodano nowy wpis wagi", Toast.LENGTH_SHORT).show()
                weightInput.text.clear()
            } else {
                Toast.makeText(this, "Wprowadź poprawną wagę", Toast.LENGTH_SHORT).show()
            }
        }
    }
}