package com.pavelwintercompany.rur_190

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.pavelwintercompany.rur_190.database.AppDatabase
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.presentation.HoursAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateHourMock()
    }

    fun generateHourMock() {
        val mockHourList = arrayListOf<Int>()
       var i : Int = 0
        repeat(24) {
            mockHourList.add(
               i
            )
            i++
        }




        //setList(mockHourList)

        populateDb(mockHourList)
    }


fun populateDb(mockhour : List<Int>){
    GlobalScope.launch {
        val notesDao = getDb()

        notesDao.hourModelDao().insertAll(HourModel(Random.nextInt(), "ffgfgfgfgfggg",
            354545545435, 5, "fgfgfgfgg"))
        val notes: List<HourModel> = notesDao.hourModelDao().getAll()
        setList(mockhour, notes)

        val note = notes[0]
    }
}


    suspend fun getDb(): AppDatabase = Room.databaseBuilder(
        activity?.applicationContext!!,
        AppDatabase::class.java, "database-name"
    ).build()



    private fun setList(mockInt : List<Int>, quotaModelList : List<HourModel>){

        with(hours_list_rv) {
            adapter = HoursAdapter(mockInt, quotaModelList)
            layoutManager = LinearLayoutManager(this@FirstFragment.context)
        }

    }


}