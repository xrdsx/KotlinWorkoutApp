package globaltrans.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import globaltrans.activity.Model.User
import globaltrans.activity.Model.WeightUser
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val age = ageInput.text.toString().toIntOrNull() ?: 0
            val weight = weightInput.text.toString().toFloatOrNull() ?: 0f

            val newUser = User(User.allUsers.size + 1, email, password, name, age)
            newUser.weightHistory.add(WeightUser(newUser.weightHistory.size + 1, newUser.id, Date(), weight))
            if (newUser.register()) {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}