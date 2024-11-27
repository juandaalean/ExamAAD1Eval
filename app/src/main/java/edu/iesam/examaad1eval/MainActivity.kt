package edu.iesam.examaad1eval

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.examaad1eval.features.ex1.data.Ex1DataRepository
import edu.iesam.examaad1eval.features.ex1.data.local.Ex1XmlLocalDataSource
import edu.iesam.examaad1eval.features.ex1.data.remoto.MockEx1RemoteDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var localDataSource: Ex1XmlLocalDataSource
    private lateinit var remoteDataSource: MockEx1RemoteDataSource
    private lateinit var dataRepository: Ex1DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_exam)
        localDataSource = Ex1XmlLocalDataSource(this)
        remoteDataSource = MockEx1RemoteDataSource()
        dataRepository = Ex1DataRepository(localDataSource, remoteDataSource)
        executeExercise1()
        executeExercise2()
    }

    private fun executeExercise1() {
        val users = remoteDataSource.getUsers()
        val items = remoteDataSource.getItems()
        val services = remoteDataSource.getServices()

        val usersLayout =
            findViewById<LinearLayout>(R.id.users) // Reemplaza con el ID de tu layout para usuarios
        val itemsLayout =
            findViewById<LinearLayout>(R.id.items) // Reemplaza con el ID de tu layout para items
        val servicesLayout =
            findViewById<LinearLayout>(R.id.services) // Reemplaza con el ID de tu layout para services

        users.forEach { user ->
            val userTextView = TextView(this)
            userTextView.text = "ID: ${user.id}, Nombre: ${user.name}, Apellido: ${user.surname}"
            usersLayout.addView(userTextView)
        }

        items.forEach { item ->
            val itemTextView = TextView(this)
            itemTextView.text = "ID: ${item.id}, Nombre: ${item.name}, Precio: ${item.price}"
            itemsLayout.addView(itemTextView)
        }

        services.forEach { service ->
            val serviceTextView = TextView(this)
            serviceTextView.text = "ID: ${service.id}, Nombre: ${service.name}"
            servicesLayout.addView(serviceTextView)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun executeExercise2() {
        //Ejecutar el ejercicio 2 desde aquí llamando al Ex2DataRepository directamente
        GlobalScope.launch {
            //llamar a Room
        }
    }
}
