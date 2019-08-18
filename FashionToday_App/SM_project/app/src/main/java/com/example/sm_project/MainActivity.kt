package com.example.sm_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var listData=ArrayList<HashMap<String,String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RegisterButton.setOnClickListener { view->
            var intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        LoginButton.setOnClickListener { view->
            var thread=getDataThread()
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
            }

        }

    }

    inner class getDataThread:Thread(){
        override fun run() {
            var client=OkHttpClient()
            var builder=Request.Builder()
            var url=builder.url("http://172.30.1.22:8085/SomaServer/get_list.jsp")
            var request=url.build()
            var callback=Callback1()

            client.newCall(request).enqueue(callback)
        }
    }

    inner class Callback1:Callback{
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            var result=response.body?.string()

            listData.clear()

            var root=JSONArray(result)

            for(i in 0 until root.length()){
                var obj=root.getJSONObject(i)

                var user_id=obj.getString("user_id")
                var user_password=obj.getString("user_password")

                var map=HashMap<String,String>()
                map.put("user_id",user_id)
                map.put("user_password",user_password)

                listData.add(map)
            }

        }
    }
}
