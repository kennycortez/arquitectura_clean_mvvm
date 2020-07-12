package com.example.domain.model.crokis

import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

class Crokis(var CreatorId:String, var IsEditable:Boolean?, var LastModDate:String, var MapId:String, var MapName:String,
                  var MapPassword:String, var Pins:HashMap<String,Pins>?, var mapToken: MapToken?
):Serializable{
    constructor():this("",null,"","","","", null,null){

    }
}