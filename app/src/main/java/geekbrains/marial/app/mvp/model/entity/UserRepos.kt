package geekbrains.marial.app.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserRepos(
    @Expose val id: String,
    @Expose val fullName: String,
    @Expose val forksCount: Int
):Parcelable