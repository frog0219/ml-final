package com.example.jetsnack.usecase

import com.apollographql.apollo3.ApolloClient
import com.example.jetsnack.database.*
import com.example.rocketreserver.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GraphqlUsecase {
    suspend fun getUser(userId: String): User {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:3000/graphql")
            .build()
        // Execute your query. This will suspend until the response is received.
        val response = apolloClient.query(
            AuthorQuery(userId = userId,)
        ).execute()
        val id=response.data?.author?.userId.toString()!!
        val key=response.data?.author?.userKey!!
        val name=response.data?.author?.userName!!
        val equipDog=response.data?.author?.userEquipDog!!
        val achievement=(getUserAchievement(userId))!!
        val own_dogs=(getUserDog(userId))!!
        val introduction=response.data?.author?.userIntroduction!!
        val money=response.data?.author?.userMoney!!
        var continue_focus_time=response.data?.author?.continue_focus_time!!
        var total_focus_time=response.data?.author?.total_focus_time!!
        var friend_number=response.data?.author?.friend_number!!
        var continue_login_day=response.data?.author?.continue_login_day!!
        var last_login_day=response.data?.author?.last_focus_time!!
        var day_focus_time=response.data?.author?.day_focus_time!!
        var notification=response.data?.author?.notification!!
        var new_friend=response.data?.author?.meetingFriend!!
        var moon=response.data?.author?.disturdMode!!
        return User(id, key,name,equipDog, achievement as ArrayList<Int>,
            own_dogs as ArrayList<Int>,introduction,money,continue_focus_time,total_focus_time,friend_number,continue_login_day,
            last_login_day,day_focus_time,notification,new_friend,moon)
    }
    suspend fun getUserDog(userId: String): List<Int> {
        //Log.d("GraphQL", "getCountryTodayWeather: $country")
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:3000/graphql")
            .build()
        // Execute your query. This will suspend until the response is received.
        val response = apolloClient.query(
            UserDogQuery(userId = userId,)
        ).execute()
        var tmp=ArrayList<Int>()
        response.data?.userDog?.forEach(){
            it?.dogobtain?.let { it1 -> tmp.add(it1) }
        }
        return tmp
    }
    suspend fun getUserAchievement(userId: String): List<Int> {
        //Log.d("GraphQL", "getCountryTodayWeather: $country")
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:3000/graphql")
            .build()
        // Execute your query. This will suspend until the response is received.
        val response = apolloClient.query(
            UserAchievementQuery(userId = userId,)
        ).execute()
        var tmp=ArrayList<Int>()
        response.data?.userAchievement?.forEach(){
            it?.achievementobtain?.let { it1 -> tmp.add(it1) }
        }
        return tmp
    }
//    suspend fun getUserEgg(userId: String): ArrayList<Hatch> {
//        //Log.d("GraphQL", "getCountryTodayWeather: $country")
//        val apolloClient = ApolloClient.Builder()
//            .serverUrl("http://10.0.2.2:3000/graphql")
//            .build()
//        // Execute your query. This will suspend until the response is received.
//        val response = apolloClient.query(
//            UserEggQuery(userId = userId,)
//        ).execute()
//        var tmp=ArrayList<Hatch>()
//        response.data?.userEgg?.forEach(){
//            tmp.add(Hatch(it?.eggSequence!!,it.eggRemain_time,it.eggId))
//        }
//        return tmp
//    }
    suspend fun getUserEvent(userId: String): ArrayList<Calender> {
        //Log.d("GraphQL", "getCountryTodayWeather: $country")
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:3000/graphql")
            .build()
        // Execute your query. This will suspend until the response is received.
        val response = apolloClient.query(
            UserEventQuery(userId = userId,)
        ).execute()
        var tmp=ArrayList<Calender>()
        response.data?.userEvent?.forEach(){
            tmp.add(Calender(it?.eventYear!!,it.eventMonth,it.eventDay,it.eventHour,it.eventHeader,it.eventContent))
        }
        return tmp
    }


    suspend fun createAuthor(userId: String,userName:String,userKey:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorMutation(userId = userId,userKey = userKey, userName = userName)
                ).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthor?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun createFriend(userId: String,friendId:String,friendName:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorFriendMutation(
                        userId = userId,
                        friendId = friendId,
                        friendName = friendName
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthorFriend?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun createAuthorMultipleFocusMutation(userId: String,roomCode:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorMultipleFocusMutation(
                        userId = userId,
                        roomCode = roomCode,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthorMultipleFocus?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun createAuthorEvent(userId: String,eventYear:Int,eventMonth:Int,eventDay:Int,eventHour:Int,eventHeader:String,eventContent:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorEventMutation(
                        userId = userId,
                        eventYear = eventYear,
                        eventMonth = eventMonth,
                        eventDay = eventDay,
                        eventHour = eventHour,
                        eventHeader = eventHeader,
                        eventContent = eventContent
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthorEvent?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun createAuthorFriendApplication(userId: String,friendIdApplication:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorFriendApplicationMutation(
                        userId = userId,
                        friendIdApplication = friendIdApplication,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthorFriendApplication?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun createAuthorEgg(userId: String,eggId:Int,eggRemainTime:Int,eggSequence:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    CreateAuthorEggMutation(
                        userId = userId,
                        eggId= eggId,
                        eggRemainTime=eggRemainTime,
                        eggSequence=eggSequence
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.createAuthorEgg?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorName(userId: String,userName: String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorNameMutation(
                        userId = userId,
                        userName = userName,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorName?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorMoney(userId: String,userMoney:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorMoneyMutation(
                        userId = userId,
                        userMoney = userMoney
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorMoney?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorEquipDog(userId: String,userEquipDog:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorEquipDogMutation(
                        userId = userId,
                        userEquipDog = userEquipDog,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorEquipDog?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorIntroduction(userId: String,userIntroduction:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorIntroductionMutation(
                        userId = userId,
                        userIntroduction = userIntroduction,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorIntroduction?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updatecontinue_focus_time(userId: String,continueFocusTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updatecontinue_focus_timeMutation(
                        userId = userId,
                        continueFocusTime = continueFocusTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updatecontinue_focus_time?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updatetotal_focus_time(userId: String,totalFocusTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updatetotal_focus_timeMutation(
                        userId = userId,
                        totalFocusTime = totalFocusTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updatetotal_focus_time?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updatefriend_number(userId: String,friendNumber:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updatefriend_numberMutation(
                        userId = userId,
                        friendNumber = friendNumber,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updatefriend_number?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updatecontinue_login_day(userId: String,continueLoginDay:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updatecontinue_login_dayMutation(
                        userId = userId,
                        continueLoginDay = continueLoginDay,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updatecontinue_login_day?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updatelast_focus_time(userId: String,lastFocusTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updatelast_focus_timeMutation(
                        userId = userId,
                        lastFocusTime = lastFocusTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updatelast_focus_time?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateday_focus_time(userId: String,dayFocusTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    Updateday_focus_timeMutation(
                        userId = userId,
                        dayFocusTime = dayFocusTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateday_focus_time?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorDog(userId: String,dogId:Int,dogobtain:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorDogMutation(
                        userId = userId,
                        dogId = dogId,
                        dogobtain = dogobtain
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorDog?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorEggId(userId: String,eggId:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorEggIdMutation(
                        userId = userId,
                        eggId = eggId,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorEggId?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorEggSequence(userId: String,eggSequence:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorEggSequenceMutation(
                        userId = userId,
                        eggSequence = eggSequence,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorEggSequence?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorEggRemain_time(userId: String,eggRemainTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorEggRemain_timeMutation(
                        userId = userId,
                        eggRemainTime = eggRemainTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorEggRemain_time?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorAchievement(userId: String,achievementId:Int,achievementobtain:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorAchievementMutation(
                        userId = userId,
                        achievementId = achievementId,
                        achievementobtain = achievementobtain
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorAchievement?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorFriendName(userId: String,friendId:String,friendName:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorFriendNameMutation(
                        userId = userId,
                        friendId = friendId,
                        friendName = friendName
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorFriendName?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorNotification(userId: String,notification:Boolean): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorNotificationMutation(
                        userId = userId,
                        notification = notification,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorNotification?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorMeetingFriend(userId: String,meetingFriend:Boolean): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorMeetingFriendMutation(
                        userId = userId,
                        meetingFriend = meetingFriend,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorMeetingFriend?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorDisturdMode(userId: String,disturdMode:Boolean): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorDisturdModeMutation(
                        userId = userId,
                        disturdMode = disturdMode,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorDisturdMode?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun  updateAuthorRoomPeopleNumber(roomCode: String,roomPeopleNumber:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorRoomPeopleNumberMutation(
                        roomCode = roomCode,
                        roomPeopleNumber = roomPeopleNumber,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorRoomPeopleNumber?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorUserId2(userId: String,roomCode:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorUserId2Mutation(
                        userId = userId,
                        roomCode = roomCode,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorUserId2?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorUserId3(userId: String,roomCode :String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorUserId3Mutation(
                        userId = userId,
                        roomCode = roomCode ,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorUserId3?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorUserId4(userId: String,roomCode:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorUserId4Mutation(
                        userId = userId,
                        roomCode = roomCode,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorUserId4?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorRestTime(userId: String,restTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorRestTimeMutation(
                        userId = userId,
                        restTime = restTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorRestTime?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorStartTime(userId: String,startTime:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorStartTimeMutation(
                        userId = userId,
                        startTime = startTime,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorStartTime?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun updateAuthorStartFocus(userId: String,startFocus:Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorStartFocusMutation(
                        userId = userId,
                        startFocus = startFocus,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorStartFocus?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun UpdateAuthorEvent(userId: String,eventYear:Int,eventMonth:Int,eventDay:Int,eventHour:Int,eventHeader: String,eventContent: String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    UpdateAuthorEventMutation(
                        userId = userId,
                        eventYear = eventYear,
                        eventMonth = eventMonth,
                        eventDay = eventDay,
                        eventHour = eventHour,
                        eventHeader = eventHeader,
                        eventContent = eventContent
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.updateAuthorEvent?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun deleteAuthor(userId: String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    DeleteAuthorMutation(
                        userId = userId
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.deleteAuthor?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun deleteAuthorMultipleFocus(roomCode: String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    DeleteAuthorMultipleFocusMutation(
                        roomCode = roomCode
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.deleteAuthorMultipleFocus?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun deleteAuthorFriendApplication(userId: String,friendIdApplication:String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    DeleteAuthorFriendApplicationMutation(
                        userId = userId,
                        friendIdApplication = friendIdApplication,
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.deleteAuthorFriendApplication?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun deleteAuthorEvent(userId: String,eventYear: Int,eventMonth: Int,eventDay: Int,eventHour: Int): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    DeleteAuthorEventMutation(
                        userId = userId,
                        eventYear = eventYear,
                        eventMonth = eventMonth,
                        eventDay = eventDay,
                        eventHour = eventHour
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.deleteAuthorEvent?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
    suspend fun  deleteAuthorEgg(userId: String): String {
        return withContext(Dispatchers.IO) {
            //Log.d("GraphQL", "getCountryTodayWeather: $country")
            try {
                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://10.0.2.2:3000/graphql")
                    .build()
                // Execute your query. This will suspend until the response is received.
                val response = apolloClient.mutation(
                    DeleteAuthorEggMutation(
                        userId = userId
                    ),).execute()
                //println("Hero.name=${response.data?.weather?.list?.get(0)?.weather?.get(0)?.description}")
                return@withContext response.data?.deleteAuthorEgg?.userId
            } catch (e: Exception) {
                System.out.println("Error " + e.message);
            }
        }.toString()
    }
}