package app.unithon2018.team1.team1unithon2018android.model


//[
//{
//    "create_at": "2018-07-28 16:52:31",
//    "user": [
//    {
//        "joined_event_id": 1,
//        "email": "jspiner@naver.com",
//        "id": 1,
//        "name": "sdfsdf",
//        "image": null,
//        "access_token": "KC5WJKQIADVQMINDN1CGBYJ7CPRQZILI",
//        "uuid": "hel\n",
//        "nickname": "익명의 악어"
//    }
//    ],
//    "files": [
//    "/files/6Q107NIT32SW95JFCGNBE934E21DYEBA.jpg",
//    "/files/63TRBBV9Q5TM4ES5SM253VMC57UEEG4G.png",
//    "/files/J79197QA6FE51TINSWWZ1JV10IS7H49I.jpg"
//    ],
//    "text": "fdfdffsdf",
//    "id": 22,
//    "user_id": 1,
//    "event_id": 1,
//    "hashtags": [
//    "활발한",
//    "피곤한"
//    ]
//},
data class TimeLine(
        val create_at: String,
        val users: List<UserInfo>,
        val files: List<String>,
        val text: String,
        val id: Int,
        val user_id: Int,
        val event_id: Int,
        val hashtags: List<String>
)