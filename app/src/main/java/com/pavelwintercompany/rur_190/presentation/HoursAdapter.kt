package com.pavelwintercompany.rur_190.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavelwintercompany.rur_190.R
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.utils.DateHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.hour_row.view.*
import kotlinx.android.synthetic.main.row_inner_hour.view.*
import kotlin.random.Random

class HoursAdapter(var quotaList: List<Int>, var tasksList : List<HourModel>) :
    RecyclerView.Adapter<HoursAdapter.QuotaViewHolder>() {

    override fun getItemCount() = quotaList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hour_row, parent, false)
        return QuotaViewHolder(view)
    }


    override fun onBindViewHolder(holder: QuotaViewHolder, position: Int) {
        holder.bind(quotaList[position])
    }


    inner class QuotaViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(model: Int) {

            itemView.hours_time_tv.text = hoursDescribeGenerator(adapterPosition)

                val mockHourList = arrayListOf<HourModel>()




            val newList = tasksList.filter { DateHelper.formattedTime(it.taskStartDime,"hh").toInt()== position }

                setList(itemView.context, itemView.inner_hour_rv, newList, itemView.width)

        }

    }


    private fun setList(context : Context,
                        recyclerView : RecyclerView,
                        quotaModelList : List<HourModel>,
                        itemWidth : Int){

        with(recyclerView) {
            adapter = InnerHourAdapter(quotaModelList, itemWidth)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

    }


    fun hoursDescribeGenerator(itemOrder : Int): String {
        var secondDigit = 0
        if(itemOrder!=23){
            secondDigit = itemOrder+1
        }
        return nolAdder(itemOrder)+"-"+nolAdder(secondDigit)
    }


    fun nolAdder(digit : Int): String{

        return if(digit<10){
            "0$digit"
        }else{
            digit.toString()
        }

    }

}

