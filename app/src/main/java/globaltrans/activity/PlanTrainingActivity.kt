package globaltrans.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import globaltrans.activity.Model.*
import java.text.SimpleDateFormat
import java.util.*

class PlanTrainingActivity : AppCompatActivity() {

    private lateinit var trainingSpinner: Spinner
    private lateinit var pickDateButton: Button
    private lateinit var scheduleButton: Button
    private lateinit var scheduledTrainingsListView: ListView

    private lateinit var toggleVisibilityButton: Button
    private var isVisible = true

    private var selectedDate: Date? = null

    private val scheduledTrainings = ScheduledTrainingsManager.scheduledTrainings
    private val availableTrainings = Training.trainingList.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_training)

        trainingSpinner = findViewById(R.id.trainingSpinner)
        pickDateButton = findViewById(R.id.pickDateButton)
        scheduleButton = findViewById(R.id.scheduleButton)
        scheduledTrainingsListView = findViewById(R.id.scheduledTrainingsListView)

        toggleVisibilityButton = findViewById(R.id.toggleVisibilityButton)

        toggleVisibilityButton.setOnClickListener {
            isVisible = !isVisible
            updateScheduledTrainingsListView()
        }

        updateTrainingSpinner()
        updateScheduledTrainingsListView()

        pickDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _: DatePicker, year: Int, month: Int, day: Int ->
                    calendar.set(year, month, day)
                    selectedDate = calendar.time
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        scheduleButton.setOnClickListener {
            val selectedTrainingName = trainingSpinner.selectedItem as String
            val selectedTraining =
                availableTrainings.find { it.nameOfTraining == selectedTrainingName }

            if (selectedTraining != null && selectedDate != null) {
                val newScheduledTraining = ScheduledTraining(selectedTraining, selectedDate!!)
                scheduledTrainings.add(newScheduledTraining)
                availableTrainings.remove(selectedTraining)
                updateScheduledTrainingsListView()
                updateTrainingSpinner()
            }
        }

    }

    private fun updateTrainingSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            availableTrainings.map { it.nameOfTraining }
        )
        trainingSpinner.adapter = adapter
    }
    private fun showTrainingInfo(scheduledTraining: ScheduledTraining) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(scheduledTraining.training.nameOfTraining)

        val exercisesInfo = StringBuilder()
        for (exercise in scheduledTraining.training.exercises) {
            exercisesInfo.append("Cwiczenie: ${exercise.exercise.nameOfExercise}\n")
            exercisesInfo.append("Serii: ${exercise.sets}\n")
            exercisesInfo.append("Powtorzen: ${exercise.reps}\n\n")
        }

        builder.setMessage(exercisesInfo.toString())
        builder.setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    private fun updateScheduledTrainingsListView() {
        val adapter = object : ArrayAdapter<ScheduledTraining>(
            this,
            R.layout.scheduled_training_list_item,
            if (isVisible) scheduledTrainings else emptyList()
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.scheduled_training_list_item, parent, false)

                val trainingNameTextView = view.findViewById<TextView>(R.id.trainingNameTextView)
                val trainingDateTextView = view.findViewById<TextView>(R.id.trainingDateTextView)
                val startTrainingButton = view.findViewById<Button>(R.id.startTrainingButton)
                val finishTrainingButton = view.findViewById<Button>(R.id.finishTrainingButton)
                val infoButton = view.findViewById<Button>(R.id.infoButton)

                val scheduledTraining = getItem(position)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                trainingNameTextView.text = scheduledTraining?.training?.nameOfTraining
                trainingDateTextView.text = scheduledTraining?.date?.let { dateFormat.format(it) }

                startTrainingButton.setOnClickListener {
                    scheduledTraining?.let { it.isStarted = true }
                    notifyDataSetChanged()
                }

                finishTrainingButton.setOnClickListener {
                    val loggedInUser = User.allUsers[0]
                    scheduledTraining?.let {
                        if (it.isStarted) {
                            it.isCompleted = true
                            it.training.completedCount++ // Increment the completed count

                            // Add the training back to the available list
                            availableTrainings.add(it.training)

                            // Create new history entry
                            val newHistoryEntry = TrainingHistoryEntry(
                                id = loggedInUser.trainingHistory.size + 1,
                                userId = loggedInUser.id,
                                date = Date(),
                                training = it.training
                            )

                            // Add new history entry to user's training history
                            loggedInUser.trainingHistory.add(newHistoryEntry)

                            // Update training completion goals
                            loggedInUser.goals.filter { it.type == GoalType.TrainingCompletion }.forEach { goal ->
                                goal.progress++
                            }

                            // Update the spinner to reflect this change
                            updateTrainingSpinner()
                        }
                    }
                    notifyDataSetChanged()
                }

                infoButton.setOnClickListener {
                    scheduledTraining?.let { showTrainingInfo(it) }
                }

                if (scheduledTraining != null) {
                    startTrainingButton.visibility =
                        if (scheduledTraining.isStarted || scheduledTraining.isCompleted) View.GONE else View.VISIBLE
                    finishTrainingButton.visibility =
                        if (!scheduledTraining.isStarted || scheduledTraining.isCompleted) View.GONE else View.VISIBLE
                }

                return view
            }
        }

        scheduledTrainingsListView.adapter = adapter
    }
}