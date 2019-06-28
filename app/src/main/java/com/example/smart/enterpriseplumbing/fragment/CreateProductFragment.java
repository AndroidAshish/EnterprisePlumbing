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

import java.util.ArrayList;

/**
 * Created by Smart on 10/2/2018.
 */

public class CreateProductFragment  extends CommonBaseFragment {

    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog, progressDialog2, progressDialog3, progressDialog4;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, catogory_name_array1, catogory_id_array1, catogory_name_array2, catogory_id_array2, catogory_name_array3, catogory_id_array3;
    Spinner spinner_Pipline, spinner_Location, spinner_Brand, spinner_fixture, spinner_finish;

    ArrayList<String> listOfString;
    String final_prodcut, final_Finish, Final_fixture, final_Brand;

    String brand1;
    String brand2;
    String brand3;
    String brand4;
    String brand5;
    String product1;
    String product2;
    String product3;
    String product4;
    String product5;

    String feature1;
    String feature2;
    String feature3;
    String feature4;
    String feature5;

    String finish1;
    String finish2;
    String finish3;
    String finish4;
    String finish5;

    String brand,finish,feature;
    String product;

    String pro_First = "";
    String pro_Second = "";
    String pro_Third = "";
    String pro_Fourth = "";

    String brand_first = "";
    String brand_second = "";
    String brand_third = "";
    String brand_fourth = "";

    String fix_first = "";
    String fix_second = "";
    String fix_third = "";
    String fix_fourth = "";

    String finish_first = "";
    String finish_second = "";
    String finish_third = "";
    String finish_fourth = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_product_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        connectionDetector = new ConnectionDetector(getActivity());
        catogory_name_array = new ArrayList<String>();
        catogory_id_array = new ArrayList<String>();
        catogory_name_array1 = new ArrayList<String>();
        catogory_id_array1 = new ArrayList<String>();
        catogory_name_array2 = new ArrayList<String>();
        catogory_id_array2 = new ArrayList<String>();
        catogory_name_array3 = new ArrayList<String>();
        catogory_id_array3 = new ArrayList<String>();


        final EditText edit_pro_First = (EditText) v.findViewById(R.id.edit_Loc_First);
        final EditText edit_pro_Second = (EditText) v.findViewById(R.id.edit_Loc_Second);
        final EditText edit_pro_Third = (EditText) v.findViewById(R.id.edit_Loc_Third);
        final EditText edit_pro_Fourth = (EditText) v.findViewById(R.id.edit_Loc_fourth);

        final EditText edit_brand_First = (EditText) v.findViewById(R.id.edit_Brand_First);
        final EditText edit_brand_Second = (EditText) v.findViewById(R.id.edit_Brand_Second);
        final EditText edit_brand_Third = (EditText) v.findViewById(R.id.edit_Brand_Third);
        final EditText edit_brand_Fourth = (EditText) v.findViewById(R.id.edit_Brand_fourth);


        final EditText edit_fix_First = (EditText) v.findViewById(R.id.edit_fix_First);
        final EditText edit_fix_Second = (EditText) v.findViewById(R.id.edit_fix_Second);
        final EditText edit_fix_Third = (EditText) v.findViewById(R.id.edit_fix_Third);
        final EditText edit_fix_Fourth = (EditText) v.findViewById(R.id.edit_fix_Fourth);

        final EditText edit_finish_First = (EditText) v.findViewById(R.id.edit_finish_First);
        final EditText edit_finish_Second = (EditText) v.findViewById(R.id.edit_finish_Second);
        final EditText edit_finish_Third = (EditText) v.findViewById(R.id.edit_finish_Third);
        final EditText edit_finish_Fourth = (EditText) v.findViewById(R.id.edit_finish_Fourth);


        pro_First = edit_pro_First.getText().toString();
        pro_Second = edit_pro_Second.getText().toString();
        pro_Third = edit_pro_Third.getText().toString();
        pro_Fourth = edit_pro_Fourth.getText().toString();

