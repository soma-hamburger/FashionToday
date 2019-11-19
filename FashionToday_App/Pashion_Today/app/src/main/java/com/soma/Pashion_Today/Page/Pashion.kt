package com.soma.Pashion_Today.Page

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.util.rangeTo
import com.soma.Pashion_Today.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_dialog.*
import kotlinx.android.synthetic.main.main_dialog.view.*
import kotlinx.android.synthetic.main.pashion.*
import kotlinx.android.synthetic.main.pashion_content.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


/*****
 * 프로그램 ID : HAM-PA-500
 * 프로그램명 : Pashion.kt
 * 작성자명 : 오원석
 * 작성일자 : 2019.11.19
 * 버전 : v0.9
 */
class Pashion : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // 서버로부터 받은 JSON array
    var look_JSONAry:JSONArray?=null

    // 메인 리스트뷰를 구성하기 위한 룩 list
    var look_list=ArrayList<HashMap<String,Any>>()

    // 서버로부터 받은 JSON object
    var look_JSONObj : JSONObject?=null

    // 상세 옷들의 정보가 담긴 list
    var user_clothes_list=ArrayList<HashMap<String,Any>>()

    // 옷
    var clothes_list= mapOf<String,Int>(
        "accesory" to R.drawable.accesory_icon,
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
        setContentView(R.layout.pashion)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       var header_view=nav_view.getHeaderView(0)
       header_view.setBackgroundResource(R.drawable.menu_back)

        // 사용자 그리드 뷰 구성 어댑터객체 생성
        var look_list_adapter=ListAdapter()
        Look_list.adapter=look_list_adapter
        // 사용자 그리드 뷰 확장 높이
        Look_list.SetExpanded(true)


        var upload_thread=UploadThread()
        upload_thread.start()
        upload_thread.join()
        // <임시 데이터 넣는 작업>
        var temp_Thread=NetworkThread()
        temp_Thread.start()
        temp_Thread.join()


        for(i in 0 until look_JSONAry?.length()!!) {
            var temp_JSONObj = look_JSONAry?.getJSONObject(i)
            var user_Name = temp_JSONObj?.getString("user_name")
            var user_Profile = temp_JSONObj?.getString("user_profile_image")
            var user_Fashion = temp_JSONObj?.getString("look_image")

            var map = HashMap<String, Any>()
            map.put("name", user_Name!!)
            map.put("profile", user_Profile!!)
            map.put("fashion", user_Fashion!!)

            look_list.add(map)
        }

        // 데이터 추가 받거나 화면 이동하여 다시 액티비티 실행 시 최신화
        runOnUiThread{
            var look_list_adapter=Look_list.adapter as ListAdapter
            look_list_adapter.notifyDataSetChanged()
        }

        // 리스트 뷰 클릭 시 실행되는 이벤트
        Look_list.setOnItemClickListener { parent, view, position, id ->
            Log.d("msg","댓다")
            // 상세 룩 정보 받기
            var network_Thread=NetworkDetailThread()
            network_Thread.start()
            network_Thread.join()

            var dialog=Dialog(this)
            var dialog_view=layoutInflater.inflate(R.layout.main_dialog,null)


            var temp_look_title=look_JSONObj?.getString("look_title")
            var temp_look_intro=look_JSONObj?.getString("look_introduction")
            var temp_JSONAry=look_JSONObj?.getJSONArray("clothes_array")
            var temp_JSONObj=look_JSONObj?.getJSONObject("recommender")
            var temp_name=temp_JSONObj?.getString("name")
            var temp_likenum=look_JSONObj?.getString("like_num")
            var temp_profile_site=temp_JSONObj?.getString("profile_image")


            var profile_view=dialog_view.findViewById<ImageView>(R.id.profile)
            // Picasso 라이브러리를 이용하여 이미지 등록
            Picasso.with(this).load(temp_profile_site).into(profile_view)

            dialog_view.look_title.text=temp_look_title
            dialog_view.look_introduction.text=temp_look_intro
            dialog_view.recommender_name.text=temp_name
            dialog_view.look_num.text=temp_likenum

            var grid_adapter=DetailListAdapter()
            dialog_view.Clothes_list.adapter=grid_adapter
            dialog_view.Clothes_list.SetExpanded(true)

            user_clothes_list.clear()
            for(i in 0 until temp_JSONAry?.length()!!){
                var obj=temp_JSONAry.getJSONObject(i)
                var clothes_color=obj.getString("color")
                var clothes_category=obj.getString("category")
                var clothes_img=obj.getString("clothes_image")

                var map=HashMap<String,Any>()
                map.put("color",clothes_color)
                map.put("category",clothes_category)
                map.put("image",clothes_img)

                user_clothes_list.add(map)
            }


            dialog.setContentView(dialog_view)

            // 다이얼로그 크기 조절
            var lp=WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width=950
            lp.height=1200
            dialog.window!!.attributes=lp
            dialog.window!!.setBackgroundDrawableResource(R.drawable.white_border)

            dialog_view.close_button.setOnClickListener { view->
                dialog.dismiss()
            }
            dialog.show()
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
                var closet_intent = Intent(this, Closet::class.java)
                startActivity(closet_intent)
            }
            R.id.menu_daily_look -> {
                var daily_look_intent=Intent(this,DailyLook::class.java)
                startActivity(daily_look_intent)
            }
            R.id.menu_calendar -> {
                var calendar_intent = Intent(this, CalendarActivity::class.java)
                startActivity(calendar_intent)
            }
            R.id.menu_recommend -> {
                var recommend_intent=Intent(this,Recommend::class.java)
                startActivity(recommend_intent)
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


    // 사진을 서버에서 받아 저장하여 관리하는 리스트 어뎁터
    inner class ListAdapter:BaseAdapter(){
        override fun getCount(): Int {
            return look_list.size
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

            var name_text=convertView?.findViewById<TextView>(R.id.user_name)
            var profile_img=convertView?.findViewById<ImageView>(R.id.user_profile)
            var fashion_img=convertView?.findViewById<ImageView>(R.id.user_look)

            var user_name=map.get("name") as String
            var profile_site=map.get("profile") as String
            var fashion_site=map.get("fashion") as String


            if(profile_site!="null"){
                Picasso.with(applicationContext).load(profile_site).into(profile_img)
            }
            Picasso.with(applicationContext).load(fashion_site).into(fashion_img)
            name_text?.text=user_name
            return convertView!!
        }
    }

    inner class DetailListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return user_clothes_list.size
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var convertView=p1

            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.pashion_poplook_image,null)
            }

            var map=user_clothes_list.get(p0)

            var garment_image=convertView?.findViewById<ImageView>(R.id.clothes)
            var garment_info=convertView?.findViewById<TextView>(R.id.clothes_info)
            var garment_type=convertView?.findViewById<ImageView>(R.id.clothes_type)

            var image=map.get("image") as String
            var color=map.get("color") as String
            var category=map.get("category") as String

            Picasso.with(applicationContext).load(image).into(garment_image)

            garment_type?.setImageResource(clothes_list.get(category)!!)
            garment_info?.text="${color} , ${category}"

            return convertView!!
        }
    }

     // 서버에서 JSONAry 받는 쓰래드
     inner class NetworkThread:Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/Look_list.jsp"
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
            look_JSONAry=obj.getJSONArray("look_array")

        }
    }

    // 서버에서 Detail Look_Ary 받는 쓰래드
    inner class NetworkDetailThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/Look_detail_list.jsp"
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
            look_JSONObj=obj
        }
    }

    inner class UploadThread : Thread(){
        override fun run() {
            var client= OkHttpClient()
            var request_builder= Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/user/info")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTczODE5Njg2MTM4LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MTAwLCJtY0RhdGVUaW1lIjoiMjAxOS0xMS0wMlQwNzowMzowNy40NzkiLCJtY0RhdGUiOiIyMDE5Tk9WRU1CRVIyIiwibWNUaW1lIjoiNzM3IiwibWlkIjoxMTU4Nzc1NTc4LCJtcHJvZmlsZVVybCI6bnVsbCwibW5hbWUiOiLsnqXrj5ntm4giLCJtc3RhciI6OTEsIm1tYWlsIjoiZGhvb25qYW5nQGdtYWlsLmNvbSIsIm1iaXJ0aGRheSI6IjA4MDMiLCJtc29jaWFsS2luZCI6Imtha2FvIiwibWhhc2hWYWwiOm51bGwsIm1zb2NpYWxJZCI6ImRob29uamFuZ0BnbWFpbC5jb20iLCJtZWRpdG9yIjoyLCJtZ3JhZGUiOjEwMCwibWNvbW1lbnQiOiLtjKjshZjtiKzrjbDsnbQg6rCc67Cc7J6Q7J6F64uI64ukLiIsIm1jb25EYXRlVGltZSI6bnVsbH19.CWn4k20fvRsEoRzrxnklO6DsrtByWyuFDHzQfZVmmy8")
            url.addHeader("Content-Type","application/json")


            var formBody=FormBody.Builder().build()

            var post=url.post(formBody)
            var request=post.build()
            var response=client.newCall(request).execute()

            var body=response.body!!.string()
            Log.d("msg","${body}")
        }
    }


}


// 그리드 뷰를 상속받아 높이를 자동 계산하여 알려주는 그리드 뷰
class ExpandableHeightGridView : GridView{

    var expanded=false

    constructor(context: Context) : this(context,null)

    constructor(context : Context, attrs : AttributeSet?) :super(context,attrs)

    constructor(context : Context, attrs : AttributeSet?, defStyle : Int) : super(context,attrs,defStyle)

    fun isExpanded() : Boolean{
        return expanded
    }

    fun SetExpanded( expanded : Boolean){
        this.expanded=expanded
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(isExpanded()){
            var expandSpec=MeasureSpec.makeMeasureSpec(View.MEASURED_SIZE_MASK,MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, expandSpec)

            var params:ViewGroup.LayoutParams?=null
            params?.height=measuredHeight

        }
        else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
