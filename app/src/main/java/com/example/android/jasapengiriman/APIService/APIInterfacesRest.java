package com.example.android.jasapengiriman.APIService;

/**
 * Created by user on 1/10/2018.
 */




import com.example.android.jasapengiriman.UpdateOrder;
import com.example.android.jasapengiriman.modelservice.Jasa;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

 public interface APIInterfacesRest {

 @GET("api/jasa/all")
 Call<Jasa> getAllList();

 @Multipart
 @POST("api/jasa/update")
 Call<UpdateOrder> updateData(
         @Part("id") RequestBody id,
         @Part("pengirim") RequestBody pengirim,
         @Part("penerima") RequestBody penerima,
         @Part("asal") RequestBody asal,
         @Part("tujuan") RequestBody tujuan,
         @Part MultipartBody.Part foto
 );

 @Multipart
 @POST("api/jasa/add")
 Call<UpdateOrder> saveData(
         @Part("pengirim") RequestBody pengirim,
         @Part("penerima") RequestBody penerima,
         @Part("asal") RequestBody asal,
         @Part("tujuan") RequestBody tujuan,
         @Part MultipartBody.Part foto
 );


}

