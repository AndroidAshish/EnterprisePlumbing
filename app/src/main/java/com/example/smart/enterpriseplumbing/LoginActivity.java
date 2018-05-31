package com.example.smart.enterpriseplumbing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.smart.enterpriseplumbing.adapter.PendingWorkAdapter;
import com.example.smart.enterpriseplumbing.custom_view.MyEditText;

import com.example.smart.enterpriseplumbing.models.PendingModel;
import com.example.smart.enterpriseplumbing.utils.ConnectionDetector;
import com.example.smart.enterpriseplumbing.utils.Constants;
import com.example.smart.enterpriseplumbing.utils.Perser;
import com.example.smart.enterpriseplumbing.utils.VolleySingleton;

import java.util.ArrayList;


/**
 * Created by App_DEVELOPER on 4/3/2017.
 */

public class LoginActivity extends CommonBaseActivity {
    private ProgressDialog progressDialog;
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    MyEditText email_Login, email_Password;
    String userType;
    ArrayList<PendingModel> planArray;
    ToggleButton tbutton;
    CheckBox rem_userpass;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginfragment);
        LoginActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        LinearLayout login_Btn = (LinearLayout) findViewById(R.id.loginButtonFragment);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //  TextView forgot_button =(TextView)findViewById(R.id.myTextView);
        connectionDetector = new ConnectionDetector(LoginActivity.this);

        email_Login = (MyEditText) findViewById(R.id.email_Login);
        email_Password = (MyEditText) findViewById(R.id.pass_Login);

        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginWithEmail();
            }
        });


        rem_userpass = (CheckBox)findViewById(R.id.remember_CkbxFragment);

        if (rem_userpass.isChecked()) {
            saveLoginDetails();
        } else {
            prefs.setBooleanValueForTag(Constants.KEY_REMEMBER, false);
        }
      /*  if(prefs.getBooleanValueForTag(Constants.KEY_REMEMBER))
        {
            rem_userpass.setChecked(true);
        }
        else
        {
            rem_userpass.setChecked(false);

            email_Login.setText(prefs.getStringValueForTag(Constants.SAVE_EMAIL));
            email_Password.setText(prefs.getStringValueForTag(Constants.SAVE_PASSWORD));
            rem_userpass.setOnCheckedChangeListener(this);

        }
*/




    }



    public void hideSoftKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) view
                .getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder binder = view.getWindowToken();
        inputManager.hideSoftInputFromWindow(binder,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void loginWithEmail() {
        String email_login = email_Login.getText().toString().trim();
        String pass_logon = email_Password.getText().toString();
        prefs.setStringValueForTag(Constants.SAVE_EMAIL,email_login);
        prefs.setStringValueForTag(Constants.SAVE_PASSWORD,pass_logon);

        if (email_login == null || email_login.equalsIgnoreCase("")) {
            Toast.makeText(LoginActivity.this, "Email should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
     /*   if (!isValidEmail(email_login)) {
            Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (pass_logon == null || pass_logon.equalsIgnoreCase("")) {
            Toast.makeText(LoginActivity.this, "Password should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        loginApi();
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }

    private ProgressDialog setProgressBar() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        return progressDialog;
    }

    public void showSimpleAlertWithMessage(Context context, String msg) {
        new android.app.AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                })
                .show();
    }

    public void loginApi() {
        isInternetPresent = connectionDetector.isConnectingToInternet();
        if (isInternetPresent) {
            progressDialog = setProgressBar();
            StringRequest request = new StringRequest(Request.Method.GET, Constants.HOST_URL +"Get_Assigned_Task/?username="+email_Login.getText().toString()+"&password="+email_Password.getText().toString(),

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                             //   http://108.80.86.132:91/api/Contact/AuthenticateUser?email=rlbaid15@gmail.com&password=user12

                                JSONArray jsonArray = new JSONArray(response);
                               // String status = jsonObject.getString("statusCode");
                              //  String messagee=jsonObject.getString("message");

                                planArray = Perser.getTitleHis(jsonArray);

                                if (!planArray.isEmpty()) {
                                    Intent i=new Intent(LoginActivity.this,PendingWorkActivity.class);
                                    startActivity(i);

                                } else {


                                }

                               // prefs.setBooleanValueForTag(Constants.IS_LOGIN, true);


                               // showSimpleAlertWithMessage(LoginActivity.this, messagee);
                                progressDialog.dismiss();








                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this, "Connection problem. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });


            VolleySingleton.getInstance(LoginActivity.this).addToRequestque(request);
            //http://ec2-18-217-91-105.us-east-2.compute.amazonaws.com:93/api/order_form/Get_Assigned_Task/?username=Vik%20Baid&password=Aladdin123!

        } else {
            Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }




   /* private void managePrefs(){
        if(rem_userpass.isChecked()){
            String email_login = email_Login.getText().toString().trim();
            String pass_logon = email_Password.getText().toString();

            prefs.setStringValueForTag(Constants.SAVE_EMAIL,email_login);
            prefs.setStringValueForTag(Constants.SAVE_PASSWORD,pass_logon);
            prefs.setBooleanValueForTag(Constants.KEY_REMEMBER,true);

        }else{

            prefs.setBooleanValueForTag(Constants.KEY_REMEMBER,false);
            prefs.clearPreferences();

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();
    }*/

    private void saveLoginDetails() {
        String email = ((EditText) findViewById(R.id.email_Login)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.pass_Login)).getText().toString();
        prefs.setStringValueForTag(Constants.SAVE_EMAIL, email);
        prefs.setStringValueForTag(Constants.SAVE_PASSWORD, password);
        prefs.setBooleanValueForTag(Constants.KEY_REMEMBER, true);
    }
}
