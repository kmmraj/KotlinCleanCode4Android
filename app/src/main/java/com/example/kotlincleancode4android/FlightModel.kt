package com.example.kotlincleancode4android

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

// Inline function to create Parcel Creator
inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

// custom readParcelable to avoid reflection
fun <T : Parcelable> Parcel.readParcelable(creator: Parcelable.Creator<T>): T? {
    if (readString() != null) return creator.createFromParcel(this) else return null
}

open class FlightModel(
        var flightName: String,
        var startingTime: String,
        var departureCity: String,
        var arrivalCity: String,
        var departureTime: String,
        var arrivalTime: String
    ): Parcelable {

    constructor(parcelIn: Parcel) : this(
            flightName = parcelIn.readString(),
            startingTime = parcelIn.readString(),
            departureCity = parcelIn.readString(),
            arrivalCity = parcelIn.readString(),
            departureTime = parcelIn.readString(),
            arrivalTime = parcelIn.readString()
    )



    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(flightName)
        dest.writeString(startingTime)
        dest.writeString(departureCity)
        dest.writeString(arrivalCity)
        dest.writeString(departureTime)
        dest.writeString(arrivalTime)
    }

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { FlightModel(it) }
    }
}


class FlightViewModel(
        var noOfDaysToFly: String?,
        flightName: String,
        startingTime: String,
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String
    ): FlightModel(
        flightName,
        startingTime,
        departureCity,
        arrivalCity,
        departureTime,
        arrivalTime
    ) {

    constructor(parcelIn: Parcel): this(
            noOfDaysToFly = parcelIn.readString(),
            flightName = parcelIn.readString(),
            startingTime = parcelIn.readString(),
            departureCity = parcelIn.readString(),
            arrivalCity = parcelIn.readString(),
            departureTime = parcelIn.readString(),
            arrivalTime = parcelIn.readString()
    )


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeString(noOfDaysToFly)
    }

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { FlightViewModel(it) }
    }

}