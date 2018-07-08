package github.johnnysc.testappintechretrofit2.main.di.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Asatryan on 08.07.18
 */
@Parcelize
data class Song(@SerializedName("artistName") val artistName: String,
                @SerializedName("trackName") val trackName: String,
                @SerializedName("artworkUrl100") val coverUrl: String,
                @SerializedName("previewUrl") val previewUrl: String
) : Parcelable