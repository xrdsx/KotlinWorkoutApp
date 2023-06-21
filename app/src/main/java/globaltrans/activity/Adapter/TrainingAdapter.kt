package globaltrans.activity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import globaltrans.activity.Model.Training
import globaltrans.activity.R

class TrainingAdapter(private val context: Context, private val dataSource: List<Training>) : ArrayAdapter<Training>(context, R.layout.list_item_training, dataSource) {

    private class ViewHolder {
        lateinit var trainingNameTextView: TextView
        lateinit var exercisesLinearLayout: LinearLayout
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.list_item_training, parent, false)
            viewHolder = ViewHolder()
            viewHolder.trainingNameTextView = view.findViewById(R.id.trainingNameTextView)
            viewHolder.exercisesLinearLayout = view.findViewById(R.id.exercisesLinearLayout)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val training = getItem(position) as Training
        viewHolder.trainingNameTextView.text = training.nameOfTraining

        // Clear the previous exercises
        viewHolder.exercisesLinearLayout.removeAllViews()

        // Add a TextView for each exercise
        for (exercise in training.exercises) {
            val exerciseTextView = TextView(context)
            exerciseTextView.text = "${exercise.exercise.nameOfExercise} - serie: ${exercise.sets}, powtórzenia: ${exercise.reps}"
            viewHolder.exercisesLinearLayout.addView(exerciseTextView)
        }

        return view
    }
}