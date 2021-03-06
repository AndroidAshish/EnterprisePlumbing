package com.example.smart.enterpriseplumbing.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart.enterpriseplumbing.R;
import com.example.smart.enterpriseplumbing.utils.ConnectionDetector;
import com.example.smart.enterpriseplumbing.utils.Constants;
import com.example.smart.enterpriseplumbing.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Smart on 10/2/2018.
 */

public class CreatePlanFragment extends CommonBaseFragment {
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog2, progressDialog3, progressDialog4;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, catogory_name_array1, catogory_id_array1;
    Spinner spinner_Pipline, spinner_Location, spinner_Brand, spinner_fixture, spinner_finish;
    String final_plumber;
    String ser1;
    String ser2;
    String ser3;
    String ser4;
    String ser5;
    String plumber;
    Spinner spinner_Alw;
    LinearLayout relativeLayout_Customer;
    String    final_customer;
    String strTime, todayDate, strDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_plan_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        connectionDetector = new ConnectionDetector(getActivity());
        catogory_name_array = new ArrayList<String>();
        catogory_id_array = new ArrayList<String>();
        catogory_name_array1 = new ArrayList<String>();
        catogory_id_array1 = new ArrayList<String>();

        final EditText txt_Id = (EditText) v.findViewById(R.id.txt_ID);
        final EditText txt_Code = (EditText) v.findViewById(R.id.txt_Code);
        //  EditText txt_Plumber=(EditText)v.findViewById(R.id.txt_Plumber);
        final EditText txt_First = (EditText) v.findViewById(R.id.txt_First);
        final EditText txt_Last = (EditText) v.findViewById(R.id.txt_Last);
        final EditText txt_Addres = (EditText) v.findViewById(R.id.txt_Addresss);
        final EditText txt_State = (EditText) v.findViewById(R.id.txt_State);
        final EditText txt_City = (EditText) v.findViewById(R.id.txt_City);
        final EditText txt_Zip = (EditText) v.findViewById(R.id.txt_Zip);
        final EditText txt_Mobile = (EditText) v.findViewById(R.id.txt_Mobile);
        final EditText txt_Email = (EditText) v.findViewById(R.id.txt_Email);
       // final EditText txt_Off = (EditText) v.findViewById(R.id.txt_Of);
        relativeLayout_Customer=(LinearLayout)v.findViewById(R.id.real_DisID);





        txt_Id.setText(prefs.getStringValueForTag(Constants.CREATE_DISPACTH_ID));
        txt_Code.setText(prefs.getStringValueForTag(Constants.CREATE_GATE_CODE));
        txt_First.setText(prefs.getStringValueForTag(Constants.CREATE_FIRST));
        txt_Last.setText(prefs.getStringValueForTag(Constants.CREATE_LAST));
        txt_Addres.setText(prefs.getStringValueForTag(Constants.CREATE_ADDRESS));
        txt_State.setText(prefs.getStringValueForTag(Constants.CREATE_STATE));
        txt_City.setText(prefs.getStringValueForTag(Constants.CREATE_CITY));
        txt_Zip.setText(prefs.getStringValueForTag(Constants.CREATE_ZIP));
        txt_Mobile.setText(prefs.getStringValueForTag(Constants.CREATE_MOBILE));
        txt_Email.setText(prefs.getStringValueForTag(Constants.CREATE_EMAIL));






        ser1= prefs.getStringValueForTag(Constants.CREATE_CUSTO_OFF);
        ser2= prefs.getStringValueForTag(Constants.CREATE_PLUMBER);





