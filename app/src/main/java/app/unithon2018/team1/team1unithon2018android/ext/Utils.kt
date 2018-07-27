package app.unithon2018.team1.team1unithon2018android.ext

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.toast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT)

inline fun <reified T : Any> log(message: String) = Log.d(T::class.java.name, message)

typealias ClosureCallback<T> = (T) -> Unit