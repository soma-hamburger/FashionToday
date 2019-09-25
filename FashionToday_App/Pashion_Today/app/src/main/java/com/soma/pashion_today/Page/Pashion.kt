package com.soma.pashion_today.Page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.pashion_content.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


/*****
 * 프로그램 ID : HAM-PA-301
 * 프로그램명 : Pashion.kt : 오원석
 * 작성일자 : 2019.09.24
 * 버전 : v0.1
 */
class Pashion : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // 서버로부터 받은 JSON array
    var look_JSON_list:JSONArray?=null

    // 리스트뷰를 구성하기 위한 룩 list
    var look_list=ArrayList<HashMap<String,Any>>()

    // 사진은 서버로 받아서 관리하기 위한 list
    var profile_image_list=HashMap<String,Bitmap>()
    var fashion_image_list=HashMap<String,Bitmap>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pashion)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 리스트뷰 구성 어댑터객체 생성
        var look_list_adapter=ListAdapter()
        Look_list.adapter=look_list_adapter

        // 데이터 받기 시작 -> 서버에 요청하는 작업으로 변경해야한다

        // <임시 데이터 넣는 작업>
        var network_thread=NetworkThread()
        network_thread.start()
        network_thread.join()

        for(i in 0 until look_JSON_list?.length()!!) {
            var root = look_JSON_list?.getJSONObject(i)
            var user_name = root?.getString("user_name")
            var user_icon = root?.getString("user_profile_image")
            var user_pashion = root?.getString("look_image")

            var map = HashMap<String, Any>()
            map.put("user_name", user_name!!)
            map.put("user_icon", user_icon!!)
            map.put("user_pashion", user_pashion!!)

            look_list.add(map)
        }
        // <여기까지 임시 데이터 넣는 작업>

        // 데이터 추가 받거나 화면 이동하여 다시 액티비티 실행 시 최신화
        runOnUiThread{
            var look_list_adapter=Look_list.adapter as ListAdapter
            look_list_adapter.notifyDataSetChanged()
        }

        // 리스트 뷰 클릭 시 실행되는 이벤트
        var listenr=ListListener()
        Look_list.setOnItemClickListener(listenr)

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

    // 사진을 서버에서 받아 저장하여 관리하
    inner class ListAdapter:BaseAdapter(){
        override fun getCount(): Int {
            return look_list?.size
        }

        override fun getItem(p0: Int): Any {
            return 0
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        // p1 재사용 가능한
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var convertView=p1

            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.pashion_image,null)
            }

            var map=look_list.get(p0)

            var user=convertView?.findViewById<TextView>(R.id.user_name)
            var profile_img=convertView?.findViewById<ImageView>(R.id.user_icon)
            var fashion_img=convertView?.findViewById<ImageView>(R.id.user_pashion)

            var user_name=map.get("user_name") as String
            var user_profile_image=map.get("user_icon") as String
            var look_image=map.get("user_pashion") as String

            var profile_image:Bitmap?=profile_image_list.get(user_profile_image)
            if(profile_image==null){
                var image_thread=ProfileImageNetworkThread(user_profile_image)
                image_thread?.start()
            }
            else{
                profile_img?.setImageBitmap(profile_image)
            }

            var fashion_image:Bitmap?=fashion_image_list.get(look_image)
            if(fashion_image==null){
                var image_thread=FashionImageNetworkThread(look_image);
                image_thread.start()
            }
            else{
                fashion_img?.setImageBitmap(fashion_image)
            }
            user?.text=user_name



            return convertView!!
        }
    }

    // 리스트뷰 항목 클릭시 실행되는 이벤트
    inner class ListListener : AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            var view=look_list.get(p2)
            var detail_intent=Intent(applicationContext,PashionDetail::class.java)

            detail_intent.putExtra("user_name",view.get("user_name") as String)
            detail_intent.putExtra("user_icon",view.get("user_icon") as String)
            detail_intent.putExtra("user_pashion",view.get("user_pashion") as String)

            startActivity(detail_intent)
        }
    }

     // 서버에서 JSONarray 받는 클래스
     inner class NetworkThread:Thread(){
        override fun run() {
            var site="http://172.30.1.23:8085/MobileServer/Look_list.jsp"
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
            look_JSON_list=obj.getJSONArray("look_array")

        }
    }


    // 서버에서 프로필 이미지 받는 클래스
    inner class ProfileImageNetworkThread(var site:String):Thread(){
        override fun run() {
            var url=URL(site)
            var connection=url.openConnection()
            var stream=connection.getInputStream()
            var bitmap=BitmapFactory.decodeStream(stream)

            profile_image_list.put(site,bitmap)

            runOnUiThread{
                var look_list_adapter=Look_list.adapter as ListAdapter
                look_list_adapter.notifyDataSetChanged()
            }
        }
    }

    // 서버에서 코디 이미지 받는 클래스
    inner class FashionImageNetworkThread(var site:String):Thread(){
        override fun run() {
            var url=URL(site)
            var connection=url.openConnection()
            var stream=connection.getInputStream()
            var bitmap=BitmapFactory.decodeStream(stream)

            fashion_image_list.put(site,bitmap)

            runOnUiThread{
                var look_list_adapter=Look_list.adapter as ListAdapter
                look_list_adapter.notifyDataSetChanged()
            }
        }
    }

}



