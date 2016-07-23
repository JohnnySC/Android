package github.johnnysc.notesrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.RealmQuery;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class NoteDetailActivity extends AppCompatActivity {
    TextView data,head,body;
    FloatingActionButton fabDelete;
    RealmManager realmManager;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        data = (TextView)findViewById(R.id.detailData);
        head = (TextView)findViewById(R.id.detailHead);
        body = (TextView)findViewById(R.id.detailBody);
        fabDelete = (FloatingActionButton)findViewById(R.id.fabDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        date = bundle.getString("DATE");
        data.setText(date);
        head.setText(bundle.getString("HEAD"));
        body.setText(bundle.getString("BODY"));

        realmManager = new RealmManager(getApplicationContext());

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Delete note?",Snackbar.LENGTH_LONG)
                        .setAction("Yes",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Note deleted!",Toast.LENGTH_SHORT).show();
                                RealmQuery<Note> result = realmManager.getRealm().where(Note.class).equalTo("date",date);
                                realmManager.getRealm().beginTransaction();
                                result.findAll().clear();
                                realmManager.getRealm().commitTransaction();
                                MainActivity.notesAdapter.notifyDataSetChanged();
                                finish();
                            }
                        });
                snackbar.show();
            }
        });
    }
}
