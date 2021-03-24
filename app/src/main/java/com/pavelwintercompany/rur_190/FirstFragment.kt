package com.pavelwintercompany.rur_190

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.pavelwintercompany.rur_190.database.AppDatabase
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.presentation.HoursAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

   private fun generateHourMock() {
        val mockHourList = arrayListOf<Int>()
       var i = 0
        repeat(24) {
            mockHourList.add(
               i
            )
            i++
        }

        GlobalScope.launch {
            val notes: List<HourModel> = getDb().hourModelDao().getAll()
            setList(mockHourList, notes)
        }

    }


    private fun getDb(): AppDatabase = Room.databaseBuilder(
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