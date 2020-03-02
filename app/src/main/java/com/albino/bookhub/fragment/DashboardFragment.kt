package com.albino.bookhub.fragment


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albino.bookhub.R
import com.albino.bookhub.adapter.DashboardRecyclerAdapter
import com.albino.bookhub.model.Book
import com.albino.bookhub.utils.ConnnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */



class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter:DashboardRecyclerAdapter

/*
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
*/

    var bookInfoList= arrayListOf<Book>()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard=view.findViewById(R.id.recyclerView)//view.findViewBy() is used as this a fragment and after the layout is inflated onto the view val, we can find the id.

        layoutManager=LinearLayoutManager(activity)//as this is a fragment we cannot pass this as the parameter.Instead we pass activity
        // Inflate the layout for this fragment


        if(ConnnectionManager().checkConnectivity(activity as Context)){


            val queue=Volley.newRequestQueue(activity as Context)

            val url="http://13.235.250.119/v1/book/fetch_books/"

            val jsonObjectRequest=object:JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    println("Response12 is "+it)

                    val success=it.getBoolean("success")
                    if(success){

                        val data=it.getJSONArray("data")

                        for(i in 0 until data.length()){
                            val bookJsonObject=data.getJSONObject(i)
                            val bookObject=Book(
                                bookJsonObject.getString("book_id"),
                                bookJsonObject.getString("name"),
                                bookJsonObject.getString("author"),
                                bookJsonObject.getString("rating"),
                                bookJsonObject.getString("price"),
                                bookJsonObject.getString("image")
                            )
                            bookInfoList.add(bookObject)


                            recyclerAdapter= DashboardRecyclerAdapter(activity as Context,bookInfoList)//instance of the adapter with the context and data as parameters
                            //as is used for type casting


                            recyclerDashboard.adapter=recyclerAdapter//attaching the adapter to the respective file

                            recyclerDashboard.layoutManager=layoutManager//attaching the adapter to the respective file
                            //the list is setup now

                            //spacing between list items
                            recyclerDashboard.addItemDecoration(
                                DividerItemDecoration(
                                    recyclerDashboard.context,(layoutManager as LinearLayoutManager).orientation
                                )
                            )
                        }



                    }
                },
                Response.ErrorListener {
                    println("Error12 is "+it)

                    Toast.makeText(activity as Context,"Some Error occurred!!!",Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers=HashMap<String,String>()

                    headers["Content-type"]="application/json"
                    headers["token"]="acdc385cfd7264"

                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }else
        {
            val alterDialog=androidx.appcompat.app.AlertDialog.Builder(activity as Context)

            alterDialog.setTitle("No Internet")
            alterDialog.setMessage("Internet Connection can't be establish!")
            alterDialog.setPositiveButton("Open Settings"){text,listener->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)//open wifi settings
                startActivity(settingsIntent)
                activity?.finish()
            }

            alterDialog.setNegativeButton("Exit"){ text,listener->
                ActivityCompat.finishAffinity(activity as Activity)//closes all the instances of the app and the app closes completely
            }
            alterDialog.create()
            alterDialog.show()

        }

        return view
    }



}
