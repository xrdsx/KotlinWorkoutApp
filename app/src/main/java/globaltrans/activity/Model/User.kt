package globaltrans.activity.Model

import android.util.Patterns
import java.util.*

data class User(
    var id: Int,
    var email: String,
    var password: String,
    var name: String,
    var age: Int,
    var weightHistory: MutableList<WeightUser> = mutableListOf(),
    val trainingHistory: MutableList<TrainingHistoryEntry> = mutableListOf(),
    val goals: MutableList<Goal> = mutableListOf(),

) {
    companion object {
        val allUsers = mutableListOf<User>().apply {
            val admin = User(1, "admin@example.com", "admin123", "Admin", 25)
            admin.weightHistory.apply {
                add(WeightUser(1, admin.id, Date(), 70.0f))
                add(WeightUser(2, admin.id, Date(), 72.0f))
                add(WeightUser(3, admin.id, Date(), 71.0f))
            }

            val user = User(2, "user@example.com", "user123", "User", 23)
            user.weightHistory.apply {
                add(WeightUser(4, user.id, Date(), 65.0f))
                add(WeightUser(5, user.id, Date(), 67.0f))
                add(WeightUser(6, user.id, Date(), 66.0f))
            }

            val test = User(3, "test@example.com", "test123", "Test", 30)
            test.weightHistory.apply {
                add(WeightUser(7, test.id, Date(), 80.0f))
                add(WeightUser(8, test.id, Date(), 82.0f))
                add(WeightUser(9, test.id, Date(), 81.0f))
            }

            add(admin)
            add(user)
            add(test)

        }
    }

    fun register(): Boolean {
        // Prosta walidacja
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            println("Nieprawidłowy format email.")
            return false
        }
        if (password.length < 8) {
            println("Hasło musi mieć co najmniej 8 znaków.")
            return false
        }
        if (name.isBlank()) {
            println("Nazwa nie może być pusta.")
            return false
        }
        if (age < 18) {
            println("Musisz mieć co najmniej 18 lat.")
            return false
        }

        println("Rejestracja użytkownika $email.")
        allUsers.add(this)
        // logika rejestracji użytkownika
        return true
    }

    fun login(): Boolean {
        // Prosta walidacja
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            println("Nieprawidłowy format email.")
            return false
        }
        if (password.length < 8) {
            println("Hasło musi mieć co najmniej 8 znaków.")
            return false
        }

        // Sprawdź, czy użytkownik istnieje
        val userExists = allUsers.any { it.email == email && it.password == password }

        if (userExists) {
            println("Logowanie użytkownika $email.")
            // logika logowania użytkownika
            return true
        } else {
            println("Nie znaleziono użytkownika $email.")
            return false
        }
    }

    fun logout() {
        println("Wylogowanie użytkownika $email.")
        // logika wylogowania użytkownika
    }

    fun deleteUser(user: User): Boolean {
        return allUsers.remove(user)
    }

    fun updateUser(user: User): Boolean {
        val index = allUsers.indexOfFirst { it.id == user.id }
        if (index != -1) {
            allUsers[index] = user
            return true
        }
        return false
    }
}