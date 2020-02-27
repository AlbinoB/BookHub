package com.albino.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var coordinatorLayout:CoordinatorLayout
    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView
    lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolBar)
        frameLayout=findViewById(R.id.frameLayout)
        navigationView=findViewById(R.id.navigationView)
        drawerLayout=findViewById(R.id.drawerLayout)

        val actionBarDrawerToggle=  ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,//hamburger icon to open
            R.string.close_drawer)//once opened it changes to back arrow

        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()//to sync with the state of the navigation toggle with the state of the navigation drawer


        navigationView.setNavigationItemSelectedListener {

            when(it.itemId){//it holds the id of the currently selected item
                R.id.menu_item_dasboard->{
                    Toast.makeText(this@MainActivity,"dashboard",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_favourite->{
                    Toast.makeText(this@MainActivity,"favourites",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_profile->{
                    Toast.makeText(this@MainActivity,"profile",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_about->{
                    Toast.makeText(this@MainActivity,"about",Toast.LENGTH_SHORT).show()
                }
            }

            return@setNavigationItemSelectedListener true
        }

        setToolBar()
    }

    fun setToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Tool Bar"
        supportActionBar?.setHomeButtonEnabled(true)//enables the button on the tool bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//displays the icon on the button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)//change icon to custom
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId

        if(id==android.R.id.home){//home id is the id of the button on the tool bar
            drawerLayout.openDrawer(GravityCompat.START)//start he drawer from the start
        }

        return super.onOptionsItemSelected(item)
    }
}
