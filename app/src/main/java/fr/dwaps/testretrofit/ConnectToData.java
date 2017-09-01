package fr.dwaps.testretrofit;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dwaps on 01/09/2017.
 */

public class ConnectToData {
    private static final String[] ERRORS = {
            "Les données n'ont pas été envoyées.\nContactez le développeur.",
            "La suppression n'a pas été faite.\nContactez le développeur.",
            "Veuillez renseigner un age correct !"
    };

    private Context ctx;
    private PersonAPI personAPI;
    private Person person;
    private List<EditText> ets;

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    ConnectToData(Context ctx, List<EditText> ets) {
        this.ctx = ctx;
        this.ets = ets;

        personAPI = new Retrofit.Builder()
            .baseUrl(PersonAPI.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PersonAPI.class);
    }

    public Person sendPerson(View v) {
        if (hydratePerson()) {
            callAPI("POST", ERRORS[0]);
        }
        return person;
    }

    public Person deletePerson(View v) {
        if (hydratePerson()) {
            callAPI("DELETE", ERRORS[1]);
        }
        return person;
    }

    private void callAPI(String httpMethod, final String error) {
        Call<List<Person>> call = null;

        switch (httpMethod) {
            case "GET":
                call = personAPI.getPersons();
                break;
            case "POST":
                call = personAPI.postPerson(
                        person.getNom(),
                        person.getEmail(),
                        person.getAge()
                );
                break;
            case "DELETE":
                call = personAPI.deletePerson(
                        person.getNom(),
                        person.getEmail(),
                        person.getAge()
                );
                break;
        }

        if (null != call) {
            call.enqueue(new Callback<List<Person>>() {
                @Override
                public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                    Toast.makeText(ctx, response.body().toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<Person>> call, Throwable t) {
                    Toast.makeText(ctx, error, Toast.LENGTH_LONG).show();
                }
            });

            for (EditText et : ets) {
                et.setText("");
            }
        }
    }

    private boolean hydratePerson() {
        String nom = ets.get(0).getText().toString();
        String email = ets.get(1).getText().toString();
        String age = ets.get(2).getText().toString();

        if (
            (null != nom && !nom.equals(""))
            &&
            (null != email && !email.equals(""))
            &&
            (null != age && !age.equals(""))
        ) {
            try {
                person = new Person(nom, email, Integer.parseInt(age));
                return true;
            } catch (NumberFormatException e) {
                Toast.makeText(ctx, ERRORS[2], Toast.LENGTH_LONG).show();
            }
        }

        return false;
    }
}
