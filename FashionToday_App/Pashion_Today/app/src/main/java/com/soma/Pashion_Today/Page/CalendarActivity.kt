package com.soma.Pashion_Today.Page

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.soma.Pashion_Today.R
import com.google.android.material.navigation.NavigationView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.calendar_content.*
import kotlinx.android.synthetic.main.calendar_popup.view.*
import kotlinx.android.synthetic.main.calendar_popup2.view.*
import kotlinx.android.synthetic.main.daily_look.nav_view
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import okhttp3.MediaType.Companion.toMediaType

/*****
 * 프로그램 ID : HAM-PA-100
 * 프로그램명 : CalendarActivity.kt
 * 작성자명 : 오원석
 * 작성일자 : 2019.11.31
 * 버전 : v0.6
 */
class CalendarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // 일정있는 날짜 리스트
    var date_list=ArrayList<CalendarDay>()

    // 상세 옷들을 담을 JSON Array
    var JSON_Ary:JSONArray?=null

    // 서버로 요청받은 JSON Object
    var JSON_Obj:JSONObject?=null

    // 상세 옷들을 가지고 있는 리스트
    var recommend_list=ArrayList<HashMap<String,Any>>()

    // 옷 변수 png list
    var clothes_list= mapOf<String,Int>(
        "accessory" to R.drawable.accesory_icon,
        "bag" to R.drawable.bag_icon,
        "dress" to R.drawable.dress_icon,
        "hat" to R.drawable.hat_icon,
        "jean" to R.drawable.jean_icon,
        "shirts" to R.drawable.shirts_icon,
        "shoes" to R.drawable.shoes_icon,
        "tee" to R.drawable.tee_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view= nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        var date_thread=NetworkThread()
        date_thread.start()
        date_thread.join()

        var date_text=findViewById<TextView>(R.id.date_Text)
        var today_Date=CalendarDay.today()
        date_text.text="${today_Date.year}년 ${today_Date.month+1}월 ${today_Date.day}일"
        dday_Text.text="D-day"
        star_image.visibility=View.GONE
        star_text.visibility=View.GONE

        for(i in 0 until JSON_Ary?.length()!!){
            var obj=JSON_Ary?.getJSONObject(i)
            var date=obj?.getInt("date")!!

            var year=date/10000
            date=date%10000
            var month=(date/100)-1
            date=date%100
            var day=date

            var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
            var calendar_day=CalendarDay(year,month,day)

            date_list.add(calendar_day)
            calendar_view.addDecorator(EventDecorator(Color.RED,date_list))

        }

        // 달력 데코레이션
        var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
        calendar_view.addDecorators(SundayDecorator(),SaturdayDecorator(),TodayDecorator())


        calendar_view.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            date_Text.text="${date.year}년 ${date.month+1}월 ${date.day}일"

            var temp_Date=CalendarDay(date.year,date.month,date.day)

            var temp_Day=Calendar.getInstance()
            temp_Day.set(temp_Date.year,temp_Date.month,temp_Date.day)

            var temp_millions=temp_Day.timeInMillis

            var today_millions=Calendar.getInstance().timeInMillis
            // 디데이 계산
            var dday=(temp_millions-today_millions)/(24*60*60*1000)

            var date_int=date.year*10000+(date.month+1)*100+date.day
            //////////// 미래일정 //////////////
            if(dday>=0){
                if(dday>0){
                    dday_Text.text="D-${dday}"
                }
                else {
                    dday_Text.text = "D-day"
                }

                calendar_button.setText("일정등록")
                var check=false
                for(i in 0 until date_list.size){
                    var calendar_day=date_list.get(i)
                    if(calendar_day==temp_Date){
                        check=true
                        break
                    }
                }
                if(check){
                    star_image.visibility=View.VISIBLE
                    star_text.visibility=View.VISIBLE

                    var detail_thread=DetailNetworkThread(date_int)
                    detail_thread.start()
                    detail_thread.join()

                    var title=JSON_Obj?.getString("title")
                    var intro=JSON_Obj?.getString("introduce")
                    var num =JSON_Obj?.getInt("star")

                    date_title.text=title
                    date_intro.text=intro
                    star_text.text="${num}"

                    calendar_button.setOnClickListener{ view->
                        var msg=Toast.makeText(this,"이미 일정이 등록되어 있습니다",Toast.LENGTH_SHORT)
                        msg.show()
                    }
                }
                // 일정 추가
               else{
                    star_image.visibility=View.GONE
                    star_text.visibility=View.GONE

                    date_title.text=""
                    date_intro.text="일정이 없습니다"
                    calendar_button.setOnClickListener { view->
                        var dialog=Dialog(this)
                        var dialog_v=layoutInflater.inflate(R.layout.calendar_popup2,null)

                        dialog_v.closet_button.setOnClickListener { view->
                            dialog.dismiss()
                        }

                        var title=dialog_v.findViewById<EditText>(R.id.date_title)
                        var intro=dialog_v.findViewById<EditText>(R.id.date_intro)
                        var star=dialog_v.findViewById<EditText>(R.id.star_edit)

                        dialog_v.register_button.setOnClickListener { view->
                            var year=dialog_v.date_picker.year
                            var month=dialog_v.date_picker.month
                            var day=dialog_v.date_picker.dayOfMonth
                            var date=CalendarDay(year,month,day)
                            date_list.add(date)
                            calendar_view.addDecorator(EventDecorator(Color.RED,date_list))

                            var date_int=year*10000+(month+1)*100+day
                            var upload_thread=UploadThread(date_int,title.text.toString(),intro.text.toString(), star.text.toString())
                            upload_thread.start()
                            upload_thread.join()

                            dialog.dismiss()
                        }
                        dialog_v.closet_button.setOnClickListener { view->
                            dialog.dismiss()
                        }

                        dialog.setContentView(dialog_v)
                        var lp=WindowManager.LayoutParams()
                        lp.copyFrom(dialog.window!!.attributes)
                        lp.width=900
                        lp.height=1100
                        dialog.window!!.attributes=lp
                        dialog.window!!.setBackgroundDrawableResource(R.drawable.white_border)
                        dialog.show()
                    }
                }
            }
            /////////// 과거일정 ///////////////
            else{
                dday*=-1
                dday_Text.text="D+${dday}"
                calendar_button.setText("추천코디")

                var check=false
                for(i in 0 until date_list.size){
                    var calendar_day=date_list.get(i)
                    if(calendar_day==temp_Date){
                        check=true
                        break
                    }
                }

                // 추천 코디 받았을 경우
                if(check){

                    star_image.visibility=View.VISIBLE
                    star_text.visibility=View.VISIBLE
                    var detail_thread=DetailNetworkThread(date_int)
                    detail_thread.start()
                    detail_thread.join()

                    var star_num=JSON_Obj?.getInt("star")
                    var look_JSONObj=JSON_Obj?.getJSONObject("look")
                    var look_title=look_JSONObj?.getString("look_title")
                    var look_intro=look_JSONObj?.getString("look_introduction")
                    var look_image=look_JSONObj?.getString("look_image")
                    var temp_JSONAry=look_JSONObj?.getJSONArray("clothes_array")

                    star_text.text="${star_num}"
                    date_title.text=look_title
                    date_intro.text=look_intro

                    calendar_button.setOnClickListener { view->

                        var dialog=Dialog(this)
                        var dialog_v=layoutInflater.inflate(R.layout.calendar_popup,null)

                        var grid_adapter=ListAdapter()
                        dialog_v.Grid_list.adapter=grid_adapter
                        dialog_v.Grid_list.SetExpanded(true)

                        Picasso.with(this).load(look_image).into(dialog_v.look_Imageview)

                        dialog_v.look_title_text.text=look_title
                        dialog_v.look_intro_text.text=look_intro

                        recommend_list.clear()
                        for(i in 0 until temp_JSONAry?.length()!!){
                            var obj=temp_JSONAry.getJSONObject(i)
                            var garment_color=obj.getString("color")
                            var garment_category=obj.getString("category")
                            var garment_image=obj.getString("clothes_image")

                            var map=HashMap<String,Any>()
                            map.put("color",garment_color)
                            map.put("category",garment_category)
                            map.put("image",garment_image)

                            recommend_list.add(map)
                        }

                        dialog.setContentView(dialog_v)

                        var lp=WindowManager.LayoutParams()
                        lp.copyFrom(dialog.window!!.attributes)
                        lp.width=950
                        lp.height=1200
                        dialog.window!!.attributes=lp
                        dialog.window!!.setBackgroundDrawableResource(R.drawable.white_border)

                        dialog_v.close_btn.setOnClickListener { view->
                            dialog.dismiss()
                        }
                        dialog.show()
                    }
                }
                // 일정이 없을 경우
                else{
                    // 버튼 클릭 무효화
                    star_image.visibility=View.GONE
                    star_text.visibility=View.GONE
                    date_title.text=""
                    date_intro.text="일정이 없습니다"
                    calendar_button.setOnClickListener{ view->
                        var msg=Toast.makeText(this,"추천 받은 코디가 없습니다",Toast.LENGTH_SHORT)
                        msg.show()
                    }
                }
            }

        })

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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_calendar -> {
                var intent = Intent(this, CalendarActivity::class.java)
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

    inner class ListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return recommend_list.size
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view=convertView

            if(view==null){
                view=layoutInflater.inflate(R.layout.pashion_poplook_image,null)
            }

            var map=recommend_list.get(position)

            var name_text=view?.findViewById<TextView>(R.id.clothes_info)
            var cloth_img=view?.findViewById<ImageView>(R.id.clothes)
            var type_img=view?.findViewById<ImageView>(R.id.clothes_type)

            var color=map.get("color") as String
            var category=map.get("category") as String
            var image=map.get("image") as String

            name_text?.text="${category}, ${color}"

            Picasso.with(applicationContext).load(image).into(type_img)

            type_img?.setImageResource(clothes_list.get(category)!!)

            return view!!

        }
    }

    // 일정 받는 네트워크 쓰레드
    inner class NetworkThread : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/schedule/list")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")

            var request=request_builder.build()
            var response=client.newCall(request).execute()

            var body=response.body!!.string()

            var obj= JSONObject(body)
            JSON_Ary=obj.getJSONArray("schedule_array")
        }
    }

    inner class DetailNetworkThread(var date : Int) : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/schedule/detail")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")

            var json_form=JSONObject()
            json_form.put("date",date)

            var body=RequestBody.create("application/json".toMediaType(),json_form.toString())

            var post=url.post(body)
            var request=post.build()
            var response=client.newCall(request).execute()

            var result=response.body!!.string()
            Log.d("hi","${result}")
            JSON_Obj= JSONObject(result)
        }
    }

    inner class UploadThread(var date : Int, var title : String, var introduce : String, var star : String ) : Thread(){
        override fun run() {
            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/schedule")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")

            var json_form=JSONObject()
            json_form.put("date",date)
            json_form.put("title",title)
            json_form.put("introduce",introduce)
            json_form.put("star",star.toInt())

            var body=RequestBody.create("application/json".toMediaType(),json_form.toString())

            var post=url.post(body)
            var request=post.build()
            client.newCall(request).execute()

        }
    }

}

