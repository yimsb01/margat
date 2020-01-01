package com.example.margat.request

import com.example.margat.domain.Following
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowingRequest {
    @GET("/followings/member/{no}")
    fun findAllFollowingsOf(@Path("no") memberNo: Int): Call<Array<Following>>
}