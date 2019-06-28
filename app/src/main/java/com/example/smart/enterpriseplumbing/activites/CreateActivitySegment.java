package com.example.smart.enterpriseplumbing.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.smart.enterpriseplumbing.ActivitySegment;
import com.example.smart.enterpriseplumbing.CommonBaseActivity;
import com.example.smart.enterpriseplumbing.R;
import com.example.smart.enterpriseplumbing.TermAndConditionActivity;
import com.example.smart.enterpriseplumbing.fragment.CreatePlanFragment;
import com.example.smart.enterpriseplumbing.fragment.CreateProductFragment;
import com.example.smart.enterpriseplumbing.fragment.CreateServiceFragment;
import com.example.smart.enterpriseplumbing.fragment.CreateSpecationFragment;
import com.example.smart.enterpriseplumbing.fragment.PinFragment;
import com.example.smart.enterpriseplumbing.fragment.PlanFragment;
import com.example.smart.enterpriseplumbing.fragment.SepectificationFragment;
import com.example.smart.enterpriseplumbing.fragment.ServiceFragment;
import com.example.smart.enterpriseplumbing.models.PendingModel;
import com.example.smart.enterpriseplumbing.utils.Constants;

/**
 * Created by Smart on 10/2/2018.
 */

public class CreateActivitySegment extends CommonBaseActivity implements View.OnClickListener {
    TextView txt_Header;
    RadioButton radio_All, radio_Call, radio_Meeting, radio_Email, radio_Lost;
    Fragment fragment = null;
    FrameLayout container_Parent;
    PendingModel dealModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity_new_layout);
        txt_Header = (TextView) findViewById(R.id.header_title);
        txt_Header.setText("Create Work");
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
        LinearLayout linear_Frist = (LinearLayout) findViewById(R.id.linear_Frist);


        linear_Frist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CreateActivitySegment.this, CreateTermConditionActivity.class);
                startActivity(i);



/*
                if (save_Sig.equalsIgnoreCase("null")) {
                    Intent i = new Intent(CreateActivitySegment.this, TermAndConditionActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(CreateActivitySegment.this, FinalSignatureActivity.class);
                    startActivity(i);

                }*/








           /*     if(!save_Sig.isEmpty())
                {


                }
                if ((save_Sig.equals(null))) {

                }
*/


            }
        });

        header_Save.setVisibility(View.INVISIBLE);



        radio_All.setOnClickListener(this);
        radio_Call.setOnClickListener(this);
        radio_Meeting.setOnClickListener(this);
        radio_Email.setOnClickListener(this);


        radio_All.setBackgroundColor(getResources().getColor(R.color.text_color));
        radio_All.setTextColor(getResources().getColor(R.color.product_text_color));
        fragment = new CreatePlanFragment();
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

                fragment = new CreatePlanFragment();
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


                fragment = new CreateSpecationFragment();
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


                fragment = new CreateServiceFragment();
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


                fragment = new CreateProductFragment();
                transactionFragments(fragment, true, R.id.container_Parent);
                break;

            default:

                fragment = new CreatePlanFragment();
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