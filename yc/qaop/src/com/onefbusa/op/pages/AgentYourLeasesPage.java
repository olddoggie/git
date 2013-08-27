package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AgentYourLeasesPage extends AgentDashboardPage {
    // web elements in this page
    public static MyWebElement agentLeases_title =  myWebelement("jQuerySelector:h3.row-fluid div:contains('YOUR LEASE')", "AgentLeasesPage_Title");
    public static MyWebElement agentLeases_subTitle =  myWebelement("cssSelector:span.sub-title", "AgentLeasesPage_SubTitle");

    public static MyWebElement addNewLease_btn = myWebelement("addLease", "AddNewLease_Button");
    public static MyWebElement viewPending_link = myWebelement("viewPending", "ViewPendingLease_Link");

    public static MyWebElement leaseTable_table = myWebelement("jQuerySelector:table tbody", "Lease_Table");
    public static MyWebElement leaseTable_title_1 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col1",0);
    public static MyWebElement leaseTable_title_2 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col2",1);
    public static MyWebElement leaseTable_title_3 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col3",2);
    public static MyWebElement leaseTable_title_4 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col4",3);
    public static MyWebElement leaseTable_title_5 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col5",4);
    public static MyWebElement leaseTable_title_6 = myWebelement("cssSelector:table thead tr th", "LeaseTable_Title_Col6",5);
    public static MyWebElement leaseTable_property1 = getElementFromLeaseTable(leaseTable_table, 1, 1);
    public static MyWebElement leaseTable_property2 = getElementFromLeaseTable(leaseTable_table, 2, 1);
    public static MyWebElement leaseTable_property3 = getElementFromLeaseTable(leaseTable_table, 3, 1);
    public static MyWebElement leaseTable_property4 = getElementFromLeaseTable(leaseTable_table, 4, 1);
    public static MyWebElement leaseTable_property5 = getElementFromLeaseTable(leaseTable_table,5,1);
    public static MyWebElement leaseTable_tenant1 = getElementFromLeaseTable(leaseTable_table,1,2);
    public static MyWebElement leaseTable_tenant2 = getElementFromLeaseTable(leaseTable_table,2,2);
    public static MyWebElement leaseTable_tenant3 = getElementFromLeaseTable(leaseTable_table,3,2);
    public static MyWebElement leaseTable_tenant4 = getElementFromLeaseTable(leaseTable_table,4,2);
    public static MyWebElement leaseTable_tenant5 = getElementFromLeaseTable(leaseTable_table,5,2);
    public static MyWebElement leaseTable_term1 = getElementFromLeaseTable(leaseTable_table,1,3);
    public static MyWebElement leaseTable_term2 = getElementFromLeaseTable(leaseTable_table,2,3);
    public static MyWebElement leaseTable_term3 = getElementFromLeaseTable(leaseTable_table,3,3);
    public static MyWebElement leaseTable_term4 = getElementFromLeaseTable(leaseTable_table,4,3);
    public static MyWebElement leaseTable_term5 = getElementFromLeaseTable(leaseTable_table,5,3);
    public static MyWebElement leaseTable_rent1 = getElementFromLeaseTable(leaseTable_table,1,4);
    public static MyWebElement leaseTable_rent2 = getElementFromLeaseTable(leaseTable_table,2,4);
    public static MyWebElement leaseTable_rent3 = getElementFromLeaseTable(leaseTable_table,3,4);
    public static MyWebElement leaseTable_rent4 = getElementFromLeaseTable(leaseTable_table,4,4);
    public static MyWebElement leaseTable_rent5 = getElementFromLeaseTable(leaseTable_table,5,4);
    public static MyWebElement leaseTable_security1 = getElementFromLeaseTable(leaseTable_table,1,5);
    public static MyWebElement leaseTable_security2 = getElementFromLeaseTable(leaseTable_table,2,5);
    public static MyWebElement leaseTable_security3 = getElementFromLeaseTable(leaseTable_table,3,5);
    public static MyWebElement leaseTable_security4 = getElementFromLeaseTable(leaseTable_table,4,5);
    public static MyWebElement leaseTable_security5 = getElementFromLeaseTable(leaseTable_table,5,5);
    public static MyWebElement leaseTable_status1 = getElementFromLeaseTable(leaseTable_table,1,6);
    public static MyWebElement leaseTable_status2 = getElementFromLeaseTable(leaseTable_table,2,6);
    public static MyWebElement leaseTable_status3 = getElementFromLeaseTable(leaseTable_table,3,6);
    public static MyWebElement leaseTable_status4 = getElementFromLeaseTable(leaseTable_table,4,6);
    public static MyWebElement leaseTable_status5 = getElementFromLeaseTable(leaseTable_table,5,6);
    public static MyWebElement leaseTable_viewBtn1 = myWebelement("jQuerySelector:button:contains('VIEW')", "LeaseTable_ViewButton1", 0);
    public static MyWebElement leaseTable_viewBtn2 = myWebelement("jQuerySelector:button:contains('VIEW')", "LeaseTable_ViewButton2", 1);
    public static MyWebElement leaseTable_viewBtn3 = myWebelement("jQuerySelector:button:contains('VIEW')", "LeaseTable_ViewButton3", 2);
    public static MyWebElement leaseTable_viewBtn4 = myWebelement("jQuerySelector:button:contains('VIEW')", "LeaseTable_ViewButton4", 3);
    public static MyWebElement leaseTable_viewBtn5 = myWebelement("jQuerySelector:button:contains('VIEW')","LeaseTable_ViewButton5",4);

    public static MyWebElement viewActiveLease_Btn = myWebelement("jQuerySelector:td:contains('ACTIVE') + td button:contains('VIEW')","ViewActiveLease_Button");
    public static MyWebElement viewActiveRenewLease_Btn = myWebelement("jQuerySelector:td:contains('ACTIVE') + td button:contains('VIEW')","ViewActiveRenewLease_Button");
    public static MyWebElement viewPendingLease_Btn = myWebelement("jQuerySelector:td:contains('PENDING') + td button:contains('VIEW')","ViewPendingLease_Button");
    public static MyWebElement viewResolvedLease_Btn = myWebelement("jQuerySelector:td:contains('RESOLVED') + td button:contains('VIEW')","ViewResolvedLease_Button");
    public static MyWebElement viewDisputedLease_Btn = myWebelement("jQuerySelector:td:contains('DISPUTED') + td button:contains('VIEW')","ViewDisputedLease_Button");

    // page title
    public static String pageTitle = "YOUR LEASES";

    // define some web elements group used in test cases, which will make test case as simple as possible
        // lease table's titles
    public static MyWebElement[] leaseTable_titles = {leaseTable_title_1,leaseTable_title_2,leaseTable_title_3,leaseTable_title_4,leaseTable_title_5,leaseTable_title_6};
        // lease table from row1 to row5
    public static MyWebElement[] leaseTable_items_row1 = {leaseTable_property1,leaseTable_tenant1,leaseTable_term1,leaseTable_rent1,leaseTable_security1,leaseTable_status1};
    public static MyWebElement[] leaseTable_items_row2 = {leaseTable_property2,leaseTable_tenant2,leaseTable_term2,leaseTable_rent2,leaseTable_security2,leaseTable_status2};
    public static MyWebElement[] leaseTable_items_row3 = {leaseTable_property3,leaseTable_tenant3,leaseTable_term3,leaseTable_rent3,leaseTable_security3,leaseTable_status3};
    public static MyWebElement[] leaseTable_items_row4 = {leaseTable_property4,leaseTable_tenant4,leaseTable_term4,leaseTable_rent4,leaseTable_security4,leaseTable_status4};
    public static MyWebElement[] leaseTable_items_row5 = {leaseTable_property5,leaseTable_tenant5,leaseTable_term5,leaseTable_rent5,leaseTable_security5,leaseTable_status5};
        //  lease table's status col
    public static MyWebElement[] leaseTable_items_statusColumn = {leaseTable_status1,leaseTable_status2,leaseTable_status3,leaseTable_status4,leaseTable_status5};

    // method, used to get element from lease table
    public static MyWebElement getElementFromLeaseTable(MyWebElement leaseTable,int row, int col) {
        String col_name;
        switch(col)  {
                case 1:
                    col_name = "PROPERTY"; break;
                case 2:
                    col_name = "TENANT"; break;
                case 3:
                    col_name = "TERM"; break;
                case 4:
                    col_name = "RENT"; break;
                case 5:
                    col_name = "SECURITY"; break;
                case 6:
                    col_name = "STATUS"; break;
                default:throw new IllegalStateException( "Table: " + leaseTable.getElementName() + " only got 6 columns at all!");
            }
        System.out.println(" Get row" + row + " of " + col_name + " column from table: " + leaseTable.getElementName());
        int _row = row -1;
        int _col = col -1;
        MyWebElement leaseTable_item =
                myWebelement("jQuerySelector:table tbody tr:eq("
                        + _row + ") td:eq(" + _col + ")", col_name + "_" + row);
        return leaseTable_item;
    }

    // method, according to the number to locator the view button(the number equals to the sequence of a certain status: pending, resolved,disputed,etc.)
    // also can easily use viewPendingLease_btn,viewPendingLease_btn,viewPendingLease_btn,etc. to locator view button
    public static void clickViewBtn_byNum(int num) {
        switch(num)  {
            case 1:
                leaseTable_viewBtn1.click(); break;
            case 2:
                leaseTable_viewBtn2.click(); break;
            case 3:
                leaseTable_viewBtn3.click(); break;
            case 4:
                leaseTable_viewBtn4.click(); break;
            case 5:
                leaseTable_viewBtn5.click(); break;
            default:throw new IllegalStateException( "Table only got 5 View button at all!");
        }
    }
    // method, used with clickViewBtn
    public static void clickFirstViewBtn_byLeaseStatus(String lease_status) {
        int i = 1;
        for (MyWebElement status: leaseTable_items_statusColumn) {
           if (status.getText().equals(lease_status))  {
               break;
           }
           i++;
        }
        clickViewBtn_byNum(i);
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AgentYourLeasesPage load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AgentYourLeasesPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        verifyPageTitle(pageTitle);
        agentLeases_title.verifyElementPresent();
        agentLeases_subTitle.verifyContainsIgnoreSpaces("- Resolve Your PENDING Security Deposit Refunds ");
    }

}
