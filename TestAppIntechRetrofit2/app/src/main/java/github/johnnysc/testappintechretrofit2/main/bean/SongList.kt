package github.johnnysc.testappintechretrofit2.main.di.bean

import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 08.07.18
 */
data class SongList(@SerializedName("results") val songsList : List<Song>)