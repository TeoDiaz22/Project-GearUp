package com.example.project_gearup.models

import android.os.Parcel
import android.os.Parcelable

class Motorcycle(
    var image: String?,
    var model: String?,
    var price: Double,
    var cylinderCapacity: Int,
    var motor: String?,
    var motorcycleClass: ArrayList<String>,
    var transmission: String?,
    var suspension: String?,
    var brakes: String?,
    var fuelCapacity: Double,
    var description: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        TODO("motorcycleClass"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    fun getMotorcycleClasses(): String {
        var motorcycleClasses = ""
        for (motorcycleClass in this.motorcycleClass) {
            motorcycleClasses += "$motorcycleClass |"
        }
        return motorcycleClasses.dropLast(2)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(model)
        parcel.writeDouble(price)
        parcel.writeInt(cylinderCapacity)
        parcel.writeString(motor)
        parcel.writeString(transmission)
        parcel.writeString(suspension)
        parcel.writeString(brakes)
        parcel.writeDouble(fuelCapacity)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Motorcycle> {
        override fun createFromParcel(parcel: Parcel): Motorcycle {
            return Motorcycle(parcel)
        }

        override fun newArray(size: Int): Array<Motorcycle?> {
            return arrayOfNulls(size)
        }
    }
}