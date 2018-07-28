package app.unithon2018.team1.team1unithon2018android.model

data class Event(
        val id: String,
        val description: String,
        val locatopn: String,
        val name: String,
        val lat: Float,
        val radius: String,
        val lng: Float,
        val distance: Float,
        val host_id: Int,
        val start_at: String,
        val images: List<String>,
        val hashtags: List<String>,
        val members_count: Int)