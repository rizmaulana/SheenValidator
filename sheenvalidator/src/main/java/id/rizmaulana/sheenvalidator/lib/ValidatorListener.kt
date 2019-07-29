package id.rizmaulana.sheenvalidator.lib

import android.widget.TextView
import id.rizmaulana.sheenvalidator.utils.ValidationTypeAbstraction

/**
 * rizmaulana@live.com 2019-07-18.
 */
typealias ValidatorListener = () -> Unit

typealias ErrorValidatorListener = (List<Pair<TextView, ValidationTypeAbstraction>>) -> Unit