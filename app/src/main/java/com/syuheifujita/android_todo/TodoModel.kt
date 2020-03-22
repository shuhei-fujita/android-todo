package com.syuheifujita.android_todo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TodoModel : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var detail: String = ""
}
