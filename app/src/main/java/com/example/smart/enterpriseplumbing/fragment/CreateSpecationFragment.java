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

public class CreateSpecationFragment extends CommonBaseFragment {
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog1, progressDialog2, progressDialog3, progressDialog4;
    private String status;
    private boolean isFetchingData = false;
    ArrayList<String> catogory_name_array, catogory_id_array, catogory_name_array1, catogory_id_array1, catogory_name_array2, catogory_id_array2;
    Spinner spinner_Pipline, spinner_Location, spinner_Desc, spinner_Diagon, spinner_Resul;
    String final_Resolu, final_Diagonis, final_Desc;
    String des1;
    String des2;
    String des3;
    String des4;
    String des5;

    String dis1;
    String dis2;
    String dis3;
    String dis4;
    String dis5;

    String res1;
    String res2;
    String res3;
    String res4;
    String res5;
    String descriptio;

    String spinner_Des, spinner_DIG, spinner_Res;

    String digonis;
    EditText edit_Dis_First, edit_Dis_Second, edit_Dis_Third, edit_Dis_Fourth;
    String dis_First = "";
    String dis_Second = "";
    String dis_Third = "";
    String dis_Fourth = "";

    String dai_first = "";
    String dai_second = "";
    String dai_third = "";
    String dai_fourth = "";

    String res_first = "";
    String res_second = "";
    String res_third = "";
    String res_fourth = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_specfication_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        connectionDetector = new ConnectionDetector(getActivity());
        catogory_name_array = new ArrayList<String>();
        catogory_id_array = new ArrayList<String>();
        catogory_name_array1 = new ArrayList<String>();
        catogory_id_array1 = new ArrayList<String>();
        catogory_name_array2 = new ArrayList<String>();
        catogory_id_array2 = new ArrayList<String>();
      /*  TextView txt_Desc=(TextView)v.findViewById(R.id.txt_Desc);
        TextView txt_Digo=(TextView)v.findViewById(R.id.txt_Desclaimer);
        TextView txt_Res=(TextView)v.findViewById(R.id.txt_Res);
        TextView txt_Disgo=(TextView)v.findViewById(R.id.txt_Diago);
        TextView txt_finish=(TextView)v.findViewById(R.id.txt_finish);
        TextView txt_Feature=(TextView)v.findViewById(R.id.txt_feature);
*/

        //  txt_Desc.setText(prefs.getStringValueForTag(Constants.DISCRIPTION));
        //    txt_Digo.setText(prefs.getStringValueForTag(Constants.DISCLAIMER));
        //  txt_Res.setText(prefs.getStringValueForTag(Constants.RESOLUTION));
        //  txt_Disgo.setText(prefs.getStringValueForTag(Constants.DIAGONIS));
        //  txt_finish.setText(prefs.getStringValueForTag(Constants.FINISH));
        // txt_Feature.setText(prefs.getStringValueForTag(Constants.FEATURE));


        spinner_Desc = (Spinner) v.findViewById(R.id.spinner_Desc);
        spinner_Diagon = (Spinner) v.findViewById(R.id.spinner_Diagon);
        spinner_Resul = (Spinner) v.findViewById(R.id.spinner_Resul);
        edit_Dis_First = (EditText) v.findViewById(R.id.edit_Dis_First);
        edit_Dis_Second = (EditText) v.findViewById(R.id.edit_Dis_Second);
        edit_Dis_Third = (EditText) v.findViewById(R.id.edit_Dis_Third);
        edit_Dis_Fourth = (EditText) v.findViewById(R.id.edit_Dis_Fourth);

        final EditText edit_Dai_First = (EditText) v.findViewById(R.id.edit_Dai_First);
        final EditText edit_Dai_Second = (EditText) v.findViewById(R.id.edit_Dai_Second);
        final EditText edit_Dai_Third = (EditText) v.findViewById(R.id.edit_Dai_Third);
        final EditText edit_Dai_Fourth = (EditText) v.findViewById(R.id.edit_Dai_Fourth);


        final EditText edit_Res_First = (EditText) v.findViewById(R.id.edit_Res_First);
        final EditText edit_Res_Second = (EditText) v.findViewById(R.id.edit_Res_Second);
        final EditText edit_Res_Third = (EditText) v.findViewById(R.id.edit_Res_Third);
        final EditText edit_Res_Fourth = (EditText) v.findViewById(R.id.edit_Res_Fourth);


