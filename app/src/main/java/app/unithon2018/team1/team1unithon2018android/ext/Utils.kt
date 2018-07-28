package app.unithon2018.team1.team1unithon2018android.ext

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

fun Context.toast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()

inline fun <reified T : Any> log(message: String) = Log.d(T::class.java.name, message)

typealias ClosureCallback<T> = (T) -> Unit

fun mainThread(closure: suspend CoroutineScope.() -> Unit) = launch(UI, block = closure)

var View.isVisible
  get() = this.visibility == View.VISIBLE
  set(value) {
    this.visibility = if (value) View.VISIBLE else View.GONE
  }