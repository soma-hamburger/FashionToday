package com.soma.pashion_today.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.soma.pashion_today.Page.PashionActivity
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.login_acitivity.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

/*****
 * 프로그램 ID : HAM-PA-100
 * 프로그램명 : LoginActivity.kt
 * 작성자명 : 오원석
 * 작성일자 : 2019.09.01
 * 버전 : v0.1
 */
class LoginActivity : AppCompatActivity() {

    // 모든 유저리스트
    var user_list=ArrayList<HashMap<String,String>>()

    // 액티비티 생성 메소드
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_acitivity)


        // 회원가입 버튼 메서드
        Register_button.setOnClickListener { view->
            var register_intent= Intent(this, RegisterActivity::class.java)
            startActivity(register_intent)
        }

        // 로그인 버튼 메서드
        Login_button.setOnClickListener { view->
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
            if("ppp"==Login_user_id.text.toString()){
                if("ppp"==Login_user_pw.text.toString()){
                    var toast_msg= Toast.makeText(this,"로그인 되었습니다", Toast.LENGTH_SHORT)
                    toast_msg.show()
                    var pashion_intent=Intent(this, PashionActivity::class.java)
                    startActivity(pashion_intent)

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
            var get_user_callback=Get_user_Callback()

            client.newCall(request).enqueue(get_user_callback)
        }
    }

    // 데이터 받는 쓰레드
    inner class Get_user_Callback: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            var response_result=response.body?.string()

            user_list.clear()

            var temp_user_list= JSONArray(response_result)

            for(i in 0 until temp_user_list.length()){
                var user_obj=temp_user_list.getJSONObject(i)

                var user_id=user_obj.getString("user_id")
                var user_password=user_obj.getString("user_passwo  rd")

                var map=HashMap<String,String>()
                map.put("user_id",user_id)
                map.put("user_password",user_password)

                user_list.add(map)
            }

        }
    }
}
