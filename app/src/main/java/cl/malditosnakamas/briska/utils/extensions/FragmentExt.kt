package cl.malditosnakamas.briska.utils.extensions

import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import cl.malditosnakamas.briska.utils.validator.EmailValidator
import cl.malditosnakamas.briska.utils.validator.NameValidator
import cl.malditosnakamas.briska.utils.validator.PassValidator
import cl.malditosnakamas.briska.utils.validator.RutValidator

fun Fragment.alert(message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
