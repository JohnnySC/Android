package github.johnnysc.notesrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class CreateNote extends AppCompatActivity {
    EditText etHead, etBody;
    Button createButton;
    RealmManager realmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        etHead = (EditText)findViewById(R.id.etHead);
        etBody = (EditText)findViewById(R.id.etBody);
        createButton = (Button)findViewById(R.id.createButton);

        realmManager = new RealmManager(getApplicationContext());

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmManager.getRealm().beginTransaction();

                Note note = realmManager.getRealm().createObject(Note.class);
                note.setDate((new SimpleDateFormat("EEE, dd-MM-yyyy, HH:mm:ss", Locale.ENGLISH).format(new Date())));
                note.setHead(etHead.getText().toString());
                note.setBody(etBody.getText().toString());

                realmManager.getRealm().commitTransaction();

                Toast.makeText(getApplicationContext(),"Note created!",Toast.LENGTH_SHORT).show();
                MainActivity.notesAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}
