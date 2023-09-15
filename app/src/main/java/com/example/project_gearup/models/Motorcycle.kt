package com.example.project_gearup.models

class Motorcycle(
    var image: Int,
    var model: String,
    var price: Double,
    var cylinderCapacity: Int,
    var motor: String,
    var motorcycleClass: ArrayList<String>,
    var transmission: String,
    var suspension: String,
    var brakes: String,
    var fuelCapacity: Double,
    var description: String
) {
    fun getMotorcycleClasses(): String {
        var motorcycleClasses = ""
        for (motorcycleClass in this.motorcycleClass) {
            motorcycleClasses += "$motorcycleClass |"
        }
        return motorcycleClasses.dropLast(2)
    }
}