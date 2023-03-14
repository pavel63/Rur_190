package com.pavelwintercompany.rur_190

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.pavelwintercompany.rur_190.database.AppDatabase
import com.pavelwintercompany.rur_190.databinding.FragmentFirstBinding
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.presentation.HoursAdapter
import com.pavelwintercompany.rur_190.utils.BaseFragment
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater)
        val view = binding.root

      //  setContentView(view)
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_first, container, false)
        return view
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
        with(binding.hoursListRv) {
            adapter = HoursAdapter(mockInt, quotaModelList)
            layoutManager = LinearLayoutManager(this@FirstFragment.context)
        }


        setupRvListener(binding.hoursListRv)
            .subscribe(
                { result ->
                    if (result.recyclerview ===  binding.hoursListRv) {

                        Toast.makeText(context, mockInt[result.idClicked], Toast.LENGTH_SHORT).show()
        Log.d("TAGG", mockInt[result.idClicked].toString())
                       // val popularItem = kindPaymentDtoList!![result.idClicked]
                       // kindOfPayment = popularItem .kindOfPayment
                    }}
                ,
                {
                        err->
                    err.toString()}
            )



    }


}