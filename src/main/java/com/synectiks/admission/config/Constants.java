package com.synectiks.admission.config;

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
	
	enum EnquiryStatus{
      	RECEIVED, FOLLOWUP, ACCEPTED_FOR_ADMISSION, DECLINED, CONVERTED_TO_ADMISSION
    }
	
    enum AdmissionApplicationStatus{
        RECEIVED, INPROCESS, DECLINED, PARKED, ADMISSION_GRANTED
    }

    enum ModeOfEnquiry{
       INPERSON, TELEPHONE, EMAIL, ONLINE, APPLICATION_LETTER
    }

    

}
