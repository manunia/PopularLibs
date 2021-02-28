package geekbrains.marial.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import geekbrains.marial.app.databinding.ActivityMainBinding
import geekbrains.marial.app.mvp.presenter.MainPresenter
import geekbrains.marial.app.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        
        vb?.btnCounter1?.setOnClickListener(View.OnClickListener {
            presenter.counterClick(0)
        })
        vb?.btnCounter2?.setOnClickListener(View.OnClickListener {
            presenter.counterClick(1)
        })
        vb?.btnCounter3?.setOnClickListener(View.OnClickListener {
            presenter.counterClick(2)
        })
    }

    override fun setButtonText(index: Int, text: String) {
        when(index) {
            0 -> vb?.btnCounter1?.text = text
            1 -> vb?.btnCounter2?.text = text
            2 -> vb?.btnCounter3?.text = text
        }
    }
}