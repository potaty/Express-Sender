package com.potaty.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */

public class InputActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private AutoCompleteTextView addressView, expressNumberView, phoneView;
    private Button button;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        addressView = (AutoCompleteTextView) findViewById(R.id.address);
        expressNumberView = (AutoCompleteTextView) findViewById(R.id.express_number);
        phoneView = (AutoCompleteTextView) findViewById(R.id.phone_number);
        button = (Button) findViewById(R.id.btn05);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {



                new Thread(){
                    @Override
                    public void run()
                    {
                        //把网络访问的代码放在这里
                        String av, en, pv;
                        av = addressView.getText().toString();
                        en = expressNumberView.getText().toString();
                        pv = phoneView.getText().toString();


                        try {
                            String s = HttpRequest.sendPost("http://express.magica.tech/api/mailer/add_item", "title=" + en + "&address=" + av + "&username=" + pv);
                            Log.v("verbose", "title=" + en + "&address=" + av + "&username=" + pv);
                            Intent intent = new Intent(InputActivity.this, ItemListActivity.class);
                            startActivity(intent);
                        }catch(Exception e){

                        }

                    }
                }.start();

            }
        });
    }
}