        dis_First = edit_Dis_First.getText().toString();
        dis_Second = edit_Dis_Second.getText().toString();
        dis_Third = edit_Dis_Third.getText().toString();
        dis_Fourth = edit_Dis_Fourth.getText().toString();

        dai_first = edit_Dai_First.getText().toString();
        dai_second = edit_Dai_Second.getText().toString();
        dai_third = edit_Dai_Third.getText().toString();
        dai_fourth = edit_Dai_Fourth.getText().toString();

        res_first = edit_Res_First.getText().toString();
        res_second = edit_Dai_Second.getText().toString();
        res_third = edit_Dai_Third.getText().toString();
        res_fourth = edit_Dai_Fourth.getText().toString();

        spinner_Des = prefs.getStringValueForTag(Constants.CREATE_FIRST_DES);
        spinner_DIG = prefs.getStringValueForTag(Constants.CREATE_FIRST_DIG);
        spinner_Res = prefs.getStringValueForTag(Constants.CREATE_FIRST_RES);


     //this is saving state


        edit_Dis_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_DES));
        edit_Dis_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_DES));
        edit_Dis_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_DES));
        edit_Dis_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_DES));

        edit_Dai_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_DIG));
        edit_Dai_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_DIG));
        edit_Dai_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_DIG));
        edit_Dai_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_DIG));

        edit_Res_First.setText(prefs.getStringValueForTag(Constants.CREATE_F_RES));
        edit_Res_Second.setText(prefs.getStringValueForTag(Constants.CREATE_S_RES));
        edit_Res_Third.setText(prefs.getStringValueForTag(Constants.CREATE_T_RES));
        edit_Res_Fourth.setText(prefs.getStringValueForTag(Constants.CREATE_FO_RES));
