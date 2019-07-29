package id.rizmaulana.sheenvalidatorsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_sheen_validator_with_listener.*

class SheenValidatorErrorListenerActivity : AppCompatActivity() {

    lateinit var sheenValidator: SheenValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheen_validator_with_listener)

        sheenValidator = SheenValidator(this)
        sheenValidator.registerAsRequired(txt_username)
        sheenValidator.registerAsRequired(txt_password)
        sheenValidator.setOnValidatorListener {
            til_username.error = ""
            til_password.error = ""
            Toast.makeText(this@SheenValidatorErrorListenerActivity, "Your form is valid, good job!", Toast.LENGTH_SHORT)
                .show()
        }
        sheenValidator.setOnErrorValidatorListener { list ->
            list.forEach {
                when(it.first.id){
                    R.id.txt_username -> til_username.error = "Oops!... looks like your username is not valid"
                    R.id.txt_password -> til_password.error = "Hmm... password is required"

                }
            }
        }

        btn_login.setOnClickListener { sheenValidator.validate() }
    }
}
