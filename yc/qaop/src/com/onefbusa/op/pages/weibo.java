package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class weibo extends OPBasePage {
    public static MyWebElement tieba_link = myWebelement("cssSelector:li[class=product-life] a:nth-child(2)", "tieba_link");
    //public static MyWebElement requestAccount_link = myWebelement("className:btn-primary", "request_link");
    //public static MyWebElement requestAccount_link = myWebelement("linkText:REQUEST ACCOUNT", "RequestAccount_Link");

    public static String pageTitle = "baidu";

    public static weibo load() {
        //verifyPageTitle(pageTitle);
        print("\nOn " + pageTitle + " page: ");
        return new weibo();
    }

}