package geekbrains.marial.app.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser (
    val login: String
) : Parcelable