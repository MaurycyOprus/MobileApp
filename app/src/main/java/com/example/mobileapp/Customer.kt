package com.example.mobileapp

class Customer {
    var dateArr : String? = null
    var dateDep : String? = null
    var phoneNum : String? = null
    var email : String? = null
    var houseNum : String? = null
    var additionalInfo : String? = null

    constructor(){}

    constructor(dateArr:String,
                dateDep:String,
                phoneNum:String,
                email: String,
                houseNum:String,
                additionalInfo: String){
        this.dateArr = dateArr
        this.dateDep = dateDep
        this.phoneNum = phoneNum
        this.email = email
        this.houseNum = houseNum
        this.additionalInfo = additionalInfo
    }

}