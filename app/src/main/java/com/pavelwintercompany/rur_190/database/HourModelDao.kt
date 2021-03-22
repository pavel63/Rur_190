package com.pavelwintercompany.rur_190.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pavelwintercompany.rur_190.entity.HourModel

@Dao
interface HourModelDao {
    @Query("SELECT * FROM hourmodel")
    fun getAll(): List<HourModel>

    @Insert
    fun insertAll(vararg notes: HourModel)


    /*  @Query("SELECT * FROM user WHERE uid IN (:userIds)")
      fun loadAllByIds(userIds: IntArray): List<User>

      @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
              "last_name LIKE :last LIMIT 1")
      fun findByName(first: String, last: String): User


      @Delete
      fun delete(user: User)*/
}