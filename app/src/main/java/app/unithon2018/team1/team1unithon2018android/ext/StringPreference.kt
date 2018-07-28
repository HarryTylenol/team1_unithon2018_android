package app.unithon2018.team1.team1unithon2018android.ext

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference(val context: Context) : ReadWriteProperty<Any, String> {

  private val pref by lazy {
    PreferenceManager.getDefaultSharedPreferences(context)
  }

  override fun getValue(thisRef: Any, property: KProperty<*>): String {
    return pref.getString(property.name, "")
  }

  override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
    pref.edit().putString(property.name, value).apply()
  }
}