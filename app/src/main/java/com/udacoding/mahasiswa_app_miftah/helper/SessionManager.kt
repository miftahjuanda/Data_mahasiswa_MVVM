package com.udacoding.mahasiswa_app_miftah.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(var c: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGINUSER"

    var ISLOGIN = "islogin"
    var NAME = "name"
    var EMAIL = "email"

    init {
        pref = c.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    var login: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(login) {
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }


    var name: String?
        get() = pref?.getString(NAME, "")
        set(name) {
            editor?.putString(NAME, name)
        }

    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(email) {
            editor?.putString(EMAIL, email)
        }
}