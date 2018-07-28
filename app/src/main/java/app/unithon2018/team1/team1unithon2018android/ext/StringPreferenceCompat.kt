package app.unithon2018.team1.team1unithon2018android.ext

import android.content.Context


open class StringPreferenceCompat(context: Context) {

    private var _accessToken by StringPreference(context)

    fun saveAccessToken(accessToken: String) {
        this._accessToken = accessToken
    }

    fun getAccessToken() = this._accessToken


}