package com.example.android.starbridges.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 5/8/2018.
 */

public class Authentication implements Serializable, Parcelable
{
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;
    @SerializedName("as:client_id")
    @Expose
    private String asClientId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName(".issued")
    @Expose
    private String issued;
    @SerializedName(".expires")
    @Expose
    private String expires;
    public final static Parcelable.Creator<Authentication> CREATOR = new Creator<Authentication>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Authentication createFromParcel(Parcel in) {
            return new Authentication(in);
        }

        public Authentication[] newArray(int size) {
            return (new Authentication[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2275292401821803935L;

    protected Authentication(Parcel in) {
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.tokenType = ((String) in.readValue((String.class.getClassLoader())));
        this.expiresIn = ((int) in.readValue((int.class.getClassLoader())));
        this.asClientId = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.nik = ((String) in.readValue((String.class.getClassLoader())));
        this.issued = ((String) in.readValue((String.class.getClassLoader())));
        this.expires = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Authentication() {
    }

    /**
     *
     * @param expires
     * @param tokenType
     * @param accessToken
     * @param expiresIn
     * @param issued
     * @param userName
     * @param fullName
     * @param nik
     * @param asClientId
     */
    public Authentication(String accessToken, String tokenType, int expiresIn, String asClientId, String userName, String fullName, String nik, String issued, String expires) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.asClientId = asClientId;
        this.userName = userName;
        this.fullName = fullName;
        this.nik = nik;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Authentication withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Authentication withTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Authentication withExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getAsClientId() {
        return asClientId;
    }

    public void setAsClientId(String asClientId) {
        this.asClientId = asClientId;
    }

    public Authentication withAsClientId(String asClientId) {
        this.asClientId = asClientId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Authentication withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Authentication withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Authentication withNik(String nik) {
        this.nik = nik;
        return this;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public Authentication withIssued(String issued) {
        this.issued = issued;
        return this;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Authentication withExpires(String expires) {
        this.expires = expires;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(tokenType);
        dest.writeValue(expiresIn);
        dest.writeValue(asClientId);
        dest.writeValue(userName);
        dest.writeValue(fullName);
        dest.writeValue(nik);
        dest.writeValue(issued);
        dest.writeValue(expires);
    }

    public int describeContents() {
        return  0;
    }


}
