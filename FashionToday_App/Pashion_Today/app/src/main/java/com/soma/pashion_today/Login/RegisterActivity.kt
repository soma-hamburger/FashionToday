package com.soma.pashion_today.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.soma.pashion_today.R
import kotlinx.android.synthetic.main.register_activity.*
import okhttp3.*
import java.io.IOException

/*****
 * 프로그램 ID : HAM-PA-200
 * 프로그램명 : RegisterActivity.kt
 * 작성자명 : 오원석
 * 작성일자 : 2019.09.01
 * 버전 : v0.1
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        // 회원가입하기 버튼 구현
        Register_finish_button.setOnClickListener { view->
            // 여기서 회원가입 중복 판단 후 중복이 되지 않은 경우 서버에 연결
            var builder= AlertDialog.Builder(this)
            if(Register_user_id.text.toString()!="ows"){

                builder.setMessage("회원가입 성공")
                builder.setPositiveButton("확인",null)
                builder.show()
                var thread=UploadThread()
                thread.start()
            }
            else{
                builder.setMessage("이미 존재하는 아이디입니다")
                builder.setNegativeButton("취소",null)
                builder.show()
            }
        }
    }

    //
    inner class UploadThread : Thread(){
        override fun run() {
            // 요청
            var client= OkHttpClient()
            var request_builder= Request.Builder()
            var url=request_builder.url("http://172.30.1.22:8085/SomaServer/upload.jsp")

            // 이미지, 문자열 다른 형식이니 멀티 바디객체로 사용
            var multipart_builder= MultipartBody.Builder()
            // 서버에게 멀티 바디 객체로 사용하겠다고 전달
            multipart_builder.setType(MultipartBody.FORM)

            multipart_builder.addFormDataPart("userId",Register_user_id.text.toString())
            multipart_builder.addFormDataPart("userPassword",Register_user_pw.text.toString())
            multipart_builder.addFormDataPart("userName",Register_user_name.text.toString())
            multipart_builder.addFormDataPart("userAge",Register_user_age.text.toString())

            var body=multipart_builder.build()
            var post=url.post(body)

            var request=post.build()

            var callback=Callback1()

            client.newCall(request).enqueue(callback)



        }
    }

    inner class Callback1 : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            var result=response?.body?.string()

            if(result?.trim().equals("OK")){
                finish()
            }
        }
    }
}
