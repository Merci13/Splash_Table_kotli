package com.example.splash_table_kotli

import android.os.Parcel
import android.os.Parcelable

data class Ticketes(
    val name:String?,
    val apellidos:String?,
    val numeroCedula:Int,
    val numeroPersonas:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(apellidos)
        parcel.writeInt(numeroCedula)
        parcel.writeString(numeroPersonas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ticketes> {
        override fun createFromParcel(parcel: Parcel): Ticketes {
            return Ticketes(parcel)
        }

        override fun newArray(size: Int): Array<Ticketes?> {
            return arrayOfNulls(size)
        }
    }
}