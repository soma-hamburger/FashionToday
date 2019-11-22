package com.soma.Pashion_Today.Page

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.soma.Pashion_Today.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recommend_detail.*
import kotlinx.android.synthetic.main.recommend_detail_content.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.math.abs
import kotlin.math.sqrt


/*****
 * 프로그램 ID : HAM-PA-800
 * 프로그램명 : RecommendDetail.kt
 * 작성자명: 오원석
 * 작성일자 : 2019.11.19
 * 버전 : v0.9
 */
class RecommendDetail : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {

    // 옷 분류 스피너
    var closet_type=arrayOf("Category","Pants","Jean","Shirts","Tee","Dress","Shoes","Bag","Hat","Accessory")

    // 옷 색상 스피터
    var closet_color=arrayOf("Color","Red","Blue","Green","Brown","Yellow","Purple","Black","White")

    // 옷 분류 이미지
    var closet_map= mapOf<String,Int>(
        "category" to R.drawable.jean_icon,
        "pants" to R.drawable.jean_icon,
        "jean" to R.drawable.jean_icon,
        "shirts" to R.drawable.shirts_icon,
        "tee" to R.drawable.tee_icon,
        "dress" to R.drawable.dress_icon,
        "shoes" to R.drawable.shoes_icon,
        "bag" to R.drawable.bag_icon,
        "hat" to R.drawable.hat_icon,
        "accessory" to R.drawable.accesory_icon
    )

    // 서버로부터 받은 JSON Array
    var recommend_JSON_Ary: JSONArray?=null

    // 옷장 구성을 위해 필요한 옷 리스트
    var closet_look=ArrayList<HashMap<String,Any>>()

    // 스피너로 분류된 옷 리스트
    var look_list=ArrayList<HashMap<String,Any>>()

    var spinner_category :String="category"

