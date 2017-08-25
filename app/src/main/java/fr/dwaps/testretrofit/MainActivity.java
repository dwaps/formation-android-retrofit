package fr.dwaps.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dwaps.fr/tests/bdd-to-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        retrofit.create(PersonAPI.class);
    }
}
