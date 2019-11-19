package com.soma.Pashion_Today.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soma.Pashion_Today.Page.Pashion
import com.soma.Pashion_Today.R
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

        // 로그인 버튼 메서드
        Kakao_login.setOnClickListener { view->
            var pashion_intent=Intent(this, Pashion::class.java)
            startActivity(pashion_intent)
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