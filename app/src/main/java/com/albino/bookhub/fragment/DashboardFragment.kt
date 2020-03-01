package com.albino.bookhub.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albino.bookhub.R
import com.albino.bookhub.adapter.DashboardRecyclerAdapter

/**
 * A simple [Fragment] subclass.
 */



class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter:DashboardRecyclerAdapter

    val bookList= arrayListOf<String>(
        "P.S I Love You",
        "The Great Gatsby",
        "Madame Bovary",
        "War and peace",
        "Lolita",
        "The Lord of the Ring",
        "Madame Bovary",
        "War and peace",
        "Lolita",
        "The Lord of the Ring"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard=view.findViewById(R.id.recyclerView)//view.findViewBy() is used as this a fragment and after the layout is inflated onto the view val, we can find the id.

        layoutManager=LinearLayoutManager(activity)//as this is a fragment we cannot pass this as the parameter.Instead we pass activity
        // Inflate the layout for this fragment

        recyclerAdapter= DashboardRecyclerAdapter(activity as Context,bookList)//instance of the adapter with the context and data as parameters
        //as is used for type casting


        recyclerDashboard.adapter=recyclerAdapter//attaching the adapter to the respective file

        recyclerDashboard.layoutManager=layoutManager//attaching the adapter to the respective file
        //the list is setup now


        return view
    }


}
