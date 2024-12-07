package edu.iesam.examaad1eval.features.ex1.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class DbExam(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        "db-exam", Context.MODE_PRIVATE
    )

    private val gson = Gson()
    private val editor = sharedPref.edit()

    fun saveAllUsers(users: List<User>) {
        editor.putString("users", gson.toJson(users))
        editor.apply()
    }

    fun saveAllItems(items: List<Item>) {
        editor.putString("items", gson.toJson(items))
        editor.apply()
    }

    fun saveAllServices(services: List<Services>) {
        editor.putString("services", gson.toJson(services))
        editor.apply()
    }

    fun findAllUsers(): List<User> {
        val json = sharedPref.getString("users", null)?: return emptyList()
        return gson.fromJson(json, Array<User>::class.java).toList()
    }

    fun findAllItems(): List<Item> {
        val json = sharedPref.getString("items", null) ?: return emptyList()
        return gson.fromJson(json, Array<Item>::class.java).toList()
    }

    fun findAllServices(): List<Services> {
        val json = sharedPref.getString("services", null) ?: return emptyList()
        return gson.fromJson(json, Array<Services>::class.java).toList()
    }
}