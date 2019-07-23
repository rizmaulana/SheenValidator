package id.rizmaulana.sheenvalidator.anotation

import id.rizmaulana.sheenvalidator.utils.ValidationType

/**
 * rizmaulana@live.com 2019-07-23.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class RegisterValidation(vararg val validationType : ValidationType)