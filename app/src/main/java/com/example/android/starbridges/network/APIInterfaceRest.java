package com.example.android.starbridges.network;

import com.example.android.starbridges.model.Attendence;
import com.example.android.starbridges.model.Authentication;
import com.example.android.starbridges.model.CorrectionDetail.CorrectionDetail;
import com.example.android.starbridges.model.DecisionNumber.DecisionNumber;
import com.example.android.starbridges.model.EditLeaveCancelation.EditLeaveCancelation;
import com.example.android.starbridges.model.EditReimbursement.EditReimbursement;
import com.example.android.starbridges.model.ListAttendanceCorrection.ListAttendanceCorrection;
import com.example.android.starbridges.model.ListDraftCorrection.ListDraftCorrection;
import com.example.android.starbridges.model.ListDraftLeaveCancelation.ListDraftLeaveCancelation;
import com.example.android.starbridges.model.ListDraftOvertime.ListDraftOvertime;
import com.example.android.starbridges.model.ListDraftReimbursement.ListDraftReimbursement;
import com.example.android.starbridges.model.ListLeaveCancelation.ListLeaveCancelation;
import com.example.android.starbridges.model.ListOvertime.Overtime;
import com.example.android.starbridges.model.OLocation.OLocation;
import com.example.android.starbridges.model.OPost;
import com.example.android.starbridges.model.MessageReturn.MessageReturn;
import com.example.android.starbridges.model.PersonalOvertime.PersonalOvertime;
import com.example.android.starbridges.model.Reimbursement.Reimbursement;
import com.example.android.starbridges.model.ReimbursementType.ReimbursementType;
import com.example.android.starbridges.model.balanceType.BalanceType;
import com.example.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import com.example.android.starbridges.model.editleaverequest.EditLeaveRequest;
import com.example.android.starbridges.model.getclaimpolicy.GetClaimPolicy;
import com.example.android.starbridges.model.getemployeefamily.GetEmployeeFamily;
import com.example.android.starbridges.model.getimage.GetImage;
import com.example.android.starbridges.model.getmedicalpolicy.GetMedicalPolicy;
import com.example.android.starbridges.model.getmedicalsupport.GetMedicalSupport;
import com.example.android.starbridges.model.history.History;
import com.example.android.starbridges.model.leaverequest.LeaveRequest;
import com.example.android.starbridges.model.listdraftleaverequest.ListDraftLeaveRequest;
import com.example.android.starbridges.model.requestconfirmation.RequestConfirmation;
import com.example.android.starbridges.model.requesttype.RequestType;
import com.example.android.starbridges.model.saveLeaveRequest.SaveLeaveRequest;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
            @Field("Notes") String notes,
            @Field("GMT") int gmt)
            ;

    @FormUrlEncoded
    @POST("api/Attendance/History")
    Call<History> getHistory(@Field("DateFrom") String DateFrom, @Field("DateTo") String DateTo);

    @GET("api/Attendance/GetImage")
    Call<GetImage> getImage();

    @FormUrlEncoded
    @POST("api/AttendanceCorrection/ListAttendanceCorrection")
    Call<ListAttendanceCorrection> getListAttendanceCorrection(@Field("DateFrom") String DateFrom, @Field("DateTo") String DateTo);

    @POST("api/AttendanceCorrection/GetDetail")
    Call<CorrectionDetail> getDetailAttendanceCorrection(@Query("uid") String uid);

    @POST("api/AttendanceCorrection/SaveDetail")
    Call<MessageReturn> saveSubmitAttendanceCorrection(
            @Query("transactionStatus") String transactionStatus,
            @Body RequestBody body
            );

    @GET("api/Attendance/GetLocations")
    Call<OLocation> getLocation();

    @GET("api/AttendanceCorrection/ListDraft")
    Call<ListDraftCorrection> getListDraftCorrection();

    @POST("api/AttendanceCorrection/GetDetailDraft")
    Call<CorrectionDetail> getDetailDraftAttendanceCorrection(@Query("id") String id);

    @POST("api/AttendanceCorrection/DeleteDraft")
    Call<MessageReturn> deleteDraftCorrection(
            @Body RequestBody body
    );

    @POST("api/LeaveCancelation/GetDecisionNumber")
    Call<DecisionNumber> getDecisionNumber(
            @Query("employeeID") String employeeID
    );

    @GET("api/LeaveCancelation/ListLeaveCancelation")
    Call<ListLeaveCancelation> getLeaveCancelation();

    @GET("api/OvertimeRequest/ListOvertimeRequest")
    Call<Overtime> getListOvertime();

    @GET("api/OvertimeRequest/ListDraft")
    Call<ListDraftOvertime> getListDraftOvertime();

    // LEAVE REQUEST
    //@FormUrlEncoded
    @GET("api/LeaveRequest/ListLeaveRequest")
    Call<LeaveRequest> getListLeaveRequest();

    //@FormUrlEncoded
    @POST("api/LeaveRequest/GetLeaveRequestRule")
    Call<RequestType> getRequestType(@Query("employeeID") String employeeID);

    //@FormUrlEncoded
    @POST("api/LeaveRequest/GetEmployeeLeaveBalance")
    Call<BalanceType> getBalanceType(@Query("employeeID") String employeeID);

    //@FormUrlEncoded
    @GET("api/LeaveRequest/ListDraft")
    Call<ListDraftLeaveRequest> getListDraftLeaveRequest();

    //@FormUrlEncoded
    @POST("api/LeaveRequest/EditDraft")
    Call<EditLeaveRequest> editLeaveRequst(@Query("id") String id);

    //@FormUrlEncoded
    @POST("api/LeaveRequest/DeleteDraft")
    Call<DeleteLeaveRequest> deleteLeaveRequst(@Body RequestBody body);

    @FormUrlEncoded
    @POST("api/LeaveRequest/SaveDetail")
    Call<SaveLeaveRequest> saveLeaveRequest(
            @Field("ID") String ID,
            @Field("EmployeeID") Integer EmployeeID,
            @Field("Roster") String Rooster,
            @Field("RequestDate") String RequestDate,
            @Field("EmployeeNIK") String EmployeeNIK,
            @Field("EmployeeName") String EmployeeName,
            @Field("LeaveRequestRuleID") Integer LeaveRequestRuleID,
            @Field("LeaveRequestType") String LeaveRequestType,
            @Field("EmployeeLeaveBalanceUID") String EmployeeLeaveBalanceUID,
            @Field("CurrentBalance") String CurrentBalance,
            @Field("BalanceExpireDate") String BalanceExpireDate,
            @Field("TotalUnit") String TotalUnit,
            @Field("TotalUnitReduce") String TotalUnitReduce,
            @Field("StartLeave") String StartLeave,
            @Field("EndLeave") String EndLeave,
            @Field("LeaveAt") String LeaveAt,
            @Field("ReturnAt") String ReturnAt,
            @Field("MinIntervalViolation") Boolean MinIntervalViolation,
            @Field("UnitLimitViolation") Boolean UnitLimitViolation,
            @Field("OccurenceViolation") Boolean OccurenceViolation,
            @Field("Notes") String Notes,
            @Field("AttachmentFile") String AttachmentFile,
            @Field("AttachmentID") String AttachmentID,
            @Field("DecisionNumber") String DecisionNumber,
            @Field("TransactionStatusID") String TransactionStatusID,
            @Field("ApprovedDate") String ApprovedDate,
            @Field("IsHalfDay") Boolean IsHalfDay,
            @Field("SubmitType") String SubmitType,
            @Field("Message") String Message,
            @Field("TransactionStatusSaveOrSubmit") String TransactionStatusSaveOrSubmit,
            @Field("Photo") String Photo,
            @Field("FullAccess") Boolean FullAccess,
            @Field("ExclusionFields") List<String> ExclusionFields,
            @Field("AccessibilityAttribute") String AccessibilityAttribute);

    @FormUrlEncoded
    @POST("api/LeaveRequest/DetailRequestConfirmation?transactionStatus=Save")
    Call<RequestConfirmation> requestConfirmationSave(
            @Field("ID") String ID,
            @Field("EmployeeID") Integer EmployeeID,
            @Field("Roster") String Rooster,
            @Field("RequestDate") String RequestDate,
            @Field("EmployeeNIK") String EmployeeNIK,
            @Field("EmployeeName") String EmployeeName,
            @Field("LeaveRequestRuleID") Integer LeaveRequestRuleID,
            @Field("LeaveRequestType") String LeaveRequestType,
            @Field("EmployeeLeaveBalanceUID") String EmployeeLeaveBalanceUID,
            @Field("CurrentBalance") String CurrentBalance,
            @Field("BalanceExpireDate") String BalanceExpireDate,
            @Field("TotalUnit") String TotalUnit,
            @Field("TotalUnitReduce") String TotalUnitReduce,
            @Field("StartLeave") String StartLeave,
            @Field("EndLeave") String EndLeave,
            @Field("LeaveAt") String LeaveAt,
            @Field("ReturnAt") String ReturnAt,
            @Field("MinIntervalViolation") Boolean MinIntervalViolation,
            @Field("UnitLimitViolation") Boolean UnitLimitViolation,
            @Field("OccurenceViolation") Boolean OccurenceViolation,
            @Field("Notes") String Notes,
            @Field("AttachmentFile") String AttachmentFile,
            @Field("AttachmentID") String AttachmentID,
            @Field("DecisionNumber") String DecisionNumber,
            @Field("TransactionStatusID") String TransactionStatusID,
            @Field("ApprovedDate") String ApprovedDate,
            @Field("IsHalfDay") Boolean IsHalfDay,
            @Field("SubmitType") String SubmitType,
            @Field("Message") String Message,
            @Field("TransactionStatusSaveOrSubmit") String TransactionStatusSaveOrSubmit,
            @Field("Photo") String Photo,
            @Field("FullAccess") Boolean FullAccess,
            @Field("ExclusionFields") List<String> ExclusionFields,
            @Field("AccessibilityAttribute") String AccessibilityAttribute);

    @FormUrlEncoded
    @POST("api/LeaveRequest/DetailRequestConfirmation?transactionStatus=Submit")
    Call<RequestConfirmation> requestConfirmationSubmit(
            @Field("ID") String ID,
            @Field("EmployeeID") Integer EmployeeID,
            @Field("Roster") String Rooster,
            @Field("RequestDate") String RequestDate,
            @Field("EmployeeNIK") String EmployeeNIK,
            @Field("EmployeeName") String EmployeeName,
            @Field("LeaveRequestRuleID") Integer LeaveRequestRuleID,
            @Field("LeaveRequestType") String LeaveRequestType,
            @Field("EmployeeLeaveBalanceUID") String EmployeeLeaveBalanceUID,
            @Field("CurrentBalance") String CurrentBalance,
            @Field("BalanceExpireDate") String BalanceExpireDate,
            @Field("TotalUnit") String TotalUnit,
            @Field("TotalUnitReduce") String TotalUnitReduce,
            @Field("StartLeave") String StartLeave,
            @Field("EndLeave") String EndLeave,
            @Field("LeaveAt") String LeaveAt,
            @Field("ReturnAt") String ReturnAt,
            @Field("MinIntervalViolation") Boolean MinIntervalViolation,
            @Field("UnitLimitViolation") Boolean UnitLimitViolation,
            @Field("OccurenceViolation") Boolean OccurenceViolation,
            @Field("Notes") String Notes,
            @Field("AttachmentFile") String AttachmentFile,
            @Field("AttachmentID") String AttachmentID,
            @Field("DecisionNumber") String DecisionNumber,
            @Field("TransactionStatusID") String TransactionStatusID,
            @Field("ApprovedDate") String ApprovedDate,
            @Field("IsHalfDay") Boolean IsHalfDay,
            @Field("SubmitType") String SubmitType,
            @Field("Message") String Message,
            @Field("TransactionStatusSaveOrSubmit") String TransactionStatusSaveOrSubmit,
            @Field("Photo") String Photo,
            @Field("FullAccess") Boolean FullAccess,
            @Field("ExclusionFields") List<String> ExclusionFields,
            @Field("AccessibilityAttribute") String AccessibilityAttribute);

    @GET("api/LeaveCancelation/ListDraft")
    Call<ListDraftLeaveCancelation> getListDraftLeaveCancelation();

    @POST("api/LeaveCancelation/SaveDetail")
    Call<MessageReturn> saveLeaveCancelation(@Body RequestBody body, @Query("TransactionStatusSaveOrSubmit") String transactionStatusSaveOrSubmit);

    @POST("api/LeaveCancelation/EditDraft")
    Call<EditLeaveCancelation> editDraftLeaveCancelation(@Query("id") String id);

    @POST("api/LeaveCancelation/DeleteDraft")
    Call<MessageReturn> deleteDraftCancelation(@Body RequestBody body);

    @POST("api/LeaveCancelation/DetailRequestConfirmation")
    Call<MessageReturn> detailRequestConfirmationCancelation(@Body RequestBody body, @Query("TransactionStatusSaveOrSubmit") String transactionStatusSaveOrSubmit);

    @GET("api/MedicalClaim/GetMedicalSupport")
    Call<GetMedicalSupport> getMedicalSupport();

    @POST("api/MedicalClaim/GetMedicalPolicy")
    Call<GetMedicalPolicy> getMedicalPolicy(@Query("MedicalSupportID") String medicalSupportID);

    @GET("api/MedicalClaim/GetEmployeeFamily")
    Call<GetEmployeeFamily> getEmployeeFamily(@Query("MedicalSupportID") String medicalSupportID);

    @POST("api/MedicalClaim/GetClaimPolicy")
    Call<GetClaimPolicy> getClaimPolicy(@Query("MedicalPolicyID") String medicalPolicyID);

    @GET("api/Reimbursement/ListReimbursement")
    Call<Reimbursement> getReimburse();

    @GET("api/Reimbursement/GetReimbursementType")
    Call<ReimbursementType> getReimbursementType();

    @GET("api/Reimbursement/ListDraft")
    Call<ListDraftReimbursement> getListDraftReimbursement();

    @POST("api/Reimbursement/SaveDetail")
    Call<MessageReturn> saveSubmitDetailReimbursement(@Body RequestBody body, @Query("transactionStatus") String transactionStatus);

    @POST("api/Reimbursement/DeleteDraft")
    Call<MessageReturn> deleteDraftReimbursement(@Body RequestBody body);

    @POST("api/Reimbursement/EditDraft")
    Call<EditReimbursement> editDraftReimbursement(@Query("id") String id);

    @GET("api/OvertimeRequest/GetPersonalOvertime")
    Call<PersonalOvertime> getPersonalOvertime();

    @POST("api/OvertimeRequest/SaveDetail")
    Call<MessageReturn> saveDetailOvertime(@Body RequestBody body);


}
