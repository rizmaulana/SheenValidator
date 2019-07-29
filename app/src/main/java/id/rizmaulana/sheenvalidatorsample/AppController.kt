package id.rizmaulana.sheenvalidatorsample

import android.app.Application
import android.content.Context
import android.os.Build
import java.util.*




/**
 * rizmaulana@live.com 2019-07-29.
 */
class AppController : Application() {

    override fun attachBaseContext(base: Context?) {
        val locale = Locale("id")
        Locale.setDefault(locale)
        val config = base?.resources?.configuration
        config?.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config?.let {
                base.createConfigurationContext(it)
            }
        } else {
            config?.let {
                base.resources.updateConfiguration(config, base.resources.displayMetrics)
            }

        }

        super.attachBaseContext(base)
    }

}