    var spinner_color : String="color"

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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        var detail_id=intent.getIntExtra("user_id",0)
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
            var rd_view=findViewById<ImageView>(R.id.rd_profile)
            Picasso.with(this).load(detail_site).into(rd_view)
        }

        var data_get_thread=NetworkThread(detail_id)
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
                    0->{ spinner_category="category" }
                    1->{ spinner_category="pants" }
                    2->{ spinner_category="jean" }
                    3->{ spinner_category="shirts" }
                    4->{ spinner_category="tee" }
                    5->{ spinner_category="dress" }
                    6->{ spinner_category="shoes" }
                    7->{ spinner_category="bag" }
                    8->{ spinner_category="hat" }
                    9->{ spinner_category="accessory"}
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
                    0->{spinner_color="color"}
                    1->{spinner_color="red"}
                    2->{spinner_color="blue"}
                    3->{spinner_color="green"}
                    4->{spinner_color="brown"}
                    5->{spinner_color="yellow"}
                    6->{spinner_color="purple"}
                    7->{spinner_color="black"}
                    8->{spinner_color="white"}
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var rd_adapter=ListAdapter()
        rd_gridview.adapter=rd_adapter
        rd_gridview.SetExpanded(true)

        rd_select.setOnClickListener { view->
            Log.d("msg","${spinner_category}, ${spinner_color}")
            if(spinner_category=="category"){
                if(spinner_color=="color"){
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
                if(spinner_color=="color"){
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
        var listener=ListListener()
        rd_gridview.setOnItemClickListener(listener)

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
                var intent= Intent(this,DailyLook::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_calendar -> {
                var intent = Intent(this, CalendarActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.menu_recommend -> {
                var recommend=Intent(this,Recommend::class.java)
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

    inner class ListListener : AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            var map=closet_look.get(p2)
            var category=map.get("category") as String
            var color=map.get("color") as String
            var site=map.get("image") as String



            var view=p1
            var img=view?.findViewById<ImageView>(R.id.clothes_view)
            Picasso.with(applicationContext).load(site).into(img) as Bitmap
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


            Picasso.with(applicationContext).load(site).into(clothes_view)
            type_view?.setImageResource(closet_map.get(category)!!)
            info_view?.text="${category}, ${color}"

            return view!!
        }
    }

    inner class NetworkThread(var userid : Int) : Thread(){
        override fun run() {

            var client=OkHttpClient()
            var request_builder=Request.Builder()
            var url=request_builder.url("https://api.pashiontoday.com/closet")
            url.addHeader("Authorization","eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTc0MzcwNjQwODcwLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MCwibWNEYXRlVGltZSI6IjIwMTktMTEtMDZUMDY6NDY6MDkuNzc4IiwibWNEYXRlIjoiMjAxOU5PVkVNQkVSNiIsIm1jVGltZSI6IjY0NjkiLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtaWQiOjEyMDczNDg5MjUsIm1zZWxlY3RkYXRlIjoiMTk5NDExMDUiLCJtbWFpbCI6Im9vczMwOTBAbmF2ZXIuY29tIiwibWJpcnRoZGF5IjoiMTAwNyIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6bnVsbCwibXNvY2lhbElkIjoib29zMzA5MEBuYXZlci5jb20iLCJtZWRpdG9yIjowLCJtZ3JhZGUiOjUsIm1jb21tZW50Ijoi7JWI65Oc66Gc7J2065OcIOyVhOydtOyYpOyVhOydtCIsIm1jb25EYXRlVGltZSI6bnVsbH19.0sBpI01JWmROBByBkhzePY8-OollGtrFN93BKWmJp68")
            url.addHeader("Content-Type","application/json")


            var json_form=JSONObject()
            json_form.put("user_id",userid)

            var body= RequestBody.create("application/json".toMediaType(),json_form.toString())

            var post=url.post(body)
            var request=post.build()
            var response=client.newCall(request).execute()

            var result=response.body!!.string()


            var obj=JSONObject(result)
            recommend_JSON_Ary=obj.getJSONArray("clothes_array")

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
        }
    }
}


class CutomView : View {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBackgroundColor(Color.WHITE)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var res=resources
        var bd : BitmapDrawable?=null



        var img = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        canvas!!.drawBitmap(img,0f,0f,null)
    }


}

class MoveObject: View{
    var X :Int=0
    var Y : Int =0
    var HEIGHT : Int=0
    var WIDTH : Int = 0
    var MU : MovingUnit?=null

    constructor(context : Context) : super(context)

    fun setSelectedImage( bitmap : Bitmap) {
        var image = bitmap
        MU= MovingUnit(image)
        invalidate()
    }

    override fun onTouchEvent(event : MotionEvent) : Boolean{
        MU?.Touchprocess(event)
        invalidate()
        return true
    }

    override fun draw(canvas : Canvas){
        canvas.drawBitmap(MU?.getImg()!!,null,MU?.getRect()!!,null)
        super.draw(canvas)
    }

}

class MovingUnit{
    var Image : Bitmap?=null

    var X : Float=0f
    var Y : Float=0f

    var Width : Float=0f
    var Height : Float=0f

    var offsetX : Float=0f
    var offsetY : Float=0f

    var posX1 : Int= 0
    var posX2 : Int= 0
    var posY1 : Int=0
    var posY2 : Int=0

    var oldDist : Float =1f
    var newDist : Float =1f

    val NONE : Int = 0
    val DRAG : Int = 1
    val ZOOM : Int = 1

    var mode : Int =NONE

    constructor(Image : Bitmap){
        this.Image=Image
        setSize(Image.height as Float,Image.width as Float)
        setXY(0 as Float,0 as Float)
    }

    fun Touchprocess(event : MotionEvent){
        var act=event.action

        when(act and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN->{
                if(InObject(event.getX(),event.getY())){
                    posX1=event.getX() as Int
                    posY1=event.getY() as Int
                    offsetX=posX1-X
                    offsetY=posY1-Y

                    Log.d("msg","mode=DRAG")
                    mode=DRAG
                }
            }
            MotionEvent.ACTION_MOVE->{
                if(mode==DRAG){
                    X=posX2-offsetX
                    Y=posY2-offsetY
                    posX2=event.getX() as Int
                    posY2=event.getY() as Int
                    if(abs(posX2-posX1)>20 || abs(posY2-posY1)>20){
                        posX1=posX2
                        posY1=posY2
                        Log.d("msg","mode=DRAG")
                    }
                }
                else if(mode==ZOOM){
                    newDist=spacing(event)

                    if(newDist-oldDist>20){
                        var scale=sqrt(((newDist-oldDist)*(newDist-oldDist))/(Height*Height+Width*Width))
                        Y=Y-(Height*scale/2)
                        X=X-(Width*scale/2)

                        Height=Height*(1+scale)
                        Width=Width*(1+scale)

                        oldDist=newDist
                    }
                    else if(oldDist-newDist>20){
                        var scale=sqrt(((newDist-oldDist)*(newDist-oldDist))/(Height*Height+Width*Width))
                        scale=0-scale
                        Y=Y-(Height*scale/2)
                        X=X-(Width*scale/2)

                        Height=Height*(1+scale)
                        Width=Width*(1+scale)

                        oldDist=newDist
                    }
                }
            }
            MotionEvent.ACTION_UP->{

            }
            MotionEvent.ACTION_POINTER_UP->{
                mode=NONE
            }
            MotionEvent.ACTION_POINTER_DOWN->{
                mode=ZOOM
                newDist=spacing(event)
                oldDist=spacing(event)
                Log.d("msg","newDist + ${newDist}")
                Log.d("msg","oldDist + ${oldDist}")
                Log.d("msg","mode=ZOOM")
            }
            MotionEvent.ACTION_CANCEL-> {

            }
        }
    }

    fun getRect() : Rect{
        var rect : Rect?=null
        rect?.set(X as Int,Y as Int,X as Int +Width as Int,Y as Int + Height as Int)
        return rect!!
    }

    private fun spacing(event : MotionEvent) : Float{
        var x:Float=event.getX(0)-event.getX(1)
        var y:Float=event.getY(0)-event.getY(1)
        return sqrt(x*x+y*y)
    }

    fun InObject(eventX : Float, eventY : Float) : Boolean{
        if(eventX<(X+Width+30) && eventX>(X-30) && eventX<Y+Height+30 && eventY>Y-30){
            return true
        }
        return false
    }

    fun setSize(Height : Float, Width : Float){
        this.Height=Height
        this.Width=Width
    }

    fun setXY(X : Float, Y : Float){
        this.X=X
        this.Y=Y
    }

    fun getImg() : Bitmap{
        return Image!!
    }


}
