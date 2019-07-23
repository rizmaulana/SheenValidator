package id.rizmaulana.sheenvalidator.lib

import android.content.Context
import android.widget.TextView
import id.rizmaulana.sheenvalidator.R
import id.rizmaulana.sheenvalidator.utils.Helper

/**
 * rizmaulana@live.com 2019-07-18.
 */
class SheenValidator constructor(private val context: Context) {

    private val TAG = javaClass::getSimpleName

    private val asRequiredFormList: MutableList<TextView> = mutableListOf()
    private val asEmailFormList: MutableList<TextView> = mutableListOf()
    private val asPhoneFormList: MutableList<TextView> = mutableListOf()
    private val asWebsiteFormList: MutableList<TextView> = mutableListOf()
    private val errorFormList: MutableList<TextView> = mutableListOf()


    private var validatorListener: ValidatorListener? = null
    private var errorValidatorListener: ErrorValidatorListener? = null
    private var showDefaultError = true

    fun setOnValidatorListener(validatorListener: () -> Unit) {
        this.validatorListener = validatorListener
    }

    fun setOnErrorValidatorListener(errorValidatorListener: (List<TextView>) -> Unit) {
        this.showDefaultError = false
        this.errorValidatorListener = errorValidatorListener
    }

    fun registerAsRequired(view: TextView) {
        if (!asRequiredFormList.contains(view)) {
            asRequiredFormList.add(view)
        }
    }

    fun registerAsEmail(view: TextView) {
        if (!asEmailFormList.contains(view)) {
            asEmailFormList.add(view)
        }
    }

    fun registerAsPhone(view: TextView) {
        if (!asPhoneFormList.contains(view)) {
            asPhoneFormList.add(view)
        }
    }

    fun registerAsWebsite(view: TextView) {
        if (!asWebsiteFormList.contains(view)) {
            asWebsiteFormList.add(view)
        }
    }

    fun removeViewValidation(view: TextView) {
        asRequiredFormList.remove(view)
        asEmailFormList.remove(view)
        asPhoneFormList.remove(view)
        asWebsiteFormList.remove(view)
    }

    fun validate() {
        errorFormList.clear()

        asEmailFormList.forEach {
            if (!Helper.isEmailValid(it.text.toString())) {
                errorFormList.add(it)
                if (showDefaultError) {
                    it.error = context.resources.getString(R.string.err_form_email, it.hint)
                }
            }
        }
        asPhoneFormList.forEach {
            if (!Helper.isPhoneNumberValid(it.text.toString())) {
                errorFormList.add(it)
                if (showDefaultError) {
                    it.error = context.resources.getString(R.string.err_form_phone, it.hint)
                }
            }
        }

        asWebsiteFormList.forEach {
            if (!Helper.isValidWebsite(it.text.toString())) {
                errorFormList.add(it)
                if (showDefaultError) {
                    it.error = context.resources.getString(R.string.err_form_website, it.hint)
                }
            }
        }

        asRequiredFormList.forEach {
            if (it.text.toString().isEmpty()) {
                errorFormList.add(it)
                if (showDefaultError) {
                    it.error = context.resources.getString(R.string.err_form_required, it.hint)
                }
            }
        }

        if (errorFormList.isEmpty()) {
            validatorListener?.invoke()
        } else {
            errorValidatorListener?.invoke(errorFormList)
        }
    }


}