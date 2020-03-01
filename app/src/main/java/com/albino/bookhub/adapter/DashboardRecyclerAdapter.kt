package com.albino.bookhub.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albino.bookhub.R
import kotlinx.android.synthetic.main.recycler_dashboard_single_row.view.*



class DashboardRecyclerAdapter:RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {//binds the adpater to the viewholder class

    class DashboardViewHolder(view:View):RecyclerView.ViewHolder(view){//creating the view holder
        val textView:TextView=view.findViewById(R.id.textViewRecyclerItem)
    }




    //these methods are called for setting the adapter to the list
    //if we there are 100 view this method will only create the initial 10 views which are displayed and recycle them when the user scrolls
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}