package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class RefundSecurityDepositPopUpBox extends LeaseDetailPage {
    // web elements in this page
    public static MyWebElement enterRefundInfo_popUpBoxTitle = myWebelement("cssSelector:h4#myModalLabel","EnterRefundInfo_PopUpBoxTitle");

    public static MyWebElement closeEnterRefundInfoPopUpBox_btn = myWebelement("cssSelector:button.close","CloseEnterRefundInfoPopUpBox_Button");
    public static MyWebElement saveRefundSecurityDepositInfo_btn = myWebelement("jQuerySelector:button[type=submit]:contains('SAVE')","SaveRefundSecurityDepositInfo_Button");
    public static MyWebElement cancelRefundSecurityDepositInfo_btn = myWebelement("reset","CancelRefundSecurityDepositInfo_Button");

    public static MyWebElement securityDepositRefundAmount_label = myWebelement("jQuerySelector:div.row-fluid:contains('SECURITY DEPOSIT REFUND AMOUNT: *')","SecurityDepositRefundAmount*OutOf$500_Label");
    public static MyWebElement securityDepositRefundAmount_textBox = myWebelement("refundAmount","SecurityRefundAmountTextBox");
    public static MyWebElement securityRefundComment_label = myWebelement("jQuerySelector:span:contains('COMMENT: *')","SecurityRefundComment*_Label");
    public static MyWebElement securityRefundComment_selectBox = myWebelement("commentTypeId","SecurityRefundComment_SelectBox");

    public static MyWebElement amountHeld_title = myWebelement("cssSelector:div.span5","AmountHeld_Title",0);
    public static MyWebElement amountHeld_row1 = myWebelement("cssSelector:div.span5","AmountHeld_Title",1,"heldAmount0.amountHeld");
    public static MyWebElement amountHeld_row2 = myWebelement("cssSelector:div.span5","AmountHeld_Title",2,"heldAmount1.amountHeld");
    public static MyWebElement amountHeld_row3 = myWebelement("cssSelector:div.span5","AmountHeld_Title",3,"heldAmount2.amountHeld");

    public static MyWebElement refundReason_title = myWebelement("cssSelector:div.span7","RefundReason_Title",0);
    public static MyWebElement refundReason_row1 = myWebelement("cssSelector:div.span7","RefundReason_Title",1,"heldAmount0.heldReasonTypeId");
    public static MyWebElement refundReason_row2 = myWebelement("cssSelector:div.span7","RefundReason_Title",2,"heldAmount1.heldReasonTypeId");
    public static MyWebElement refundReason_row3 = myWebelement("cssSelector:div.span7","RefundReason_Title",3,"heldAmount2.heldReasonTypeId");

    public static MyWebElement dateReturned_label = myWebelement("jQuerySelector:span:contains('DATE RETURNED: *')","DateReturned*_Label");
    public static MyWebElement dateReturnedDatePicker_textBox = myWebelement("refundDate","DateReturnedDatePicker_TextBox");
    public static MyWebElement dateReturnedDatePicker_img = myWebelement("jQuerySelector:input#refundDate + img","DateReturnedDatePicker_Image");

    public static MyWebElement refundAmount_$ = myWebelement("cssSelector:span.add-on","TermNewRentAmount_$1",0);
    public static MyWebElement amountHeld1_$ = myWebelement("cssSelector:span.add-on","TermNewRentAmount_$2",1);
    public static MyWebElement amountHeld2_$ = myWebelement("cssSelector:span.add-on","TermNewRentAmount_$3",2);
    public static MyWebElement amountHeld3_$ = myWebelement("cssSelector:span.add-on","TermNewRentAmount_$4",3);

    // methods, used to make the pending lease refunded
    public static void RefundLease(String...str) throws Exception {
        securityDepositRefundAmount_textBox.input(str[0]);
        securityRefundComment_selectBox.selectByVisibleText(str[1]);
        amountHeld_row1.input(str[2]);
        amountHeld_row2.input(str[3]);
        amountHeld_row3.input(str[4]);
        refundReason_row1.selectByVisibleText(str[5]);
        refundReason_row2.selectByVisibleText(str[6]);
        refundReason_row3.selectByVisibleText(str[7]);
        if (str[8].equalsIgnoreCase("TODAY")) {
            dateReturnedDatePicker_img.click();
            selectToday_tableBox.click();
        }
        Thread.sleep(500);  // wait after pick up date from data picker
        saveRefundSecurityDepositInfo_btn.click();
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static RefundSecurityDepositPopUpBox load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">RefundSecurityDeposit</a> PopUpBox: ");
        waitUntilPageReady();
        return new RefundSecurityDepositPopUpBox();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        enterRefundInfo_popUpBoxTitle.verifyContains("Please enter refund information:", "* Denotes a required field");
    }

}
