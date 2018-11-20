package co.th.udrinkidrive.datalayer.apiservice;

import co.th.udrinkidrive.datalayer.entity.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/**
 * Created by programmer on 1/10/18.
 */

public interface SOService {

    @FormUrlEncoded
    @POST("customer/login/")
    Call<Item> Login(
            @Field("username") String username,
            @Field("password") String password
    );

}