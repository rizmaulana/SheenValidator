package id.rizmaulana.sheenvalidatorsample

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import id.rizmaulana.sheenvalidator.anotation.RegisterValidation
import id.rizmaulana.sheenvalidator.anotation.ValidationBinder
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import id.rizmaulana.sheenvalidator.utils.ValidationType
import kotlinx.android.synthetic.main.activity_sheen_validator_annotation.*


class SheenValidatorAnnotationActivity : AppCompatActivity() {


    lateinit var sheenValidator: SheenValidator

    @RegisterValidation(ValidationType.REQUIRED, ValidationType.EMAIL)
    lateinit var txtUsername: TextInputEditText
    @RegisterValidation(ValidationType.REQUIRED)
    lateinit var txtPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheen_validator_annotation)

        txtUsername = findViewById(R.id.txt_email)
        txtPassword = findViewById(R.id.txt_password)

        sheenValidator = SheenValidator(this)
        sheenValidator.setOnValidatorListener {
            til_email.error = ""
            til_password.error = ""
            Toast.makeText(this@SheenValidatorAnnotationActivity, "Your form is valid, good job!", Toast.LENGTH_SHORT)
                .show()
        }
        sheenValidator.setOnErrorValidatorListener { list ->
            list.forEach {
                when (it.id) {
                    R.id.txt_email -> til_email.error = "Oops!... looks like your Email is not valid"
                    R.id.txt_password -> til_password.error = "Hmm... password is required"

                }
            }
        }

        ValidationBinder.bindValidator(sheenValidator, this)

        btn_login.setOnClickListener { sheenValidator.validate() }

    }
}
