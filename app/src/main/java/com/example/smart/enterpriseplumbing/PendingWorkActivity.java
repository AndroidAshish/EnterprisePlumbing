package com.example.smart.enterpriseplumbing;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart.enterpriseplumbing.adapter.PendingWorkAdapter;
import com.example.smart.enterpriseplumbing.models.PendingModel;
import com.example.smart.enterpriseplumbing.utils.ConnectionDetector;
import com.example.smart.enterpriseplumbing.utils.Constants;
import com.example.smart.enterpriseplumbing.utils.Perser;
import com.example.smart.enterpriseplumbing.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Smart on 3/19/2018.
 */

public class PendingWorkActivity extends CommonBaseActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    TextView txt_Header;
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static RecyclerView recyclerView;
    PendingWorkAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isFetchingData = false;
    private Boolean isInternetPresent;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog;
    private String status;
    TextView no_value_tv;
    ArrayList<PendingModel> planArray;
    RelativeLayout rela_Layout;
    private TextWatcher searchTextWatcher;
    SwipeRefreshLayout mItemsContainer;
    String userName;
    String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_work_layout);
        PendingWorkActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        connectionDetector = new ConnectionDetector(PendingWorkActivity.this);
        initCharacterSearch();
        ImageView back_image = (ImageView) findViewById(R.id.back_Arrow);
        ImageView search_icon = (ImageView) findViewById(R.id.search_icon);
        TextView txt_Log=(TextView)findViewById(R.id.txt_Log) ;
        txt_Log.setVisibility(View.VISIBLE);
        rela_Layout = (RelativeLayout) findViewById(R.id.search_edit_layout);
        back_image.setOnClickListener(this);
        back_image.setVisibility(View.GONE);
        search_icon.setVisibility(View.GONE);
        mItemsContainer = (SwipeRefreshLayout) findViewById(R.id.container_items);
        EditText textFromActionBar = (EditText) findViewById(R.id.edit_Search);
        textFromActionBar.addTextChangedListener(searchTextWatcher);
        mItemsContainer.setOnRefreshListener(this);
        no_value_tv = (TextView) findViewById(R.id.no_value_tv);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(PendingWorkActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        search_icon.setOnClickListener(this);
        txt_Log.setOnClickListener(this);
    /*    userName="Vik%20Baid";
        password="Aladdin123!";
*/
        userName=prefs.getStringValueForTag(Constants.SAVE_EMAIL);
        password=prefs.getStringValueForTag(Constants.SAVE_PASSWORD);
        getTrainListApi();
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.back_Arrow:
                finish();
                break;

            case R.id.search_icon:
                rela_Layout.setVisibility(View.VISIBLE);
                txt_Header.setVisibility(View.GONE);
                break;

            case R.id.txt_Log:

                new android.support.v7.app.AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logout")
                        .setMessage("Do you want to logout?")
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                        prefs.clearPreferences();
                                        Intent intent = new Intent(PendingWorkActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("No", null).show();


                break;





            default:

                break;
        }

    }

    public void getTrainListApi() {
        isInternetPresent = connectionDetector.isConnectingToInternet();
        if (isInternetPresent) {
            progressDialog = setProgressBar();
            StringRequest request = new StringRequest(Constants.HOST_URL + "Get_Assigned_Task/?username="+userName+"&password="+password,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                //JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray=new JSONArray(response);
                                //status = jsonObject.getString("success");




                                    planArray = Perser.getTitleHis(jsonArray);

                                    if (!planArray.isEmpty()) {
                                        adapter = new PendingWorkAdapter(PendingWorkActivity.this, planArray);
                                        recyclerView.setAdapter(adapter);

                                    } else {
                                        recyclerView.setVisibility(View.GONE);
                                        no_value_tv.setVisibility(View.VISIBLE);
                                    }





                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(PendingWorkActivity.this, "Connection problem. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });


            VolleySingleton.getInstance(PendingWorkActivity.this).addToRequestque(request);


        } else {
            Toast.makeText(PendingWorkActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private ProgressDialog setProgressBar() {
        progressDialog = new ProgressDialog(PendingWorkActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        return progressDialog;
    }

    private void initCharacterSearch() {
        searchTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    @Override
    public void onRefresh() {
        getTrainListApi();
        mItemsContainer.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                // .setTitleColor(Color.parseColor(HALLOWEEN_ORANGE))
                //   .setDividerColor(HALLOWEEN_ORANGE)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();

    }
}