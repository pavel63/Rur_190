package com.pavelwintercompany.rur_190.extensions

import androidx.recyclerview.widget.RecyclerView

class ViewHolder {

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition())
        }
        return this
    }
}