package fr.dwaps.testretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Michael on 25/08/2017.
 */

public interface PersonAPI {
    public static final String ENDPOINT = "https://www.dwaps.fr/";

    @GET("/tests/bdd-to-json")
    Call<List<Person>> getPersons();

    @FormUrlEncoded
    @POST("/tests/bdd-to-json/user-new")
    Call<List<Person>> postPerson(
        @Field("nom") String nom,
        @Field("email") String email,
        @Field("age") int age
    );

}
