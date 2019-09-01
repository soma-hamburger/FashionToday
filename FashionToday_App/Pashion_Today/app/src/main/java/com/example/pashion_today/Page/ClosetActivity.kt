package com.example.pashion_today.Page

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pashion_today.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.closet_content.*
import java.io.File


// 옷장 화면
class ClosetActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // 옷 타입 분류 변수
    var clothes_type = arrayOf("전체","상의","하의","신발")
    // 임시 변수
    var closet_list= intArrayOf(
        R.drawable.top,
        R.drawable.top2,
        R.drawable.top3,
        R.drawable.botton,
        R.drawable.button2,
        R.drawable.button3,
        R.drawable.shoes,
        R.drawable.shoes2
    )

    // 사진, 내부저장소 허가 권한 배열
    var permission_list=arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 경로
    var dirPath:String?=null
    // 파일의 전체경로
    var contentUri:Uri?=null
    var pic_path:String?=null

    // 액티비티 create 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.closet_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


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

        // 스피너 adapter
        var adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,clothes_type);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        closet_spinner.adapter=adapter

        // 스피너 클릭 리스너 구현
        closet_spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2) {
                    // 전체 출력
                    0 -> {
                        var list=ArrayList<HashMap<String,Int>>()
                        var idx=0;
                        while(idx<closet_list.size){
                            var map=HashMap<String,Int>()
                            map.put("img",closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys=arrayOf("img")
                        var ids= intArrayOf(R.id.ClosetImage)

                        var closet_adapter=SimpleAdapter(applicationContext,list,
                            R.layout.closet_image,keys,ids)
                        gridview.adapter=closet_adapter

                    }
                    // 상의 출력
                    1 -> {
                        var list=ArrayList<HashMap<String,Int>>()
                        var idx=0;
                        while(idx<3){
                            var map=HashMap<String,Int>()
                            map.put("img",closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys=arrayOf("img")
                        var ids= intArrayOf(R.id.ClosetImage)

                        var closet_adapter=SimpleAdapter(applicationContext,list,
                            R.layout.closet_image,keys,ids)
                        gridview.adapter=closet_adapter


                    }
                    // 하의 출력
                    2 -> {
                        var list=ArrayList<HashMap<String,Int>>()
                        var idx=3;
                        while(idx<6){
                            var map=HashMap<String,Int>()
                            map.put("img",closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys=arrayOf("img")
                        var ids= intArrayOf(R.id.ClosetImage)

                        var closet_adapter=SimpleAdapter(applicationContext,list,
                            R.layout.closet_image,keys,ids)
                        gridview.adapter=closet_adapter

                    }
                    // 신발 출력
                    3->{
                        var list=ArrayList<HashMap<String,Int>>()
                        var idx=6;
                        while(idx<8){
                            var map=HashMap<String,Int>()
                            map.put("img",closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys=arrayOf("img")
                        var ids= intArrayOf(R.id.ClosetImage)

                        var closet_adapter=SimpleAdapter(applicationContext,list,
                            R.layout.closet_image,keys,ids)
                        gridview.adapter=closet_adapter
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
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
        menuInflater.inflate(R.menu.closet_camera, menu)
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
            R.id.camera_menu->{
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    requestPermissions(permission_list,0)
                }
                else{
                    init()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // requestPermission의 결과로 모두가 권한 허가이면 실행, 아니면 return
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        for(result in grantResults){
            if(result==PackageManager.PERMISSION_DENIED){
                return
            }
        }
        init()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.menu_closet -> {
                var intent= Intent(this, ClosetActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_daily_look -> {

            }
            R.id.menu_calendar -> {
                var intent= Intent(this, CalendarActivity::class.java)
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

    fun init(){

        var tempPath=Environment.getExternalStorageDirectory().absolutePath
        dirPath="${tempPath}/Android/data/${packageName}"

        var file=File(dirPath)

        if(file.exists()==false){
            file.mkdir()
        }

        var camera_intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        var fileName="temp_${System.currentTimeMillis()}.jpg"
        var picPath="${dirPath}/${fileName}"

        var file2=File(picPath);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            contentUri=FileProvider.getUriForFile(this,"com.example.pashion_today.httpmultipart.file_provider",file2)
        }
        else{
            contentUri=Uri.fromFile(file2)
        }
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,contentUri)
        startActivityForResult(camera_intent,1)
    }

}
