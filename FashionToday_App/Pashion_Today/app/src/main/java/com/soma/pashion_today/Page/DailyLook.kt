package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.daily_look.*
import kotlinx.android.synthetic.main.daily_look_content.*
import kotlinx.android.synthetic.main.daily_look_view.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL



/*****
 * 프로그램 ID : HAM-PA-300
 * 프로그램명 : DailyLook.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.12
 * 버전 : v0.6
 */
class DailyLook : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {


    // 서버에서 받은 데일리 룩 JSONAry
    var Daily_look_JSONAry:JSONArray?=null

    // 서버에서 받은 디테일 데일리 룩 정보
    var Detail_JSONObj : JSONObject?=null

    // 서버에서 받은 날짜
    var Daily_date : Int?=null

    // 서버에서 받은 이미지
    var bitmap:Bitmap?=null

    // 추천 상세 룩 리스트
    var clothes_array=ArrayList<HashMap<String,Any>>()

    // ViewPager에 들어갈 뷰들의 집합
    var view_list=ArrayList<View>()

    // 옷 타입 분류 변수
    var type_list= arrayOf("accesory","bag","dress","hat","jean","shirts","shoes","tee")

    // 옷 변수 png list
    var type_imglist= intArrayOf(
        R.drawable.accesory_icon,
        R.drawable.bag_icon,
        R.drawable.dress_icon,
        R.drawable.hat_icon,
        R.drawable.jean_icon,
        R.drawable.shirts_icon,
        R.drawable.shoes_icon,
        R.drawable.tee_icon
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
            startActivity(intent)
        }

        var Get_datathread=NetworkThread()
        Get_datathread.start()
        Get_datathread.join()

        var year=Daily_date!!/10000
        Daily_date=Daily_date!!%10000

        var month=Daily_date!!/100
        Daily_date=Daily_date!!%100

        var day=Daily_date

        for(i in 0 until Daily_look_JSONAry?.length()!!){
            var obj=Daily_look_JSONAry?.getJSONObject(i)
            var name=obj?.getString("recommender_name")!!
            var user_image=obj?.getString("recommender_profile_image")
            var grade=obj?.getInt("recommender_grade")
            var look_image=obj?.getString("look_image")

            var view=layoutInflater.inflate(R.layout.daily_look_view,null)
            view.daily_date.text="${year}년 ${month}월 ${day}일"
            view.daily_recommender.text=name
            view.daily_grade.text="${grade}"

            // 추천자 이미지 받는 쓰레드
            if(user_image!="null"){
                var user_imagethread=ImageNetworkThead(user_image)
                user_imagethread.start()
                user_imagethread.join()
                view.daily_profile.setImageBitmap(bitmap)
            }

            // 데일리 룩 이미지 받는 쓰레
            var look_imagethread=ImageNetworkThead(look_image)
            look_imagethread.start()
            look_imagethread.join()
            view.daily_img.setImageBitmap(bitmap)


            /////////데일리 룩 상세 표시 ////////////
            var detail_thread=DetailNetworkThread()
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

                var image_thread=ImageNetworkThead(img_site)
                image_thread.start()
                image_thread.join()

                var map=HashMap<String,Any>()
                map.put("color",color)
                map.put("category",category)
                map.put("image",bitmap!!)

                clothes_array.add(map)
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
            var image=map.get("image") as Bitmap

            look_img?.setImageBitmap(image)
            look_info?.text="${category}, ${color}"

            for(i in 0 until type_list.size){
                if(category==type_list.get(i)){
                    look_type?.setImageResource(type_imglist.get(i))
                    break;
                }
            }

            return v!!


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
            Daily_date=obj.getInt("date")
            Daily_look_JSONAry=obj.getJSONArray("daily_look_array")

        }
    }

    inner class DetailNetworkThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/daily_look_list_detail.jsp"
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

            Detail_JSONObj= JSONObject(buf.toString())
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
