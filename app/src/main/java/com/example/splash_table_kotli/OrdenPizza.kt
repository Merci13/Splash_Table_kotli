package com.example.splash_table_kotli

import android.os.Parcel
import android.os.Parcelable

data class OrdenPizza(
 private  val name:String?,
 private  val apellido:String?,
 private  val tamanno:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(apellido)
        parcel.writeString(tamanno)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrdenPizza> {
        override fun createFromParcel(parcel: Parcel): OrdenPizza {
            return OrdenPizza(parcel)
        }

        override fun newArray(size: Int): Array<OrdenPizza?> {
            return arrayOfNulls(size)
        }
    }
}

