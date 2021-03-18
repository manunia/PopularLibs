package geekbrains.marial.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import geekbrains.marial.app.databinding.ItemUserBinding
import geekbrains.marial.app.mvp.model.image.IImageLoader
import geekbrains.marial.app.mvp.presenter.list.IUsersListPresenter
import geekbrains.marial.app.mvp.view.list.IUserItemView

class UsersRVAdapter(
    val presenter: IUsersListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {
        override var pos = -1

        override fun setLogin(login: String) = with(vb) {
            tvLogin.text = login
        }

        override fun loadAvatar(url: String) = with(vb) {
            imageLoader.load(url, vb.ivAvatar)
        }

    }
}