/////////////////

        //super_Dis= final_Desc +";"+dis_First+";"+dis_Second+";"+dis_Third+";"+dis_Fourth;


       /* digonis = prefs.getStringValueForTag(Constants.DIAGONIS);
        resol = prefs.getStringValueForTag(Constants.RESOLUTION);
        String feature = prefs.getStringValueForTag(Constants.FEATURE);
        String finish = prefs.getStringValueForTag(Constants.FINISH);

        descriptio = prefs.getStringValueForTag(Constants.DISCRIPTION);


        String[] allDes = new String[0];
        try {
            allDes = descriptio.split(";");
            //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
            des1 = "";
            des2 = "";
            des3 = "";
            des4 = "";
            des5 = "";
            if (allDes.length >= 1 && allDes[0].contains("Description:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                des1 = allDes[0].substring(allDes[0].indexOf(":") + 1);
            }

            if (allDes.length >= 1 && allDes[1].contains("Description1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                des2 = allDes[1].substring(allDes[1].indexOf(":") + 1);
                edit_Dis_First.setText(des2);
            }


            if (allDes.length >= 1 && allDes[2].contains("Description2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                des3 = allDes[2].substring(allDes[2].indexOf(":") + 1);
                edit_Dis_Second.setText(des3);
            }

            if (allDes.length >= 1 && allDes[3].contains("Description3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                des4 = allDes[3].substring(allDes[3].indexOf(":") + 1);
                edit_Dis_Third.setText(des4);
            }

            if (allDes.length >= 1 && allDes[4].contains("Description4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                des5 = allDes[4].substring(allDes[4].indexOf(":") + 1);
                edit_Dis_Fourth.setText(des5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] allDis = digonis.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            dis1 = "";
            dis2 = "";
            dis3 = "";
            dis4 = "";
            dis5 = "";
            if (allDes.length >= 1 && allDis[0].contains("Diagnosis:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                dis1 = allDis[0].substring(allDis[0].indexOf(":") + 1);
            }

            if (allDis.length >= 1 && allDis[1].contains("Diagnosis1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                dis2 = allDis[1].substring(allDis[1].indexOf(":") + 1);
                edit_Dai_First.setText(dis2);
            }


            if (allDis.length >= 1 && allDis[2].contains("Diagnosis2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                dis3 = allDis[2].substring(allDis[2].indexOf(":") + 1);
                edit_Dai_Second.setText(dis3);
            }

            if (allDis.length >= 1 && allDis[3].contains("Diagnosis3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                dis4 = allDis[3].substring(allDis[3].indexOf(":") + 1);
                edit_Dai_Third.setText(dis4);
            }

            if (allDis.length >= 1 && allDis[4].contains("Diagnosis4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                dis5 = allDis[4].substring(allDis[4].indexOf(":") + 1);
                edit_Dai_Fourth.setText(dis5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] allRes = resol.split(";");
        //This isn't good coding standard, but is the easiest way to do it for this API. in future API's will be much better.
        try {
            res1 = "";
            res2 = "";
            res3 = "";
            res4 = "";
            res5 = "";
            if (allDes.length >= 1 && allRes[0].contains("Resolution:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                res1 = allRes[0].substring(allRes[0].indexOf(":") + 1);
            }

            if (allRes.length >= 1 && allRes[1].contains("Resolution1:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                res2 = allRes[1].substring(allRes[1].indexOf(":") + 1);
                edit_Res_First.setText(res2);
            }


            if (allRes.length >= 1 && allRes[2].contains("Resolution2:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                res3 = allRes[2].substring(allRes[2].indexOf(":") + 1);
                edit_Res_Second.setText(res3);
            }

            if (allRes.length >= 1 && allRes[3].contains("Resolution3:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                res4 = allRes[3].substring(allRes[3].indexOf(":") + 1);
                edit_Res_Third.setText(res4);
            }

            if (allRes.length >= 1 && allRes[4].contains("Resolution4:")) {
                //Use substring and get everything after Brands:. I think this is the code in java, if not let me know, I have been
                //using c# last few months mostly.
                res5 = allRes[4].substring(allRes[4].indexOf(":") + 1);
                edit_Res_Fourth.setText(res5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        groupPipStageApi();
     /*   super_Dis= final_Desc.concat(";").concat(dis_First).concat(";").concat(dis_Second).concat(";").concat(dis_Third).concat(";").concat(dis_Fourth);
        prefs.setStringValueForTag(Constants.FINAL_DESC,super_Dis);



   */


        final RelativeLayout confirm_your_booking = (RelativeLayout) v.findViewById(R.id.rel_Save);
        confirm_your_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis_First = edit_Dis_First.getText().toString();
                String dis_Second = edit_Dis_Second.getText().toString();
                String dis_Third = edit_Dis_Third.getText().toString();
                String dis_Fourth = edit_Dis_Fourth.getText().toString();

                String das_first = edit_Dai_First.getText().toString();
                String das_Second = edit_Dai_Second.getText().toString();
                String das_third = edit_Dai_Third.getText().toString();
                String das_fourth = edit_Dai_Fourth.getText().toString();

                String res_first = edit_Res_First.getText().toString();
                String res_Second = edit_Res_Second.getText().toString();
                String res_third = edit_Res_Third.getText().toString();
                String res_fourth = edit_Res_Fourth.getText().toString();


                String super_Dis = "Description:".concat(final_Desc).concat(";").concat("Description1:").concat(dis_First).concat(";").concat("Description2:").concat(dis_Second).concat(";").concat("Description3:").concat(dis_Third).concat(";").concat("Description4:").concat(dis_Fourth).concat(";");
                String super_Das = "Diagnosis:".concat(final_Diagonis).concat(";").concat("Diagnosis1:").concat(das_first).concat(";").concat("Diagnosis2:").concat(das_Second).concat(";").concat("Diagnosis3:").concat(das_third).concat(";").concat("Diagnosis4:").concat(das_fourth).concat(";");
                String super_Ras = "Resolution:".concat(final_Resolu).concat(";").concat("Resolution1:").concat(res_first).concat(";").concat("Resolution2:").concat(res_Second).concat(";").concat("Resolution3:").concat(res_third).concat(";").concat("Resolution4:").concat(res_fourth).concat(";");


                prefs.setStringValueForTag(Constants.CREATE_FINAL_DESC, super_Dis);
                prefs.setStringValueForTag(Constants.CREATE_FINAL_DIAGONIS, super_Das);
                prefs.setStringValueForTag(Constants.CREATE_FINAL_RESOLTI, super_Ras);

                //saving state here and create constant

                prefs.setStringValueForTag(Constants.CREATE_FIRST_DES, final_Desc);
                prefs.setStringValueForTag(Constants.CREATE_FIRST_DIG, final_Diagonis);
                prefs.setStringValueForTag(Constants.CREATE_FIRST_RES, final_Resolu);

                prefs.setStringValueForTag(Constants.CREATE_F_DES, dis_First);
                prefs.setStringValueForTag(Constants.CREATE_F_DIG, das_first);
                prefs.setStringValueForTag(Constants.CREATE_F_RES, res_first);

                prefs.setStringValueForTag(Constants.CREATE_S_DES, dis_Second);
                prefs.setStringValueForTag(Constants.CREATE_S_DIG, das_Second);
                prefs.setStringValueForTag(Constants.CREATE_S_RES, res_Second);

                prefs.setStringValueForTag(Constants.CREATE_T_DES, dis_Third);
                prefs.setStringValueForTag(Constants.CREATE_T_DIG, das_third);
                prefs.setStringValueForTag(Constants.CREATE_T_RES, res_third);

                prefs.setStringValueForTag(Constants.CREATE_FO_DES, dis_Fourth);
                prefs.setStringValueForTag(Constants.CREATE_FO_DIG, das_fourth);
                prefs.setStringValueForTag(Constants.CREATE_FO_RES, res_fourth);


                Toast.makeText(getActivity(), "Data Save", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

    private void groupPipStageApi() {

        isInternetPresent = connectionDetector.isConnectingToInternet();
        if (isInternetPresent) {
            progressDialog1 = setThirdProgressBar();
            StringRequest request = new StringRequest(Constants.HOST_URL + "Form_PickerValues",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                //  jsonArray = new JSONArray(response);


                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String items = jsonArray.getJSONObject(i).getString("category");

                                    if (items.equalsIgnoreCase("description")) {
                                        catogory_name_array.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array.add(jsonArray.getJSONObject(i).getString("id"));


                                        setSecondCator(catogory_name_array, catogory_id_array);

                                    } else if (items.equalsIgnoreCase("diagnosis")) {
                                        {
                                            catogory_name_array1.add(jsonArray.getJSONObject(i).getString("items"));
                                            catogory_id_array1.add(jsonArray.getJSONObject(i).getString("id"));
                                            setthirdCatogry(catogory_name_array1, catogory_id_array1);
                                        }
                                    } else if (items.equalsIgnoreCase("resolution")) {
                                        catogory_name_array2.add(jsonArray.getJSONObject(i).getString("items"));
                                        catogory_id_array2.add(jsonArray.getJSONObject(i).getString("id"));
                                        setfourthCato(catogory_name_array2, catogory_id_array2);

                                    }


                                    {

                                    }


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog1.dismiss();

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
            spinner_Resul.setAdapter(adapter_tasting_room_atmosphere_spinner);

            if (spinner_Res.equals("")) {
                spinner_Resul.setSelection(getIndex(spinner_Resul, spinner_Res));
            } else if (!res1.isEmpty()) {
                spinner_Resul.setSelection(getIndex(spinner_Resul, spinner_Res));
            }


            //     spinner_Resul.setSelection(getIndex3(spinner_Resul, res1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Resul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Resolu = catogory_name_array.get(position);

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
            spinner_Diagon.setAdapter(adapter_tasting_room_atmosphere_spinner);

            if (spinner_DIG.equals("")) {
                spinner_Diagon.setSelection(getIndex(spinner_Resul, spinner_DIG));
            } else if (!dis1.isEmpty()) {
                spinner_Diagon.setSelection(getIndex(spinner_Resul, spinner_DIG));
            }

            //  spinner_Diagon.setSelection(getIndex(spinner_Diagon, dis1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Diagon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Diagonis = catogory_name_array.get(position);

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
                    getActivity(), android.R.layout.simple_spinner_item,
                    catogory_name_array);

            adapter_tasting_room_atmosphere_spinner
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_Desc.setAdapter(adapter_tasting_room_atmosphere_spinner);

            if (spinner_Des.equals("")) {
                spinner_Desc.setSelection(getIndex(spinner_Desc, spinner_Des));
            } else if (!spinner_Des.isEmpty()) {
                spinner_Desc.setSelection(getIndex(spinner_Desc, spinner_Des));
            }

            // spinner_Desc.setSelection(getIndex(spinner_Desc, des1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinner_Desc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final_Desc = catogory_name_array.get(position);

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

    private int getIndex3(Spinner spinner, String myString) {
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }
}