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
import androidx.core.graphics.get
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.recommend_detail.*
import kotlinx.android.synthetic.main.recommend_detail_content.*
import kotlinx.android.synthetic.main.recommend_image.*
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


/*****
 * 프로그램 ID : HAM-PA-800
 * 프로그램명 : RecommendDetail.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.1
 * 버전 : v0.6
 */
class RecommendDetail : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {

    // 옷 분류 스피너
    var closet_type=arrayOf("Category","Pants","Jean","Shirts","Tee","Dress","Shoes","Bag","Hat","Accessory")

    // 옷 색상 스피터
    var closet_color=arrayOf("Color","Red","Blue","Green")

    // 옷 분류 이미지
    var closet_imglist= intArrayOf(
        R.drawable.jean_icon,
        R.drawable.jean_icon,
        R.drawable.jean_icon,
        R.drawable.shirts_icon,
        R.drawable.tee_icon,
        R.drawable.dress_icon,
        R.drawable.shoes_icon,
        R.drawable.bag_icon,
        R.drawable.hat_icon,
        R.drawable.accesory_icon
    )

    var closet_map= mapOf<String,Int>(
        "Category" to R.drawable.jean_icon,
        "Pants" to R.drawable.jean_icon,
        "Jean" to R.drawable.jean_icon,
        "Shirts" to R.drawable.shirts_icon,
        "Tee" to R.drawable.tee_icon,
        "Dress" to R.drawable.dress_icon,
        "Shoes" to R.drawable.shoes_icon,
        "Bag" to R.drawable.bag_icon,
        "Hat" to R.drawable.hat_icon,
        "Accessory" to R.drawable.accesory_icon
    )

    // 서버로부터 받은 JSON Array
    var recommend_JSON_Ary: JSONArray?=null

    // 옷장 구성을 위해 필요한 옷 리스트
    var closet_look=ArrayList<HashMap<String,Any>>()

    // 스피너로 분류된 옷 리스트
    var look_list=ArrayList<HashMap<String,Any>>()

    // 이미지 리스트
    var image_list=HashMap<String,Bitmap>()

    // 이미지
    var bitmap : Bitmap?=null

    var spinner_category :String="Category"

    var spinner_color : String="Color"

