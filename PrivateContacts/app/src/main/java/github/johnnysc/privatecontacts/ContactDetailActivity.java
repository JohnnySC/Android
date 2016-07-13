package github.johnnysc.privatecontacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

import github.johnnysc.privatecontacts.Contacts.ContactContent;

public class ContactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ContactDetailFragment.phoneNumber));
                    startActivity(intent);
                }catch(android.content.ActivityNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fabSMS = (FloatingActionButton)findViewById(R.id.fabSMS);
        fabSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    EditText smsText = (EditText)findViewById(R.id.etSMS);
                    String textSMS = smsText.getText().toString();
                    SmsManager smsManager = SmsManager.getDefault();
                    if(textSMS.length()>0) {
                        smsManager.sendTextMessage(ContactDetailFragment.phoneNumber, null, textSMS, null, null);
                        showSnackBar("Сообщение отправлено");
                        smsText.setText("");
                    }else{
                        showSnackBar("Введите текст сообщения");
                    }
                }catch(android.content.ActivityNotFoundException e){
                    showSnackBar("Не получилось отправить");
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton)findViewById(R.id.fabDELETE);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), "Удалить контакт?", Snackbar.LENGTH_LONG)
                        .setAction("Да", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ContactContent.removeItem(ContactDetailFragment.mItem);
                                Toast.makeText(getApplicationContext(),"Контакт удален!",Toast.LENGTH_SHORT).show();
                                ContactListActivity.adapter.notifyDataSetChanged();
                                try {
                                    FileIO.saveFile(openFileOutput(FileIO.FILE_NAME,MODE_PRIVATE));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                finish();
                            }
                        });
                snackbar.show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ContactDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ContactDetailFragment.ARG_ITEM_ID));
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_detail_container, fragment)
                    .commit();
        }
    }

    private void showSnackBar(String text){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout),text,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ContactListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
