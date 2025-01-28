package com.company.expenses_management.utils;

public abstract class SQLConstants {

    public static final String USER_TABLE_NAME = "user";
    public static final String ID_PROPERTY = "id";
    public static final String USER_NAME_PROPERTY = "first_name";
    public static final String USER_SURNAME_PROPERTY = "last_name";
    public static final String USER_GENDER_PROPERTY = "gender";
    public static final String USER_EMAIL_PROPERTY = "email";
    public static final String USER_PASSWORD_PROPERTY = "password";
    public static final String USER_BIRTHDAY_PROPERTY = "birthday";
    public static final String USER_PHONE_NUMBER_PROPERTY = "phone_number";
    public static final String USER_ROLE_PROPERTY = "role";
    public static final String EXPENSE_TABLE_NAME = "expense";
    public static final String EXPENSE_EMPLOYEE_ID_PROPERTY = "employee_id";
    public static final String AMOUNT_TO_REFUND_PROPERTY = "amount_to_refund";
    public static final String EXPENSE_DESCRIPTION_PROPERTY = "expense_description";
    public static final String STATUS_PROPERTY = "status";
    public static final String REFUNDED_BY_PROPERTY = "refunded_by";
    public static final String CREATED_DATE_PROPERTY= "created_date";
    public static final String STATUS_UPDATED_DATE_PROPERTY = "status_updated_date";
    public static final String REPORT_TABLE_NAME = "report";
    public static final String START_DATE_PROPERTY = "start_date";
    public static final String END_DATE_PROPERTY = "end_date";
    public static final String TOTAL_AMOUNT_REFUNDED = "refunded_amount";

}
