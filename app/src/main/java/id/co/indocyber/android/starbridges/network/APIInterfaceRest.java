package id.co.indocyber.android.starbridges.network;

import java.math.BigDecimal;
import java.util.List;

import id.co.indocyber.android.starbridges.model.Attendence;
import id.co.indocyber.android.starbridges.model.Authentication;
import id.co.indocyber.android.starbridges.model.CorrectionDetail.CorrectionDetail;
import id.co.indocyber.android.starbridges.model.DecisionNumber.DecisionNumber;
import id.co.indocyber.android.starbridges.model.DeleteShiftExchange.DeleteShiftExchange;
import id.co.indocyber.android.starbridges.model.EditLeaveCancelation.EditLeaveCancelation;
import id.co.indocyber.android.starbridges.model.EditOvertime.EditOvertime;
import id.co.indocyber.android.starbridges.model.EditReimbursement.EditReimbursement;
import id.co.indocyber.android.starbridges.model.EditShiftExchange.EditShiftExchange;
import id.co.indocyber.android.starbridges.model.LeaveCancelationTransaction.LeaveCancelationTransaction;
import id.co.indocyber.android.starbridges.model.ListAttendanceCorrection.ListAttendanceCorrection;
import id.co.indocyber.android.starbridges.model.ListDraftCorrection.ListDraftCorrection;
import id.co.indocyber.android.starbridges.model.ListDraftLeaveCancelation.ListDraftLeaveCancelation;
import id.co.indocyber.android.starbridges.model.ListDraftOvertime.ListDraftOvertime;
import id.co.indocyber.android.starbridges.model.ListDraftReimbursement.ListDraftReimbursement;
import id.co.indocyber.android.starbridges.model.ListDraftShiftExchange.ListDraftShiftExchange;
import id.co.indocyber.android.starbridges.model.ListEmployee.ListEmployee;
import id.co.indocyber.android.starbridges.model.ListLeaveCancelation.ListLeaveCancelation;
import id.co.indocyber.android.starbridges.model.ListOvertime.Overtime;
import id.co.indocyber.android.starbridges.model.ListShift.ListShift;
import id.co.indocyber.android.starbridges.model.ListShiftExchange.ListShiftExchange;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.model.OLocation.OLocation;
import id.co.indocyber.android.starbridges.model.OPost;
import id.co.indocyber.android.starbridges.model.PersonalOvertime.PersonalOvertime;
import id.co.indocyber.android.starbridges.model.Reimbursement.Reimbursement;
import id.co.indocyber.android.starbridges.model.ReimbursementType.ReimbursementType;
import id.co.indocyber.android.starbridges.model.SaveShiftExchange.SaveShiftExchange;
import id.co.indocyber.android.starbridges.model.SubmitOvertime.SubmitOvertime;
import id.co.indocyber.android.starbridges.model.balanceType.BalanceType;
import id.co.indocyber.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import id.co.indocyber.android.starbridges.model.deletemedical.DeleteMedical;
import id.co.indocyber.android.starbridges.model.editleaverequest.EditLeaveRequest;
import id.co.indocyber.android.starbridges.model.editmedical.EditMedical;
import id.co.indocyber.android.starbridges.model.getclaimpolicy.GetClaimPolicy;
import id.co.indocyber.android.starbridges.model.getemployeefamily.GetEmployeeFamily;
import id.co.indocyber.android.starbridges.model.getimage.GetImage;
import id.co.indocyber.android.starbridges.model.getmedicalpolicy.GetMedicalPolicy;
import id.co.indocyber.android.starbridges.model.getmedicalsupport.GetMedicalSupport;
import id.co.indocyber.android.starbridges.model.history.History;
import id.co.indocyber.android.starbridges.model.leaverequest.LeaveRequest;
import id.co.indocyber.android.starbridges.model.listdraftleaverequest.ListDraftLeaveRequest;
import id.co.indocyber.android.starbridges.model.listdraftmedical.ListDraftMedical;
import id.co.indocyber.android.starbridges.model.listmedicalclaim.ListMedicalClaim;
import id.co.indocyber.android.starbridges.model.medicalrequestconfirmation.MedicalRequestConfirmation;
import id.co.indocyber.android.starbridges.model.medicalsavedetail.MedicalSaveDetail;
import id.co.indocyber.android.starbridges.model.requestconfirmation.RequestConfirmation;
import id.co.indocyber.android.starbridges.model.requesttype.RequestType;
import id.co.indocyber.android.starbridges.model.saveLeaveRequest.SaveLeaveRequest;
import id.co.indocyber.android.starbridges.model.versioning.Versioning;
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
    @POST("api/Attendance/ValidasiLogin")
    Call<MessageReturn> getValidation(
            @Field("username") String username,
            @Field("password") String password,
            @Field("IMEI") String IMEI);

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
    @POST("api/LeaveRequest/DetailRequestConfirmation")
    Call<RequestConfirmation> requestConfirmation(
            @Query("transactionStatus") String transactionStatus,

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

    @GET("api/MedicalClaim/GetMedicalSupport")
    Call<GetMedicalSupport> getMedicalSupport();

    @POST("api/MedicalClaim/GetMedicalPolicy")
    Call<GetMedicalPolicy> getMedicalPolicy(@Query("MedicalSupportID") String medicalSupportID);

    @GET("api/MedicalClaim/GetEmployeeFamily")
    Call<GetEmployeeFamily> getEmployeeFamily(@Query("MedicalSupportID") String medicalSupportID);

    @POST("api/MedicalClaim/GetClaimPolicy")
    Call<GetClaimPolicy> getClaimPolicy(@Query("MedicalPolicyID") String medicalPolicyID);

    //@FormUrlEncoded
    @GET("api/MedicalClaim/ListMedicalClaim")
    Call<ListMedicalClaim> getListMedicalClaim();

    //@FormUrlEncoded
    @GET("api/MedicalClaim/ListDraft")
    Call<ListDraftMedical> getListDraftMedical();

    //@FormUrlEncoded
    @POST("api/MedicalClaim/EditDraft")
    Call<EditMedical> editMedical(@Query("id") String id);

    //@FormUrlEncoded
    @POST("api/MedicalClaim/DeleteDraft")
    Call<DeleteMedical> deleteMedical(@Body RequestBody body);

    @FormUrlEncoded
    @POST("api/MedicalClaim/DetailRequestConfirmation")
    Call<MedicalRequestConfirmation> medicalRequestConfirmation(
            @Query("transactionStatus") String transactionStatus,

            @Field("EmployeeID") String EmployeeID,
            @Field("ID") String ID,
            @Field("MedicalSupportID") String MedicalSupportID,
            @Field("MedicalSupportName") String MedicalSupportName,
            @Field("MedicalPolicyID") String MedicalPolicyID,
            @Field("PolicyTypeID") String PolicyTypeID,
            @Field("MedicalPolicyName") String MedicalPolicyName,
            @Field("RemainingBalance") String RemainingBalance,
            @Field("EmployeeFamilyID") String EmployeeFamilyID,
            @Field("EmployeeFamilyName") String EmployeeFamilyName,
            @Field("MedicalClaimPolicyID") String MedicalClaimPolicyID,
            @Field("TotalClaim") String TotalClaim,
            @Field("TotalReimbursement") String TotalReimbursement,
            @Field("AttachmentFile") String AttachmentFile,
            @Field("AttachmentID") String AttachmentID,
            @Field("ReceiptDate") String ReceiptDate,
            @Field("DecisionNumber") String DecisionNumber,
            @Field("TransactionStatusID") String TransactionStatusID,
            @Field("ApprovedDate") String ApprovedDate,
            @Field("Claim") String Claim,
            @Field("TransactionStatusSaveOrSubmit") String TransactionStatusSaveOrSubmit,
            @Field("FullAccess") String FullAccess,
            @Field("ExclusionFields") List<String> ExclusionFields,
            @Field("AccessibilityAttribute") String AccessibilityAttribute);

    @POST("api/MedicalClaim/DetailRequestConfirmation")
    Call<MedicalRequestConfirmation> medicalRequestConfirmation2(
            @Query("transactionStatus") String transactionStatus,
            @Body RequestBody body);

    @FormUrlEncoded
    @POST("api/MedicalClaim/SaveDetail")
    Call<MedicalSaveDetail> medicalSaveDetail(
            @Field("EmployeeID") String EmployeeID,
            @Field("ID") String ID,
            @Field("MedicalSupportID") String MedicalSupportID,
            @Field("MedicalSupportName") String MedicalSupportName,
            @Field("MedicalPolicyID") String MedicalPolicyID,
            @Field("PolicyTypeID") String PolicyTypeID,
            @Field("MedicalPolicyName") String MedicalPolicyName,
            @Field("RemainingBalance") String RemainingBalance,
            @Field("EmployeeFamilyID") String EmployeeFamilyID,
            @Field("EmployeeFamilyName") String EmployeeFamilyName,
            @Field("MedicalClaimPolicyID") String MedicalClaimPolicyID,
            @Field("TotalClaim") String TotalClaim,
            @Field("TotalReimbursement") int TotalReimbursement,
            @Field("AttachmentFile") String AttachmentFile,
            @Field("AttachmentID") String AttachmentID,
            @Field("ReceiptDate") String ReceiptDate,
            @Field("DecisionNumber") String DecisionNumber,
            @Field("TransactionStatusID") String TransactionStatusID,
            @Field("ApprovedDate") String ApprovedDate,
            @Field("Claim") String Claim,
            @Field("TransactionStatusSaveOrSubmit") String TransactionStatusSaveOrSubmit,
            @Field("FullAccess") String FullAccess,
            @Field("ExclusionFields") List<String> ExclusionFields,
            @Field("AccessibilityAttribute") String AccessibilityAttribute);


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

    @POST("api/OvertimeRequest/EditDraft")
    Call<EditOvertime> editOvertime(@Query("id") String id);

    @POST("api/OvertimeRequest/DetailRequestConfirmation")
    Call<SubmitOvertime> submitOvertime(@Body RequestBody body, @Query("transactionStatus") String transactionStatus);

    @POST("api/OvertimeRequest/DeleteDraft")
    Call<MessageReturn> deleteDraftOvertime(@Body RequestBody body);

    @GET("api/LeaveCancelation/ListDraft")
    Call<ListDraftLeaveCancelation> getListDraftLeaveCancelation();

    @POST("api/LeaveCancelation/SaveDetail")
    Call<MessageReturn> saveLeaveCancelation(@Body RequestBody body, @Query("TransactionStatusSaveOrSubmit") String transactionStatusSaveOrSubmit);

    @POST("api/LeaveCancelation/EditDraft")
    Call<EditLeaveCancelation> editDraftLeaveCancelation(@Query("id") String id);

    @POST("api/LeaveCancelation/DeleteDraft")
    Call<MessageReturn> deleteDraftCancelation(@Body RequestBody body);

    @POST("api/LeaveCancelation/DetailRequestConfirmation")
    Call<LeaveCancelationTransaction> detailRequestConfirmationCancelation(@Body RequestBody body, @Query("TransactionStatusSaveOrSubmit") String transactionStatusSaveOrSubmit);

    @GET("api/ShiftExchange/ListShiftExchange")
    Call<ListShiftExchange> getListShiftExchange();

    @GET("api/ShiftExchange/ListDraft")
    Call<ListDraftShiftExchange> getListDraftShiftExchange();

    @GET("api/ShiftExchange/GetShift")
    Call<ListShift> getListShift();

    @GET("api/ShiftExchange/GetListEmployee")
    Call<ListEmployee> getListShiftEmployee();

    @POST("api/ShiftExchange/SaveDetail")
    Call<SaveShiftExchange> saveShiftExchange(@Body RequestBody body, @Query("transactionStatus") String transactionStatus);
    //@Query("transactionStatus") String transactionStatus,
    //@Field("ID") String id,
    //@Field("EmployeeID") String employeeID,
    //@Field("Date") String date,
    //@Field("ShiftID") String shiftID,
    //@Field("ShiftName") String shiftName,
    //@Field("Notes") String notes,
    //@Field("TransactionStatusID") String transactionStatusID,
    //@Field("AttachmentFile") String attachmentFile,
    //@Field("AttachmentID") String attachmentID)
    //;

    @POST("api/ShiftExchange/EditDraft")
    Call<EditShiftExchange> editShiftExchange(@Query("id") String id);

    @POST("api/ShiftExchange/DeleteDraft")
    Call<DeleteShiftExchange> deleteShiftExchange(@Body RequestBody body);

    @GET("api/Attendance/GetAppVersion")
    Call<Versioning>checkAppVerion();
}
