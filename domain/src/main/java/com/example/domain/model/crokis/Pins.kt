package com.example.domain.model.crokis

import java.io.Serializable

class Pins(var PinId:String?,var PinLatitude:String?,var PinLongitude:String?, var PinName:String?, var PinNote:String?):
    Serializable {
    constructor():this("","","","",""){

    }
}