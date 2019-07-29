package id.rizmaulana.sheenvalidator.utils.type

import id.rizmaulana.sheenvalidator.utils.ValidationType
import id.rizmaulana.sheenvalidator.utils.ValidationTypeAbstraction

/**
 * rizmaulana@live.com 2019-07-29.
 */
class ValidationPhone : ValidationTypeAbstraction {
    override fun getType() = ValidationType.PHONE
}