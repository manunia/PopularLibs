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
            presenter.counterBtn1Click()
        })
        vb?.btnCounter2?.setOnClickListener(View.OnClickListener {
            presenter.counterBtn2Click()
        })
        vb?.btnCounter3?.setOnClickListener(View.OnClickListener {
            presenter.counterBtn3Click()
        })
    }

    override fun setButton1Text(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        vb?.btnCounter3?.text = text
    }
}