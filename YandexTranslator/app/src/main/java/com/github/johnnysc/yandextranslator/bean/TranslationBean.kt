package com.github.johnnysc.yandextranslator.bean

import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 22.06.18
 */
data class TranslationBean(@SerializedName("code") var code: Int,
                           @SerializedName("lang") var lang: String,
                           @SerializedName("text") var text: List<String>)