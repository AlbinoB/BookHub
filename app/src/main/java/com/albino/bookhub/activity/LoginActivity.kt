package com.albino.bookhub.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.albino.bookhub.DatabaseScripts.Constants
import com.albino.bookhub.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap


class LoginActivity : AppCompatActivity() {

    lateinit var editTextUserName:EditText
    lateinit var editTextContactNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUserName=findViewById(R.id.editTextUserName)
        editTextContactNumber=findViewById(R.id.editTextContactNumber)


    }

    private fun registerUser(){

        val  userName=editTextUserName.text.toString()
        val contactNumber=editTextContactNumber.text.toString()

        var resp:String="holds the response"


        val url=Constants.URL_REGISTER+"?userName="+userName+"&contanctNumber="+contactNumber

        val request = JsonObjectRequest(Request.Method.GET,Constants.URL_REGISTER,null,
            Response.Listener { response ->
                // Process the json
                try {
                    editTextUserName.setText( "Response is: ${response}")
                }catch (e:Exception){
                    editTextUserName.setText( "Exception is: ${response}")
                }

            }, Response.ErrorListener{
                // Error in request
                editTextUserName.setText( "Error is: ${it}")
            })

        val requestQueue=Volley.newRequestQueue(this)
        requestQueue.getCache().clear();


        requestQueue.add(request)


/*
        val queue = Volley.newRequestQueue(this)
        val url = Constants.URL_REGISTER

// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                resp = "Response is: ${response.substring(0, 40)}"
            },
            Response.ErrorListener { resp = "That didn't work!" })

// Add the request to the RequestQueue.


        queue.add(stringRequest)


*/
/*


        val queue = Volley.newRequestQueue(this)
        val url = Constants.URL_REGISTER

// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                editTextUserName.setText( "Response is: ${response}")
            },
            Response.ErrorListener { editTextUserName.setText( "That didn't work!" )}

            ){

        }

// Add the request to the RequestQueue.
        queue.add(stringRequest)
*/

        //Toast.makeText(this,""+resp,Toast.LENGTH_LONG).show()
    }

    public fun buttonLoginRegister(view: View){
        registerUser()
    }

    fun buttonRegister(view: View) {}
    fun buttonLogin(view: View) {}
    fun funCheckConnectivity(view: View) {}
}
