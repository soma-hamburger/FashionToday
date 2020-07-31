package com.soma.Pashion_Today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.navigation.NavigationView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.soma.Pashion_Today.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.daily_look.*
import kotlinx.android.synthetic.main.daily_look_content.*
import kotlinx.android.synthetic.main.daily_look_view.view.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import okhttp3.MediaType.Companion.toMediaType
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/*****
 * 프로그램 ID : HAM-PA-300
 * 프로그램명 : DailyLook.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.19
 * 버전 : v0.9
 */
class DailyLook : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    // 데일리 룩 상태
    var Daily_state : Boolean?=null

    // 서버에서 받은 데일리 룩 JSONAry
    var Daily_look_JSONAry:JSONArray?=null

    // 서버에서 받은 디테일 데일리 룩 정보
    var Detail_JSONObj : JSONObject?=null

    // 추천 상세 룩 리스트
    var clothes_array=ArrayList<HashMap<String,Any>>()

    // ViewPager에 들어갈 뷰들의 집합
    var view_list=ArrayList<View>()

    // 옷 변수 png list
    var type_imglist= mapOf<String,Int>(
        "accesory" to R.drawable.accesory_icon,
        "bag" to R.drawable.bag_icon,
        "dress" to R.drawable.dress_icon,
        "hat" to R.drawable.hat_icon,
        "jean" to R.drawable.jean_icon,
        "shirts" to R.drawable.shirts_icon,
        "shoes" to R.drawable.shoes_icon,
        "tee" to R.drawable.tee_icon,
        "pants" to R.drawable.jean_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_look)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view= nav_view.getHeaderView(0)
        header_view.setBackgroundResource(R.drawable.menu_back)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        var thread=UploadThread()
        thread.start()
        thread.join()

        if(Daily_state==false){
            daily_look_pager.visibility=View.VISIBLE
            err_img.visibility=View.GONE
            err_text.visibility=View.GONE

            var Get_datathread=NetworkThread()
            Get_datathread.start()
            Get_datathread.join()

            var today=CalendarDay.today()
            var year=today.year
            var month=today.month
            var day=today.day

            for(i in 0 until Daily_look_JSONAry?.length()!!){
                var obj=Daily_look_JSONAry?.getJSONObject(i)
                var look_image=obj?.getString("look_image")
                var look_id=obj?.getInt("look_id")!!

                var recommender=obj?.getJSONObject("recommender")
                var name=recommender?.getString("name")!!
                var user_image=recommender?.getString("profile_image")
                var grade=recommender?.getInt("grade")


                var view=layoutInflater.inflate(R.layout.daily_look_view,null)
                view.daily_date.text="${year}년 ${month+1}월 ${day}일"
                view.daily_recommender.text=name
                view.daily_grade.text="${grade}"

                // 추천자 이미지 받기
                if(user_image!="null"){
                    var profile_view=view.findViewById<ImageView>(R.id.daily_profile)
                    Picasso.with(this).load(user_image).into(profile_view)
                }

                // 데일리 룩 이미지 받기
                var look_view=view.findViewById<ImageView>(R.id.daily_img)
                Picasso.with(this).load(look_image).into(look_view)

                /////////데일리 룩 상세 표시 ////////////
                var detail_thread=DetailNetworkThread(look_id)
                detail_thread.start()
                detail_thread.join()

                var title=Detail_JSONObj?.getString("look_title")
                var intro=Detail_JSONObj?.getString("look_introduction")
                view.daily_title.text=title
                view.daily_intro.text=intro

                var list=Detail_JSONObj?.getJSONArray("clothes_array")

                var daily_adapter=ListAdapter()
                view.daily_gridview.adapter=daily_adapter
                view.daily_gridview.SetExpanded(true)

                clothes_array.clear()
                for(i in 0 until list?.length()!!){
                    var obj=list.getJSONObject(i)
                    var color=obj.getString("color")
                    var category=obj.getString("category")
                    var img_site=obj.getString("clothes_image")

                    var map=HashMap<String,Any>()
                    map.put("color",color)
                    map.put("category",category)
                    map.put("image",img_site)

                    clothes_array.add(map)
                }

                view_list.add(view)
            }

            daily_look_pager.adapter=CustomAdater()
        }
        else{
            daily_look_pager.visibility=View.GONE
            err_img.visibility=View.VISIBLE
            err_text.visibility=View.VISIBLE
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

        // 커스텀 뷰 사용하기 위한 작업
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
                startActivity(intent)
            }
            R.id.menu_calendar -> {
                var intent = Intent(this, CalendarActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_recommend -> {
                var intent=Intent(this,Recommend::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
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

    inner class ListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return clothes_array.size
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
                v=layoutInflater.inflate(R.layout.pashion_poplook_image,null)
            }

            var look_img=v?.findViewById<ImageView>(R.id.clothes)
            var look_info=v?.findViewById<TextView>(R.id.clothes_info)
            var look_type=v?.findViewById<ImageView>(R.id.clothes_type)

            var map=clothes_array.get(position)

            var color=map.get("color") as String
            var category=map.get("category") as String
            var image=map.get("image") as String

            Picasso.with(applicationContext).load(image).into(look_img)
            look_info?.text="${category}, ${color}"
            look_type?.setImageResource(type_imglist.get(category)!!)


            return v!!


        }
    }

    inner class NetworkThread : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/dailylist")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")

            var request=request_builder.build()
            var response=client.newCall(request).execute()
            var body=response.body!!.string()

            Log.d("msg","${body}")
            var obj=JSONObject(body)
            Daily_look_JSONAry=obj.getJSONArray("daily_look_array")

        }
    }

    inner class DetailNetworkThread(var lookid : Int) : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/dailylook")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")

            var json_form=JSONObject()
            json_form.put("look_id",lookid)

            var body=RequestBody.create("application/json".toMediaType(),json_form.toString())

            var post=url.post(body)
            var request=post.build()
            var response=client.newCall(request).execute()

            var result=response.body!!.string()

            Detail_JSONObj= JSONObject(result)

        }
    }


    inner class UploadThread : Thread(){
        override fun run() {
            var client= OkHttpClient()
            var request_builder= Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/user/info")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")


            var formBody= FormBody.Builder().build()

            var post=url.post(formBody)
            var request=post.build()
            var response=client.newCall(request).execute()

            var body=response.body!!.string()
            var obj=JSONObject(body)
            Log.d("msg","${body}")
            Daily_state=obj.getBoolean("select")
        }
    }

}
