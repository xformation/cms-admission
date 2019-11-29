package com.synectiks.admission.config;

import java.util.List;

/**
 * Application constants.
 */
public interface Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

    String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	String DATE_FORMAT_dd_MM_yyyy = "dd-MM-yyyy";
	String DATE_FORMAT_MM_dd_yyyy = "MM-dd-yyyy";
	String DATE_FORMAT_d_MMM_yyyy = "dd MMM yyyy";

    String OS_WINDOWS = "windows";
	String OS_UNIX = "unix";
	String OS_MAC = "mac";
	String OS_SOLARIS = "solaris";

	String STATUS_RECEIVED = "RECEIVED";
	String STATUS_FOLLOWUP = "FOLLOWUP";
	String STATUS_ACCEPTED_FOR_ADMISSION = "ACCEPTED_FOR_ADMISSION";
	String STATUS_DECLINED = "DECLINED";
	String STATUS_CONVERTED_TO_ADMISSION = "CONVERTED_TO_ADMISSION";

	public static List<String> ENQUIRY_STATUS_LIST = initAdmissionEnquiryStatusList();
    String ERROR_ADMISSIONENQUIRY_ALREADY_EXISTS = "Admission Enquiry already exists. Application allows only one Enquiry.";
    String VALIDATION_FAILURE = "Business validation failed: ";


    public static List<String> initAdmissionEnquiryStatusList(){
    	ENQUIRY_STATUS_LIST.add(STATUS_RECEIVED);
    	ENQUIRY_STATUS_LIST.add(STATUS_FOLLOWUP);
    	ENQUIRY_STATUS_LIST.add(STATUS_ACCEPTED_FOR_ADMISSION);
    	ENQUIRY_STATUS_LIST.add(STATUS_DECLINED);
    	ENQUIRY_STATUS_LIST.add(STATUS_CONVERTED_TO_ADMISSION);
    	return ENQUIRY_STATUS_LIST;
    }

	String STATUS_INPROCESS = "INPROCESS";
	String STATUS_PARKED = "PARKED";
	String STATUS_ADMISSION_GRANTED = "ADMISSION_GRANTED";

	public static List<String> ADMISSION_STATUS_LIST = initAdmissionStatusList();
    public static List<String> initAdmissionStatusList(){
    	ADMISSION_STATUS_LIST.add(STATUS_RECEIVED);
    	ADMISSION_STATUS_LIST.add(STATUS_INPROCESS);
    	ADMISSION_STATUS_LIST.add(STATUS_PARKED);
    	ADMISSION_STATUS_LIST.add(STATUS_DECLINED);
    	ADMISSION_STATUS_LIST.add(STATUS_ADMISSION_GRANTED);
    	return ADMISSION_STATUS_LIST;
    }

    String MODE_INPERSON = "INPERSON";
    String MODE_TELEPHONE = "TELEPHONE";
    String MODE_EMAIL = "EMAIL";
    String MODE_ONLINE = "ONLINE";
    String MODE_APPLICATION_LETTER = "APPLICATION_LETTER";

    public static List<String> MODE_OF_ENQUIRY_LIST = initModeOfEnquiryStatusList();
    public static List<String> initModeOfEnquiryStatusList(){
    	MODE_OF_ENQUIRY_LIST.add(MODE_INPERSON);
    	MODE_OF_ENQUIRY_LIST.add(MODE_TELEPHONE);
    	MODE_OF_ENQUIRY_LIST.add(MODE_EMAIL);
    	MODE_OF_ENQUIRY_LIST.add(MODE_ONLINE);
    	MODE_OF_ENQUIRY_LIST.add(MODE_APPLICATION_LETTER);
    	return MODE_OF_ENQUIRY_LIST;
    }


}
