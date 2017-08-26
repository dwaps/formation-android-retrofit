package fr.dwaps.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private PersonAPI personAPI;

    private EditText et_nom;
    private EditText et_email;
    private EditText et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nom = (EditText) findViewById(R.id.et_nom);
        et_email = (EditText) findViewById(R.id.et_email);
        et_age = (EditText) findViewById(R.id.et_age);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

        personAPI = new Retrofit.Builder()
            .baseUrl(PersonAPI.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PersonAPI.class);
    }

    public void sendData(View v) {
        String nom = et_nom.getText().toString();
        String email = et_email.getText().toString();
        String age = et_age.getText().toString();

        if (!nom.equals("") && !email.equals("") && !age.equals("")) {
            Person person = new Person(nom, email, Integer.parseInt(age));

            Call<List<Person>> call = personAPI.postPerson(
                    person.getNom(),
                    person.getEmail(),
                    person.getAge()
            );

            call.enqueue(new Callback<List<Person>>() {
                @Override
                public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<Person>> call, Throwable t) {

                }
            });

            et_nom.setText("");
            et_email.setText("");
            et_age.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        personAPI = null;

        et_nom = null;
        et_email = null;
        et_age = null;
    }
}
