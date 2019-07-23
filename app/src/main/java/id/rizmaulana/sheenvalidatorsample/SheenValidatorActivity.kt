package id.rizmaulana.sheenvalidatorsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_sheen_validator.*

class SheenValidatorActivity : AppCompatActivity() {

    private lateinit var sheenValidator: SheenValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheen_validator)

        sheenValidator = SheenValidator(this)
        sheenValidator.setOnValidatorListener {
            Toast.makeText(this@SheenValidatorActivity, "Registration form is valid, good job!", Toast.LENGTH_SHORT)
                .show()
        }
        sheenValidator.registerAsRequired(txt_first_name)
        sheenValidator.registerAsRequired(txt_lastname)
        sheenValidator.registerAsRequired(txt_email)
        sheenValidator.registerAsEmail(txt_email)
        sheenValidator.registerAsRequired(txt_phone)
        sheenValidator.registerAsPhone(txt_phone)
        sheenValidator.registerAsRequired(txt_website)
        sheenValidator.registerAsWebsite(txt_website)

        btn_register.setOnClickListener {
            sheenValidator.validate()
        }
    }
}
