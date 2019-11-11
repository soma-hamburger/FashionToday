package com.soma.pashion_today.Page

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
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.soma.pashion_today.R
import com.google.android.material.navigation.NavigationView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import kotlinx.android.synthetic.main.calendar_content.*
import kotlinx.android.synthetic.main.calendar_popup.view.*
import kotlinx.android.synthetic.main.daily_look.nav_view
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet


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

    // 옷 타입 분류 변수
    var clothes_type= arrayOf("accesory","bag","dress","hat","jean","shirts","shoes","tee")

    // 옷 변수 png list
    var clothes_list= intArrayOf(
        R.drawable.accesory_icon,
        R.drawable.bag_icon,
        R.drawable.dress_icon,
        R.drawable.hat_icon,
        R.drawable.jean_icon,
        R.drawable.shirts_icon,
        R.drawable.shoes_icon,
        R.drawable.tee_icon
    )

    var look_img:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view= nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
        }

        var date_thread=NetworkThread()
        date_thread.start()
        date_thread.join()

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

            Log.d("msg","${calendar_day}")
            date_list.add(calendar_day)
            calendar_view.addDecorator(EventDecorator(Color.RED,date_list))

        }

        var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
        calendar_view.addDecorators(SundayDecorator(),SaturdayDecorator(),TodayDecorator())

        var date_text=findViewById<TextView>(R.id.date_Text)
        var today_Date=CalendarDay.today()
        date_text.text="${today_Date.year}년 ${today_Date.month+1}월 ${today_Date.day}일"

        calendar_view.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            date_Text.text="${date.year}년 ${date.month+1}월 ${date.day}일"

            var temp_Date=CalendarDay(date.year,date.month,date.day)

            var temp_Day=Calendar.getInstance()
            temp_Day.set(temp_Date.year,temp_Date.month,temp_Date.day)

            var temp_millions=temp_Day.timeInMillis

            var today_millions=Calendar.getInstance().timeInMillis
            // 디데이 계산
            var dday=(temp_millions-today_millions)/(24*60*60*1000)

            // 미래일정
            if(dday>=0){
                if(dday>0){
                    dday_Text.text="D-${dday}"
                }
                else {
                    dday_Text.text = "D-day"
                }

                day_button.setText("일정등록")
                var check=false
                for(i in 0 until date_list.size){
                    var calendar_day=date_list.get(i)
                    if(calendar_day==temp_Date){
                        check=true
                        break
                    }
                }

                if(check){
                    day_button.setOnClickListener { view->
                        // 일정이 이미 등록되어있습니다.
                    }
                }
                else{
                    day_button.setOnClickListener{view->

                    }
                }
            }
            // 과거일정
            else{
                dday*=-1
                dday_Text.text="D+${dday}"
                day_button.setText("추천코디")

                var check=false
                for(i in 0 until date_list.size){
                    var calendar_day=date_list.get(i)
                    if(calendar_day==temp_Date){
                        check=true
                        break
                    }
                }

                if(check){
                    day_button.setOnClickListener { view->
                        // 일정이 이미 등록되어있습니다.
                        Log.d("msg","${temp_Date}")
                        var detail_thread=DetailNetworkThread()
                        detail_thread.start()
                        detail_thread.join()

                        var dialog=Dialog(this)
                        var dialog_v=layoutInflater.inflate(R.layout.calendar_popup,null)

                        var grid_adapter=ListAdapter()
                        dialog_v.Grid_list.adapter=grid_adapter
                        dialog_v.Grid_list.SetExpanded(true)

                        var look_JSONObj=JSON_Obj?.getJSONObject("look")
                        var temp_JSONAry=look_JSONObj?.getJSONArray("clothes_array")

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

                        dialog_v.close_btn.setOnClickListener { view->
                            dialog.dismiss()
                        }
                        dialog.show()
                    }
                }
                else{
                    day_button.setOnClickListener{view->

                    }
                }
            }


            var check=false
            for(i in 0 until date_list.size){
                var calendar_day=date_list.get(i)
                if(calendar_day==temp_Date){
                    check=true
                    break
                }
            }
            if(check){
                Log.d("msg","${temp_Date}")
                var detail_thread=DetailNetworkThread()
                detail_thread.start()
                detail_thread.join()


                var state=JSON_Obj?.getString("state")
                if(state=="past"){
                    detail_button.setOnClickListener { view->

                        var dialog=Dialog(this)
                        var dialog_v=layoutInflater.inflate(R.layout.calendar_popup,null)

                        var grid_adapter=ListAdapter()
                        dialog_v.Grid_list.adapter=grid_adapter
                        dialog_v.Grid_list.SetExpanded(true)

                        var look_JSONObj=JSON_Obj?.getJSONObject("look")
                        var temp_JSONAry=look_JSONObj?.getJSONArray("clothes_array")

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

                        dialog_v.close_btn.setOnClickListener { view->
                            dialog.dismiss()
                        }
                        dialog.show()
                    }
                }
                else{
                    detail_button.setOnClickListener { view->
                        var dialog=AlertDialog.Builder(this)
                        dialog.setTitle("실패")
                        dialog.show()
                    }
                }

            }
            else{
                // 일정 없을 시 아무것도 안뜨게 구현
                detail_button.setOnClickListener { view-> }
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
        menuInflater.inflate(R.menu.calendar_menu, menu)

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
//            R.id.plus_menu->{
//                var dialog=AlertDialog.Builder(this)
//
//                var v1=layoutInflater.inflate(R.layout.calendar_do,null)
//                dialog.setView(v1)
//
//
//                var listener=object:DialogInterface.OnClickListener{
//                    override fun onClick(p0: DialogInterface?, p1: Int) {
//                        when(p1){
//                            DialogInterface.BUTTON_POSITIVE->{
//                                var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
//                                var year=v1.date_picker.year
//                                var month=v1.date_picker.month
//                                var day=v1.date_picker.dayOfMonth
//                                var date=CalendarDay(year,month,day)
//                               date_list.add(date)
//                                calendar_view.addDecorator(EventDecorator(Color.RED,date_list))
//                            }
//                            DialogInterface.BUTTON_NEUTRAL->{
//
//                            }
//                        }
//                    }
//                }
//
//                dialog.setPositiveButton("확인",listener)
//                dialog.setNeutralButton("취소",listener)
//                dialog.show()
//
//
//                return true
//            }
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

    // 일정 받는 네트워크 쓰레드
    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/date.jsp"
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

            var obj= JSONObject(buf.toString())
            JSON_Ary=obj.getJSONArray("schedule_array")
        }
    }

    inner class DetailNetworkThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/date_detail.jsp"
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

            JSON_Obj= JSONObject(buf.toString())
        }
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

            var name_text=view?.findViewById<TextView>(R.id.info)
            var cloth_img=view?.findViewById<ImageView>(R.id.clothes)
            var type_img=view?.findViewById<ImageView>(R.id.clothes_type)

            var color=map.get("color") as String
            var category=map.get("category") as String
            var image=map.get("image") as String

            name_text?.text="${category}, ${color}"

            var image_thread=ImageNetworkThread(image)
            image_thread.start()
            image_thread.join()

            cloth_img?.setImageBitmap(look_img)

            for(i in 0 until clothes_type.size){
                if(category==clothes_type.get(i)){
                    type_img?.setImageResource(clothes_list.get(i))
                }
            }

            return view!!

        }
    }

    inner class ImageNetworkThread(var site : String) : Thread(){
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var input=conn.getInputStream()
            look_img=BitmapFactory.decodeStream(input)
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
        view?.addSpan(ForegroundColorSpan(Color.GREEN))
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