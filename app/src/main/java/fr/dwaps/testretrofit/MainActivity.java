package fr.dwaps.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConnectToData connectToData;
    private List<EditText> ets;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persons = new ArrayList<Person>();

        ets = new ArrayList<EditText>();
        ets.add((EditText) findViewById(R.id.et_nom));
        ets.add((EditText) findViewById(R.id.et_email));
        ets.add((EditText) findViewById(R.id.et_age));

        connectToData = new ConnectToData(
            this,
            ets
        );
    }

    public void sendPerson(View v) {
        persons.add(connectToData.sendPerson(v));
    }

    public void deletePerson(View v) {
        persons.remove(connectToData.deletePerson(v));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!persons.isEmpty()) {
            persons.clear();
        }
        persons = null;
        connectToData = null;
    }
}
