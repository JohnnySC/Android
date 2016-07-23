package github.johnnysc.notesrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.RealmResults;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class MainActivity extends AppCompatActivity {
    public static NotesAdapter notesAdapter;
    FloatingActionButton fab;
    RealmManager realmManager;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        listView = (ListView)findViewById(R.id.notesListView);

        realmManager = new RealmManager(getApplicationContext());
        RealmResults<Note> notesList = realmManager.getRealm().allObjects(Note.class);
        notesAdapter = new NotesAdapter(notesList);
        listView.setAdapter(notesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = (Note) notesAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,NoteDetailActivity.class);
                intent.putExtra("DATE",note.getDate());
                intent.putExtra("HEAD",note.getHead());
                intent.putExtra("BODY",note.getBody());
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNote.class);
                startActivity(intent);
            }
        });

    }
}