      /*  String[] allDes = plumber.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            ser1 = "";
            ser2 = "";
            ser3 = "";
            ser4 = "";
            ser5 = "";
            if (allDes.length >= 1 && allDes[0].contains("ServiceType:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser1 = allDes[0].substring(allDes[0].indexOf(":") + 1);
            }

            if (allDes.length >= 1 && allDes[1].contains("ServiceType1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser2 = allDes[1].substring(allDes[1].indexOf(":") + 1);
            }


            if (allDes.length >= 1 && allDes[2].contains("ServiceType2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser3 = allDes[2].substring(allDes[2].indexOf(":") + 1);
            }

            if (allDes.length >= 1 && allDes[3].contains("ServiceType3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser4 = allDes[3].substring(allDes[3].indexOf(":") + 1);
            }

            if (allDes.length >= 1 && allDes[4].contains("ServiceType4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser5 = allDes[4].substring(allDes[4].indexOf(":") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        spinner_Location = (Spinner) v.findViewById(R.id.spinner_Plumber);
        spinner_Alw=(Spinner)v.findViewById(R.id.spinner_Alw);
        groupPipStageApi();

        RelativeLayout confirm_your_booking = (RelativeLayout) v.findViewById(R.id.rel_Save);
        confirm_your_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String final_Id = txt_Id.getText().toString();
                String final_Code = txt_Code.getText().toString();
                String final_first = txt_First.getText().toString();
                String final_Last = txt_Last.getText().toString();
                String final_Address = txt_Addres.getText().toString();
                String final_State = txt_State.getText().toString();
                String final_City = txt_City.getText().toString();
                String fianl_Zip = txt_Zip.getText().toString();
                String mobile=txt_Mobile.getText().toString();
                String email=txt_Email.getText().toString();
              //  String final_Off = txt_Off.getText().toString();

                prefs.setStringValueForTag(Constants.CREATE_DISPACTH_ID, final_Id);
                prefs.setStringValueForTag(Constants.CREATE_GATE_CODE, final_Code);
                prefs.setStringValueForTag(Constants.CREATE_FIRST, final_first);
                prefs.setStringValueForTag(Constants.CREATE_LAST, final_Last);
                prefs.setStringValueForTag(Constants.CREATE_ADDRESS, final_Address);
                prefs.setStringValueForTag(Constants.CREATE_STATE, final_State);
                prefs.setStringValueForTag(Constants.CREATE_CITY, final_City);
                prefs.setStringValueForTag(Constants.CREATE_ZIP, fianl_Zip);
                prefs.setStringValueForTag(Constants.CREATE_CUSTO_OFF, final_customer);
                prefs.setStringValueForTag(Constants.CREATE_PLUMBER, final_plumber);
                prefs.setStringValueForTag(Constants.CREATE_MOBILE,mobile);
                prefs.setStringValueForTag(Constants.CREATE_EMAIL,email);
                Toast.makeText(getActivity(), "Data Save", Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }

    private void groupPipStageApi() {

        isInternetPresent = connectionDetector.isConnectingToInternet();
        if (isInternetPresent) {
            progressDialog3 = setThirdProgressBar();
            StringRequest request = new StringRequest(Constants.HOST_URL + "Form_PickerValues",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                //  jsonArray = new JSONArray(response);

                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String items = jsonArray.getJSONObject(i).getString("category");

                                    if (items.equalsIgnoreCase("tech")) {
                                        catogory_name_array.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array.add(jsonArray.getJSONObject(i).getString("id"));

                                        setCatogorySpinner(catogory_name_array, catogory_id_array);

                                    }

                                    else if (items.equalsIgnoreCase("customer_of")) {
                                        catogory_name_array1.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array1.add(jsonArray.getJSONObject(i).getString("id"));
                                        setFirstCato(catogory_name_array1, catogory_id_array1);

                                    }
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog3.dismiss();

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getActivity(), "Connection problem. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });


            VolleySingleton.getInstance(getActivity()).addToRequestque(request);


        } else {
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        }


    }

    private void setCatogorySpinner(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {


        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Location.setAdapter(adapter_tasting_room_atmosphere_spinner);


           if (ser2.equals("")) {
               spinner_Location.setSelection(getIndex(spinner_Location, ser2));
            } else if (!ser2.isEmpty()) {
               spinner_Location.setSelection(getIndex(spinner_Location, ser2));
            }


            //  spinner_Location.setSelection(getIndex(spinner_Location, ser1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                final_plumber = catogory_name_array.get(position);




                // regionSpinnerApi(countri_spinner_selected_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }

    private ProgressDialog setThirdProgressBar() {
        progressDialog3 = new ProgressDialog(getActivity());
        progressDialog3.setMessage("Loading...");
        progressDialog3.show();
        return progressDialog3;
    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }


    private void setFirstCato(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {


        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Alw.setAdapter(adapter_tasting_room_atmosphere_spinner);


            if (ser1.equals("")) {
                spinner_Alw.setSelection(getIndex(spinner_Alw, ser1));
            } else if (!ser1.isEmpty()) {
                spinner_Alw.setSelection(getIndex(spinner_Alw, ser1));
            }


            //  spinner_Location.setSelection(getIndex(spinner_Location, ser1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Alw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_customer = catogory_name_array.get(position);

                if(final_customer.equalsIgnoreCase("AHS"))
                {
                    relativeLayout_Customer.setVisibility(View.VISIBLE);
                }

                if(final_customer.equalsIgnoreCase("NON-AHS"))
                {
                    relativeLayout_Customer.setVisibility(View.GONE);
                }

                // regionSpinnerApi(countri_spinner_selected_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }


}