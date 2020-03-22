package com.syuheifujita.android_todo

import android.app.Application

class TodoApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
