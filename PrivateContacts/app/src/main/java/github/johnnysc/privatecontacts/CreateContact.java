package github.johnnysc.privatecontacts;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

import github.johnnysc.privatecontacts.Contacts.ContactContent;

public class CreateContact extends AppCompatActivity {
    EditText phoneNumber, contactName;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        phoneNumber = (EditText)findViewById(R.id.etNumber);
        contactName = (EditText)findViewById(R.id.etName);
        addButton = (Button)findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phoneNumber.length()>6 && contactName.length()>1){
                    ContactContent.addItem(new ContactContent.ContactItem(contactName.getText().toString(),phoneNumber.getText().toString()));
                    Toast.makeText(getApplicationContext(),"Добавлен контакт",Toast.LENGTH_LONG).show();
                    ContactListActivity.adapter.notifyDataSetChanged();
                    ContactListActivity.sortContacts();
                    try {
                        FileIO.saveFile(openFileOutput(FileIO.FILE_NAME, MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    finish();
                }else{
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.addLayout),"Номер должен быть больше 6 знаков и непустое имя",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
}
