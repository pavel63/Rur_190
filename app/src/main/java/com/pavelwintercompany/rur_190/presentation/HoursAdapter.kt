package com.pavelwintercompany.rur_190.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavelwintercompany.rur_190.R
import com.pavelwintercompany.rur_190.entity.HourModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.hour_row.view.*

class HoursAdapter(var quotaList: List<HourModel>) :
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

        fun bind(model: HourModel) {

            itemView.hours_time_tv.text = hoursDescribeGenerator(adapterPosition)
            itemView.hour_row_task_name.text = model.taskDescribe
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

