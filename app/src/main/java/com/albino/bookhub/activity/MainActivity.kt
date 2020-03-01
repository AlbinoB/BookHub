package com.albino.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.albino.bookhub.fragment.DashboardFragment
import com.albino.bookhub.fragment.FavouriteFragment
import com.albino.bookhub.fragment.ProfileFragment
import com.albino.bookhub.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var coordinatorLayout:CoordinatorLayout
    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView
    lateinit var drawerLayout:DrawerLayout

    var previousMenuItemSelected:MenuItem?=null

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
            R.string.close_drawer
        )//once opened it changes to back arrow

        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()//to sync with the state of the navigation toggle with the state of the navigation drawer

        openDashboard()//set the first fragment to the dashboard when the app opens

        navigationView.setNavigationItemSelectedListener {


            if(previousMenuItemSelected!=null){
                previousMenuItemSelected?.setChecked(false)
            }

            previousMenuItemSelected=it//the current fragment will be previos fragment when a user clicks a new fragment

            it.setCheckable(true)//if not the first time set the current to checked
            it.setChecked(true)


            when(it.itemId){//it holds the id of the currently selected item
                R.id.menu_item_dasboard ->{
                    openDashboard()//function called below as the same code is present in the oncreate of the activity
                    drawerLayout.closeDrawers()


                    Toast.makeText(this@MainActivity,"dashboard",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_favourite ->{
                    //the supportFragmentManager is responsible for starting a new fragment and each call is called a transaction
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            FavouriteFragment()
                        )//replace the old layout with the new frag  layout
                        //.addToBackStack("Favourite")//so that the app foesnt close on clicking the backbtn, it store the fragments in backstack and goies to previous fragment
                        .commit()//apply changes

                    supportActionBar?.title="Favourite"//change the title when each new fragment is opened

                    drawerLayout.closeDrawers()

                    Toast.makeText(this@MainActivity,"favourites",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_profile ->{

                    //the supportFragmentManager is responsible for starting a new fragment and each call is called a transaction
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            ProfileFragment()
                        )//replace the old layout with the new frag  layout
                        //.addToBackStack("Favourite")//so that the app foesnt close on clicking the backbtn, it store the fragments in backstack and goies to previous fragment
                        .commit()//apply changes

                    supportActionBar?.title="Profile"//change the title when each new fragment is opened

                    drawerLayout.closeDrawers()


                    Toast.makeText(this@MainActivity,"profile",Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_about ->{
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


    fun openDashboard(){
        val fragment= DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()

        transaction.replace(
            R.id.frameLayout,
            DashboardFragment()
        )//replace the old layout with the new frag  layout

        transaction .commit()//apply changes

        supportActionBar?.title="DashBoard"//change the title when each new fragment is opened


        navigationView.setCheckedItem(R.id.menu_item_dasboard)//is used to set the default dashboard to checked when the app opens or the back btn is pressed to go to the previous fragment
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frameLayout)//gets the id og=f the current fragment

        when(currentFragment){
            !is DashboardFragment -> openDashboard()
            else ->super.onBackPressed()
        }
    }



}
