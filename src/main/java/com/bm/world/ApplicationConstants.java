package com.bm.world;

/**
 * This class used for declare the constants for our student service project
 */
public class ApplicationConstants {
    // Declaring the Table Names contants
    public static  final String STUDENT_DETAILS_TABLE_NAME="student_details";

    // messages Constants
    public static  final String STUDENT_SAVE_MESSAGE="student data is saved with this studentID:\t";

    //Controller url constants
    public static final String BASE_URL="/bm/std";

    public  static final String STUDENT_SAVE="/save";
    public static final String STUDENT_DELETE="/delete/{studentId}";
}