        brand_first = edit_brand_First.getText().toString();
        brand_second = edit_brand_Second.getText().toString();
        brand_third = edit_brand_Third.getText().toString();
        brand_fourth = edit_brand_Fourth.getText().toString();

        fix_first = edit_fix_First.getText().toString();
        fix_second = edit_fix_Second.getText().toString();
        fix_third = edit_fix_Third.getText().toString();
        fix_fourth = edit_fix_Fourth.getText().toString();

        finish_first = edit_finish_First.getText().toString();
        finish_second = edit_finish_Second.getText().toString();
        finish_third = edit_finish_Third.getText().toString();
        finish_fourth = edit_finish_Fourth.getText().toString();



        edit_pro_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_P));
        edit_pro_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_P));
        edit_pro_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_P));
        edit_pro_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_P));

        edit_brand_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_B));
        edit_brand_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_B));
        edit_brand_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_B));
        edit_brand_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_B));


        edit_fix_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_F));
        edit_fix_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_F));
        edit_fix_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_F));
        edit_fix_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_F));


        edit_finish_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_FI));
        edit_finish_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_FI));
        edit_finish_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_FI));
        edit_finish_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_FI));





        product = prefs.getStringValueForTag(Constants.CREATE_FIRST_P);
        brand = prefs.getStringValueForTag(Constants.CREATE_FIRST_B);
        feature = prefs.getStringValueForTag(Constants.CREATE_FIRST_F);
        finish = prefs.getStringValueForTag(Constants.CREATE_FIRST_FI);



     /*   brand = prefs.getStringValueForTag(Constants.CREATE_FIRST_P);
        product = prefs.getStringValueForTag(Constants.CREATE_FIRST_B);
        feature = prefs.getStringValueForTag(Constants.FEATURE);
        finish = prefs.getStringValueForTag(Constants.FINISH);


        //   String  brand1= brand.replace(";","\n");
        //This will split the brand into array elements of type: Brands:Price Pfister, Brands1:, Brands2:, Brands3:
        String[] allBrands = brand.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            brand1 = "";
            brand2 = "";
            brand3 = "";
            brand4 = "";
            brand5 = "";
            if (allBrands.length >= 1 && allBrands[0].contains("Brands:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                brand1 = allBrands[0].substring(allBrands[0].indexOf(":") + 1);
            }

            if (allBrands.length >= 1 && allBrands[1].contains("Brands1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                brand2 = allBrands[1].substring(allBrands[1].indexOf(":") + 1);
                edit_brand_First.setText(brand2);
            }


            if (allBrands.length >= 1 && allBrands[2].contains("Brands2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                brand3 = allBrands[2].substring(allBrands[2].indexOf(":") + 1);
                edit_brand_Second.setText(brand3);
            }

            if (allBrands.length >= 1 && allBrands[3].contains("Brands3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                brand4 = allBrands[3].substring(allBrands[3].indexOf(":") + 1);
                edit_brand_Third.setText(brand4);
            }

            if (allBrands.length >= 1 && allBrands[4].contains("Brands4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                brand5 = allBrands[4].substring(allBrands[4].indexOf(":") + 1);
                edit_brand_Fourth.setText(brand5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] allprodcut = product.split(";");
        try {
            //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
            product1 = "";
            product2 = "";
            product3 = "";
            product4 = "";
            product5 = "";
            if (allprodcut.length >= 1 && allprodcut[0].contains("Location:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                product1 = allprodcut[0].substring(allprodcut[0].indexOf(":") + 1);
            }

            if (allprodcut.length >= 1 && allprodcut[1].contains("Location1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                product2 = allprodcut[1].substring(allprodcut[1].indexOf(":") + 1);
                edit_pro_First.setText(product2);
            }


            if (allprodcut.length >= 1 && allprodcut[2].contains("Location2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                product3 = allprodcut[2].substring(allprodcut[2].indexOf(":") + 1);
                edit_pro_Second.setText(product3);
            }

            if (allprodcut.length >= 1 && allprodcut[3].contains("Location3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                product4 = allprodcut[3].substring(allprodcut[3].indexOf(":") + 1);
                edit_pro_Third.setText(product4);
            }

            if (allprodcut.length >= 1 && allprodcut[4].contains("Location4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                product5 = allprodcut[4].substring(allprodcut[4].indexOf(":") + 1);
                edit_pro_Fourth.setText(product5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] allfeature = feature.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            feature1 = "";
            feature2 = "";
            feature3 = "";
            feature4 = "";
            feature5 = "";
            if (allfeature.length >= 1 && allfeature[0].contains("Fixture:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                feature1 = allfeature[0].substring(allfeature[0].indexOf(":") + 1);
            }

            if (allfeature.length >= 1 && allfeature[1].contains("Fixture1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                feature2 = allfeature[1].substring(allfeature[1].indexOf(":") + 1);
                edit_fix_First.setText(feature2);
            }


            if (allfeature.length >= 1 && allfeature[2].contains("Fixture2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                feature3 = allfeature[2].substring(allfeature[2].indexOf(":") + 1);
                edit_fix_Second.setText(feature3);
            }

            if (allfeature.length >= 1 && allfeature[3].contains("Fixture3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                feature4 = allfeature[3].substring(allfeature[3].indexOf(":") + 1);
                edit_fix_Third.setText(feature4);
            }

            if (allfeature.length >= 1 && allfeature[4].contains("Fixture4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                feature5 = allfeature[4].substring(allfeature[4].indexOf(":") + 1);
                edit_fix_Fourth.setText(feature5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] allfinish = finish.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            finish1 = "";
            finish2 = "";
            finish3 = "";
            finish4 = "";
            finish5 = "";
            if (allfinish.length >= 1 && allfinish[0].contains("FinishColor:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                finish1 = allfinish[0].substring(allfinish[0].indexOf(":") + 1);
            }

            if (allfinish.length >= 1 && allfinish[1].contains("FinishColor1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                finish2 = allfinish[1].substring(allfinish[1].indexOf(":") + 1);
                edit_finish_First.setText(finish2);
            }


            if (allfinish.length >= 1 && allfinish[2].contains("FinishColor2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                finish3 = allfinish[2].substring(allfinish[2].indexOf(":") + 1);
                edit_finish_Second.setText(finish3);
            }

            if (allfinish.length >= 1 && allfinish[3].contains("FinishColor3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                finish4 = allfinish[3].substring(allfinish[3].indexOf(":") + 1);
                edit_finish_Third.setText(finish4);
            }

            if (allfinish.length >= 1 && allfinish[4].contains("FinishColor4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                finish5 = allfinish[4].substring(allfinish[4].indexOf(":") + 1);
                edit_finish_Fourth.setText(finish5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //   txt_brands.setText(brand1);


        spinner_Location = (Spinner) v.findViewById(R.id.spinner_Location);
        spinner_Brand = (Spinner) v.findViewById(R.id.spinner_Brand);
        spinner_fixture = (Spinner) v.findViewById(R.id.spinner_fixture);
        spinner_finish = (Spinner) v.findViewById(R.id.spinner_finish);

        groupPipStageApi();


        RelativeLayout confirm_your_booking = (RelativeLayout) v.findViewById(R.id.button_Save);
        confirm_your_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis_First = edit_pro_First.getText().toString();
                String dis_Second = edit_pro_Second.getText().toString();
                String dis_Third = edit_pro_Third.getText().toString();
                String dis_Fourth = edit_pro_Fourth.getText().toString();

                String das_first = edit_brand_First.getText().toString();
                String das_Second = edit_brand_Second.getText().toString();
                String das_third = edit_brand_Third.getText().toString();
                String das_fourth = edit_brand_Fourth.getText().toString();

                String res_first = edit_finish_First.getText().toString();
                String res_Second = edit_finish_Second.getText().toString();
                String res_third = edit_finish_Third.getText().toString();
                String res_fourth = edit_finish_Fourth.getText().toString();

                String fix_first = edit_fix_First.getText().toString();
                String fix_Second = edit_fix_Second.getText().toString();
                String fix_third = edit_fix_Third.getText().toString();
                String fix_fourth = edit_fix_Fourth.getText().toString();

                String final_pro = "Location:".concat(final_prodcut).concat(";").concat("Location1:").concat(dis_First).concat(";").concat("Location2:").concat(dis_Second).concat(";").concat("Location3:").concat(dis_Third).concat(";").concat("Location4:").concat(dis_Fourth).concat(";");
                String final_brand = "Brands:".concat(final_Brand).concat(";").concat("Brands1:").concat(das_first).concat(";").concat("Brands2:").concat(das_Second).concat(";").concat("Brands3:").concat(das_third).concat(";").concat("Brands4:").concat(das_fourth).concat(";");
                String final_fix = "Fixture:".concat(Final_fixture).concat(";").concat("Fixture1:").concat(fix_first).concat(";").concat("Fixture2:").concat(fix_Second).concat(";").concat("Fixture3:").concat(fix_third).concat(";").concat("Fixture4:").concat(fix_fourth).concat(";");
                String final_finish = "FinishColor:".concat(final_Finish).concat(";").concat("FinishColor1:").concat(res_first).concat(";").concat("FinishColor2:").concat(res_Second).concat(";").concat("FinishColor3:").concat(res_third).concat(";").concat("FinishColor4:").concat(res_fourth).concat(";");


                prefs.setStringValueForTag(Constants.CREATE_FINAL_PRODCUT, final_pro);
                prefs.setStringValueForTag(Constants.CREATE_FINAL_FINISH, final_finish);
                prefs.setStringValueForTag(Constants.CREATE_FINAL_FIXTURE, final_fix);
                prefs.setStringValueForTag(Constants.CREATE_FINAL_BRAND, final_brand);
                Toast.makeText(getActivity(), "Data Save", Toast.LENGTH_SHORT).show();


                //this is for only save instanat for prodcut page

                prefs.setStringValueForTag(Constants.CREATE_FIRST_P,final_prodcut);
                prefs.setStringValueForTag(Constants.CREATE_FIRST_B,final_Brand);
                prefs.setStringValueForTag(Constants.CREATE_FIRST_F,Final_fixture);
                prefs.setStringValueForTag(Constants.CREATE_FIRST_FI,final_Finish);

                prefs.setStringValueForTag(Constants.CREATE_F_P,dis_First);
                prefs.setStringValueForTag(Constants.CREATE_F_B,das_first);
                prefs.setStringValueForTag(Constants.CREATE_F_F,fix_first);
                prefs.setStringValueForTag(Constants.CREATE_F_FI,res_first);


                prefs.setStringValueForTag(Constants.CREATE_S_P,dis_Second);
                prefs.setStringValueForTag(Constants.CREATE_S_B,das_Second);
                prefs.setStringValueForTag(Constants.CREATE_S_F,res_Second);
                prefs.setStringValueForTag(Constants.CREATE_S_FI,fix_Second);


                prefs.setStringValueForTag(Constants.CREATE_T_P,dis_Third);
                prefs.setStringValueForTag(Constants.CREATE_T_B,das_third);
                prefs.setStringValueForTag(Constants.CREATE_T_F,res_third);
                prefs.setStringValueForTag(Constants.CREATE_T_FI,fix_third);


                prefs.setStringValueForTag(Constants.CREATE_FO_P,dis_Fourth);
                prefs.setStringValueForTag(Constants.CREATE_FO_B,das_fourth);
                prefs.setStringValueForTag(Constants.CREATE_FO_F,res_fourth);
                prefs.setStringValueForTag(Constants.CREATE_FO_FI,fix_fourth);


                ////////////////////



            }
        });


        //private method of your class


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

                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String items = jsonArray.getJSONObject(i).getString("category");

                                    if (items.equalsIgnoreCase("brands")) {
                                        catogory_name_array.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array.add(jsonArray.getJSONObject(i).getString("id"));
                                        setSecondCator(catogory_name_array, catogory_id_array);

                                    } else if (items.equalsIgnoreCase("features")) {
                                        {
                                            catogory_name_array1.add(jsonArray.getJSONObject(i).getString("items"));
                                            catogory_id_array1.add(jsonArray.getJSONObject(i).getString("id"));
                                            setthirdCatogry(catogory_name_array1, catogory_id_array1);
                                        }
                                    } else if (items.equalsIgnoreCase("finish")) {
                                        catogory_name_array2.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array2.add(jsonArray.getJSONObject(i).getString("id"));
                                        setfourthCato(catogory_name_array2, catogory_id_array2);

                                    } else if (items.equalsIgnoreCase("product")) {
                                        catogory_name_array3.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array3.add(jsonArray.getJSONObject(i).getString("id"));
                                        setCatogorySpinner(catogory_name_array3, catogory_id_array3);

                                    }


                                    {

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

    private void setfourthCato(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {

        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_finish.setAdapter(adapter_tasting_room_atmosphere_spinner);

            if (finish.equals("")) {
                spinner_finish.setSelection(getIndex(spinner_finish, finish));
            } else if (!finish.isEmpty()) {
                spinner_finish.setSelection(getIndex(spinner_finish, finish));
            }

            //  spinner_finish.setSelection(getIndex3(spinner_finish, finish1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_finish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Finish = catogory_name_array.get(position);

                // regionSpinnerApi(countri_spinner_selected_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void setthirdCatogry(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {

        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_fixture.setAdapter(adapter_tasting_room_atmosphere_spinner);
            // spinner_Brand.setSelection(2);


            if (feature.equals("")) {
                spinner_fixture.setSelection(getIndex(spinner_fixture, feature));
            } else if (!feature.isEmpty()) {
                spinner_fixture.setSelection(getIndex(spinner_fixture, feature));
            }



            // spinner_fixture.setSelection(getIndex2(spinner_fixture, feature1));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_fixture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Final_fixture = catogory_name_array.get(position);

                // regionSpinnerApi(countri_spinner_selected_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void setSecondCator(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {
        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item, catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Brand.setAdapter(adapter_tasting_room_atmosphere_spinner);


            if (brand.equals("")) {
                spinner_Brand.setSelection(getIndex(spinner_Brand, brand));
            } else if (!brand.isEmpty()) {
                spinner_Brand.setSelection(getIndex(spinner_Brand, brand));
            }



            // spinner_Brand.setSelection(getIndex(spinner_Brand, brand1));


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Brand = catogory_name_array.get(position);

                // regionSpinnerApi(countri_spinner_selected_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }

    private void setCatogorySpinner(final ArrayList<String> catogory_name_array, final ArrayList<String> catogory_id_array) {

        try {
            ArrayAdapter<String> adapter_tasting_room_atmosphere_spinner = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Location.setAdapter(adapter_tasting_room_atmosphere_spinner);

            if (product.equals("")) {
                spinner_Location.setSelection(getIndex(spinner_Location, product));
            } else if (!product.isEmpty()) {
                spinner_Location.setSelection(getIndex(spinner_Location, product));
            }


            //  spinner_Location.setSelection(getIndex1(spinner_Location, product1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_prodcut = catogory_name_array.get(position);

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

    private int getIndex1(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    private int getIndex2(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    private int getIndex3(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }
}