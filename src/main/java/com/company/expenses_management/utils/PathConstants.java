package com.company.expenses_management.utils;

public abstract class PathConstants {

    public static final String basePath = "/cmp-exp";
    public static final String createUser = "/create-user";
    public static final String userLogin = "/login";
    public static final String getUserProfile = "/user-profile/{id}";
    public static final String listAllUsers = "/user/all";
    public static final String expenses = "/expenses";
    public static final String createRequest = "/create";
    public static final String viewExpenseRequestById = "/{id}";
    public static final String viewAllExpenses = "/all";
    public static final String viewAllExpensesByEmployeeId = "/{id}/all";
    public static final String updateStatus = viewExpenseRequestById + "/status";
    public static final String reportPath = "/report";
    public static final String createReport = "/create";
    public static final String listAllReports = "/all";
}