    // 옷장에서 옷을 선택한 리스트
    var recommend_look=mutableSetOf(HashMap<String,Any>())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend_detail)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent= Intent(this,Pashion::class.java)
            startActivity(intent)
        }


        var detail_site=intent.getStringExtra("profile_site")
        var detail_name=intent.getStringExtra("user_name")
        var detail_grade=intent.getStringExtra("user_grade")
        var detail_user_intro=intent.getStringExtra("user_intro")
        var detail_date=intent.getStringExtra("schedule_date")
        var detail_dday=intent.getStringExtra("schedule_dday")
        var detail_title=intent.getStringExtra("schedule_title")
        var detail_intro=intent.getStringExtra("schedule_intro")

        rd_name.text=detail_name
        rd_grade.text=detail_grade
        rd_userintro.text=detail_user_intro
        rd_date.text=detail_date
        rd_dday.text=detail_dday
        rd_title.text=detail_title
        rd_intro.text=detail_intro
        rd_button.setText("${detail_name} 님의 옷장")

        if(detail_site!="null"){
            var image_thread=ImageThread(detail_site)
            image_thread.start()
            image_thread.join()
            rd_profile.setImageBitmap(bitmap)
        }

        var data_get_thread=NetworkThread()
        data_get_thread.start()
        data_get_thread.join()

        var type_adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,closet_type)
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rd_typespinner.adapter=type_adapter
        rd_typespinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{ spinner_category="Category" }
                    1->{ spinner_category="Pants" }
                    2->{ spinner_category="Jean" }
                    3->{ spinner_category="Shirts" }
                    4->{ spinner_category="Tee" }
                    5->{ spinner_category="Dress" }
                    6->{ spinner_category="Shoes" }
                    7->{ spinner_category="Bag" }
                    8->{ spinner_category="Hat" }
                    9->{ spinner_category="Accessory"}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var color_adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,closet_color)
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rd_colorspinner.adapter=color_adapter
        rd_colorspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{spinner_color="Color"}
                    1->{spinner_color="Red"}
                    2->{spinner_color="Blue"}
                    3->{spinner_color="Green"}
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var rd_adapter=ListAdapter()
        rd_gridview.adapter=rd_adapter
        rd_gridview.SetExpanded(true)

        for(i in 0 until recommend_JSON_Ary?.length()!!){
            var obj=recommend_JSON_Ary?.getJSONObject(i)
            var color=obj?.getString("color")
            var category=obj?.getString("category")
            var image=obj?.getString("clothes_image")

            var map=HashMap<String,Any>()
            map.put("color",color!!)
            map.put("category",category!!)
            map.put("image",image!!)

            look_list.add(map)
            closet_look.add(map)
        }

        rd_select.setOnClickListener { view->
            Log.d("msg","${spinner_category}, ${spinner_color}")
            if(spinner_category=="Category"){
                if(spinner_color=="Color"){
                    closet_look.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as String

                        var tempmap=HashMap<String,Any>()
                        tempmap.put("color",color)
                        tempmap.put("category",category)
                        tempmap.put("image",image)

                        closet_look.add(tempmap)
                    }
                }
                else{
                    closet_look.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as String

                        if(color==spinner_color){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_look.add(tempmap)
                        }
                    }
                }
            }
            else{
                if(spinner_color=="Color"){
                    closet_look.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as String

                        if(category==spinner_category){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_look.add(tempmap)
                        }
                    }
                }
                else{
                    closet_look.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as String

                        if(category==spinner_category && color==spinner_color){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_look.add(tempmap)
                        }
                    }
                }
            }
            var adapter=rd_gridview.adapter as ListAdapter
            adapter.notifyDataSetChanged()
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
                startActivity(intent)
            }
            R.id.menu_daily_look -> {
                var intent= Intent(this,DailyLook::class.java)
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

    inner class ListListener : AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            var map=closet_look.get(p2)
            var category=map.get("category") as String
            var color=map.get("color") as String
            var site=map.get("image") as String
            var img=image_list.get(site)

            var view=p1
            var chk=view?.findViewById<CheckBox>(R.id.colorcheck)
            chk?.toggle()
            if(chk?.isChecked==true){
                recommend_look.add(map)
            }
            else{
                recommend_look.remove(map)
            }

        }
    }

    inner class ListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return closet_look.size
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
                view=layoutInflater.inflate(R.layout.recommend_detail_image,null)
            }

            var map=closet_look.get(position)

            var clothes_view=view?.findViewById<ImageView>(R.id.clothes_view)
            var type_view=view?.findViewById<ImageView>(R.id.type_view)
            var info_view=view?.findViewById<TextView>(R.id.info_view)

            var site=map.get("image") as String
            var category=map.get("category") as String
            var color=map.get("color") as String


            var img:Bitmap?=image_list.get(site)
            if(img==null){
                var image_thread=ImageNetworkThread(site)
                image_thread.start()
                image_thread.join()
            }
            else{
                clothes_view?.setImageBitmap(img)
            }

            type_view?.setImageResource(closet_map.get(category)!!)
            info_view?.text="${category}, ${color}"

            return view!!
        }
    }

    inner class NetworkThread : Thread(){
        override fun run() {

            var site="http://172.20.10.4:8085/MobileServer/recommender_closet.jsp"
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
            recommend_JSON_Ary=obj.getJSONArray("clothes_array")

        }
    }

    inner class ImageNetworkThread(var site : String?) : Thread() {
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var stream=conn.getInputStream()
            var bitmap=BitmapFactory.decodeStream(stream)

            image_list.put(site!!,bitmap)
            runOnUiThread {
                var adapter=rd_gridview.adapter as ListAdapter
                adapter.notifyDataSetChanged()
            }

        }
    }

    inner class ImageThread(var site : String?) : Thread(){
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var stream=conn.getInputStream()
            bitmap=BitmapFactory.decodeStream(stream)
        }
    }



}
