package edu.iesam.examaad1eval.features.ex1.data

import edu.iesam.examaad1eval.features.ex1.data.local.Ex1XmlLocalDataSource
import edu.iesam.examaad1eval.features.ex1.data.remoto.MockEx1RemoteDataSource
import edu.iesam.examaad1eval.features.ex1.domain.Ex1Repository
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class Ex1DataRepository(
    private val xmlLocalDataSource: Ex1XmlLocalDataSource,
    private val remoteDataSource: MockEx1RemoteDataSource
) : Ex1Repository {

    override fun getUsers(): List<User> {
        val local = xmlLocalDataSource.findAllUsers()
        if (local.isEmpty()) {
            val remote = remoteDataSource.getUsers()
            xmlLocalDataSource.saveAllUsers(remote)
            return remote
        }
        return local
    }

    override fun getItems(): List<Item> {
        val local = xmlLocalDataSource.findAllItems()
        if (local.isEmpty()) {
            val remote = remoteDataSource.getItems()
            xmlLocalDataSource.saveAllItems(remote)
            return remote
        }
        return local
    }

    override fun getServices(): List<Services> {
        val local = xmlLocalDataSource.findAllServices()
        if (local.isEmpty()) {
            val remote = remoteDataSource.getServices()
            xmlLocalDataSource.saveAllServices(remote)
            return remote
        }
        return local
    }

}