/**
 * 일요일 빨간색으로 데코레이션 하기 위한 클래스
 */
class SundayDecorator : DayViewDecorator{

    val calendar=Calendar.getInstance()


    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        var weekday:Int=calendar.get(Calendar.DAY_OF_WEEK)
        return weekday==Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.RED))
    }
}

/**
 * 토요일 파란색으로 데코레이션 하기 위한 클래스
 */
class SaturdayDecorator : DayViewDecorator{
    val calendar=Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        var weekday:Int=calendar.get(Calendar.DAY_OF_WEEK)
        return weekday==Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.BLUE))
    }
}

/**
 * 오늘 날짜 데코레이션을 위한 클래스
 */
class TodayDecorator : DayViewDecorator{

    var date=CalendarDay()

    constructor(){
        date=CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return date!=null && day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(StyleSpan(Typeface.BOLD))
        view?.addSpan(RelativeSizeSpan(1.4f))
        view?.addSpan(ForegroundColorSpan(Color.rgb(127,103,211)))
    }
}

class EventDecorator : DayViewDecorator{
    var Color:Int?=null
    var dates=HashSet<CalendarDay>()

    constructor(color : Int, dates : Collection<CalendarDay>){
        this.Color=color
        this.dates=HashSet<CalendarDay>(dates)
    }
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(5.toFloat(),Color!!))
    }

}