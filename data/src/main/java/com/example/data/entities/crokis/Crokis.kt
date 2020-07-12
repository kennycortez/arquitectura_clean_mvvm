package com.example.data.entities.crokis

import com.example.domain.model.crokis.MapToken
import com.example.domain.model.crokis.Pins

data class Crokis(var CreatorId:String?=null, var IsEditable:String?, var LastModDate:String?, var MapId:String?, var MapName:String?,
                  var MapPassword:String?, var Pins: Pins, var mapToken: MapToken
)