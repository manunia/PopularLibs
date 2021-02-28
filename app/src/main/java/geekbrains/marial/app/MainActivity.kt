package geekbrains.marial.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import geekbrains.marial.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var vb: ActivityMainBinding? = null

    var countres = mutableListOf(0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener {
            vb?.btnCounter1?.text = (++countres[0]).toString()
        }
        vb?.btnCounter2?.setOnClickListener {
            vb?.btnCounter2?.text = (++countres[1]).toString()
        }
        vb?.btnCounter3?.setOnClickListener {
            vb?.btnCounter3?.text = (++countres[2]).toString()
        }

        initViews()
    }


    fun initViews() {
        vb?.btnCounter1?.text = countres[0].toString()
        vb?.btnCounter2?.text = countres[1].toString()
        vb?.btnCounter3?.text = countres[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", countres.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray("counters")
        countersArray?.toList()?.let {
            countres.clear()
            countres.addAll(it)
        }
        initViews()
    }
}