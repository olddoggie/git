package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AgentYourPropertiesPage extends AgentDashboardPage {
    // web elements in this page
    public static MyWebElement agentProperties_title =  myWebelement("jQuerySelector:h3.row-fluid div:contains('YOUR PROPERTIES')", "AgentPropertiesPage_Title");
    public static MyWebElement agentProperties_subTitle =  myWebelement("cssSelector:span.sub-title", "AgentPropertiesPage_SubTitle");

    public static MyWebElement addNewProperties_btn = myWebelement("addProperty", "AddNewProperties_Button");
    public static MyWebElement viewPending_link = myWebelement("viewPending", "ViewPendingLease_Link");

    public static MyWebElement propertiesTable_table = myWebelement("jQuerySelector:table tbody", "Properties_Table");
    public static MyWebElement propertiesTable_title_1 = myWebelement("cssSelector:table thead tr th", "PropertiesTable_Title_Col1",0);
    public static MyWebElement propertiesTable_title_2 = myWebelement("cssSelector:table thead tr th", "PropertiesTable_Title_Col2",1);
    public static MyWebElement propertiesTable_title_3 = myWebelement("cssSelector:table thead tr th", "PropertiesTable_Title_Col3",2);
    public static MyWebElement propertiesTable_nameAddress1 = getElementFromPropertiesTable(propertiesTable_table, 1, 1);
    public static MyWebElement propertiesTable_nameAddress2 = getElementFromPropertiesTable(propertiesTable_table, 2, 1);
    public static MyWebElement propertiesTable_nameAddress3 = getElementFromPropertiesTable(propertiesTable_table, 3, 1);
    public static MyWebElement propertiesTable_nameAddress4 = getElementFromPropertiesTable(propertiesTable_table, 4, 1);
    public static MyWebElement propertiesTable_nameAddress5 = getElementFromPropertiesTable(propertiesTable_table, 5, 1);
    public static MyWebElement propertiesTable_floorUnit1 = getElementFromPropertiesTable(propertiesTable_table,1,2);
    public static MyWebElement propertiesTable_floorUnit2 = getElementFromPropertiesTable(propertiesTable_table,2,2);
    public static MyWebElement propertiesTable_floorUnit3 = getElementFromPropertiesTable(propertiesTable_table,3,2);
    public static MyWebElement propertiesTable_floorUnit4 = getElementFromPropertiesTable(propertiesTable_table,4,2);
    public static MyWebElement propertiesTable_floorUnit5 = getElementFromPropertiesTable(propertiesTable_table,5,2);
    public static MyWebElement propertiesTable_pendingRefunds1 = getElementFromPropertiesTable(propertiesTable_table,1,3);
    public static MyWebElement propertiesTable_pendingRefunds2 = getElementFromPropertiesTable(propertiesTable_table,2,3);
    public static MyWebElement propertiesTable_pendingRefunds3 = getElementFromPropertiesTable(propertiesTable_table,3,3);
    public static MyWebElement propertiesTable_pendingRefunds4 = getElementFromPropertiesTable(propertiesTable_table,4,3);
    public static MyWebElement propertiesTable_pendingRefunds5 = getElementFromPropertiesTable(propertiesTable_table,5,3);
    public static MyWebElement propertiesTable_viewBtn1 = myWebelement("jQuerySelector:button:contains('VIEW')", "PropertiesTable_ViewButton1", 0);
    public static MyWebElement propertiesTable_viewBtn2 = myWebelement("jQuerySelector:button:contains('VIEW')", "PropertiesTable_ViewButton2", 1);
    public static MyWebElement propertiesTable_viewBtn3 = myWebelement("jQuerySelector:button:contains('VIEW')", "PropertiesTable_ViewButton3", 2);
    public static MyWebElement propertiesTable_viewBtn4 = myWebelement("jQuerySelector:button:contains('VIEW')", "PropertiesTable_ViewButton4", 3);
    public static MyWebElement propertiesTable_viewBtn5 = myWebelement("jQuerySelector:button:contains('VIEW')","PropertiesTable_ViewButton5",4);

    public static MyWebElement view0PendingLease_Btn = myWebelement("jQuerySelector:td:contains('0 PENDING') + td button:contains('VIEW')","ViewPendingLease_Button");
    public static MyWebElement view1PendingLease_Btn = myWebelement("jQuerySelector:td:contains('1 PENDING') + td button:contains('VIEW')","ViewPendingLease_Button");
    public static MyWebElement view2PendingLease_Btn = myWebelement("jQuerySelector:td:contains('2 PENDING') + td button:contains('VIEW')","ViewPendingLease_Button");

    // page title
    public static String pageTitle = "YOUR PROPERTIES";
    // define some web elements group used in test cases, which will make test case as simple as possible
        //  properties table's title
    public static MyWebElement[] propertiesTable_titles = {propertiesTable_title_1,propertiesTable_title_2,propertiesTable_title_3};
        //  properties table's row1 to row5
    public static MyWebElement[] propertiesTable_items_row1 = {propertiesTable_nameAddress1,propertiesTable_floorUnit1,propertiesTable_pendingRefunds1};
    public static MyWebElement[] propertiesTable_items_row2 = {propertiesTable_nameAddress2,propertiesTable_floorUnit2,propertiesTable_pendingRefunds2};
    public static MyWebElement[] propertiesTable_items_row3 = {propertiesTable_nameAddress3,propertiesTable_floorUnit3,propertiesTable_pendingRefunds3};
    public static MyWebElement[] propertiesTable_items_row4 = {propertiesTable_nameAddress4,propertiesTable_floorUnit4,propertiesTable_pendingRefunds4};
    public static MyWebElement[] propertiesTable_items_row5 = {propertiesTable_nameAddress5,propertiesTable_floorUnit5,propertiesTable_pendingRefunds5};
        //  properties table's propertyName col
    public static MyWebElement[] propertiesTable_items_propertyNameColumn = {propertiesTable_nameAddress1,propertiesTable_nameAddress2,propertiesTable_nameAddress3,propertiesTable_nameAddress4,propertiesTable_nameAddress5};
        //  properties table's pending refund col
    public static MyWebElement[] propertiesTable_items_pendingRefundsColumn = {propertiesTable_pendingRefunds1,propertiesTable_pendingRefunds2,propertiesTable_pendingRefunds3,propertiesTable_pendingRefunds4,propertiesTable_pendingRefunds5};

    // methods, get element from properties table
    public static MyWebElement getElementFromPropertiesTable(MyWebElement propertiesTable,int row, int col) {
        String col_name;
        switch(col)  {
            case 1:
                col_name = "NAME / ADDRESS"; break;
            case 2:
                col_name = "FLOORS & UNIT"; break;
            case 3:
                col_name = "PENDING REFUNDS"; break;
            default:throw new IllegalStateException( "Table: " + propertiesTable.getElementName() + " only got 3 columns at all!");
        }
        //System.out.println(" Get row" + row + " of " + col_name + " column from table: " + propertiesTable.getElementName());
        int _row = row -1;
        int _col = col -1;
        MyWebElement propertiesTable_item =
                myWebelement("jQuerySelector:table tbody tr:eq("
                        + _row + ") td:eq(" + _col + ")", col_name + "_" + row);
        return propertiesTable_item;
    }

    // methods, according to the number to locator the view button(the number equals to the number of a its pending leases: 0,1,2,etc.)
    // also can easily use view0PendingProperty_btn,view1PendingProperty_btn,view2PendingProperty_btn,etc. to locator view button
    public static void clickViewBtn_byNum(int num) {
        switch(num)  {
            case 1:
                propertiesTable_viewBtn1.click(); break;
            case 2:
                propertiesTable_viewBtn2.click(); break;
            case 3:
                propertiesTable_viewBtn3.click(); break;
            case 4:
                propertiesTable_viewBtn4.click(); break;
            case 5:
                propertiesTable_viewBtn5.click(); break;
            default:throw new IllegalStateException( "Table only got 5 View button at all!");
        }
    }
    // methods, used with clickViewBtn
    public static void clickFirstViewBtn_byPropertyName (String name) {
        int i = 1;
        for (MyWebElement status: propertiesTable_items_propertyNameColumn) {
            if (status.getText().contains(name))  {
                break;
            }
            i++;
        }
        clickViewBtn_byNum(i);
    }

    // methods, used to click the first found View button according to 0 pending, 1 pending, 2 pending, etc.
    public static void clickFirstViewBtn_byPendingRefundsNum (int num) {
        int i = 1;
        String pending = num + " PENDING";
        for (MyWebElement status: propertiesTable_items_pendingRefundsColumn) {
            if (status.getText().equals(pending))  {
                break;
            }
            i++;
        }
        clickViewBtn_byNum(i);
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AgentYourPropertiesPage load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AgentYourPropertiesPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        verifyPageTitle(pageTitle);
        agentProperties_title.verifyElementPresent();
        agentProperties_subTitle.verifyElementPresent();
    }

}
