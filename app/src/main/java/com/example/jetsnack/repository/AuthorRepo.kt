package com.example.jetsnack.repository

import com.example.jetsnack.database.Calender
import com.example.jetsnack.database.Hatch
import com.example.jetsnack.database.User
import com.example.jetsnack.usecase.GraphqlUsecase

class AuthorRepo {
    // Variable Section
    private val graphqlUsecase = GraphqlUsecase()
    suspend fun getUser(userId: String): User = graphqlUsecase.getUser(userId)
    //suspend fun getHatch(userId: String): ArrayList<Hatch> = graphqlUsecase.getUserEgg(userId)
    suspend fun getCalender(userId: String): ArrayList<Calender> = graphqlUsecase.getUserEvent(userId)


    suspend fun createAccount(userId: String, userName: String, userKey: Int): String =
        graphqlUsecase.createAuthor(userId, userName, userKey)

    suspend fun createFriend(userId: String, friendId: String, friendName: String): String =
        graphqlUsecase.createFriend(userId, friendId, friendName)

    suspend fun createFocusRoom(userId: String, roomCode: String): String =
        graphqlUsecase.createAuthorMultipleFocusMutation(userId, roomCode)

    suspend fun createEvent(
        userId: String,
        eventYear: Int,
        eventMonth: Int,
        eventDay: Int,
        eventHour: Int,
        eventHeader: String,
        eventContent: String
    ): String = graphqlUsecase.createAuthorEvent(
        userId,
        eventYear,
        eventMonth,
        eventDay,
        eventHour,
        eventHeader,
        eventContent
    )

    suspend fun createFriendApplication(userId: String, friendIdApplication: String): String =
        graphqlUsecase.createAuthorFriendApplication(userId, friendIdApplication)

    suspend fun createEgg(
        userId: String,
        eggId: Int,
        eggRemainTime: Int,
        eggSequence: Int
    ): String = graphqlUsecase.createAuthorEgg(userId, eggId, eggRemainTime, eggSequence)

    suspend fun updateName(userId: String, userName: String): String =
        graphqlUsecase.updateAuthorName(userId, userName)

    suspend fun updateMoney(userId: String, userMoney: Int): String =
        graphqlUsecase.updateAuthorMoney(userId, userMoney)

    suspend fun updateEquipDog(userId: String, userEquipDog: Int): String =
        graphqlUsecase.updateAuthorEquipDog(userId, userEquipDog)

    suspend fun updateIntroduction(userId: String, userIntroduction: String): String =
        graphqlUsecase.updateAuthorIntroduction(userId, userIntroduction)

    suspend fun update_continue_focus_time(userId: String, continueFocusTime: Int): String =
        graphqlUsecase.updatecontinue_focus_time(userId, continueFocusTime)

    suspend fun update_total_focus_time(userId: String, totalFocusTime: Int): String =
        graphqlUsecase.updatetotal_focus_time(userId, totalFocusTime)

    suspend fun update_friend_number(userId: String, friendNumber: Int): String =
        graphqlUsecase.updatefriend_number(userId, friendNumber)

    suspend fun update_continue_login_day(userId: String, continueLoginDay: Int): String =
        graphqlUsecase.updatecontinue_login_day(userId, continueLoginDay)

    suspend fun update_last_focus_time(userId: String, lastFocusTime: Int): String =
        graphqlUsecase.updatelast_focus_time(userId, lastFocusTime)

    suspend fun update_day_focus_time(userId: String, dayFocusTime: Int): String =
        graphqlUsecase.updateday_focus_time(userId, dayFocusTime)

    suspend fun updateDog(userId: String, dogId: Int, dogobtain: Int): String =
        graphqlUsecase.updateAuthorDog(userId, dogId, dogobtain)

    suspend fun updateEggId(userId: String, eggId: Int): String =
        graphqlUsecase.updateAuthorEggId(userId, eggId)

    suspend fun updateEggSequence(userId: String, eggSequence: Int): String =
        graphqlUsecase.updateAuthorEggSequence(userId, eggSequence)

    suspend fun updateEggRemain_time(userId: String, eggRemainTime: Int): String =
        graphqlUsecase.updateAuthorEggRemain_time(userId, eggRemainTime)

    suspend fun updateAchievement(
        userId: String,
        achievementId: Int,
        achievementobtain: Int
    ): String = graphqlUsecase.updateAuthorAchievement(userId, achievementId, achievementobtain)

    suspend fun updateFriendName(userId: String, friendId: String, friendName: String): String =
        graphqlUsecase.updateAuthorFriendName(userId, friendId, friendName)

    suspend fun updateNotification(userId: String, notification: Boolean): String =
        graphqlUsecase.updateAuthorNotification(userId, notification)

    suspend fun updateMeetingFriend(userId: String, meetingFriend: Boolean): String =
        graphqlUsecase.updateAuthorMeetingFriend(userId, meetingFriend)

    suspend fun updateDisturdMode(userId: String, disturdMode: Boolean): String =
        graphqlUsecase.updateAuthorDisturdMode(userId, disturdMode)

    suspend fun updateRoomPeopleNumber(roomCode: String, roomPeopleNumber: Int): String =
        graphqlUsecase.updateAuthorRoomPeopleNumber(roomCode, roomPeopleNumber)

    suspend fun updateUserId2(userId: String, roomCode: String): String =
        graphqlUsecase.updateAuthorUserId2(userId, roomCode)

    suspend fun updateUserId3(userId: String, roomCode: String): String =
        graphqlUsecase.updateAuthorUserId3(userId, roomCode)

    suspend fun updateUserId4(userId: String, roomCode: String): String =
        graphqlUsecase.updateAuthorUserId4(userId, roomCode)

    suspend fun updateRestTime(userId: String, restTime: Int): String =
        graphqlUsecase.updateAuthorRestTime(userId, restTime)

    suspend fun updateStartTime(userId: String, startTime: Int): String =
        graphqlUsecase.updateAuthorStartTime(userId, startTime)

    suspend fun updateStartFocus(userId: String, startFocus: Int): String =
        graphqlUsecase.updateAuthorStartFocus(userId, startFocus)

    suspend fun updateEvent(
        userId: String,
        eventYear: Int,
        eventMonth: Int,
        eventDay: Int,
        eventHour: Int,
        eventHeader: String,
        eventContent: String
    ): String = graphqlUsecase.UpdateAuthorEvent(
        userId,
        eventYear,
        eventMonth,
        eventDay,
        eventHour,
        eventHeader,
        eventContent
    )

    suspend fun deleteUser(userId: String): String = graphqlUsecase.deleteAuthor(userId)
    suspend fun deleteMultipleFocus(roomCode: String): String =
        graphqlUsecase.deleteAuthorMultipleFocus(roomCode)

    suspend fun deleteFriendApplication(userId: String, friendIdApplication: String): String =
        graphqlUsecase.deleteAuthorFriendApplication(userId, friendIdApplication)

    suspend fun deleteEvent(
        userId: String,
        eventYear: Int,
        eventMonth: Int,
        eventDay: Int,
        eventHour: Int
    ): String = graphqlUsecase.deleteAuthorEvent(userId, eventYear, eventMonth, eventDay, eventHour)

    suspend fun deleteEgg(userId: String): String = graphqlUsecase.deleteAuthorEgg(userId)
}

