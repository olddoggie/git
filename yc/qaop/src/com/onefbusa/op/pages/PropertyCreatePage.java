package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class PropertyCreatePage extends AgentDashboardPage {
    // web elements in this page
    public static MyWebElement createProperty_title =  myWebelement("jQuerySelector:h3:contains('CREATE PROPERTY')", "PropertyCreatePage_Title");
    public static MyWebElement page_linktrial =  myWebelement("cssSelector:ul.breadcrumb", "Page_LinkTrial");
    public static MyWebElement propertyInfo_title =  myWebelement("jQuerySelector:p.title:visible", "PropertyInfo_Title",0);
    public static MyWebElement addBuildings_title =  myWebelement("jQuerySelector:p.title:visible", "AddBuildings_Title",1);
    public static MyWebElement depositInfo_title =  myWebelement("jQuerySelector:p.title:visible", "DepositInfo_Title",2);

    public static MyWebElement saveProperties_btn = myWebelement("saveProperty", "SaveProperties_Button");

    public static MyWebElement ownedBy_label = myWebelement("jQuerySelector:label:contains('OWNED BY:*')", "OwnedBy*_Label");
    public static MyWebElement managedBy_label = myWebelement("jQuerySelector:label:contains('MANAGED BY:*')", "ManagedBy*_label");
    public static MyWebElement propertyName_label = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:*')", "PropertyName*_Label");
    public static MyWebElement propertyAddress1_label = myWebelement("jQuerySelector:label:contains('ADDRESS 1:*')", "PropertyAddress1*_Label");
    public static MyWebElement propertyAddress2_label = myWebelement("jQuerySelector:label:contains('ADDRESS 2:')", "PropertyAddress2_Label");
    public static MyWebElement propertyCity_label = myWebelement("jQuerySelector:label:contains('CITY:*')", "PropertyCity*_Label");
    public static MyWebElement propertyState_label = myWebelement("jQuerySelector:label:contains('STATE:*')", "PropertyState*_Label");
    public static MyWebElement propertyZip_label = myWebelement("jQuerySelector:label:contains('ZIP: *')", "PropertyZip*_Label");
    public static MyWebElement propertyTaxLot_label = myWebelement("jQuerySelector:label:contains('TAX LOT:')", "PropertyTAX-LOT_Label");

    public static MyWebElement bankName_label = myWebelement("jQuerySelector:label:contains('BANK NAME:')", "BankName_Label");
    public static MyWebElement interest_label = myWebelement("jQuerySelector:label:contains('INTEREST:')", "Interest_Label");
    public static MyWebElement adminFee_label = myWebelement("jQuerySelector:label:contains('ADMIN FEE:')", "adminFee_Label");
    public static MyWebElement paymentType_label = myWebelement("jQuerySelector:label:contains('PAYMENT TYPE:')", "PaymentType_Label");
    public static MyWebElement returnProcess_label = myWebelement("jQuerySelector:h5:contains('RETURNS PROCESS:')", "ReturnProcess_Label");

    public static MyWebElement ownedBy_textBox = myWebelement("inputOwnedBy","OwnedBy_TextBox");
    public static MyWebElement managedBy_textBox = myWebelement("inputManagedBy","ManagedBy_TextBox");
    public static MyWebElement selectOwnedManagedBy_selectTable = myWebelement("cssSelector:select[id=selectSource]","OwnedManagedBy_SelectTableBox");
    public static MyWebElement okOwnedManagedBy_btn = myWebelement("jQuerySelector:button:contains('OK')","OwnedManagedBy_OkButton");
    public static MyWebElement cancelOwnedManagedBy_btn = myWebelement("jQuerySelector:button:contains('CANCEL')","OwnedManagedBy_CancelButton");
    public static MyWebElement propertyName_textBox = myWebelement("propertyInfoForm.name", "PropertyName_TextBox");
    public static MyWebElement propertyAddress1_textBox = myWebelement("propertyInfoForm.address1", "PropertyAddress1_TextBox");
    public static MyWebElement propertyAddress2_textBox = myWebelement("propertyInfoForm.address2", "PropertyAddress2_TextBox");
    public static MyWebElement propertyCity_textBox = myWebelement("propertyInfoForm.city", "PropertyCity_TextBox");
    public static MyWebElement propertyState_selectBox = myWebelement("propertyInfoForm.state", "PropertyState_SelectBox");
    public static MyWebElement propertyZip_textBox = myWebelement("propertyInfoForm.zip", "PropertyZip_TextBox");
    public static MyWebElement propertyTaxLot_textBox = myWebelement("propertyInfoForm.taxLot", "PropertyTAX-LOT_TextBox");

    public static MyWebElement bankName_textBox = myWebelement("depositInfoForm.bankName", "PropertyBankName_TextBox");
    public static MyWebElement interest_selectBox = myWebelement("depositInfoForm.depositIntPayTypeId", "PropertyInterest_SelectBox");
    public static MyWebElement adminFee_selectBox = myWebelement("depositInfoForm.depositAdminFeeTypeId", "PropertyAdminFee_SelectBox");
    public static MyWebElement paymentType_singleParty_radioBox = myWebelement("cssSelector:label.radio input", "PropertyPropertyType_SinglePart_RadioBox1",0);
    public static MyWebElement paymentType_tenantPay_radioBox = myWebelement("cssSelector:label.radio input", "PropertyPropertyType_TenantPay_RadioBox2",1);
    public static MyWebElement returnProcess_textBox = myWebelement("depositInfoForm.returnPolicy", "ReturnProcess_TextBox");

    public static MyWebElement buildingName_label = myWebelement("jQuerySelector:label:contains('BUILDING NAME:'):visible", "BuildingName_Label");
    public static MyWebElement buildingType_label = myWebelement("jQuerySelector:label:contains('BUILDING TYPE:'):visible", "BuildingType_Label");

    public static MyWebElement lock_icon =  myWebelement("cssSelector:i.icon-lock", "Lock_Icon");
    public static MyWebElement plusAddBuildings_icon =  myWebelement("jQuerySelector:p.title i.icon-plus", "Plus_Icon",0);  // static plus icon before AddBuildings_PropertyCreatePage title
    public static MyWebElement addBuildings_iconBtn =  myWebelement("jQuerySelector:button#addBuilding", "AddBuilding_IconButton"); // click to add buildings
    public static MyWebElement editBuilding_iconBtn =  myWebelement("jQuerySelector:button.editBuilding i.icon-edit:visible", "EditBuilding_iconButton");
    public static MyWebElement saveBuilding_iconBtn =  myWebelement("jQuerySelector:button.saveBuilding i.icon-ok:visible", "SaveBuilding_iconButton");
    public static MyWebElement removeBuilding_iconBtn =  myWebelement("jQuerySelector:button.removeBuilding i.icon-remove:visible", "RemoveBuilding_iconButton");
    public static MyWebElement addFloorsUnitsForBuildings_Btn = myWebelement("jQuerySelector:div.accordion-heading a:contains('ADD FLOORS & UNITS for BUILDINGS')", "AddFloorsUnitsForBuildings_DropDownButton");
    public static MyWebElement arrowDownForBuildings_Icon =  myWebelement("jQuerySelector:div.accordion-heading i.icon-arrow-down", "ArrowDown_Icon");

    public static MyWebElement buildingName_textBox = myWebelement("cssSelector:input[name^=buildingBlocks][name$=name]", "BuildingName_TextBox");
    public static MyWebElement buildingType_selectBox = myWebelement("cssSelector:select[name^=buildingBlocks][name$=type]", "BuildingType_SelectBox");
    public static MyWebElement buildingTab_link1 = myWebelement("jQuerySelector:ul.buildingTabs a:contains('1')", "BuildingTab_Link_1st");

    public static MyWebElement building_label = myWebelement("jQuerySelector:label:contains('BUILDING:')", "Building_Label");
    public static MyWebElement addFloors_label = myWebelement("jQuerySelector:label:contains('ADD FLOORS:')", "AddFloors_Label");
    public static MyWebElement addUnits_label = myWebelement("jQuerySelector:label:contains('ADD UNITS: *')", "AddUnits_Label");
    public static MyWebElement addUnit_btn = myWebelement("jQuerySelector:button:contains('ADD')", "AddUnits_Button");
    public static MyWebElement selectBuilding_selectBox = myWebelement("selBuilding", "SelectBuilding_SelectBox");
    public static MyWebElement selectFloor_selectBox = myWebelement("selFloor", "SelectFloor_SelectBox");
    public static MyWebElement inputUnits_textBox = myWebelement("cssSelector:input[id=inputUnits][placeholder='A, B, C, D, E, F, G, H, I']", "InputUnit_textBox");
    public static MyWebElement buildingNameInUnitBlocks_title1 = getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[0];
    public static MyWebElement floorNameInUnitBlocks_title1= getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[1];
    public static MyWebElement unitNameInUnitBlocks_alertBox1 = getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[2];
    public static MyWebElement removeBuildingFromUnitBlocks_btn1= getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[3];
    public static MyWebElement removeFloorFromUnitBlocks_btn1= getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[4];
    public static MyWebElement removeUnitFromUnitBlocks_btn1= getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(1,1,1)[5];

    public static MyWebElement buildingName_noInput_errorLabel = myWebelement("jQuerySelector:label.error-message:contains('BUILDING NAME is required.')","BuildingName_NoInput_ErrorMessage");
    public static MyWebElement buildingType_noInput_errorLabel = myWebelement("jQuerySelector:label.error-message:contains('BUILDING TYPE is required.')","BuildingType_NoSelect_ErrorMessage");
//    public static MyWebElement officePhone_errorLabel = myWebelement("cssSelector:label[for=officePhone]","OfficePhone_ErrorMessage");
//    public static MyWebElement fein_errorLabel = myWebelement("cssSelector:label[for=fein]","FEIN_ErrorMessage");
//    public static MyWebElement address1_errorLabel = myWebelement("cssSelector:label[for=address1]","Address1_ErrorMessage");
//    public static MyWebElement address2_errorLabel = myWebelement("cssSelector:label[for=address2]","Address2_ErrorMessage");
//    public static MyWebElement city_errorLabel = myWebelement("cssSelector:label[for=city]","City_ErrorMessage");
//    public static MyWebElement zip5_errorLabel = myWebelement("cssSelector:label[for=zip5]","Zip5_ErrorMessage");
//    public static MyWebElement zip4_errorLabel = myWebelement("cssSelector:label[for=zip4]","Zip4_ErrorMessage");
//    public static MyWebElement jobTitle_errorLabel = myWebelement("cssSelector:label[for=title]","JobTitle_ErrorMessage");
//    public static MyWebElement firstName_errorLabel = myWebelement("cssSelector:label[for=firstName]","FirstName_ErrorMessage");
//    public static MyWebElement lastName_errorLabel = myWebelement("cssSelector:label[for=lastName]","LastName_ErrorMessage");
//    public static MyWebElement email_errorLabel = myWebelement("cssSelector:label[for=email]","EmployeeEmail_ErrorMessage");
//    public static MyWebElement mainPhone_errorLabel = myWebelement("cssSelector:label[for=mainPhone]","mainPhone_ErrorMessage");
//    public static MyWebElement cellPhone_errorLabel = myWebelement("cssSelector:label[for=cellPhone]","cellPhone_ErrorMessage");
//    public static MyWebElement password_errorLabel = myWebelement("cssSelector:label[for=password]","Password_ErrorMessage");
//    public static MyWebElement confirmPassword_errorLabel = myWebelement("cssSelector:label[for=confirmPassword]","ConfirmPassword_ErrorMessage");

    // page title
    public static String pageTitle = "CREATE PROPERTY";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  the original displayed items listed in the page
    public static MyWebElement[] originalDisplayedElements = {createProperty_title, page_linktrial, home_icon, propertyInfo_title, ownedBy_label, managedBy_label,
            propertyName_label, propertyAddress1_label, propertyAddress2_label,propertyCity_label, propertyState_label, propertyZip_label, propertyTaxLot_label,
            ownedBy_textBox, managedBy_textBox, propertyName_textBox, propertyAddress1_textBox, propertyAddress2_textBox, propertyCity_textBox,propertyZip_textBox,
            propertyState_selectBox ,propertyTaxLot_textBox, lock_icon, depositInfo_title, bankName_label, interest_label, adminFee_label, paymentType_label,paymentType_tenantPay_radioBox,
            paymentType_singleParty_radioBox,  returnProcess_label, bankName_textBox,interest_selectBox,adminFee_selectBox, returnProcess_textBox, plusAddBuildings_icon, addBuildings_title,
            addBuildings_iconBtn, saveProperties_btn};
        //  all the label part, add/edit/remove buttons in add buildings table
    public  static MyWebElement[] addBuildingsTableElements = {plusAddBuildings_icon, addBuildings_title, addBuildings_iconBtn, buildingTab_link1,
            buildingName_label, buildingName_textBox, buildingType_label, buildingType_selectBox, saveBuilding_iconBtn, removeBuilding_iconBtn};
        //  all the elements listed below arrow-down, after user add building, but before add floor and units
    public  static MyWebElement[] addUnitsFloorsForBuildingsTableElements_beforeAddFloorUnits = {building_label, addFloors_label, addUnits_label, selectBuilding_selectBox,
            selectFloor_selectBox, inputUnits_textBox, addFloorsUnitsForBuildings_Btn, buildingNameInUnitBlocks_title1, removeBuildingFromUnitBlocks_btn1};


    // methods, used to get the building,floor,unit, etc. elements by specifying indexes
    // get 1.building name, 2.floor name, 3.unit alert box, 4.remove building name, 5.remove floor name 6.remove unit from UnitsFloorsForBuildingsTable
    public static MyWebElement[] getBuildingName_Floor_UnitFromUnitsFloorsForBuildingsTable(int building,int floor,int unit) {
        //System.out.println(" Get floor: #" + floor  + "unit: #" + unit + " from addUnitsFloorsForBuildingsTable" + building);
        int _building = building -1;
        int _floor = floor -1;
        int _unit = unit-1;
        MyWebElement buildingName_title = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") p.title","BuildingNameInUnitBlocks_Title_" + building);
        MyWebElement floor_title = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.form-inline:visible span:visible:eq(" + _floor + ")","FloorNameInUnitBlocks_Title_" + floor);
        MyWebElement unit_alertBox = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.form-inline:visible:eq(" + _floor + ") ~ div:visible:eq(" + _unit + ")","UnitNameInUnitBlocks_AlertBox_" + unit);
        MyWebElement remove_building = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq(" + _building +
                ") button.close:visible","RemoveBuildingNameFromUnitBlocks_Button_" + building);
        MyWebElement remove_floor = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.form-inline:visible span:visible:eq(" + _floor + ") + button.close","RemoveFloorFromUnitBlocks_Button_" + floor);
        MyWebElement remove_unit = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.form-inline:visible:eq(" + _floor + ") ~ div:visible:eq(" + _unit + ") button.close","RemoveUnitFromUnitBlocks_Button_" + unit);
        MyWebElement[] BuildingName_Floor_Unit = {buildingName_title, floor_title,unit_alertBox,remove_building, remove_floor, remove_unit};
        return BuildingName_Floor_Unit;
    }

    // methods, used to get building,floor,unit elements by names
    // most of the times you need fetch the building, floor, unit, including the remove btn by their names.
    // 1.building name, 2.floor name, 3.unit alert box, 4.remove building name, 5.remove floor name 6.remove unit
    public static MyWebElement[] getBuilding_Floor_Unit_byName(String buildingName, String buildingType, String floor, String unit){
        String building =  buildingName + " - " + buildingType;
        MyWebElement building_name = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible p.title:contains('" + building + "')","BuildingNameInUnitBlocks_Title_" + building);
        MyWebElement floor_name = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains('" + building + "') div.form-inline:visible span:contains('FLOOR " + floor + "')","FloorNameInUnitBlocks_Title_" + floor);
        MyWebElement unit_alertBox = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains('" + building + "') div.form-inline:visible:contains('FLOOR "
                + floor + "') ~ div.alert:visible:contains('" + unit + "')","UnitNameInUnitBlocks_AlertBox_" + unit);
        MyWebElement remove_building = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains('" + building + "') button.close:visible","RemoveBuildingNameFromUnitBlocks_Button_" + building);
        MyWebElement remove_floor = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains('"
                + building + "') div.form-inline:visible:contains('FLOOR " + floor + "') button.close","RemoveFloorFromUnitBlocks_Button_" + floor);
        MyWebElement remove_unit = myWebelement("jQuerySelector:div#addUnitsBlock:contains(" + building + ") div.form-inline:visible:contains('FLOOR "
                + floor + "') ~ div.alert:visible:contains('" + unit + "') button.close","RemoveUnitFromUnitBlocks_Button_" + unit);
        MyWebElement[] BuildingName_Floor_Unit = {building_name, floor_name,unit_alertBox, remove_building, remove_floor, remove_unit};
        return BuildingName_Floor_Unit;
    }

    // methods, used to input property and deposit info in property create page, with no building added
    public static void Input_Basic_PropertyInfo(String[] str) throws Exception {
        ownedBy_textBox.click();
        Thread.sleep(500);    // wait for select box response
        selectOwnedManagedBy_selectTable.selectByVisibleText(str[0]);
        Thread.sleep(500);
        okOwnedManagedBy_btn.click();
        Thread.sleep(500);
        managedBy_textBox.click();
        Thread.sleep(500);    // wait for select box response
        selectOwnedManagedBy_selectTable.selectByVisibleText(str[1]);
        Thread.sleep(500);
        okOwnedManagedBy_btn.click();
        propertyName_textBox.input(str[2]);
        propertyAddress1_textBox.input(str[3]);
        propertyAddress2_textBox.input(str[4]);
        propertyCity_textBox.input(str[5]);
        propertyState_selectBox.selectByVisibleText(str[6]);
        propertyZip_textBox.input(str[7]);
        propertyTaxLot_textBox.input(str[8]);
        bankName_textBox.input(str[9]);
        interest_selectBox.selectByVisibleText(str[10]);
        adminFee_selectBox.selectByVisibleText(str[11]);
        if (str[12].equals("Tenants pay directly")) {
            paymentType_tenantPay_radioBox.click();
        }
        returnProcess_textBox.input(str[13]);
    }

    //methods, used to create basic property by inputing property info and deposit info, and click save button
    public static void Create_Basic_Property(String[] str) throws Exception {
        Input_Basic_PropertyInfo(str);
        PropertyCreatePage.saveProperties_btn.click();
    }

    // methods, used to create a full property with property info, deposit info, building name, building type, floor, unit added
    public static void Create_Full_Property(String[] basic,String... str) throws Exception {
        Input_Basic_PropertyInfo(basic);
        PropertyCreatePage.addBuildings_iconBtn.click();
        PropertyCreatePage.buildingName_textBox.input(str[0]);
        PropertyCreatePage.buildingType_selectBox.selectByVisibleText(str[1]);
        PropertyCreatePage.saveBuilding_iconBtn.click();
        Thread.sleep(500);
        PropertyCreatePage.addFloorsUnitsForBuildings_Btn.click();
        PropertyCreatePage.selectFloor_selectBox.selectByVisibleText(str[2]);
        PropertyCreatePage.inputUnits_textBox.input(str[3]);
        PropertyCreatePage.addUnit_btn.click();
        Thread.sleep(500);
        PropertyCreatePage.saveProperties_btn.click();
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static PropertyCreatePage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new PropertyCreatePage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        verifyPageTitle(pageTitle);
        createProperty_title.verifyElementPresent();
    }

}
