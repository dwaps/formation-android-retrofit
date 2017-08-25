package fr.dwaps.testretrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Michael on 25/08/2017.
 */

public interface PersonAPI {

    @GET("/")
    Call<Person> getPersons();

    @POST("/")
    Call<Person> postPerson(@Body Person person);

}
