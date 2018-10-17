package com.anb.fc_dicoding

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (val name: String?, val image: Int?, val desc: String?): Parcelable