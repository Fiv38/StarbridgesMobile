package com.example.android.starbridges.network;

import com.example.android.starbridges.model.Attendence;
import com.example.android.starbridges.model.Authentication;
import com.example.android.starbridges.model.ListAttendanceCorrection.ListAttendanceCorrection;
import com.example.android.starbridges.model.OLocation.OLocation;
import com.example.android.starbridges.model.OPost;
import com.example.android.starbridges.model.getimage.GetImage;
import com.example.android.starbridges.model.history.History;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by user on 5/11/2018.
 */

public interface APIInterfaceRest {
    @FormUrlEncoded
    @POST("token")
    Call<Authentication> getAuthentication(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password, @Field("client_id") String client_id, @Field("IMEI") String IMEI);

    @FormUrlEncoded
    @POST("api/Attendance/RegisterIMEI")
    Call<OPost> postRegisterImei(@Field("username") String username, @Field("password") String password, @Field("IMEI") String imei);

    //@FormUrlEncoded
    @GET("api/Attendance/GetLocations")
    Call<OLocation> postLocation();

    @FormUrlEncoded
    @POST("api/Attendance/Absence")
    Call<Attendence> inputAbsence(
            @Field("UserName") String username,
            @Field("EmployeeID") String employeeID,
            @Field("BusinessGroupID") String businessGroupID,
            @Field("LogDate") String date,
            @Field("LogTime") String time,
            @Field("BeaconID") String beaconID,
            @Field("LocationID") String locationID,
            @Field("LocationName") String location,
            @Field("LocationAddress") String address,
            @Field("Longitude") String longitude,
            @Field("Latitude") String latitude,
            @Field("LogType") String logType,
            @Field("Photo") String photo,
            @Field("Event") String event,
            @Field("Notes") String notes);

    @FormUrlEncoded
    @POST("api/Attendance/History")
    Call<History> getHistory(@Field("DateFrom") String DateFrom, @Field("DateTo") String DateTo);

    @GET("api/Attendance/GetImage")
    Call<GetImage> getImage();

    @FormUrlEncoded
    @POST("api/AttendanceCorrection/ListAttendanceCorrection")
    Call<ListAttendanceCorrection> getListAttendanceCorrection(@Field("DateFrom") String DateFrom, @Field("DateTo") String DateTo);
}
