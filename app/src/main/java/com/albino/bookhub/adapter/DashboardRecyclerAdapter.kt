package com.albino.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albino.bookhub.R


//in the constructor val context:Context where to display it and val itemList:ArrayList<String> the data to be displayed
class DashboardRecyclerAdapter(val context:Context,val itemList:ArrayList<String>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {//binds the adpater to the viewholder class

    class DashboardViewHolder(view:View):RecyclerView.ViewHolder(view){//creating the view holder
        val textView:TextView=view.findViewById(R.id.textViewBookName)
    }




    //these methods are called for setting the adapter to the list
    //if we there are 100 view this method will only create the initial 10 views which are displayed and recycle them when the user scrolls
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)//inflate the recycler view to return it
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size//returns the size of the itemList data from the constructor
    }


    //is used to display the right view in the right position
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val text=itemList[position]//gets the item from the itemList sent in the constructor at the position
        holder.textView.text=text//fill in the recieved data in the holder
    }
}