package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.daily_look_detail_content.*
import kotlinx.android.synthetic.main.daily_look_detail_image.view.*
import kotlinx.android.synthetic.main.pashion_detail.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class DailyLookDetail : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{


    var recommender_obj:JSONObject?=null
    // 데일리룩 구성옷 리스트
    var daily_clotheslist:JSONArray?=null

    var bitmap:Bitmap?=null

    var clothes_image_list=ArrayList<View>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_look_detail)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var getdatathread=NetworkThread()
        getdatathread.start()
        getdatathread.join()

        for(i in 0 until daily_clotheslist?.length()!!){

            var obj=daily_clotheslist?.getJSONObject(i)
            var image_category=obj?.getString("category")
            var image_color=obj?.getString("color")
            var image_site=obj?.getString("clothes_image")

            var clothes_imagethread=ImageNetworkThread(image_site)
            clothes_imagethread.start()
            clothes_imagethread.join()

            var view=layoutInflater.inflate(R.layout.daily_look_detail_image,null)
            view.recommder_Text.text=recommender_obj?.getString("name")
            view.recommder_clothesImg.setImageBitmap(bitmap)
            view.clothes_categoryText.text="종류 : ${image_category}"
            view.clothes_colorText.text="색상 : ${image_color}"

            var recommender_imagethread=ImageNetworkThread(recommender_obj?.getString("profile_image"))
            recommender_imagethread.start()
            recommender_imagethread.join()

            view.recommder_profileImg.setImageBitmap(bitmap)

            clothes_image_list.add(view)
        }
        daily_clothes_pager.adapter=CustomAdapter()



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
                var intent=Intent(this,DailyLook::class.java)
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

    inner class CustomAdapter : PagerAdapter(){
        override fun getCount(): Int {
            return clothes_image_list.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            daily_clothes_pager.addView(clothes_image_list[position])
            return clothes_image_list[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            daily_clothes_pager.removeView(`object` as View)
        }

    }


    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http://172.16.100.158:8085/MobileServer/daily_look_list_detail.jsp"
            var url=URL(site)
            var conn=url.openConnection()
            var input=conn.getInputStream()
            var isr=InputStreamReader(input)
            var br=BufferedReader(isr)

            var str:String?=null
            var buf=StringBuffer()

            do{
                str=br.readLine()
                if(str!=null){
                    buf.append(str);
                }
            }while(str!=null)

            var obj=JSONObject(buf.toString())
            recommender_obj=obj.getJSONObject("recommender")
            daily_clotheslist=obj.getJSONArray("clothes_array")


        }
    }

    inner class ImageNetworkThread(var site : String?) : Thread(){
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var stream=conn.getInputStream()
            bitmap=BitmapFactory.decodeStream(stream)
        }


    }
}
