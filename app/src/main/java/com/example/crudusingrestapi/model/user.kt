package com.example.crudusingrestapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

//    constructor() {}
//    constructor(id: Int, name: String?) {
//        this.id = id
//        this.name = name
//    }

    constructor(name: String?) {
        this.id = id
        this.name = name
    }

    constructor()
}