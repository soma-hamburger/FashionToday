package com.soma.pashion_today.Page

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
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
import kotlinx.android.synthetic.main.calendar_do.view.*
import java.util.*
import kotlin.collections.HashSet


/*****
 * 프로그램 ID : HAM-PA-500
 * 프로그램명 : CalendarActivity.kt
 * 작성자명 : 오원석
 * 작성일자 : 2019.09.05
 * 버전 : v0.1
 */
class CalendarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var date_list=ArrayList<CalendarDay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
        calendar_view.addDecorators(SundayDecorator(),SaturdayDecorator(),TodayDecorator())

        calendar_view.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            var idx=0;
            while(idx<date_list.size){
                if(date_list[idx].date==date.date){
                    textView9.text="${date.year}년 ${date.month+1}월 ${date.day}일"
                }
                idx++
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.my_page_menu -> true
            R.id.notice_menu -> true
            R.id.plus_menu->{
                var dialog=AlertDialog.Builder(this)

                var v1=layoutInflater.inflate(R.layout.calendar_do,null)
                dialog.setView(v1)


                var listener=object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when(p1){
                            DialogInterface.BUTTON_POSITIVE->{
                                var calendar_view=findViewById<MaterialCalendarView>(R.id.calendarview)
                                var year=v1.date_picker.year
                                var month=v1.date_picker.month
                                var day=v1.date_picker.dayOfMonth
                                var date=CalendarDay(year,month,day)
                                date_list.add(date)
                                calendar_view.addDecorator(EventDecorator(Color.RED,date_list))
                            }
                            DialogInterface.BUTTON_NEUTRAL->{

                            }
                        }
                    }
                }

                dialog.setPositiveButton("확인",listener)
                dialog.setNeutralButton("취소",listener)
                dialog.show()



                
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_closet -> {
                var intent=Intent(this,Closet::class.java)
                startActivity(intent)

            }
            R.id.menu_daily_look -> {

            }
            R.id.menu_calendar -> {
                var intent=Intent(this,CalendarActivity::class.java)
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