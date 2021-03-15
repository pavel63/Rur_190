package com.pavelwintercompany.rur_190

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.presentation.HoursAdapter
import kotlinx.android.synthetic.main.fragment_first.*

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

    fun generateHourMock(){
        val mockHourList = arrayListOf<HourModel>()
            repeat(24){
                mockHourList.add(HourModel(
                    "fdfdfdfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfg",
                    334434343434,
                    3434343434,
                    "dfdfdfdfdfdf"
                ))
        }

        setList(mockHourList)
    }

    private fun setList(quotaModelList : List<HourModel>){

        with(hours_list_rv) {
            adapter = HoursAdapter(quotaModelList)
            layoutManager = LinearLayoutManager(this@FirstFragment.context)
        }

    }
}