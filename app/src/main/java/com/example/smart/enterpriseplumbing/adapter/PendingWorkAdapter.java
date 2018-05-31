package com.example.smart.enterpriseplumbing.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart.enterpriseplumbing.ActivitySegment;
import com.example.smart.enterpriseplumbing.R;
import com.example.smart.enterpriseplumbing.models.PendingModel;
import com.example.smart.enterpriseplumbing.utils.AppPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Smart on 3/19/2018.
 */

public class PendingWorkAdapter extends RecyclerView.Adapter<PendingWorkAdapter.MyViewHolder> implements Filterable {
    PendingWorkAdapter.MyViewHolder myViewHolder;
    Context mContext;
    AppPreference prefs;
    private ArrayList<PendingModel> dataSet;
    private LayoutInflater layoutInflater;
    private PendingWorkAdapter.ValueFilter valueFilter;
    private ArrayList<PendingModel> mFilterList;
    private ProgressDialog progressDialog;

    public PendingWorkAdapter(Context context, ArrayList<PendingModel> data) {
        this.mContext = context;
        this.mFilterList = data;
        this.dataSet = data;

    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new PendingWorkAdapter.ValueFilter();
        }
        return valueFilter;
    }

    @Override
    public PendingWorkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_work_list_items, parent, false);

        PendingWorkAdapter.MyViewHolder myViewHolder = new PendingWorkAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PendingWorkAdapter.MyViewHolder holder, final int position) {

        TextView txt_Name = holder.txt_Name;
        TextView txt_Phone = holder.txt_Phone;

        TextView txt_Id = holder.txt_Id;
        TextView txt_Addres = holder.txt_Addres;
        TextView txt_Email = holder.txt_Email;
        TextView txt_Tech=holder.txt_Tech;

        CardView cardView = holder.cardView;
        //  brand_Title.setText(dataSet.get(position).getName());
        //  imageView.setImageResource(dataSet.get(position).getImage());

        String name=dataSet.get(position).getFname();
        txt_Name.setText(name);
        txt_Phone.setText(dataSet.get(position).getPh_mobile());
        txt_Id.setText(dataSet.get(position).getDispatch_ID());
        txt_Addres.setText(dataSet.get(position).getCity());
        txt_Email.setText(dataSet.get(position).getEmail());
        txt_Tech.setText(dataSet.get(position).getTech());


        // imageViewIcon.setImageResource(dataSet.get(position).getImage());


       try {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Context context = v.getContext();
                        Intent i = new Intent(context, ActivitySegment.class);
                        i.putExtra("DealDetails", dataSet.get(position));
                        ActivityCompat.startActivity(context, i,
                                ActivityOptionsCompat
                                        .makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight())
                                        .toBundle()
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private void showSimpleAlertMessage(String msg) {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("TribeInfluence");
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //do whatever you want to do when user clicks ok

                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });


        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_Id;
        TextView txt_Name, txt_Phone, txt_Addres, txt_Email, txt_Tech;

        CardView cardView;
        LinearLayout linear_First;
        ImageView image_Edit, image_Delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txt_Id = (TextView) itemView.findViewById(R.id.txt_Id);
            // imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.txt_Name = (TextView) itemView.findViewById(R.id.txt_Name);
            this.cardView = (CardView) itemView.findViewById(R.id.cardview);
            this.txt_Phone = (TextView) itemView.findViewById(R.id.txt_Phone);
            this.txt_Addres = (TextView) itemView.findViewById(R.id.txt_Addres);
            this.txt_Email = (TextView) itemView.findViewById(R.id.txt_Email);
            this.txt_Tech = (TextView) itemView.findViewById(R.id.txt_Tech);


           /* itemView.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {




                    // perform your operations here
                }
            });*/
/*Context context = v.getContext();
                    Intent mediaStreamIntent = new Intent(context, RestuarantDetailsActivity.class);
                    context.startActivity(mediaStreamIntent);
*/
        }
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<PendingModel> filteredList = new ArrayList<>();
                try {
                    for (PendingModel event : mFilterList) {
                        boolean io = event.getTech().toLowerCase().contains(constraint.toString().toLowerCase());
                        if (io) {
                            filteredList.add(event);
                        }
                    }
                } catch (Exception e) {
                }
                results.count = filteredList.size();
                results.values = filteredList;
            } else {
                results.count = mFilterList.size();
                results.values = mFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet = (ArrayList<PendingModel>) results.values;
            if (results.count == 0) {
                //  showAlertMsg(contextt,"There is no any messaged!.");
                Toast.makeText(mContext, "NO RESULT FOUND", Toast.LENGTH_SHORT).show();
            }
            notifyDataSetChanged();
        }
    }


}