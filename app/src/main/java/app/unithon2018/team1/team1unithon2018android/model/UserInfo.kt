package app.unithon2018.team1.team1unithon2018android.model


//{
//        "joined_event_id": 1,
//        "email": "jspiner@naver.com",
//        "id": 1,
//        "name": "sdfsdf",
//        "image": null,
//        "access_token": "KC5WJKQIADVQMINDN1CGBYJ7CPRQZILI",
//        "uuid": "hel\n",
//        "nickname": "익명의 악어"
//    }

data class UserInfo(
        val joined_event_id: Int,
        val email: String,
        val id: Int,
        val name: String,
        val image: String,
        val access_token: String,
        val uuid: String,
        val nickname: String
)