package com.bm.world;

/**
 * This class used for declare the constants for our student service project
 */
public class ApplicationConstants {
	private ApplicationConstants() {}
    // Declaring the Table Names contants	
    public static  final String STUDENT_DETAILS_TABLE_NAME="student_details";
    public static final String USER_INFO_TABLE_NAME= "user_info";
    // messages Constants
    public static  final String STUDENT_SAVE_MESSAGE="student data is saved with this studentID:\t";

    //Controller url constants
    public static final String BASE_URL="/bm/std";
    public static final String API_AUTH = "/api/auth";
    public  static final String STUDENT_SAVE="/save";
    public static final String STUDENT_UPDATE="/update";
    public static final String STUDENT_DELETE="/delete/{studentId}";
    public static final String GET_ALL_STUDENTS="/getAll";
    public static final String GET_STUDENT_BY_ID="/getStudentById/{studentId}";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/signin";
    public static final String Name="bm";
    public static final String Name1="bm";

}
