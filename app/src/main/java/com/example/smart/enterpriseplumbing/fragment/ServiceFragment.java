package com.example.smart.enterpriseplumbing.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;

/**
 * Created by Smart on 3/19/2018.
 */

public class ServiceFragment extends CommonBaseFragment {
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog, progressDialog2,progressDialog3,progressDialog4;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, region_name_array;
    Spinner spinner_Pipline,spinner_Location,spinner_Brand,spinner_fixture,spinner_finish;
String final_Service;
    String ser1;
    String ser2;
    String ser3;
    String ser4;
    String ser5;

    String amount1;
    String amount2;
    String amount3;
    String amount4;
    String amount5;

    String service_first="";
    String service_second="";
    String service_third="";
    String service_fourth="";

    String amount="";
    String amount_first="";
    String amount_second="";
    String amount_third="";
    String amount_fourth="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.service_new_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        connectionDetector = new ConnectionDetector(getActivity());
        catogory_name_array = new ArrayList<String>();
        catogory_id_array = new ArrayList<String>();
        final EditText edit_Ser_First=(EditText)v.findViewById(R.id.edit_Ser_First);
        final EditText edit_Ser_Second=(EditText)v.findViewById(R.id.edit_Ser_Second);
        final EditText edit_Ser_Third=(EditText)v.findViewById(R.id.edit_Ser_Third);
        final EditText edit_Ser_Fourth=(EditText)v.findViewById(R.id.edit_Ser_Fourth);

        final EditText edit_Amount=(EditText)v.findViewById(R.id.edit_Amount);
        final EditText edit_Amo_First=(EditText)v.findViewById(R.id.edit_Amo_First);
        final EditText edit_Amu_Second=(EditText)v.findViewById(R.id.edit_Amu_Second);
        final EditText edit_Amu_Third=(EditText)v.findViewById(R.id.edit_Amu_Third);
        final EditText edit_Amu_Fourth=(EditText)v.findViewById(R.id.edit_Amu_Fourth);



        service_first=edit_Ser_First.getText().toString();
        service_second=edit_Ser_Second.getText().toString();
        service_third =edit_Ser_Third.getText().toString();
        service_fourth=edit_Ser_Fourth.getText().toString();

        amount=edit_Amount.getText().toString();
        amount_first=edit_Amo_First.getText().toString();
        amount_second=edit_Amu_Second.getText().toString();
        amount_third=edit_Amu_Third.getText().toString();
        amount_fourth=edit_Amu_Fourth.getText().toString();





        String type=prefs.getStringValueForTag(Constants.SERVICES_TYPE);
        String amount=prefs.getStringValueForTag(Constants.SER_AMOUNT);

        String[] allDes = type.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            ser1 = "";
            ser2 = "";
            ser3 = "";
            ser4 = "";
            ser5 = "";
            if(allDes.length >= 1 && allDes[0].contains("ServiceType:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser1 = allDes[0].substring(allDes[0].indexOf(":") + 1);

            }

            if(allDes.length >= 1 && allDes[1].contains("ServiceType1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser2 = allDes[1].substring(allDes[1].indexOf(":") + 1);
                edit_Ser_First.setText(ser2);
            }


            if(allDes.length >= 1 && allDes[2].contains("ServiceType2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser3 = allDes[2].substring(allDes[2].indexOf(":") + 1);
                edit_Ser_Second.setText(ser3);
            }

            if(allDes.length >= 1 && allDes[3].contains("ServiceType3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser4 = allDes[3].substring(allDes[3].indexOf(":") + 1);
                edit_Ser_Third.setText(ser4);
            }

            if(allDes.length >= 1 && allDes[4].contains("ServiceType4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                ser5 = allDes[4].substring(allDes[4].indexOf(":") + 1);
                edit_Ser_Fourth.setText(ser5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] allAmount = amount.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            amount1 = "";
            amount2 = "";
            amount3 = "";
            amount4 = "";
            amount5 = "";
            if(allAmount.length >= 1 && allAmount[0].contains("Amount:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                amount1 = allAmount[0].substring(allAmount[0].indexOf(":") + 1);
                edit_Amount.setText(amount1);
            }

            if(allAmount.length >= 1 && allAmount[1].contains("Amount1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                amount2 = allAmount[1].substring(allAmount[1].indexOf(":") + 1);
                edit_Amo_First.setText(amount2);
            }


            if(allAmount.length >= 1 && allAmount[2].contains("Amount2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                amount3 = allAmount[2].substring(allAmount[2].indexOf(":") + 1);
                edit_Amu_Second.setText(amount3);
            }

            if(allAmount.length >= 1 && allAmount[3].contains("Amount3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                amount4 = allAmount[3].substring(allAmount[3].indexOf(":") + 1);
                edit_Amu_Third.setText(amount4);
            }

            if(allAmount.length >= 1 && allAmount[4].contains("Amount4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                amount5 = allAmount[4].substring(allAmount[4].indexOf(":") + 1);
                edit_Amu_Fourth.setText(amount5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        spinner_Location = (Spinner)v.findViewById(R.id.spinner_Service);
        groupPipStageApi();

        RelativeLayout confirm_your_booking=(RelativeLayout)v.findViewById(R.id.save_Buttom);
        confirm_your_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis_First=edit_Ser_First.getText().toString();
                String dis_Second=edit_Ser_Second.getText().toString();
                String  dis_Third=edit_Ser_Third.getText().toString();
                String dis_Fourth=edit_Ser_Fourth.getText().toString();

                String amount=edit_Amount.getText().toString();
                String das_first=edit_Amo_First.getText().toString();
                String das_Second=edit_Amu_Second.getText().toString();
                String das_third=edit_Amu_Third.getText().toString();
                String das_fourth=edit_Amu_Fourth.getText().toString();

                String  super_Dis= "ServiceType:".concat(final_Service).concat(";").concat("ServiceType1:").concat(dis_First).concat(";").concat("ServiceType2:").concat(dis_Second).concat(";").concat("ServiceType3:").concat(dis_Third).concat(";").concat("ServiceType4:").concat(dis_Fourth).concat(";");
                String super_amount= "Amount:".concat(amount).concat(";").concat("Amount1:").concat(das_first).concat(";").concat("Amount2:").concat(das_Second).concat(";").concat("Amount3:").concat(das_third).concat(";").concat("Amount4:").concat(das_fourth).concat(";");

                prefs.setStringValueForTag(Constants.FINAL_SERVICE,super_Dis);
                prefs.setStringValueForTag(Constants.FINAL_AMOUNT,super_amount);
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

                                    if (items.equalsIgnoreCase("service-type")) {
                                        catogory_name_array.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array.add(jsonArray.getJSONObject(i).getString("id"));


                                        setCatogorySpinner(catogory_name_array, catogory_id_array);

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

    private void setCatogorySpinner( final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {


        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Location.setAdapter(adapter_tasting_room_atmosphere_spinner);
            spinner_Location.setSelection(getIndex(spinner_Location, ser1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Service = catogory_name_array.get(position);

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

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
