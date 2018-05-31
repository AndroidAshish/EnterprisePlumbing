package com.example.smart.enterpriseplumbing.utils;


import com.example.smart.enterpriseplumbing.models.PendingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by App_DEVELOPER on 6/30/2017.
 */

public class Perser {
    public static ArrayList<PendingModel> postHistoryList;


    public static ArrayList<PendingModel> getTitleHis(JSONArray contentArrray) {
        try {
            postHistoryList = new ArrayList<>();
            for (int i = 0; i < contentArrray.length(); i++) {
                JSONObject contentJSONObject = contentArrray.getJSONObject(i);
                PendingModel dashboardModel = new PendingModel();
                dashboardModel.setId(contentJSONObject.getString("id"));
                dashboardModel.setCustomer_of(contentJSONObject.getString("customer_of"));
                dashboardModel.setTech(contentJSONObject.getString("tech"));
                dashboardModel.setFname(contentJSONObject.getString("fname"));
                dashboardModel.setLname(contentJSONObject.getString("lname"));
                dashboardModel.setAddress(contentJSONObject.getString("address"));
                dashboardModel.setCity(contentJSONObject.getString("city"));
                dashboardModel.setState(contentJSONObject.getString("state"));
                dashboardModel.setZip(contentJSONObject.getString("zip"));
                dashboardModel.setPh_mobile(contentJSONObject.getString("ph_mobile"));
                dashboardModel.setPh_alternate(contentJSONObject.getString("ph_alternate"));
                dashboardModel.setPh_primary(contentJSONObject.getString("ph_primary"));
                dashboardModel.setEmail(contentJSONObject.getString("email"));
                dashboardModel.setTenant(contentJSONObject.getString("tenant"));
                dashboardModel.setTenant_phone(contentJSONObject.getString("tenant_phone"));
                dashboardModel.setDisclaimer(contentJSONObject.getString("disclaimer"));
                dashboardModel.setDescription(contentJSONObject.getString("description"));
                dashboardModel.setDiagnosis(contentJSONObject.getString("diagnosis"));
                dashboardModel.setResolution(contentJSONObject.getString("resolution"));
                dashboardModel.setProduct(contentJSONObject.getString("product"));
                dashboardModel.setFinish(contentJSONObject.getString("finish"));
                dashboardModel.setBrands(contentJSONObject.getString("brands"));
                dashboardModel.setFeatures(contentJSONObject.getString("features"));
                dashboardModel.setService_type(contentJSONObject.getString("service_type"));
                dashboardModel.setService_amount(contentJSONObject.getString("service_amount"));
                dashboardModel.setService_fee(contentJSONObject.getString("service_fee"));
                dashboardModel.setPaid_by(contentJSONObject.getString("paid_by"));
                dashboardModel.setTotal_due(contentJSONObject.getString("total_due"));
                dashboardModel.setSave_signature(contentJSONObject.getString("save_signature"));
                dashboardModel.setSubmit_signature(contentJSONObject.getString("submit_signature"));
                dashboardModel.setOrder_date(contentJSONObject.getString("order_date"));
                dashboardModel.setGateCode(contentJSONObject.getString("GateCode"));
                dashboardModel.setDispatch_ID(contentJSONObject.getString("Dispatch_ID"));
                dashboardModel.setStart_order_date(contentJSONObject.getString("start_order_date"));
                dashboardModel.setIsActive(contentJSONObject.getString("IsActive"));
                dashboardModel.setAuth(contentJSONObject.getString("Auth"));
                dashboardModel.setAuth_Amount(contentJSONObject.getString("Auth_Amount"));
                dashboardModel.setCheck(contentJSONObject.getString("Check"));
                dashboardModel.setCollected(contentJSONObject.getString("Collected"));
                dashboardModel.setSign_bool(contentJSONObject.getString("sign_bool"));
                dashboardModel.setInvoice_Number(contentJSONObject.getString("Invoice_Number"));
                dashboardModel.setNote(contentJSONObject.getString("note"));
                dashboardModel.setIs_paid(contentJSONObject.getString("is_paid"));
                dashboardModel.setModifiedDate(contentJSONObject.getString("ModifiedDate"));


                postHistoryList.add(dashboardModel);
            }
            return postHistoryList;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
