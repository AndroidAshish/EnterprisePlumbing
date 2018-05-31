package com.example.smart.enterpriseplumbing.models;

import java.io.Serializable;

/**
 * Created by Smart on 3/19/2018.
 */

public class PendingModel implements Serializable {

    private String id;
    private String customer_of;
    private String tech;
    private String fname;
    private String lname;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String ph_mobile;
    private String ph_alternate;
    private String ph_primary;
    private String email;
    private String tenant;
    private String total_due;
    private String tenant_phone;
    private String disclaimer;
    private String description;
    private String diagnosis;
    private String resolution;
    private String product;
    private String finish;
    private String brands;
    private String features;
    private String service_type;
    private String service_amount;
    private String service_fee;
    private String paid_by;
    private String save_signature;
    private String submit_signature;
    private String order_date;
    private String GateCode;
    private String Dispatch_ID;
    private String start_order_date;
    private String IsActive;
    private String Auth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_of() {
        return customer_of;
    }

    public void setCustomer_of(String customer_of) {
        this.customer_of = customer_of;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPh_mobile() {
        return ph_mobile;
    }

    public void setPh_mobile(String ph_mobile) {
        this.ph_mobile = ph_mobile;
    }

    public String getPh_alternate() {
        return ph_alternate;
    }

    public void setPh_alternate(String ph_alternate) {
        this.ph_alternate = ph_alternate;
    }

    public String getPh_primary() {
        return ph_primary;
    }

    public void setPh_primary(String ph_primary) {
        this.ph_primary = ph_primary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getTenant_phone() {
        return tenant_phone;
    }

    public void setTenant_phone(String tenant_phone) {
        this.tenant_phone = tenant_phone;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_amount() {
        return service_amount;
    }

    public void setService_amount(String service_amount) {
        this.service_amount = service_amount;
    }

    public String getService_fee() {
        return service_fee;
    }

    public void setService_fee(String service_fee) {
        this.service_fee = service_fee;
    }

    public String getPaid_by() {
        return paid_by;
    }

    public void setPaid_by(String paid_by) {
        this.paid_by = paid_by;
    }

    public String getSave_signature() {
        return save_signature;
    }

    public void setSave_signature(String save_signature) {
        this.save_signature = save_signature;
    }

    public String getSubmit_signature() {
        return submit_signature;
    }

    public void setSubmit_signature(String submit_signature) {
        this.submit_signature = submit_signature;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getGateCode() {
        return GateCode;
    }

    public void setGateCode(String gateCode) {
        GateCode = gateCode;
    }

    public String getDispatch_ID() {
        return Dispatch_ID;
    }

    public void setDispatch_ID(String dispatch_ID) {
        Dispatch_ID = dispatch_ID;
    }

    public String getStart_order_date() {
        return start_order_date;
    }

    public void setStart_order_date(String start_order_date) {
        this.start_order_date = start_order_date;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    public String getAuth_Amount() {
        return Auth_Amount;
    }

    public void setAuth_Amount(String auth_Amount) {
        Auth_Amount = auth_Amount;
    }

    public String getCheck() {
        return Check;
    }

    public void setCheck(String check) {
        Check = check;
    }

    public String getCollected() {
        return Collected;
    }

    public void setCollected(String collected) {
        Collected = collected;
    }

    public String getSign_bool() {
        return sign_bool;
    }

    public void setSign_bool(String sign_bool) {
        this.sign_bool = sign_bool;
    }

    public String getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(String invoice_Number) {
        Invoice_Number = invoice_Number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(String is_paid) {
        this.is_paid = is_paid;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getTotal_due() {
        return total_due;
    }

    public void setTotal_due(String total_due) {
        this.total_due = total_due;
    }
    private String Auth_Amount;
    private String Check;
    private String Collected;
    private String sign_bool;
    private String Invoice_Number;
    private String note;
    private String is_paid;
    private String ModifiedDate;

}
