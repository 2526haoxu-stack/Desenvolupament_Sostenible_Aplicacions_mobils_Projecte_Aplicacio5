package com.example.activitat_05.viewModels

import androidx.lifecycle.ViewModel
import com.example.activitat_05.DataSourceFriend
import com.example.activitat_05.DataSourceFriendRequest
import com.example.activitat_05.FriendItem
import com.example.activitat_05.FriendRequest

class FriendItemViewModel : ViewModel() {
    // Lista mutable que gestiona el ViewModel
    val friendsList: MutableList<FriendItem> = DataSourceFriend.friends
    val requestsList: MutableList<FriendRequest> = DataSourceFriendRequest.requests

    // Elimina amigo de la lista de amigos si existe y devuelve la posicíón
    fun removeFriend(friend : FriendItem) : Int {
        val index = friendsList.indexOf(friend)
        if (index != -1) friendsList.removeAt(index) //
        return index // Devuelve la posición del elemento
    }

    // Acepta una solicitud y lo añade dentro de la lista de amigos
    fun aceptarSolicitud(request: FriendRequest) : Pair<Int, Int> {
        val requestIndex = requestsList.indexOf(request)
        if (requestIndex != -1) {
            requestsList.removeAt(requestIndex)
            val nuevoAmigo = FriendItem(request.name, request.userName)
            friendsList.add(nuevoAmigo)
            val friendInsertIndex = friendsList.size-1
            return Pair(requestIndex, friendInsertIndex)
        }
        return Pair(-1, -1)
    }

    // Rechaza una solicitud y lo elimina de la lista de solicitudes
    fun rechazarSolicitud(request: FriendRequest) : Int {
        val requestIndex = requestsList.indexOf(request)
        if (requestIndex != -1) requestsList.removeAt(requestIndex)
        return requestIndex
    }
}