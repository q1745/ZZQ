package com.example.net

import com.example.common.ConstValue
import com.example.common.sp.SPPropDelegate
import com.example.net.api.TokenApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.min

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
class RetrofitFactory {

    /**
     * 使用属性委托间接存储到sp
     */
    private var token:String by SPPropDelegate<String>("token","",false)

    companion object {
        val retrofitFactory:RetrofitFactory by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }
    }

    val retrofit:Retrofit
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(ConstValue.TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(ConstValue.TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(ConstValue.TIMEOUT,TimeUnit.MINUTES)
            .addNetworkInterceptor(createInterceptor())
            .addInterceptor(createTokenInterceptor())
            .build()
    }

    /**
     * 创建处理Token的自定义拦截器
     */
    private fun createTokenInterceptor(): Interceptor {
        val interceptor = Interceptor {
            //拿到请求
            val request = it.request()
            //响应
            var response:Response?=null
            response=it.proceed(request)


            if (token.isNotBlank()){
//                setRequestHeader(request,it)
                println("---true$token")
                response=doRequest(request,it)
            }else{
                println("---false$token")
                response=it.proceed(request)
            }

            if (response!!.code()==401){
                requestToken()
                println("---401$token")
                doRequest(request,it)
            }
            else{
                println("---200$token")
                response
            }


        }
        return interceptor
    }

    /**
     * 设置请求头并执行
     */
    private fun setRequestHeader(request: Request,chain: Interceptor.Chain):Request{
        println("---401$token")
        //设置到请求头
        return request.newBuilder()
            .addHeader("Authorization", "bearer $token")
            .build()
//        return chain.proceed(newRequest)
    }

    /**
     * 获取Token然后设置到请求头
     */
    private fun doRequest(request: Request, chain: Interceptor.Chain):Response{

        return chain.proceed(setRequestHeader(request,chain))
    }

    private fun requestToken() {
        //获取token
        val tokenApi:TokenApi=create(TokenApi::class.java)
        val tokenService = tokenApi.getToken("password", ConstValue.AUTHCODE, "")
        val result = tokenService!!.execute()
        //拿到的token
        val resultToken = result.body()!!.access_token
        /**
         * 将从服务器获取到的Token存入到SP中
         */
        token=resultToken
    }

    /**
     * 日志拦截器
     */
    private fun createInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 通过传入的请求接口类型返回具体的实体对象
     */
    fun <T> create(service: Class<T>) : T{
        return retrofit.create(service)
    }
}