package id.rizmaulana.sheenvalidatorsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_sheenvalidator.setOnClickListener { startActivity(Intent(this, SheenValidatorActivity::class.java)) }
        btn_sheenvalidatorlistener.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SheenValidatorErrorListenerActivity::class.java
                )
            )
        }
        btn_sheen_annotation.setOnClickListener {
            startActivity(Intent(this, SheenValidatorAnnotationActivity::class.java))
        }


    }
}
