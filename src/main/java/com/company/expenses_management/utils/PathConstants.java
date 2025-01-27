package com.company.expenses_management.utils;

public abstract class PathConstants {

    public static final String basePath = "/cmp-exp";
    public static final String createUser = "/create-user";
    public static final String userLogin = "/login";
    public static final String expenses = "/expenses";
    public static final String createRequest = "/create";
    public static final String viewExpenseRequestById = "/{id}";
    public static final String viewAllExpenses = "/all";
    public static final String viewAllExpensesByEmployeeNameOrLastName = viewAllExpenses + "/search";
}
