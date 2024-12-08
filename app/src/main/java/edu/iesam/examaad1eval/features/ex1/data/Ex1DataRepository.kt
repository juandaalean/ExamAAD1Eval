package edu.iesam.examaad1eval.features.ex1.data

import edu.iesam.examaad1eval.features.ex1.data.local.DbExam
import edu.iesam.examaad1eval.features.ex1.data.remote.MockEx1RemoteDataSource
import edu.iesam.examaad1eval.features.ex1.domain.Ex1Repository
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class Ex1DataRepository(
    private val local: DbExam,
    private val remote: MockEx1RemoteDataSource
) : Ex1Repository {
    override fun getUsers(): List<User> {
        val localUsers = local.findAllUsers()
        if (localUsers.isEmpty()) {
            val remoteUsers = remote.getUsers()
            local.saveAllUsers(remoteUsers)
            return remoteUsers
        }
        return localUsers
    }

    override fun getItems(): List<Item> {
        val localItems = local.findAllItems()
        if (localItems.isEmpty()) {
            val remoteItems = remote.getItems()
            local.saveAllItems(remoteItems)
            return remoteItems
        }
        return localItems
    }

    override fun getServices(): List<Services> {
        val localServices = local.findAllServices()
        if (localServices.isEmpty()) {
            val remoteServices = remote.getServices()
            local.saveAllServices(remoteServices)
            return remoteServices
        }
        return localServices
    }
}