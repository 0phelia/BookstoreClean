package com.arty.presentation.model

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class BookView(val id: String, val title: String, val description: String,
                    val authors: String,
                    val rank: Int,
                    val coverImage: String?,
                    val datePublished: String,
                    val publisher: String,
                    val isLiked: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(authors)
        parcel.writeInt(rank)
        parcel.writeString(coverImage)
        parcel.writeString(datePublished)
        parcel.writeString(publisher)
        parcel.writeByte(if (isLiked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookView> {
        override fun createFromParcel(parcel: Parcel): BookView {
            return BookView(parcel)
        }

        override fun newArray(size: Int): Array<BookView?> {
            return arrayOfNulls(size)
        }
    }


}