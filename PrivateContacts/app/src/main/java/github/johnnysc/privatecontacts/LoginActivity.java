package github.johnnysc.privatecontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hovhannes Asatryan on 13.07.16.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private int code;
    SharedPreferences sharedPreferences;
    Button forwardButton,enterButton;
    EditText passwordOne, passwordMain, passwordReEnter,hint;
    TextView textViewHINT;
    final String SAVED_TEXT = "saved_text";
    final String SAVED_CODE = "saved_code";
    final String SAVED_HINT = "saved_hint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        code = sharedPreferences.getInt(SAVED_CODE, 0);

        if(code==0) {
            findViewById(R.id.entering).setVisibility(View.INVISIBLE);
            createPassword();
        }else {
            findViewById(R.id.loginForm).setVisibility(View.INVISIBLE);
            enterExistingPassword();
        }
    }

    private void enterExistingPassword(){
        passwordMain = (EditText)findViewById(R.id.etMainPassword);
        enterButton = (Button)findViewById(R.id.enterButton);
        enterButton.setOnClickListener(this);
        textViewHINT = (TextView)findViewById(R.id.txtViewHINT);
    }

    private void createPassword(){
        passwordOne = (EditText)findViewById(R.id.etPassword01);
        passwordReEnter = (EditText)findViewById(R.id.etPassword02);
        forwardButton = (Button)findViewById(R.id.goButton);
        hint = (EditText)findViewById(R.id.etHint);
        forwardButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.goButton:
                checkMatchingPassword();
                break;
            case R.id.enterButton:
                login();
                break;
        }
    }

    private void checkMatchingPassword(){
        String passwordONE = passwordOne.getText().toString();
        String passwordTWO = passwordReEnter.getText().toString();
        String hintWord = hint.getText().toString();

        if(passwordONE.length()<5 || passwordTWO.length()<5){
            showSnackBar(1,"Пароль должен быть минимум 5 символов!");
        }else {
            if (passwordONE.equals(passwordTWO)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SAVED_TEXT,passwordONE);
                editor.putString(SAVED_HINT,hintWord);
                editor.putInt(SAVED_CODE,1);
                editor.apply();
                goToMain();
            } else {
                showSnackBar(1,"Пароли не совпадают!");
            }
        }
    }

    private void login(){
        String passwordFromFile = sharedPreferences.getString(SAVED_TEXT,"");
        String getPassword = passwordMain.getText().toString();
        String hintWord = sharedPreferences.getString(SAVED_HINT,"");
        if(passwordFromFile.equals(getPassword)){
            goToMain();
        }else{
            showSnackBar(2,"Неверный пароль!");
            textViewHINT.setText(hintWord);
        }
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, ContactListActivity.class);
        startActivity(intent);
        finish();
    }

    public void showSnackBar(int layoutCode, String text){
        Snackbar snackbar;
        if(layoutCode==1){
           snackbar = Snackbar.make(findViewById(R.id.loginForm),text,Snackbar.LENGTH_LONG);
        }else{
            snackbar = Snackbar.make(findViewById(R.id.entering),text,Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }
}
