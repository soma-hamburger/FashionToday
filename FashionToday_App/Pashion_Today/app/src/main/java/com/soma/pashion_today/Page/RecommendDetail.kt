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
import kotlinx.android.synthetic.main.pashion_detail.*
import kotlinx.android.synthetic.main.recommend_detail_content.*
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

    var closet_type=arrayOf("전체","상의","하의","신발")

    // 서버로부터 받은 JSON Array
    var recommend_JSON_Ary: JSONArray?=null;

    // 옷장에서 옷을 선택한 리스트
    var recommend_look=mutableSetOf(HashMap<String,Any>())

    // 옷장 구성을 위해 필요한 옷 리스트
    var closet_look=ArrayList<HashMap<String,Any>>()

    // 이미지 리스트
    var image_list=HashMap<String,Bitmap>()


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

        var data_get_thread=NetworkThread()
        data_get_thread.start()
        data_get_thread.join()


        var adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,closet_type)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        type_spinner.adapter=adapter



        type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0->{
                        closet_look.clear()

                        var closet_adapter=ListAdapter()
                        closet_view.adapter=closet_adapter

                        for(i in 0 until recommend_JSON_Ary?.length()!!){
                            var obj=recommend_JSON_Ary?.getJSONObject(i)
                            var color=obj?.getString("color")
                            var category=obj?.getString("category")
                            var site=obj?.getString("clothes_image")

                            var map=HashMap<String,Any>()
                            map.put("color",color!!)
                            map.put("category",category!!)
                            map.put("image",site!!)

                            closet_look.add(map)
                        }
                        Log.d("msg","kk")
                        runOnUiThread {
                            var closet_adapter=closet_view.adapter as ListAdapter
                            closet_adapter.notifyDataSetChanged()
                        }

                        var closet_listener=ListListener()
                        closet_view.setOnItemClickListener(closet_listener)

                    }
                    1->{
                        closet_look.clear()
                        var closet_adapter=ListAdapter()
                        closet_view.adapter=closet_adapter

                        for(i in 0 until recommend_JSON_Ary?.length()!!){
                            var obj=recommend_JSON_Ary?.getJSONObject(i)
                            var color=obj?.getString("color")
                            var category=obj?.getString("category")
                            var site=obj?.getString("clothes_image")

                            if(category=="Top"){
                                var map=HashMap<String,Any>()
                                map.put("color",color!!)
                                map.put("category",category!!)
                                map.put("image",site!!)
                                closet_look.add(map)
                            }
                        }

                        runOnUiThread {
                            var closet_adapter=closet_view.adapter as ListAdapter
                            closet_adapter.notifyDataSetChanged()
                        }

                        var closet_listener=ListListener()
                        closet_view.setOnItemClickListener(closet_listener)
                    }
                    2->{
                        closet_look.clear()
                        var closet_adapter=ListAdapter()
                        closet_view.adapter=closet_adapter

                        for(i in 0 until recommend_JSON_Ary?.length()!!){
                            var obj=recommend_JSON_Ary?.getJSONObject(i)
                            var color=obj?.getString("color")
                            var category=obj?.getString("category")
                            var site=obj?.getString("clothes_image")

                            if(category=="pants"){
                                var map=HashMap<String,Any>()
                                map.put("color",color!!)
                                map.put("category",category!!)
                                map.put("image",site!!)
                                closet_look.add(map)
                            }
                        }

                        runOnUiThread {
                            var closet_adapter=closet_view.adapter as ListAdapter
                            closet_adapter.notifyDataSetChanged()
                        }

                        var closet_listener=ListListener()
                        closet_view.setOnItemClickListener(closet_listener)
                    }
                    3->{
                        closet_look.clear()
                        var closet_adapter=ListAdapter()
                        closet_view.adapter=closet_adapter

                        for(i in 0 until recommend_JSON_Ary?.length()!!){
                            var obj=recommend_JSON_Ary?.getJSONObject(i)
                            var color=obj?.getString("color")
                            var category=obj?.getString("category")
                            var site=obj?.getString("clothes_image")

                            if(category=="shoes"){
                                var map=HashMap<String,Any>()
                                map.put("color",color!!)
                                map.put("category",category!!)
                                map.put("image",site!!)
                                closet_look.add(map)
                            }
                        }

                        runOnUiThread {
                            var closet_adapter=closet_view.adapter as ListAdapter
                            closet_adapter.notifyDataSetChanged()
                        }

                        var closet_listener=ListListener()
                        closet_view.setOnItemClickListener(closet_listener)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        button.setOnClickListener { view->
            Log.d("msg","${recommend_look}")
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

            var look_image=view?.findViewById<ImageView>(R.id.clothes_view)
            var look_infomation=view?.findViewById<TextView>(R.id.look_info)

            var look_site=map.get("image") as String
            var look_category=map.get("category") as String
            var look_color=map.get("color") as String

            var image:Bitmap?=image_list.get(look_site)
            if(image==null){
                var image_thread=ImageNetworkThread(look_site)
                image_thread.start()
            }
            else{
                look_image?.setImageBitmap(image)
            }
            look_infomation?.text="${look_category}, ${look_color}"

            return view!!
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
                var closet_adapter=closet_view.adapter as ListAdapter
                closet_adapter.notifyDataSetChanged()
            }
        }
    }





}
