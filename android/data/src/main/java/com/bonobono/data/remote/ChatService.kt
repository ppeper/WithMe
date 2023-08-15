package com.bonobono.data.remote

import com.bonobono.data.model.chatting.response.ChattingListResponse
import com.bonobono.data.model.chatting.response.ChattingResponse
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.registration.Member
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {

    @POST("/chatting/room")
    suspend fun postChatting(
        @Body chatRoom : ChatRoom
    ) : ChattingResponse

    @GET("/chatting/list")
    suspend fun getChattingList() : List<ChattingListResponse>

    @DELETE("/chatting/{roomNumber}")
    suspend fun deleteChattingRoom(
        @Path(value = "roomNumber") roomNumber: Int,
    ) : Response<Unit>
}