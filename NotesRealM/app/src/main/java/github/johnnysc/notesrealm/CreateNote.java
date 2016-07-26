package github.johnnysc.notesrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

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

        makeObservable(etHead, 5);

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

    private void makeObservable(EditText editText, final int charLength){
        Observable<CharSequence> observable = RxTextView.textChanges(editText);
        observable.map(new Func1<CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence) {
                return charSequence.length() >= charLength;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                createButton.setEnabled(aBoolean);
            }
        });
    }
}
