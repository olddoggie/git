package com.onefbusa.op.properties;

import java.util.ResourceBundle;


public class General {
    private static ResourceBundle rb = ResourceBundle.getBundle("com.onefbusa.op.properties.general");

    public static final String SITE_URL = rb.getString("url") + rb.getString("app_context");
    public static final String SITE_wrongURL = rb.getString("wrong_url") + rb.getString("app_context");

    public static final String AgentDashboard_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_agentDashboard");
    public static final String TenantDashboard_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_tenantDashboard");
    public static final String AdminDashboard_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_adminDashboard");
    public static final String AboutUSPage_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_aboutUs");
    public static final String ForgotPasswordPage_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_forgetPassword");
    public static final String AgentSignUpSucceed_URL = rb.getString("url") + rb.getString("app_context") + rb.getString("url_agentSignUpSucceed");

}
