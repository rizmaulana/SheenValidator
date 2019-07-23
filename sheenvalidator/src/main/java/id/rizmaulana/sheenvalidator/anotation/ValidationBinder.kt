package id.rizmaulana.sheenvalidator.anotation

import android.app.Activity
import android.widget.TextView
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import id.rizmaulana.sheenvalidator.utils.ValidationType
import java.lang.reflect.Field


/**
 * rizmaulana@live.com 2019-07-23.
 */
object ValidationBinder {
    fun bindValidator(validator: SheenValidator, target: Activity) {
        bind(validator, target, target::class.java.declaredFields)
    }

    private fun bind(validator: SheenValidator, target: Activity, fieldList: Array<Field>) {
        fieldList.iterator().forEach { field ->
            val annotation: Annotation? = field.getAnnotation(RegisterValidation::class.java)
            annotation?.let { textViewAnnotated ->
                val bindVal = textViewAnnotated as RegisterValidation
                val txt = field.get(target) as? TextView ?: throw RuntimeException("Class must be instance of TextView")

                   try {
                       field.isAccessible = true
                       bindVal.validationType.iterator().forEach { valType ->
                           when (valType) {
                               ValidationType.REQUIRED -> validator.registerAsRequired(txt)
                               ValidationType.EMAIL -> validator.registerAsEmail(txt)
                               ValidationType.PHONE -> validator.registerAsPhone(txt)
                               ValidationType.WEBSITE -> validator.registerAsWebsite(txt)
                           }
                       }
                   } catch (e: IllegalAccessException) {
                       e.printStackTrace()
                   }


            }
        }

    }
}