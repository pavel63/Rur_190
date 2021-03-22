package com.pavelwintercompany.rur_190.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HourModel(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "taskDescribe") var taskDescribe : String,
    @ColumnInfo(name = "taskStartDime")  var taskStartDime : Long,
    @ColumnInfo(name = "taskDuration")  var taskDuration : Int,
    @ColumnInfo(name = "taskImage") var taskImage : String
)
