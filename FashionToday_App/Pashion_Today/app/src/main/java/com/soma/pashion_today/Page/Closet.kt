package com.soma.pashion_today.Page

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.soma.pashion_today.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.closet.*
import kotlinx.android.synthetic.main.closet_camera.view.*
import kotlinx.android.synthetic.main.closet_content.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URL


/*****
 * 프로그램 ID : HAM-PA-200
 * 프로그램명 : Closet.kt
 * 작성자명: 오원석
// * 작성일자 : 2019.11.11
 * 버전 : v0.6
 */
class Closet : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val CAMERA_ACTIVITY = 1

    // 옷 타입 분류 변수
    var clothes_type = arrayOf("Category","Pants","Jean","Shirts","Tee","Dress","Shoes","Bag","Hat","Accessory")

    // 옷 타입 분류 이미지
    var clothes_typeimg= intArrayOf(
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

    // 옷 색상 분류 변수arrayOf("Category","Pants","Jean","Shirts","Tee","Dress","Shoes","Bag","Hat","Accessory")
    var color_type = arrayOf("Color","Red","Blue","Green")

    // 서버로 받는 JSON Object
    var closet_JSONObj:JSONObject?=null

    // 서버로 받는 closet JSON Array
    var closet_JSONAry : JSONArray?=null

    // 서버에서 받은 리스트 뷰에 들어가는 closet_list
    var closet_list=ArrayList<HashMap<String,Any>>()

    // 모든 옷들의 정보가 담긴 list
    var look_list=ArrayList<HashMap<String,Any>>()

    // 사진, 내부저장소 허가 권한 배열
    var permission_list = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 임시 이미지 변수
    var bitmap : Bitmap?=null

    // 폴더경로
    var dirPath: String? = null

    // 파일의 전체 uri
    var contentUri: Uri? = null

    // 파일 전체경로
    var pic_path: String? = null

    // 액티비티 create 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.closet)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        var header_view=nav_view.getHeaderView(0)
        header_view.setOnClickListener {
            var intent=Intent(this,Pashion::class.java)
            startActivity(intent)
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

        var type_str:String="Category"
        var color_str:String="Color"

        var Get_datathread=NetworkThread()
        Get_datathread.start()
        Get_datathread.join()


        var type_adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,clothes_type)

        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        type_spinner.adapter=type_adapter

        type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{ type_str="Category" }
                    1->{ type_str="Pants" }
                    2->{ type_str="Jean" }
                    3->{ type_str="Shirts" }
                    4->{ type_str="Tee" }
                    5->{ type_str="Dress" }
                    6->{ type_str="Shoes" }
                    7->{ type_str="Bag" }
                    8->{ type_str="Hat" }
                    9->{type_str="Accessory"}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var color_adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,color_type)

        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        color_spinner.adapter=color_adapter

        color_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{color_str="Color"}
                    1->{color_str="Red"}
                    2->{color_str="Blue"}
                    3->{color_str="Green"}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        var grid_adapter=ListAdapter()
        closet_gridview.adapter=grid_adapter
        closet_gridview.SetExpanded(true)

        for(i in 0 until closet_JSONAry?.length()!!){
            var obj=closet_JSONAry?.getJSONObject(i)
            var color=obj?.getString("color")
            var category=obj?.getString("category")
            var image=obj?.getString("clothes_image")

            var image_thread=ImageNetworkThread(image!!)
            image_thread.start()
            image_thread.join()

            var map=HashMap<String,Any>()
            map.put("color",color!!)
            map.put("category",category!!)
            map.put("image",bitmap!!)

            look_list.add(map)
        }

        for(i in 0 until look_list.size){
            var map=look_list.get(i)
            var color=map.get("color") as String
            var category=map.get("category") as String
            var image=map.get("image") as Bitmap

            var tempmap=HashMap<String,Any>()
            tempmap.put("color",color)
            tempmap.put("category",category)
            tempmap.put("image",image)

            closet_list.add(tempmap)
        }

        // 스피너로 보기
        select_btn.setOnClickListener { view->
            if(type_str=="Category"){
                // 모든 리스트 받기
                if(color_str=="Color"){
                    closet_list.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as Bitmap

                        var tempmap=HashMap<String,Any>()
                        tempmap.put("color",color)
                        tempmap.put("category",category)
                        tempmap.put("image",image)

                        closet_list.add(tempmap)
                    }
                }
                // 색깔별로 받기
                else{
                    closet_list.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as Bitmap

                        if(color==color_str){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_list.add(tempmap)
                        }
                    }
                }
            }
            // 종류별로 받기
            else{
                if(color_str=="Color"){
                    closet_list.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as Bitmap

                        if(category==type_str){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_list.add(tempmap)
                        }
                    }
                }

                else{
                    closet_list.clear()

                    for(i in 0 until look_list.size){
                        var map=look_list.get(i)
                        var color=map.get("color") as String
                        var category=map.get("category") as String
                        var image=map.get("image") as Bitmap

                        if(color==color_str && category==type_str){
                            var tempmap=HashMap<String,Any>()
                            tempmap.put("color",color)
                            tempmap.put("category",category)
                            tempmap.put("image",image)

                            closet_list.add(tempmap)
                        }
                    }
                }
            }

            var closet_adapter=closet_gridview.adapter as ListAdapter
            closet_adapter.notifyDataSetChanged()
        }

        fab_btn.setOnClickListener{view->
            camera_capture()
        }


    }

    // back버튼 클릭 메서드
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // 메뉴생성 메서드
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

    // 오른쪽 메뉴 선택 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.my_page_menu -> true
            R.id.notice_menu -> true
            R.id.plus_menu -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permission_list, 0)
                } else {
                    camera_capture()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // requestPermission의 결과로 모두가 권한 허가이면 실행, 아니면 return
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                return
            }
        }
        camera_capture()
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

    fun camera_capture() {


        var tempPath = Environment.getExternalStorageDirectory().absolutePath
        dirPath = "${tempPath}/Android/data/${packageName}"

        var file = File(dirPath)

        if (file.exists() == false) {
            file.mkdir()
        }

        var camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        var fileName = "temp_${System.currentTimeMillis()}.jpg"
        var picPath = "${dirPath}/${fileName}"

        var file2 = File(picPath);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contentUri = FileProvider.getUriForFile(this, "com.soma.pashion_today.httpmultipart.file_provider", file2)
        } else {
            contentUri = Uri.fromFile(file2)
        }
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
        startActivityForResult(camera_intent, CAMERA_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_ACTIVITY) {

            if (resultCode == Activity.RESULT_OK) {

                var dialog=Dialog(this)
                var dialog_view=layoutInflater.inflate(R.layout.closet_camera,null)

                var select_color:String="Color"
                var select_category : String="Category"

                var temp_adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,clothes_type)
                temp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                dialog_view.Type_spinner.adapter=temp_adapter

                dialog_view.Type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when(position){
                            0->{select_category="Category"}
                            1->{select_category="Pants"}
                            2->{select_category="Jean"}
                            3->{select_category="Shirts"}
                            4->{select_category="Tee"}
                            5->{select_category="Dress"}
                            6->{select_category="Shoes"}
                            7->{select_category="Bag"}
                            8->{select_category="Hat"}
                            9->{select_category="Accessory"}
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

                var temp2_adpater=ArrayAdapter(this,android.R.layout.simple_spinner_item,color_type)
                temp2_adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                dialog_view.Color_spinner.adapter=temp2_adpater

                dialog_view.Color_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when(position){
                            0->{select_color="Color"}
                            1->{select_color="Red"}
                            2->{select_color="Blue"}
                            3->{select_color="Green"}
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }


                var capture = BitmapFactory.decodeFile(contentUri?.path)
                dialog_view.capture_img.setImageBitmap(capture)
                dialog.setContentView(dialog_view)

                var lp= WindowManager.LayoutParams()
                lp.copyFrom(dialog.window!!.attributes)
                lp.width=900
                lp.height=1100
                dialog.window!!.attributes=lp
                dialog.window!!.setBackgroundDrawableResource(R.drawable.white_border)

                dialog_view.Close_btn.setOnClickListener { view->
                    dialog.dismiss()
                }
                dialog_view.Select_btn.setOnClickListener { view->
                    var map=HashMap<String,Any>()
                    map.put("color",select_color)
                    map.put("category",select_category)
                    map.put("image",capture)

                    look_list.add(map)
                    dialog.dismiss()
                }
                dialog.show()


            }
        }
    }

    inner class ListAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return closet_list.size
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

            var closet_img=v?.findViewById<ImageView>(R.id.clothes)
            var closet_type=v?.findViewById<ImageView>(R.id.clothes_type)
            var closet_info=v?.findViewById<TextView>(R.id.clothes_info)

            var map=closet_list.get(position)

            var image=map.get("image") as Bitmap
            var color=map.get("color") as String
            var category=map.get("category") as String


            closet_img?.setImageBitmap(image)

            for(i in 0 until clothes_type.size){
                if(category==clothes_type.get(i)){
                    closet_type?.setImageResource(clothes_typeimg.get(i))
                    break
                }
            }

            closet_info?.text="${category}, ${color}"

            return v!!
        }
    }

    inner class NetworkThread : Thread(){
        override fun run() {
            var site="http://172.20.10.4:8085/MobileServer/closet.jsp"
            var url=URL(site)
            var conn=url.openConnection()
            var input=conn.getInputStream()
            var isr=InputStreamReader(input)
            var br=BufferedReader(isr)

            var buf=StringBuffer()
            var str:String?=null

            do{
                str=br.readLine()
                if(str!=null){
                    buf.append(str)
                }
            }while(str!=null)

            closet_JSONObj= JSONObject(buf.toString())
            closet_JSONAry=closet_JSONObj?.getJSONArray("clothes_array")
        }
    }

    inner class ImageNetworkThread(var site : String) : Thread(){
        override fun run() {
            var url=URL(site)
            var conn=url.openConnection()
            var input=conn.getInputStream()
            bitmap=BitmapFactory.decodeStream(input)
        }
    }
}
