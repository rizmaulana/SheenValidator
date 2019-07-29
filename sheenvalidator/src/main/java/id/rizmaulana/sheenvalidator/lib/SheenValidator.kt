package id.rizmaulana.sheenvalidator.lib

import android.content.Context
import android.widget.TextView
import id.rizmaulana.sheenvalidator.R
import id.rizmaulana.sheenvalidator.utils.Helper
import id.rizmaulana.sheenvalidator.utils.ValidationType
import id.rizmaulana.sheenvalidator.utils.ValidationTypeAbstraction
import id.rizmaulana.sheenvalidator.utils.type.*

/**
 * rizmaulana@live.com 2019-07-18.
 */
class SheenValidator constructor(private val context: Context) {

    private val TAG = javaClass::getSimpleName

    private val formWithValidation: MutableList<Pair<TextView, ValidationTypeAbstraction>> = mutableListOf()
    private val errorFormWithValidation: MutableList<Pair<TextView, ValidationTypeAbstraction>> = mutableListOf()


    private var validatorListener: ValidatorListener? = null
    private var errorValidatorListener: ErrorValidatorListener? = null
    private var showDefaultError = true

    fun setOnValidatorListener(validatorListener: () -> Unit) {
        this.validatorListener = validatorListener
    }

    fun setOnErrorValidatorListener(errorValidatorListener: (List<Pair<TextView, ValidationTypeAbstraction>>) -> Unit) {
        this.showDefaultError = false
        this.errorValidatorListener = errorValidatorListener
    }

    fun registerAsRequired(view: TextView) {
        formWithValidation.add(Pair(view, ValidationRequired()))
    }

    fun registerAsEmail(view: TextView) {
        formWithValidation.add(Pair(view, ValidationEmail()))
    }

    fun registerAsPhone(view: TextView) {
        formWithValidation.add(Pair(view, ValidationPhone()))
    }

    fun registerAsWebsite(view: TextView) {
        formWithValidation.add(Pair(view, ValidationWebsite()))
    }

    fun registerHasMinLength(view: TextView, minLength: Int) {
        formWithValidation.add(Pair(view, ValidationHasMin(minLength)))
    }

    fun registerHasMaxLength(view: TextView, maxLength: Int) {
        formWithValidation.add(Pair(view, ValidationHasMax(maxLength)))
    }

    /*fun removeViewValidation(view: TextView) {
    }*/

    fun validate() {
        errorFormWithValidation.clear()

        formWithValidation.forEach {
            when (it.second.getType()) {
                ValidationType.REQUIRED -> {
                    if (it.first.text.toString().isEmpty()) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(R.string.err_form_required, it.first.hint)
                        }
                    }
                }
                ValidationType.EMAIL -> {
                    if (!Helper.isEmailValid(it.first.text.toString())) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(R.string.err_form_email, it.first.hint)
                        }
                    }
                }
                ValidationType.PHONE -> {
                    if (!Helper.isPhoneNumberValid(it.first.text.toString())) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(R.string.err_form_phone, it.first.hint)
                        }
                    }
                }
                ValidationType.WEBSITE -> {
                    if (!Helper.isValidWebsite(it.first.text.toString())) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(R.string.err_form_website, it.first.hint)
                        }
                    }
                }
                ValidationType.HAS_MIN -> {
                    val hasMinValidator = it.second as ValidationHasMin
                    if (it.first.text.toString().length < hasMinValidator.min) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(
                                R.string.err_form_has_min,
                                it.first.hint,
                                hasMinValidator.min
                            )
                        }
                    }

                }
                ValidationType.HAS_MAX -> {
                    val hasMaxVal = it.second as ValidationHasMax
                    if (it.first.text.toString().length > hasMaxVal.max) {
                        errorFormWithValidation.add(it)
                        if (showDefaultError) {
                            it.first.error = context.resources.getString(
                                R.string.err_form_has_min,
                                it.first.hint,
                                hasMaxVal.max
                            )
                        }
                    }
                }
            }
        }

        if (errorFormWithValidation.isEmpty()) {
            validatorListener?.invoke()
        } else {
            errorValidatorListener?.invoke(errorFormWithValidation)
        }
    }


}