package com.example.margat.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.margat.R
import com.example.margat.model.Member
import com.example.margat.request.MemberRequest
import com.example.margat.util.MyCallback
import com.example.margat.util.RetrofitAPI
import kotlinx.android.synthetic.main.activity_find_password.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class FindPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)

        setOnClickListenerToSubmitButton()
    }

    private fun setOnClickListenerToSubmitButton() {
        submitButton.setOnClickListener {
            var member = Member().apply {
                name = nameInput.text.toString()
                email = emailInput.text.toString()
            }

            checkNameAndEmailOf(member, RetrofitAPI.newInstance().getRetrofit().create(MemberRequest::class.java))
        }
    }

    private fun checkNameAndEmailOf(member: Member, memberRequest: MemberRequest) {
        memberRequest.findMemberByNameAndEmail(member).enqueue(object: MyCallback<ArrayList<Member>>() {
            override fun onResponse(call: Call<ArrayList<Member>>, response: Response<ArrayList<Member>>) {
                if (response.code() == 200) {
                    if (response.body().isNullOrEmpty()) {
                        Toast.makeText(applicationContext,
                            "해당하는 회원이 없습니다!", Toast.LENGTH_SHORT).show()
                        return
                    }

                    changePasswordOf(member, memberRequest)
                }
            }

        })
    }

    private fun changePasswordOf(member: Member, memberRequest: MemberRequest) {
        memberRequest.sendRandomPasswordAt(member).enqueue(object: MyCallback<ResponseBody>() {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(applicationContext,
                    "임시비밀번호가 메일로 전송되었습니다!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
