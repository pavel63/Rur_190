package com.pavelwintercompany.rur_190.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.pavelwintercompany.rur_190.R
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.extensions.dpToPx
import com.pavelwintercompany.rur_190.utils.DateHelper
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_inner_hour.view.*


class InnerHourAdapter(var quotaList: List<HourModel>, var itemWidth: Int) :
    RecyclerView.Adapter<InnerHourAdapter.QuotaViewHolder>() {

    override fun getItemCount() = quotaList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_inner_hour,
            parent,
            false
        )
        return QuotaViewHolder(view)
    }


    override fun onBindViewHolder(holder: QuotaViewHolder, position: Int) {
        holder.bind(quotaList[position])
    }


    inner class QuotaViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View
            get() = itemView

        fun bind(model: HourModel) {

            val widthPerMinute = (280.dpToPx().toDouble() / 60.0) * model.taskDuration

            val restmarg= quotaList.sortedBy { it.taskStartDime }.last()

            val rest = (DateHelper.formattedTime(restmarg.taskStartDime, "mm").toInt())+restmarg.taskDuration

            val finalMargin = DateHelper.formattedTime(model.taskStartDime,"mm").toInt()

            val fm = finalMargin-rest

           // val secondFin =  quotaList.sortedBy { it.taskStartDime }.take(position).sumBy {it.taskDuration.toInt()}

            //val restTimeForMargin = quotaList.sumBy { DateHelper.formattedTime(it.taskStartDime,"mm").toInt() }-DateHelper.formattedTime(model.taskStartDime,"mm").toInt()


            itemView.inner_hour_tv.text = model.taskDescribe
            val layoutParams = (FrameLayout.LayoutParams(
                widthPerMinute.toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            ))
            // TODO подисправить, а то как адаптер растягивается а то за пределы выходит
            layoutParams.setMargins(
                ((280.dpToPx().toDouble() / 60.0).toInt() - fm).toInt(), 0, 0, 0
            )
            itemView.inner_item_rv_main.layoutParams = layoutParams

            Picasso.get().load("https://pbs.twimg.com/media/D18BTryXcAAvBAm.jpg:large").into(itemView.inner_hour_iv);
        }

    }

}

