package com.example.pashion_today.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pashion_today.Page.PashionActivity
import com.example.pashion_today.R
import kotlinx.android.synthetic.main.login_acitivity.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

// 로그인 화면
class LoginActivity : AppCompatActivity() {

    var user_list=ArrayList<HashMap<String,String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_acitivity)


        // 회원가입 버튼 메서드
        RegisterButton.setOnClickListener { view->
            var intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼 메서드
        LoginButton.setOnClickListener { view->
            /*var thread=getDataThread()
            thread.start()

            var flag:Boolean=false

            for(i in 0 until listData.size){
                var map=listData[i]
                var user_id=map.get("user_id")
                var user_password=map.get("user_password")

                if(user_id==IdText.text.toString()){
                    if(user_password==PasswordText.text.toString()){
                        flag=true
                        var t1=Toast.makeText(this,"로그인 되었습니다",Toast.LENGTH_SHORT)
                        t1.show()
                        var intent=Intent(this,PashionActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            if(flag==false){
                var t1=Toast.makeText(this,"존재하지 않은 아이디입니다",Toast.LENGTH_SHORT)
                t1.show()
            }*/
            if("ppp"==IdText.text.toString()){
                if("ppp"==PasswordText.text.toString()){
                    var t1= Toast.makeText(this,"로그인 되었습니다", Toast.LENGTH_SHORT)
                    t1.show()
                    var intent=Intent(this, PashionActivity::class.java)
                    startActivity(intent)

                }
            }
        }

    }

    // 데이터 요청하는 쓰레드
    inner class getDataThread:Thread(){
        override fun run() {
            var client= OkHttpClient()
            var builder= Request.Builder()
            var url=builder.url("http://172.30.1.22:8085/SomaServer/get_list.jsp")
            var request=url.build()
            var callback=Callback1()

            client.newCall(request).enqueue(callback)
        }
    }

    // 데이터 받는 쓰레드
    inner class Callback1: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            var result=response.body?.string()

            user_list.clear()

            var root= JSONArray(result)

            for(i in 0 until root.length()){
                var obj=root.getJSONObject(i)

                var user_id=obj.getString("user_id")
                var user_password=obj.getString("user_passwo  rd")

                var map=HashMap<String,String>()
                map.put("user_id",user_id)
                map.put("user_password",user_password)

                user_list.add(map)
            }

        }
    }
}
