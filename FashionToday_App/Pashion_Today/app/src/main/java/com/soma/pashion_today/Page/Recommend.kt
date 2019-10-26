package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.recommend.*
import kotlinx.android.synthetic.main.recommend_content.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class Recommend : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    // 서버에서 받은 JSON 추천리스트
    var recommd_JSONAry:JSONArray?=null

    // Hashmap으로 구현한 추천리스트 : ListView에 사용
    var recommed_list=ArrayList<HashMap<String,Any>>()

    var profile_list=HashMap<String,Bitmap>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener { view->
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var recommend_adapter=ListAdapter()
        recommend_view.adapter=recommend_adapter


        var getDatathread=NetworkThread()
        getDatathread.start()
        getDatathread.join()

        for(i in 0 until recommd_JSONAry?.length()!!){

            var map=HashMap<String,Any>()

            var user_info=recommd_JSONAry?.getJSONObject(i)
            var user_name=user_info?.getString("name")
            var profile_site=user_info?.getString("profile_image")
            var schedule_info=user_info?.getJSONObject("schedule")
            var schedule_date=schedule_info?.getString("date")
            var schedule_star=schedule_info?.getString("star_num")
            var schedule_title=schedule_info?.getString("schedule_title")
            var schedule_intro=schedule_info?.getString("schedule_introduction")


            map.put("user_name",user_name!!)
            map.put("user_profile",profile_site!!)
            map.put("schedule_date",schedule_date!!)
            map.put("schedule_star",schedule_star!!)
            map.put("schedule_title",schedule_title!!)
            map.put("schedule_intro",schedule_intro!!)

            recommed_list.add(map)
        }

        runOnUiThread{
            var recommed_adapter=recommend_view.adapter as ListAdapter
            recommed_adapter.notifyDataSetChanged()
        }

        Log.d("msg","댓다")

        var listener=ListViewListener()
        recommend_view.setOnItemClickListener(listener)

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
                var intent=Intent(this,Recommend::class.java)
                startActivity(intent)
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

    // CustomAdapter 생성 : 이미지 부분 따로 저장해야한다.
    inner class ListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return recommed_list.size
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var v=convertView

            if(v==null){
                v=layoutInflater.inflate(R.layout.recommend_image,null)
            }

            var map=recommed_list.get(position)

            var profile=v?.findViewById<ImageView>(R.id.profileImg)
            var name=v?.findViewById<TextView>(R.id.nameText)
            var title=v?.findViewById<TextView>(R.id.titleText)
            var date=v?.findViewById<TextView>(R.id.dateText)
            var intro=v?.findViewById<TextView>(R.id.introText)
            var star=v?.findViewById<TextView>(R.id.starText)

            var user_name=map.get("user_name") as String
            var profile_site=map.get("user_profile") as String
            var schedule_date=map.get("schedule_date") as String
            var schedule_star=map.get("schedule_star") as String
            var schedule_title=map.get("schedule_title") as String
            var schedule_intro=map.get("schedule_intro") as String

            var profile_img=profile_list.get(profile_site)
            if(profile_img==null){
                var image=imgNetworkThread(profile_site)
                image.start()
            }
            else{
                profile?.setImageBitmap(profile_img)
            }

            name?.text=user_name
            title?.text=schedule_title
            date?.text=schedule_date
            intro?.text=schedule_intro
            star?.text=schedule_star

            return v!!

        }
    }

    inner class ListViewListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var recommend_detail_intent=Intent(applicationContext,RecommendDetail::class.java)
            startActivity(recommend_detail_intent)
        }
    }

    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http://172.16.101.14:8085/MobileServer/recommed_list.jsp"
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
            recommd_JSONAry=obj.getJSONArray("requestor_array")

        }
    }


    inner class imgNetworkThread(var site : String?): Thread(){
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var stream=conn.getInputStream()
            var bitmap=BitmapFactory.decodeStream(stream)

            profile_list.put(site!!,bitmap)
            runOnUiThread{
                var recommend_adapter=recommend_view.adapter as ListAdapter
                recommend_adapter.notifyDataSetChanged()
            }
        }
    }

}
