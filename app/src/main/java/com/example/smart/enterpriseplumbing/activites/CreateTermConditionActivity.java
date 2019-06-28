package com.example.smart.enterpriseplumbing.activites;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart.enterpriseplumbing.CommonBaseActivity;
import com.example.smart.enterpriseplumbing.PendingWorkActivity;
import com.example.smart.enterpriseplumbing.R;
import com.example.smart.enterpriseplumbing.utils.App;
import com.example.smart.enterpriseplumbing.utils.ConnectionDetector;
import com.example.smart.enterpriseplumbing.utils.Constants;
import com.github.gcacace.signaturepad.views.SignaturePad;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Smart on 10/2/2018.
 */

public class CreateTermConditionActivity extends CommonBaseActivity implements View.OnClickListener {
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, region_name_array;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
 
    String strTime, todayDate, strDay;
    CheckBox check_First,check_Second,check_Third,check_fourth,check_Fifth;
    String encoded;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_condition_layout);
        connectionDetector = new ConnectionDetector(CreateTermConditionActivity.this);
        ImageView back_image = (ImageView) findViewById(R.id.back_Arrow);
        catogory_name_array = new ArrayList<String>();
        catogory_id_array = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf4 = new SimpleDateFormat("EEE");
        strTime = sdf.format(c.getTime());
        todayDate = sdf3.format(c.getTime());
        strDay = sdf4.format(c.getTime());

        TextView txt_Header = (TextView) findViewById(R.id.header_title);
        TextView header_Save = (TextView) findViewById(R.id.header_Save);
        ImageView search_Tick = (ImageView) findViewById(R.id.search_Tick);
        check_First=(CheckBox)findViewById(R.id.remember_First) ;
         check_Second=(CheckBox)findViewById(R.id.remember_Second) ;
         check_Third=(CheckBox)findViewById(R.id.remember_Third) ;
         check_fourth=(CheckBox)findViewById(R.id.remember_Fourth) ;
         check_Fifth=(CheckBox)findViewById(R.id.remember_Fifith) ;
        header_Save.setVisibility(View.INVISIBLE);
        txt_Header.setText("New Job");
        search_Tick.setVisibility(View.GONE);
        back_image.setOnClickListener(this);
        search_Tick.setOnClickListener(this);

        back_image.setVisibility(View.VISIBLE);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(CreateTermConditionActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                 encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                //   Toast.makeText(TermAndConditionActivity.this, encoded, Toast.LENGTH_SHORT).show();


              //  loginApi(encoded);




                if(!check_First.isChecked())
                {
                    showTextNotification();
                }

                if(!check_Second.isChecked())
                {
                    showTextNotification();
                }

                if(!check_Third.isChecked())
                {
                    showTextNotification();
                }
                if(!check_fourth.isChecked())
                {
                    showTextNotification();
                }

                if(!check_Fifth.isChecked())
                {
                    showTextNotification();
                }
                else
                {

                    loginApi(encoded);
                }



            }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                


            
        });

        // groupPipStageApi();

    }

    private void showTextNotification() {

        Toast.makeText(this,"Please Select all Term and Conditions", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_Arrow:
                finish();
                break;
            case R.id.search_Tick:
                Intent i = new Intent(CreateTermConditionActivity.this, FinalSignatureActivity.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }


    private ProgressDialog setThirdProgressBar() {
        progressDialog = new ProgressDialog(CreateTermConditionActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        return progressDialog;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(CreateTermConditionActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        CreateTermConditionActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void loginApi(final String encoded) {
        if (App.getInstance().isConnected()) {
            progressDialog = setThirdProgressBar();

            try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                String URL = "http://ec2-18-217-91-105.us-east-2.compute.amazonaws.com:93/api/order_form/Postorder_form";
                JSONObject params = new JSONObject();
                params.put("save_signature", encoded);
                params.put("IsActive", "false");
                params.put("id", "0");
                params.put("Dispatch_ID", prefs.getStringValueForTag(Constants.CREATE_DISPACTH_ID));
                params.put("customer_of", prefs.getStringValueForTag(Constants.CREATE_CUSTO_OFF));
                params.put("tech", prefs.getStringValueForTag(Constants.CREATE_PLUMBER));
                params.put("fname", prefs.getStringValueForTag(Constants.CREATE_FIRST));
                params.put("lname", prefs.getStringValueForTag(Constants.CREATE_LAST));
                params.put("address", prefs.getStringValueForTag(Constants.CREATE_ADDRESS));
                params.put("city", prefs.getStringValueForTag(Constants.CREATE_CITY));
                params.put("state", prefs.getStringValueForTag(Constants.CREATE_STATE));
                params.put("zip", prefs.getStringValueForTag(Constants.CREATE_ZIP));
                params.put("ph_mobile", prefs.getStringValueForTag(Constants.CREATE_MOBILE));
                params.put("ph_alternate", "");
                params.put("ph_primary", "");
                params.put("email", prefs.getStringValueForTag(Constants.CREATE_EMAIL));
                params.put("tenant", "");
                params.put("tenant_phone", "");
                params.put("disclaimer", prefs.getStringValueForTag(Constants.DISCLAIMER));
                params.put("description", prefs.getStringValueForTag(Constants.CREATE_FINAL_DESC));
                params.put("diagnosis", prefs.getStringValueForTag(Constants.CREATE_FINAL_DIAGONIS));
                params.put("resolution", prefs.getStringValueForTag(Constants.CREATE_FINAL_RESOLTI));
                params.put("product", prefs.getStringValueForTag(Constants.CREATE_FINAL_PRODCUT));
                params.put("finish", prefs.getStringValueForTag(Constants.CREATE_FINAL_FINISH));
                params.put("brands", prefs.getStringValueForTag(Constants.CREATE_FINAL_BRAND));
                params.put("features", prefs.getStringValueForTag(Constants.CREATE_FINAL_FIXTURE));
                params.put("service_type", prefs.getStringValueForTag(Constants.CREATE_FINAL_SERVICE));
                params.put("service_amount", prefs.getStringValueForTag(Constants.CREATE_FINAL_AMOUNT));
                params.put("service_fee", "00");
                params.put("paid_by", "");
                params.put("total_due", "000");
                params.put("order_date", todayDate);
                params.put("GateCode", prefs.getStringValueForTag(Constants.CREATE_GATE_CODE));
                params.put("start_order_date", todayDate);
                params.put("Auth", "");
                params.put("Auth_Amount", "");
                params.put("Check", "");
                params.put("Collected", "");
                params.put("sign_bool", "");
                params.put("Invoice_Number", "");
                params.put("note", "");
                params.put("ModifiedDate", "");
                params.put("submit_signature", "");
                final String requestBody = params.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String meassge = "Data save successfully";

                        showSimpleAlertWithMessage(CreateTermConditionActivity.this, meassge);
                        //  Toast.makeText(getApplicationContext(), "Data save successfully", Toast.LENGTH_SHORT).show();
                        Log.i("VOLLEY", response);
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }


    }

    public void showSimpleAlertWithMessage(final Context context, String msg) {
        new android.app.AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        prefs.setStringValueForTag(Constants.CREATE_DISPACTH_ID, "");
                        prefs.setStringValueForTag(Constants.CREATE_GATE_CODE, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST, "");
                        prefs.setStringValueForTag(Constants.CREATE_LAST, "");
                        prefs.setStringValueForTag(Constants.CREATE_ADDRESS, "");
                        prefs.setStringValueForTag(Constants.CREATE_STATE, "");
                        prefs.setStringValueForTag(Constants.CREATE_CITY, "");
                        prefs.setStringValueForTag(Constants.CREATE_ZIP, "");
                        prefs.setStringValueForTag(Constants.CREATE_CUSTO_OFF, "");
                        prefs.setStringValueForTag(Constants.CREATE_PLUMBER, "");
                        prefs.setStringValueForTag(Constants.CREATE_MOBILE, "");
                        prefs.setStringValueForTag(Constants.CREATE_EMAIL, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_DES, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_DIG, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_RES, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_DES, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_DIG, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_RES, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_DES, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_DIG, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_RES, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_DES, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_DIG, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_RES, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_DES, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_DIG, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_RES, "");
                        prefs.setStringValueForTag(Constants.CREATE_SERVICE_FIRST, "");
                        prefs.setStringValueForTag(Constants.CREATE_SERVICE_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_SERVICE_S, "");
                        prefs.setStringValueForTag(Constants.CREATE_SERVICE_T, "");
                        prefs.setStringValueForTag(Constants.CREATE_SERVICE_FO, "");
                        prefs.setStringValueForTag(Constants.CREATE_AMO_FIRST, "");
                        prefs.setStringValueForTag(Constants.CREATE_AMO_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_AMO_S, "");
                        prefs.setStringValueForTag(Constants.CREATE_AMO_T, "");
                        prefs.setStringValueForTag(Constants.CREATE_AMO_FO, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_P, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_B, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_FIRST_FI, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_P, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_B, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_F_FI, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_P, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_B, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_S_FI, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_P, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_B, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_T_FI, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_P, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_B, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_F, "");
                        prefs.setStringValueForTag(Constants.CREATE_FO_FI, "");


                        Intent i = new Intent(context, PendingWorkActivity.class);
                        startActivity(i);


                    }
                })
                .show();
    }

 


 
}
