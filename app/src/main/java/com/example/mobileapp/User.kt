package com.example.mobileapp

class User {
    var username : String? = null
    var password : String? = null

    constructor(){}

    constructor(username:String, password:String){
        this.username = username
        this.password = password
    }

}