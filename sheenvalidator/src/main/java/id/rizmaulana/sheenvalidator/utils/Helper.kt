package id.rizmaulana.sheenvalidator.utils

import java.util.regex.Pattern

/**
 * rizmaulana@live.com 2019-07-18.
 */
object Helper {
    fun isEmailValid(email: String): Boolean {
        val emailPattern = Pattern.compile(Constant.REGEX_EMAIL, Pattern.CASE_INSENSITIVE)
        return emailPattern.matcher(email).matches()
    }

    fun isPhoneNumberValid(phone: String): Boolean {
        val phonePattern = Pattern.compile(Constant.REGEX_PHONE, Pattern.CASE_INSENSITIVE)
        return phonePattern.matcher(phone).matches()

    }

    fun isValidWebsite(phone: String): Boolean {
        val webPattern = Pattern.compile(Constant.REGEX_WEBSITE, Pattern.CASE_INSENSITIVE)
        return webPattern.matcher(phone).matches()

    }
}