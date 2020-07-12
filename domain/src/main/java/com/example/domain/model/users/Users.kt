package com.example.domain.model.users

import com.google.gson.annotations.SerializedName

class Users(
    @SerializedName("UID")
    var UID:String?=null,
    @SerializedName("Username")
    var Username:String?=null,
    @SerializedName("Maps")
    var Maps: Maps?=null){

    constructor():this("","",null){

    }
}