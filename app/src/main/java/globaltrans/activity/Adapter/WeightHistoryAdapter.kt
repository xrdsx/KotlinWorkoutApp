package globaltrans.activity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import globaltrans.activity.Model.WeightUser
import globaltrans.activity.R
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Int
import kotlin.math.absoluteValue


class WeightHistoryAdapter(private val weightHistory: List<WeightUser>) : RecyclerView.Adapter<WeightHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val weightTextView: TextView = view.findViewById(R.id.weightTextView)
        val weightDifferenceTextView: TextView = view.findViewById(R.id.weightDifferenceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weight_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weightHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weightEntry = weightHistory[position]
        holder.dateTextView.text = SimpleDateFormat("dd.MM.yyyy").format(weightEntry.date)
        holder.weightTextView.text = weightEntry.weight.toString()
        if (weightEntry.weightDifference != null) {
            holder.weightDifferenceTextView.text = when {
                weightEntry.weightDifference!! > 0f -> "+ ${weightEntry.weightDifference}"
                else -> "- ${weightEntry.weightDifference?.absoluteValue}"
            }
        } else {
            holder.weightDifferenceTextView.text = ""
        }
    }
}