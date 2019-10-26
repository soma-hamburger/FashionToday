package com.soma.pashion_today.Page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.pashion_detail.*
import kotlinx.android.synthetic.main.recommend_detail_content.*
import org.json.JSONArray


class RecommendDetail : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {

    var closet_type=arrayOf("전체","상의","하의","신발")

    var recommend_JSON : JSONArray?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend_detail)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent= Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,closet_type)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        type_spinner.adapter=adapter



//        type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                when(position){
//                    0->{
//
//                    }
//                }
//            }
//        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pashion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.my_page_menu -> true
            R.id.notice_menu -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.menu_closet -> {
                var intent = Intent(this, Closet::class.java)
                startActivity(intent)
            }
            R.id.menu_daily_look -> {
                var intent= Intent(this,DailyLook::class.java)
                startActivity(intent)
            }
            R.id.menu_calendar -> {
                var intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_recommend -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }




}
