package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.daily_look.*
import kotlinx.android.synthetic.main.daily_look_content.*
import kotlinx.android.synthetic.main.daily_look_image.view.*
import kotlinx.android.synthetic.main.menu_bar_image.view.*
import kotlinx.android.synthetic.main.pashion_content.*
import kotlinx.android.synthetic.main.pashion_detail_content.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL



/*****
 * 프로그램 ID : HAM-PA-300
 * 프로그램명 : DailyLook.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.1
 * 버전 : v0.6
 */
class DailyLook : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {


    var daily_look_JSON_ary:JSONArray?=null

    var bitmap:Bitmap?=null

    var view_list=ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_look)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view= nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var getdatathread=NetworkThread()
        getdatathread.start()
        getdatathread.join()

        for(i in 0 until daily_look_JSON_ary?.length()!!){
            var obj=daily_look_JSON_ary?.getJSONObject(i)
            var recommender_name=obj?.getString("recommender_name")
            var user_image_site=obj?.getString("recommender_profile_image")
            var look_image_site=obj?.getString("look_image")

            var view=layoutInflater.inflate(R.layout.daily_look_image,null)
            view.recommend_usertext.text="${recommender_name}"

            var user_imagethread=ImageNetworkThead(user_image_site)
            user_imagethread.start()
            user_imagethread.join()

            view.profile_imageview.setImageBitmap(bitmap)

            var look_imagethread=ImageNetworkThead(look_image_site)
            look_imagethread.start()
            look_imagethread.join()

            view.dailylook_image.setImageBitmap(bitmap)
            view.setOnClickListener {
                var intent=Intent(this,DailyLookDetail::class.java)
                startActivity(intent)
            }
            view_list.add(view)
        }

        daily_look_pager.adapter=CustomAdater()


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

    inner class CustomAdater : PagerAdapter(){
        override fun getCount(): Int {
            return view_list.size
        }

        // 현재 객체와 구분지을 객체랑 비교
        // 안드로이드 os가 페이지를 구성하기 위해 객체를 자동 생성
        // 안드로이드가 내부적으로 필요해서 만듬
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        // pager의 객체에 보여질 뷰를 집어 넣는 함수
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            daily_look_pager.addView(view_list[position])
            return view_list[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            daily_look_pager.removeView(`object` as View)
        }
    }

    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/daily_look_list.jsp"
            var url= URL(site)
            var conn=url.openConnection()
            var input=conn.getInputStream()
            var isr= InputStreamReader(input)
            var br= BufferedReader(isr)

            var str:String?=null
            var buf=StringBuffer()

            do{
                str=br.readLine()
                if(str!=null){
                    buf.append(str)
                }
            }while(str!=null)

            var obj= JSONObject(buf.toString())
            daily_look_JSON_ary=obj.getJSONArray("daily_look_array")

        }
    }

    inner class ImageNetworkThead(var site :String?): Thread(){
        override fun run() {
            var url = URL(site)
            var conn = url.openConnection()
            var stream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(stream)
        }
    }
}
