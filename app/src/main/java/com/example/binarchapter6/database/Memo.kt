package com.example.binarchapter6.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "memo")
@Parcelize
data class Memo(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "memo") var memo: String?,
    @ColumnInfo(name = "date") var date: String?
) : Parcelable