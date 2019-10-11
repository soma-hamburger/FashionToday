package com.soma.pashion_today.Page

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
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
import com.soma.pashion_today.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.closet.*
import kotlinx.android.synthetic.main.closet_camera.view.*
import kotlinx.android.synthetic.main.closet_content.*
import java.io.File


/*****
 * 프로그램 ID : HAM-PA-400
 * 프로그램명 : Closet.kt : 오원석
 * 작성일자 : 2019.09.04
 * 버전 : v0.1
 */
class Closet : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val CAMERA_ACTIVITY = 1

    // 옷 타입 분류 변수
    var clothes_type = arrayOf("전체", "상의", "하의", "신발")
    var color_type = arrayOf("빨", "주", "노", "초", "파", "남", "보")

    // 임시 변수
    var closet_list = intArrayOf(
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
    var permission_list = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

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

        // 스피너 adapter
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clothes_type);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        closet_spinner.adapter = adapter

        // 스피너 클릭 리스너 구현
        closet_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    // 전체 출력
                    0 -> {
                        var list = ArrayList<HashMap<String, Int>>()
                        var idx = 0;
                        while (idx < closet_list.size) {
                            var map = HashMap<String, Int>()
                            map.put("img", closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys = arrayOf("img")
                        var ids = intArrayOf(R.id.ClosetImage)

                        var closet_adapter = SimpleAdapter(
                            applicationContext, list,
                            R.layout.closet_image, keys, ids
                        )
                        gridview.adapter = closet_adapter

                    }
                    // 상의 출력
                    1 -> {
                        var list = ArrayList<HashMap<String, Int>>()
                        var idx = 0;
                        while (idx < 3) {
                            var map = HashMap<String, Int>()
                            map.put("img", closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys = arrayOf("img")
                        var ids = intArrayOf(R.id.ClosetImage)

                        var closet_adapter = SimpleAdapter(
                            applicationContext, list,
                            R.layout.closet_image, keys, ids
                        )
                        gridview.adapter = closet_adapter


                    }
                    // 하의 출력
                    2 -> {
                        var list = ArrayList<HashMap<String, Int>>()
                        var idx = 3;
                        while (idx < 6) {
                            var map = HashMap<String, Int>()
                            map.put("img", closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys = arrayOf("img")
                        var ids = intArrayOf(R.id.ClosetImage)

                        var closet_adapter = SimpleAdapter(
                            applicationContext, list,
                            R.layout.closet_image, keys, ids
                        )
                        gridview.adapter = closet_adapter

                    }
                    // 신발 출력
                    3 -> {
                        var list = ArrayList<HashMap<String, Int>>()
                        var idx = 6;
                        while (idx < 8) {
                            var map = HashMap<String, Int>()
                            map.put("img", closet_list[idx])

                            list.add(map)
                            idx++
                        }

                        var keys = arrayOf("img")
                        var ids = intArrayOf(R.id.ClosetImage)

                        var closet_adapter = SimpleAdapter(
                            applicationContext, list,
                            R.layout.closet_image, keys, ids
                        )
                        gridview.adapter = closet_adapter
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
        menuInflater.inflate(R.menu.closet_menu, menu)
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


                var builder = AlertDialog.Builder(this)
                builder.setTitle(" 옷 등록 ")

                var v1 = layoutInflater.inflate(R.layout.closet_camera, null)

                var type_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clothes_type)
                type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                v1.type_spinner.adapter = type_adapter

                var color_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, color_type)
                color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                v1.color_spinner.adapter = color_adapter

                var bitmap = BitmapFactory.decodeFile(contentUri?.path)
                v1.capture_img.setImageBitmap(bitmap)

                builder.setView(v1)

                var listener = object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when (p1) {
                            DialogInterface.BUTTON_POSITIVE -> {

                            }
                            DialogInterface.BUTTON_NEUTRAL -> {

                            }
                        }
                    }
                }

                builder.setNeutralButton("취소", null)
                builder.setPositiveButton("확인", null)

                builder.show()


            }
        }
    }

}
