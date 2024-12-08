package edu.iesam.examaad1eval

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import edu.iesam.examaad1eval.app.data.local.Ex2DataBase
import edu.iesam.examaad1eval.features.ex1.data.Ex1DataRepository
import edu.iesam.examaad1eval.features.ex1.data.local.DbExam
import edu.iesam.examaad1eval.features.ex1.data.remote.MockEx1RemoteDataSource
import edu.iesam.examaad1eval.features.ex1.domain.Ex1Repository
import edu.iesam.examaad1eval.features.ex2.data.GameDataRepository
import edu.iesam.examaad1eval.features.ex2.data.local.db.GamesDbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeExercise1()
        executeExercise2()
    }

    private fun executeExercise1() {
        //Ejecutar el ejercicio 1 desde aquí llamando al Ex1DataRepository directamente
        val dataRepository = Ex1DataRepository(DbExam(this), MockEx1RemoteDataSource())
        Log.d("@JuanDev", dataRepository.getUsers().toString())
        Log.d("@JuanDev", dataRepository.getItems().toString())
        Log.d("@JuanDev", dataRepository.getServices().toString())
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun executeExercise2() {
        //Ejecutar el ejercicio 2 desde aquí llamando al Ex2DataRepository directamente
        GlobalScope.launch {
            //llamar a Room
            val dbRoom = Room.databaseBuilder(
                applicationContext,
                Ex2DataBase::class.java,
                getString(R.string.databaseName)
            ).build()

            val gamesDao = dbRoom.gamesDao()
            val remote = MockEx2RemoteDataSource()
            val dbLocal = GamesDbLocalDataSource(gamesDao)
            val dataRepository = GameDataRepository(dbLocal, remote)

            val games = dataRepository.getGames()
            if(games != null){
                Log.d("@JuanDev", "The games has already saved")
            } else{
                Log.d("@JuanDev", "No games found :(")
            }
        }
    }
}