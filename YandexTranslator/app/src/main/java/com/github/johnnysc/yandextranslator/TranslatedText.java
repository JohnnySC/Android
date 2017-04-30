package com.github.johnnysc.yandextranslator;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Asatryan on 26.04.17.
 */

public class TranslatedText {
    @SerializedName("code")
    int code;

    @SerializedName("lang")
    String lang;

    @SerializedName("text")
    ArrayList<String> text;

    public TranslatedText(int code,
                          String lang,
                          ArrayList<String> text) {
        this.code = code;
        this.lang = lang;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getLang() {
        return lang;
    }

    public ArrayList<String> getText() {
        return text;
    }
}
