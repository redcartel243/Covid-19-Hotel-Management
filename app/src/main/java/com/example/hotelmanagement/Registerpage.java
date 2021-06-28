package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Registerpage extends AppCompatActivity {
    private Button mButtonregister=null;
    private String objects[]={};
    private String state[]={};
    int counter=0;
    DatabaseHandlerOnline mconnectMySql3;
    private static final int Fourth_ACTIVITY_REQUESTCODE=4;

    //now attributes for registration
    private EditText mFirstname,mLastname,mEmail,mPhonenumber,mUsername,mPassword;
    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);
        mButtonregister=(Button)findViewById(R.id.button3register2);
        mFirstname=(EditText)findViewById(R.id.firstname);
        mLastname=(EditText)findViewById(R.id.lastname);
        mEmail=(EditText)findViewById(R.id.email);
        mPhonenumber=(EditText)findViewById(R.id.phonenumber);
        mUsername=(EditText)findViewById(R.id.username);
        mPassword=(EditText)findViewById(R.id.password);
        mCheckBox=(CheckBox)findViewById(R.id.checkBox);
        DatabaseHandlerOnline mconnectMySql2 = new DatabaseHandlerOnline("state");
        mconnectMySql2.execute("");

        mButtonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mconnectMySql3 = new DatabaseHandlerOnline("new user",mFirstname.getText().toString(),mLastname.getText().toString(),mEmail.getText().toString(),mPhonenumber.getText().toString(),mUsername.getText().toString(),mPassword.getText().toString());
               // mconnectMySql3.execute("");

                    if(mconnectMySql3.passlog==false) {

                        Toast.makeText(Registerpage.this, "User successfully  added", Toast.LENGTH_SHORT)
                                .show();
                        state = mconnectMySql2.state;
                        Intent nextAct = new Intent(Registerpage.this, MainActivity.class);
                        nextAct.putExtra("ob", objects);
                        nextAct.putExtra("st", state);
                        nextAct.putExtra("user",mUsername.getText().toString());
                        startActivityForResult(nextAct, Fourth_ACTIVITY_REQUESTCODE);
                    }
                    else{
                        Toast.makeText(Registerpage.this, "This user already exist,use another user name ", Toast.LENGTH_SHORT).show();

                    }
                //}
            }
        });
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox.isChecked()){
                    mconnectMySql3 = new DatabaseHandlerOnline("register",mFirstname.getText().toString(),mLastname.getText().toString(),mEmail.getText().toString(),mPhonenumber.getText().toString(),mUsername.getText().toString(),mPassword.getText().toString());
                    mconnectMySql3.execute("");
                    mButtonregister.setEnabled(true);
                }
                else{
                    mButtonregister.setEnabled(false);
                }
            }
        });
    }


}