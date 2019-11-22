package com.soma.Pashion_Today.Page

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
import com.soma.Pashion_Today.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import kotlinx.android.synthetic.main.recommend.*
import kotlinx.android.synthetic.main.recommend_content.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/*****
 * 프로그램 ID : HAM-PA-700
 * 프로그램명 : Recommend.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.19
 * 버전 : v0.9
 */
class Recommend : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    // 서버에서 받은 JSON 추천리스트
    var recommd_JSONAry:JSONArray?=null

    // Hashmap으로 구현한 추천리스트 : ListView에 사용
    var recommed_list=ArrayList<HashMap<String,Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setBackgroundResource(R.drawable.menu_back)
        header_view.setOnClickListener { view->
            var intent=Intent(this,Pashion::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        // 데이터 받기
        var Getdata_thread=NetworkThread()
        Getdata_thread.start()
        Getdata_thread.join()

        var recommend_adapter=ListAdapter()
        recommend_view.adapter=recommend_adapter

        for(i in 0 until recommd_JSONAry?.length()!!){

            // 사용자 정보
            var user_info=recommd_JSONAry?.getJSONObject(i)
            var user_id=user_info?.getInt("id")
            var user_name=user_info?.getString("name")
            var profile_site=user_info?.getString("profile_image")
            var user_intro=user_info?.getString("self_introduction")
            var user_grade=user_info?.getString("grade")

            // 일정 정보
            var schedule_info=user_info?.getJSONObject("schedule")
            var schedule_date=schedule_info?.getInt("date")!!
            var schedule_star=schedule_info?.getString("star_num")
            var schedule_title=schedule_info?.getString("title")
            var schedule_intro=schedule_info?.getString("introduce")



            var map=HashMap<String,Any>()

            map.put("user_id",user_id!!)
            map.put("user_name",user_name!!)
            map.put("user_profile",profile_site!!)
            map.put("user_intro",user_intro!!)
            map.put("user_grade",user_grade!!)

            map.put("schedule_date",schedule_date)
            map.put("schedule_star",schedule_star!!)
            map.put("schedule_title",schedule_title!!)
            map.put("schedule_intro",schedule_intro!!)

            recommed_list.add(map)
        }

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

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        var actionView=layoutInflater.inflate(R.layout.action_bar,null)
        supportActionBar?.customView=actionView
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_daily_look -> {
                var intent=Intent(this,DailyLook::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_calendar -> {
                var intent = Intent(this, CalendarActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
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

            var profile=v?.findViewById<ImageView>(R.id.recommend_userprofile)
            var name=v?.findViewById<TextView>(R.id.recommend_name)
            var grade=v?.findViewById<TextView>(R.id.recommend_grade)
            var intro=v?.findViewById<TextView>(R.id.recommend_intro)


            var title=v?.findViewById<TextView>(R.id.recommend_title)
            var date=v?.findViewById<TextView>(R.id.recommend_date)
            var look_intro=v?.findViewById<TextView>(R.id.look_intro)
            var date_dday=v?.findViewById<TextView>(R.id.recommend_dday)
            var recommend_btn=v?.findViewById<Button>(R.id.recommend_button)

            var user_id=map.get("user_id") as Int
            var user_name=map.get("user_name") as String
            var profile_site=map.get("user_profile") as String
            var user_grade=map.get("user_grade") as String
            var user_intro=map.get("user_intro") as String

            var schedule_date=map.get("schedule_date") as Int
            var schedule_title=map.get("schedule_title") as String
            var schedule_intro=map.get("schedule_intro") as String

            // date 계산 과정
            var year=schedule_date/10000
            schedule_date=schedule_date%10000
            var month=(schedule_date/100)-1
            schedule_date=schedule_date%100
            var day=schedule_date
            var temp_day=Calendar.getInstance()
            temp_day.set(year,month,day)
            var temp_millions=temp_day.timeInMillis
            var today_millions=Calendar.getInstance().timeInMillis
            var dday=(temp_millions-today_millions)/(24*60*60*1000)

            var recommend_dday : String?=null

            if(dday>0){
                date_dday?.text="D-${dday}"
                recommend_dday="D-${dday}"
            }
            else if(dday<0){
                dday*=-1
                date_dday?.text="D+${dday}"
                recommend_dday="D+${dday}"
            }
            else{
                date_dday?.text="D-day"
                recommend_dday="D-day"
            }

            if(profile_site!="null"){
                Picasso.with(applicationContext).load(profile_site).into(profile)
            }

            name?.text=user_name
            grade?.text=user_grade
            intro?.text=user_intro

            title?.text=schedule_title
            date?.text="${year}년 ${month+1}월 ${day}일"
            look_intro?.text=schedule_intro
            recommend_btn?.setText("${user_name} 님의 옷장")

            var recommend_date : String="${year}년 ${month+1}월 ${day}일"

            recommend_btn?.setOnClickListener { view->
                var intent=Intent(applicationContext,RecommendDetail::class.java)
                intent.putExtra("user_id",user_id)
                intent.putExtra("profile_site",profile_site)
                intent.putExtra("user_name",user_name)
                intent.putExtra("user_grade",user_grade)
                intent.putExtra("user_intro",user_intro)
                intent.putExtra("schedule_date",recommend_date)
                intent.putExtra("schedule_dday",recommend_dday)
                intent.putExtra("schedule_title",schedule_title)
                intent.putExtra("schedule_intro",schedule_intro)
                startActivity(intent)
            }

            return v!!

        }
    }


    inner class NetworkThread : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=okhttp3.Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/recommend/list")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")


            var request=request_builder.build()
            var response=client.newCall(request).execute()
            Log.d("msg","${response}")
            var body=response.body!!.string()
            Log.d("msg","${body}")

            var obj=JSONObject(body)
            recommd_JSONAry=obj.getJSONArray("requestor_array")

        }
    }


}
