package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.pashion_content.*
import kotlinx.android.synthetic.main.pashion_detail.*
import kotlinx.android.synthetic.main.pashion_detail_content.*
import kotlinx.android.synthetic.main.pashion_detail_image.*
import kotlinx.android.synthetic.main.pashion_detail_image.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.Buffer


/*****
 * 프로그램 ID : HAM-PA-600
 * 프로그램명 : PashionDetail.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.1
 * 버전 : v0.6
 */
class PashionDetail : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener  {

    // user의 상세 룩 정보가 담긴 JSON array
    var detail_JSON_ary:JSONArray?=null

    var view_list=ArrayList<View>()

    var bitmap :Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pashion_detail)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var user_name=intent.getStringExtra("user_name")
        var user_icon=intent.getStringExtra("user_icon")
        var user_pashion=intent.getStringExtra("user_pashion")


        var getdatathread=NetworkThread()
        getdatathread.start()
        getdatathread.join()


        for(i in 0 until detail_JSON_ary?.length()!!){

            var obj=detail_JSON_ary?.getJSONObject(i)
            var clothes_color=obj?.getString("color")
            var clothes_category=obj?.getString("category")
            var site=obj?.getString("clothes_image")

            var image_thread=ImageNetworkThead(site)
            image_thread.start()
            image_thread.join()

            var view=layoutInflater.inflate(R.layout.pashion_detail_image,null)
            view.clothesImg.setImageBitmap(bitmap)
            view.nameText.text="user : ${user_name}"
            view.colorText.text="color : ${clothes_color}"
            view.categoryText.text="category : ${clothes_category}"
            view_list.add(view)
        }


        look_detail_pager.adapter=CustomAdater()




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
            look_detail_pager.addView(view_list[position])
            return view_list[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            look_detail_pager.removeView(`object` as View)
        }
    }


    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http:172.20.10.4:8085/MobileServer/Look_detail_list.jsp"
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
                    buf.append(str)
                }
            }while(str!=null)

            var obj=JSONObject(buf.toString())
            detail_JSON_ary=obj.getJSONArray("clothes_array")

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
