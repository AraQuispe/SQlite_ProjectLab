package com.lab02.sqlite_projectlab.db

class Hospital {
    private var id : Int = 0
    private var name : String = ""
    private var address: String = ""
    private var description : String = ""
    private var longitude : Float = 0.0f
    private var latitude : Float = 0.0f

    constructor(_name: String, _address: String, _description: String, _longitude: Float, _latitude: Float){
        this.name = _name
        this.address = _address
        this.description = _description
        this.longitude = _longitude
        this.latitude = _latitude
    }

    constructor(){

    }

    fun getId(): Int{
        return this.id
    }
    fun getName(): String{
        return this.name
    }

    fun getAddress(): String{
        return this.address
    }

    fun getDescription(): String{
        return this.description
    }

    fun getLongitude(): Float{
        return this.longitude
    }

    fun getLatitude(): Float{
        return this.latitude
    }

    fun setId(_id: Int){
        this.id = _id
    }

    fun setName(_name: String){
        this.name = _name
    }

    fun setAddress(_address: String){
        this.address = _address
    }

    fun setDescription(_description: String){
        this.description = _description
    }

    fun setLatitude(_latitude: Float){
        this.latitude = _latitude
    }

    fun setLongitude(_longitude: Float){
        this.longitude = _longitude
    }

    override fun toString(): String {
        return "Hospital(id=$id, name='$name', address='$address', description='$description', longitude=$longitude, latitude=$latitude)"
    }


}