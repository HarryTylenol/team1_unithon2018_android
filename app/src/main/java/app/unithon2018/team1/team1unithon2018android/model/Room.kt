package app.unithon2018.team1.team1unithon2018android.model

data class Room(
    val id: Int,
    val description: String,
    val location: String,
    val name: String,
    val lat: Double,
    val end_at: String,
    val radius: String,
    val lng: Double,
    val distance: Double,
    val host_id: Any,
    val start_at: String
)