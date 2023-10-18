package com.example.oct11.recyclerOnline.dataclass

import android.os.Parcel
import android.os.Parcelable

data class ItemsViewModelOnline(
    val textOne: String,
    val textTwo: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(textOne)
        parcel.writeString(textTwo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsViewModelOnline> {
        override fun createFromParcel(parcel: Parcel): ItemsViewModelOnline {
            return ItemsViewModelOnline(parcel)
        }

        override fun newArray(size: Int): Array<ItemsViewModelOnline?> {
            return arrayOfNulls(size)
        }
    }
}
