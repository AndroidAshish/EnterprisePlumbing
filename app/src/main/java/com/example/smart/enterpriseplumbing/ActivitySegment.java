package com.example.smart.enterpriseplumbing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.smart.enterpriseplumbing.fragment.PinFragment;
import com.example.smart.enterpriseplumbing.fragment.PlanFragment;
import com.example.smart.enterpriseplumbing.fragment.SepectificationFragment;
import com.example.smart.enterpriseplumbing.fragment.ServiceFragment;
import com.example.smart.enterpriseplumbing.fragment.SportsFragment;
import com.example.smart.enterpriseplumbing.models.PendingModel;
import com.example.smart.enterpriseplumbing.utils.Constants;


/**
 * Created by App_DEVELOPER on 12/11/2017.
 */

public class ActivitySegment extends CommonBaseActivity implements View.OnClickListener {
    TextView txt_Header;
    RadioButton radio_All, radio_Call, radio_Meeting, radio_Email, radio_Lost;
    Fragment fragment = null;
    FrameLayout container_Parent;
    PendingModel dealModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_layout);
        dealModel = (PendingModel) getIntent().getSerializableExtra("DealDetails");
        txt_Header = (TextView) findViewById(R.id.header_title);
        txt_Header.setText("Pending Work");
        ImageView back_image = (ImageView) findViewById(R.id.back_Arrow);
        back_image.setVisibility(View.VISIBLE);
        back_image.setOnClickListener(this);
        container_Parent = (FrameLayout) findViewById(R.id.container_Parent);
        View include_view = (View) findViewById(R.id.radio_Group);
        radio_All = (RadioButton) include_view.findViewById(R.id.radio_All);
        radio_Call = (RadioButton) include_view.findViewById(R.id.radio_Call);
        radio_Meeting = (RadioButton) include_view.findViewById(R.id.radio_Meeting);
        radio_Email = (RadioButton) include_view.findViewById(R.id.radio_Email);

        TextView header_Save = (TextView) findViewById(R.id.header_Save);
        LinearLayout linear_Frist=(LinearLayout)findViewById(R.id.linear_Frist) ;
        linear_Frist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ActivitySegment.this,TermAndConditionActivity.class);
                startActivity(i);

            }
        });

        header_Save.setVisibility(View.VISIBLE);
        String id=dealModel.getId();
        String email=dealModel.getEmail();
        String dis_Id=dealModel.getDispatch_ID();
        String gate_Code=dealModel.getGateCode();
        String plumber=dealModel.getTech();
        String  first=dealModel.getFname();
        String last=dealModel.getLname();
        String address=dealModel.getAddress();
        String city=dealModel.getCity();
        String state=dealModel.getState();
        String zip=dealModel.getZip();
        String off=dealModel.getCustomer_of();
        String disclaimer=dealModel.getDisclaimer();
        String discrip=dealModel.getDescription();
        String resolu=dealModel.getResolution();
        String digonis=dealModel.getDiagnosis();
        String finish=dealModel.getFinish();
        String feature=dealModel.getFeatures();

        String service_type=dealModel.getService_type();
        String servi_fee=dealModel.getService_fee();
        String ser_amount=dealModel.getService_amount();
        String paid_by=dealModel.getPaid_by();
        String due=dealModel.getTotal_due();
        String brand_type=dealModel.getBrands();

        String order_Date=dealModel.getOrder_date();
        String start_o_Date=dealModel.getStart_order_date();
        String auth=dealModel.getAuth();
        String auth_amount=dealModel.getAuth_Amount();
        String check=dealModel.getCheck();
        String collected=dealModel.getCollected();
        String in_voice=dealModel.getInvoice_Number();
        String product=dealModel.getProduct();
       prefs.setStringValueForTag(Constants.EMAIL,email);
        prefs.setStringValueForTag(Constants.ID,id);
        prefs.setStringValueForTag(Constants.ORDER_DATE,order_Date);
        prefs.setStringValueForTag(Constants.START_O_DATE,start_o_Date);
        prefs.setStringValueForTag(Constants.AUTH,auth);
        prefs.setStringValueForTag(Constants.AUTH_AMOUNT,auth_amount);
        prefs.setStringValueForTag(Constants.CHECK,check);
        prefs.setStringValueForTag(Constants.COLLECTED,collected);
        prefs.setStringValueForTag(Constants.IN_VOICE,in_voice);
        prefs.setStringValueForTag(Constants.PRODUCT,product);

        prefs.setStringValueForTag(Constants.DISPACT_ID,dis_Id);
        prefs.setStringValueForTag(Constants.GATE_CODE,gate_Code);
        prefs.setStringValueForTag(Constants.PLUMBER,plumber);
        prefs.setStringValueForTag(Constants.FIRST,first);
        prefs.setStringValueForTag(Constants.LAST,last);
        prefs.setStringValueForTag(Constants.ADDRESS,address);
        prefs.setStringValueForTag(Constants.CITY,city);
        prefs.setStringValueForTag(Constants.STATE,state);
        prefs.setStringValueForTag(Constants.ZIP,zip);
        prefs.setStringValueForTag(Constants.CUSTO_OFF,off);
        prefs.setStringValueForTag(Constants.BRAND_TYPE,brand_type);

        prefs.setStringValueForTag(Constants.DISCLAIMER,disclaimer);
        prefs.setStringValueForTag(Constants.DISCRIPTION,discrip);
        prefs.setStringValueForTag(Constants.RESOLUTION,resolu);
        prefs.setStringValueForTag(Constants.DIAGONIS,digonis);
        prefs.setStringValueForTag(Constants.FINISH,finish);
        prefs.setStringValueForTag(Constants.FEATURE,feature);
        prefs.setStringValueForTag(Constants.SERVICES_TYPE,service_type);
        prefs.setStringValueForTag(Constants.SERVICES_FEE,servi_fee);
        prefs.setStringValueForTag(Constants.SER_AMOUNT,ser_amount);
        prefs.setStringValueForTag(Constants.PIAB,paid_by);
        prefs.setStringValueForTag(Constants.DUE,due);

        radio_All.setOnClickListener(this);
        radio_Call.setOnClickListener(this);
        radio_Meeting.setOnClickListener(this);
        radio_Email.setOnClickListener(this);


        radio_All.setBackgroundColor(getResources().getColor(R.color.text_color));
        radio_All.setTextColor(getResources().getColor(R.color.product_text_color));
        fragment = new PlanFragment();
        transactionFragments(fragment, false, R.id.container_Parent);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.back_Arrow:
                finish();

                break;
            case R.id.radio_All:

                radio_All.setBackgroundColor(getResources().getColor(R.color.text_color));
                radio_All.setTextColor(getResources().getColor(R.color.product_text_color));

                radio_Call.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Call.setTextColor(getResources().getColor(R.color.text_color));

                radio_Meeting.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Meeting.setTextColor(getResources().getColor(R.color.text_color));

                radio_Email.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Email.setTextColor(getResources().getColor(R.color.text_color));

                fragment = new PlanFragment();
                transactionFragments(fragment, false, R.id.container_Parent);

                break;

            case R.id.radio_Call:

                radio_Call.setBackgroundColor(getResources().getColor(R.color.text_color));
                radio_Call.setTextColor(getResources().getColor(R.color.product_text_color));

                radio_All.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_All.setTextColor(getResources().getColor(R.color.text_color));

                radio_Meeting.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Meeting.setTextColor(getResources().getColor(R.color.text_color));

                radio_Email.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Email.setTextColor(getResources().getColor(R.color.text_color));


                fragment = new SepectificationFragment();
                transactionFragments(fragment, true, R.id.container_Parent);
                break;

            case R.id.radio_Meeting:
                radio_Meeting.setBackgroundColor(getResources().getColor(R.color.text_color));
                radio_Meeting.setTextColor(getResources().getColor(R.color.product_text_color));
                radio_All.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_All.setTextColor(getResources().getColor(R.color.text_color));
                radio_Call.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Call.setTextColor(getResources().getColor(R.color.text_color));
                radio_Email.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Email.setTextColor(getResources().getColor(R.color.text_color));



                fragment = new ServiceFragment();
                transactionFragments(fragment, true, R.id.container_Parent);
                break;


            case R.id.radio_Email:

                radio_Email.setBackgroundColor(getResources().getColor(R.color.text_color));
                radio_Email.setTextColor(getResources().getColor(R.color.product_text_color));

                radio_Meeting.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Meeting.setTextColor(getResources().getColor(R.color.text_color));

                radio_All.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_All.setTextColor(getResources().getColor(R.color.text_color));

                radio_Call.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
                radio_Call.setTextColor(getResources().getColor(R.color.text_color));


                fragment = new PinFragment();
                transactionFragments(fragment, true, R.id.container_Parent);
                break;

            default:

                fragment = new PlanFragment();
                transactionFragments(fragment, true, R.id.container_Parent);
                break;
        }

        if (fragment != null) {
            transactionFragments(fragment, false, R.id.container_Parent);
            setTitle(id);

        } else {
            Log.e("LoginActivity", "Error in creating fragment");
        }

    }

    public void transactionFragments(Fragment fragment, boolean backStackTag, int viewResource) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(viewResource, fragment);
        if (backStackTag) {
            ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            finish();
        } else {
            super.onBackPressed();
        }

    }
}