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
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.smart.enterpriseplumbing.TermAndConditionActivity;
import com.example.smart.enterpriseplumbing.utils.App;
import com.example.smart.enterpriseplumbing.utils.ConnectionDetector;
import com.example.smart.enterpriseplumbing.utils.Constants;
import com.example.smart.enterpriseplumbing.utils.VolleySingleton;
import com.github.gcacace.signaturepad.views.SignaturePad;

import org.json.JSONArray;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by Smart on 3/28/2018.
 */

public class FinalSignatureActivity extends CommonBaseActivity implements View.OnClickListener {

    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog  progressDialog3;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, region_name_array;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    String strTime, todayDate,strDay;
    String meassge;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_signature_layout);
        connectionDetector = new ConnectionDetector(FinalSignatureActivity.this);
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
        header_Save.setVisibility(View.INVISIBLE);
        txt_Header.setText("New Job");
        back_image.setOnClickListener(this);
        back_image.setVisibility(View.VISIBLE);


        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pads);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(FinalSignatureActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
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

                String   encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);


                loginApi(encoded);


/*
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(MainActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(MainActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        // groupPipStageApi();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_Arrow:
                finish();
                break;

            default:
                break;
        }
    }


    private void loginApi(String encoded) {
        if (App.getInstance().isConnected()) {
            progressDialog3 = setThirdProgressBar();



            try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                String URL = Constants.HOST_URL+"Postorder_form";
                JSONObject params = new JSONObject();
                params.put("save_signature", "");
                params.put("IsActive", "1");
                params.put("id", prefs.getStringValueForTag(Constants.ID));
                params.put("Dispatch_ID", prefs.getStringValueForTag(Constants.DISPACT_ID));
                params.put("customer_of",prefs.getStringValueForTag(Constants.CUSTO_OFF));
                params.put("tech", prefs.getStringValueForTag(Constants.PLUMBER));
                params.put("fname", prefs.getStringValueForTag(Constants.FIRST));
                params.put("lname", prefs.getStringValueForTag(Constants.LAST));
                params.put("address", prefs.getStringValueForTag(Constants.ADDRESS));
                params.put("city", prefs.getStringValueForTag(Constants.CITY));
                params.put("state", prefs.getStringValueForTag(Constants.STATE));
                params.put("zip", prefs.getStringValueForTag(Constants.ZIP));
                params.put("ph_mobile", prefs.getStringValueForTag(Constants.PH_PHONE));
                params.put("ph_alternate", prefs.getStringValueForTag(Constants.PH_ALERT));
                params.put("ph_primary", prefs.getStringValueForTag(Constants.PH_PHONE));
                params.put("email", prefs.getStringValueForTag(Constants.EMAIL));
                params.put("tenant", prefs.getStringValueForTag(Constants.TANT));
                params.put("tenant_phone", prefs.getStringValueForTag(Constants.TANT_PHONE));
                params.put("disclaimer", prefs.getStringValueForTag(Constants.DISCLAIMER));
                params.put("description", prefs.getStringValueForTag(Constants.FINAL_DESC));
                params.put("diagnosis",prefs.getStringValueForTag(Constants.FINAL_DIAGONIS));
                params.put("resolution", prefs.getStringValueForTag(Constants.FINAL_RESOLTI));
                params.put("product", prefs.getStringValueForTag(Constants.FINAL_PRODCUT));
                params.put("finish", prefs.getStringValueForTag(Constants.FINAL_FINISH));
                params.put("brands", prefs.getStringValueForTag(Constants.FINAL_BRAND));
                params.put("features", prefs.getStringValueForTag(Constants.FINAL_FIXTURE));
                params.put("service_type", prefs.getStringValueForTag(Constants.FINAL_SERVICE));
                params.put("service_amount", prefs.getStringValueForTag(Constants.FINAL_AMOUNT));
                params.put("service_fee", prefs.getStringValueForTag(Constants.SERVICES_FEE));
                params.put("paid_by", prefs.getStringValueForTag(Constants.PIAB));
                params.put("total_due", prefs.getStringValueForTag(Constants.DUE));
                params.put("order_date", prefs.getStringValueForTag(Constants.ORDER_DATE));
                params.put("GateCode", prefs.getStringValueForTag(Constants.GATE_CODE));
                params.put("start_order_date", prefs.getStringValueForTag(Constants.START_O_DATE));
                params.put("Auth", prefs.getStringValueForTag(Constants.AUTH));
                params.put("Auth_Amount",prefs.getStringValueForTag(Constants.AUTH_AMOUNT));
                params.put("Check", prefs.getStringValueForTag(Constants.CHECK));
                params.put("Collected", prefs.getStringValueForTag(Constants.COLLECTED));
                params.put("sign_bool","");
                params.put("Invoice_Number",prefs.getStringValueForTag(Constants.IN_VOICE));
                params.put("note", "");
                params.put("ModifiedDate",todayDate);
                params.put("submit_signature", encoded);
                final String requestBody = params.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        meassge="Data save successfully";
                        showSimpleAlertWithMessage( FinalSignatureActivity.this,meassge);
                       // Toast.makeText(getApplicationContext(), "Data save successfully", Toast.LENGTH_SHORT).show();
                        Log.i("VOLLEY", response);
                        progressDialog3.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());

                        meassge="Data save successfully";
                        showSimpleAlertWithMessage( FinalSignatureActivity.this,meassge);
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

                          if(responseString.equalsIgnoreCase("200"))
                          {
                              meassge="Data save successfully";
                              showSimpleAlertWithMessage( FinalSignatureActivity.this,meassge);
                          }




                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        }


    private ProgressDialog setThirdProgressBar() {
        progressDialog3 = new ProgressDialog(FinalSignatureActivity.this);
        progressDialog3.setMessage("Loading...");
        progressDialog3.show();
        return progressDialog3;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(FinalSignatureActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
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
        FinalSignatureActivity.this.sendBroadcast(mediaScanIntent);
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

    public void showSimpleAlertWithMessage( final Context context, String msg) {
        new android.app.AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent i=new Intent(context,PendingWorkActivity.class);
                        startActivity(i);


                    }
                })
                .show();
    }

}