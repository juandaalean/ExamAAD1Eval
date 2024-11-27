package edu.iesam.examaad1eval.features.ex1.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class Ex1XmlLocalDataSource(
    context: Context,
    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        "Ex1",
        Context.MODE_PRIVATE
    )
) {

    private val editor = sharedPref.edit()
    private val gson = Gson()

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
        val usersJson = sharedPref.all["users"] as? String
        return gson.fromJson(usersJson, Array<User>::class.java)?.toList() ?: emptyList()
    }

    fun findAllItems(): List<Item> {
        val itemsJson = sharedPref.getString("items", "[]")
        return gson.fromJson(itemsJson, Array<Item>::class.java)?.toList() ?: emptyList()
    }

    fun findAllServices(): List<Services> {
        val servicesJson = sharedPref.getString("services", "[]")
        return gson.fromJson(servicesJson, Array<Services>::class.java)?.toList() ?: emptyList()
    }


}