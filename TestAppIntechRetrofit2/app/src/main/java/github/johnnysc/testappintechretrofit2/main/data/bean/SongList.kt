package github.johnnysc.testappintechretrofit2.main.data.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Asatryan on 08.07.18
 */
@Parcelize
data class SongList(@SerializedName("results") val songsList : List<Song>) : Parcelable