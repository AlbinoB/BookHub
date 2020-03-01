package com.albino.bookhub.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albino.bookhub.R

/**
 * A simple [Fragment] subclass.
 */



class DashboardFragment : Fragment() {

    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView=view.findViewById(R.id.recyclerView)//view.findViewBy() is used as this a fragment and after the layout is inflated onto the view val, we can find the id.

        layoutManager=LinearLayoutManager(activity)//as this is a fragment we cannot pass this as the parameter.Instead we pass activity
        // Inflate the layout for this fragment
        return view
    }


}
