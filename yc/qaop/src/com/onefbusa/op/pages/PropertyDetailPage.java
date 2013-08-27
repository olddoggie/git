package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class PropertyDetailPage extends PropertyCreatePage {
    // web elements in this page
    public static MyWebElement propertyDetail_title =  myWebelement("jQuerySelector:h3:contains('PROPERTY DETAIL')", "PropertyDetailPage_Title");
    public static MyWebElement propertyDetail_subTitle =  myWebelement("jQuerySelector:h3:contains('PROPERTY DETAIL') + p", "PropertyDetailPage_subTitle");

    public static MyWebElement addLease_image =  myWebelement("cssSelector:img[src*=btn_addLease\\.jpg]", "AddLease_Image");
    public static MyWebElement editProperty_btn =  myWebelement("editPropertyBtn", "EditProperty_Button");
    public static MyWebElement editDeposit_btn =  myWebelement("editDepositBtn", "EditDeposit_Button");
    public static MyWebElement saveProperty_btn =  myWebelement("savePropertyBtn", "SaveProperty_Button");
    public static MyWebElement saveDeposit_btn =  myWebelement("saveDepositBtn", "SaveDeposit_Button");

    public static MyWebElement propertyInfo_title =  myWebelement("jQuerySelector:a:contains('PROPERTY INFO'):visible", "PropertyInfo_Title",0);
    public static MyWebElement propertyInfo_afterEdit_title =  myWebelement("jQuerySelector:a:contains('PROPERTY INFO'):visible", "PropertyInfo_AfterEdit_Title",1);
    public static MyWebElement ownedBy_label_before_edit = myWebelement("jQuerySelector:label:contains('OWNED BY:')", "OwnedBy_Label");
    public static MyWebElement managedBy_label_before_edit = myWebelement("jQuerySelector:label:contains('MANAGED BY:')", "ManagedBy_label");
    public static MyWebElement propertyName_label_before_edit = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:')", "PropertyName_Label");
    public static MyWebElement propertyAddress1_label_before_edit = myWebelement("jQuerySelector:label:contains('ADDRESS 1:')", "PropertyAddress1_Label");
    public static MyWebElement propertyCity_label_before_edit = myWebelement("jQuerySelector:label:contains('CITY:')", "PropertyCity_Label");
    public static MyWebElement propertyStateZip_label_before_edit = myWebelement("jQuerySelector:label[for=stateZipValue]", "PropertyState/Zip_Label");

    public static MyWebElement ownedBy_displayValue = myWebelement("jQuerySelector:label:contains('OWNED BY:') + div", "OwnedBy_DisplayValue");
    public static MyWebElement managedBy_displayValue = myWebelement("jQuerySelector:label:contains('MANAGED BY:') + div", "ManagedBy_displayValue");
    public static MyWebElement propertyName_displayValue = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:') + div", "PropertyName_DisplayValue");
    public static MyWebElement propertyAddress1_displayValue = myWebelement("jQuerySelector:label:contains('ADDRESS 1:') + div", "PropertyAddress1_DisplayValue");
    public static MyWebElement propertyAddress2_displayValue = myWebelement("jQuerySelector:label:contains('ADDRESS 2:') + div", "PropertyAddress2_DisplayValue");
    public static MyWebElement propertyCity_displayValue = myWebelement("jQuerySelector:label:contains('CITY:') + div", "PropertyCity_DisplayValue");
    public static MyWebElement propertyStateZip_displayValue = myWebelement("jQuerySelector:label[for=stateZipValue] + div", "PropertyState_DisplayValue");
    public static MyWebElement propertyTaxLot_displayValue = myWebelement("jQuerySelector:label:contains('TAX LOT:')+ div", "PropertyTAX-LOT_DisplayValue");

    public static MyWebElement ownedBy_textBox = myWebelement("jQuerySelector:label:contains('OWNED BY:*') + div input","OwnedBy_TextBox");
    public static MyWebElement managedBy_textBox = myWebelement("jQuerySelector:label:contains('MANAGED BY:*') + div input","ManagedBy_TextBox");
    public static MyWebElement propertyName_textBox = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:*') + div input", "PropertyName_TextBox");
    public static MyWebElement propertyAddress1_textBox = myWebelement("jQuerySelector:label:contains('ADDRESS 1:*') + div input", "PropertyAddress1_TextBox");
    public static MyWebElement propertyAddress2_textBox = myWebelement("jQuerySelector:label:contains('ADDRESS 2:') + div input", "PropertyAddress2_TextBox");
    public static MyWebElement propertyCity_textBox = myWebelement("jQuerySelector:label:contains('CITY:*') + div input", "PropertyCity_TextBox");
    public static MyWebElement propertyState_selectBox = myWebelement("jQuerySelector:label[for=zipCodeValue] + div select#state", "PropertyState_SelectBox");
    public static MyWebElement propertyZip_textBox = myWebelement("jQuerySelector:label[for=zipCodeValue] + div input#zip", "PropertyZip_TextBox");
    public static MyWebElement propertyTaxLot_textBox = myWebelement("jQuerySelector:label:contains('TAX LOT:') + div input", "PropertyTAX-LOT_TextBox");

    public static MyWebElement depositInfo_title =  myWebelement("jQuerySelector:a:contains('DEPOSIT INFO'):visible", "DepositInfo_Title",0);
    public static MyWebElement depositInfo_afterEdit_title = depositInfo_title ;
    public static MyWebElement bankName_label = myWebelement("jQuerySelector:label[for=inputBankName]", "BankName_Label");
    public static MyWebElement interest_label = myWebelement("jQuerySelector:label[for=inputInterest]", "Interest_Label");
    public static MyWebElement adminFee_label = myWebelement("jQuerySelector:label[for=inputAdminFee]", "adminFee_Label");
    public static MyWebElement paymentType_label = myWebelement("jQuerySelector:label[for=inputPaymentType]", "PaymentType_Label");
    public static MyWebElement returnProcess_label = myWebelement("jQuerySelector:h5:contains('RETURNS PROCESS:')", "ReturnProcess_Label");

    public static MyWebElement bankName_displayValue = myWebelement("jQuerySelector:label[for=inputBankName] + div", "BankName_DisplayValue");
    public static MyWebElement interest_displayValue = myWebelement("jQuerySelector:label[for=inputInterest] + div", "Interest_DisplayValue");
    public static MyWebElement adminFee_displayValue = myWebelement("jQuerySelector:label[for=inputAdminFee] + div", "AdminFee_DisplayValue");
    public static MyWebElement paymentType_displayValue = myWebelement("jQuerySelector:label[for=inputPaymentType] + div", "PaymentType_DisplayValue");
    public static MyWebElement returnProcess_displayValue = myWebelement("jQuerySelector:h5:contains('RETURNS PROCESS:') + p", "ReturnProcess_DisplayValue");

    public static MyWebElement bankName_textBox = myWebelement("jQuerySelector:label:contains('Bank Name:') + div input", "PropertyBankName_TextBox");
    public static MyWebElement interest_selectBox = myWebelement("jQuerySelector:label:contains('Interest:') + div select", "PropertyInterest_SelectBox");
    public static MyWebElement adminFee_selectBox = myWebelement("jQuerySelector:label:contains('Admin Fee:') + div select", "PropertyAdminFee_SelectBox");
    public static MyWebElement returnProcess_textBox = myWebelement("jQuerySelector:label:contains('RETURNS PROCESS:') + div textarea", "ReturnProcess_TextBox");

    public static MyWebElement addBuildings_title =  myWebelement("jQuerySelector:p.title:visible", "AddBuildings_Title");
    public static MyWebElement buildingTab_link1 = myWebelement("jQuerySelector:ul#buildingTabs a:contains('1')", "BuildingTab_Link_1st");
    public static MyWebElement editBuilding_iconBtn =  myWebelement("jQuerySelector:button[name=editBuildingTabBtn]:visible", "EditBuilding_iconButton");
    public static MyWebElement removeBuilding_iconBtn =  myWebelement("jQuerySelector:button[name=removeBuildingTabBtn]:visible", "SaveBuilding_iconButton");
    public static MyWebElement saveBuilding_iconBtn =  myWebelement("jQuerySelector:button[name=saveBuildingTabBtn]:visible", "SaveBuilding_iconButton");

    public static MyWebElement buildingName_textBox = myWebelement("jQuerySelector:input[name=inputBuildingName]:visible", "BuildingName_TextBox");
    public static MyWebElement buildingType_selectBox = myWebelement("jQuerySelector:select[name=selectBuildingType]:visible", "BuildingType_SelectBox");
    public static MyWebElement buildingName_displayValue = myWebelement("jQuerySelector:label:contains('BUILDING NAME:') + div", "BuildingName_DisplayValue");
    public static MyWebElement buildingType_displayValue = myWebelement("jQuerySelector:label:contains('BUILDING TYPE:') + div", "BuildingType_DisplayValue");

    public static MyWebElement unoccupied_label = myWebelement("jQuerySelector:span.label-success:contains('UNOCCUPIED')","Unoccupied_Label");
    public static MyWebElement occupied_label = myWebelement("jQuerySelector:span.label-important:contains('OCCUPIED')","Occupied_Label");
    public static MyWebElement leaseEndingSoon_label = myWebelement("jQuerySelector:span.label-warning:contains('LEASE ENDING SOON')","LeaseEndingSoon_Label");
    public static MyWebElement filterBuildingName_selectBox = myWebelement("jQuerySelector:span:contains('FILTER:') + select","FilterBuildingName_SelectBox");
    public static MyWebElement filterFloors_selectBox = myWebelement("jQuerySelector:span:contains('FILTER:') + select + select","FilterBuildingName_SelectBox");
    public static MyWebElement filterUnits_selectBox = myWebelement("jQuerySelector:span:contains('FILTER:') + select + select + select","FilterFloors_SelectBox");
    public static MyWebElement filterView_selectBox = myWebelement("jQuerySelector:span:contains('FILTER:') + select + select + select + span select","FilterViewType_SelectBox");
    public static MyWebElement arrowDownProperty_icon =  myWebelement("jQuerySelector:i.icon-arrow-down:visible:eq(0)", "ArrowDown_Icon_1");
    public static MyWebElement arrowDownDeposit_icon =  myWebelement("jQuerySelector:i.icon-arrow-down:visible:eq(1)", "ArrowDown_Icon_2");

    public static MyWebElement addFloors_label1 = myWebelement("jQuerySelector:label:contains('ADD FLOORS:*')","AddFloors*_Label_1st",0);
    public static MyWebElement selectFloor_selectBox1 = myWebelement("jQuerySelector:select[name=floor]:visible", "SelectFloor_SelectBox_1st",0);
    public static MyWebElement addUnits_label1 = myWebelement("jQuerySelector:label:contains('ADD UNITS:*')","AddUnits*_Label_1st",0);
    public static MyWebElement inputUnits_textBox1 = myWebelement("cssSelector:input[name=addUnitsName][placeholder='A, B, C, D, E, F, G, H, I']", "InputUnit_textBox_1st",0);

    public static MyWebElement duplicateBuilding_btn1 = myWebelement("cssSelector:img[name=copyBuildingIcon]","DuplicateBuilding_Button_1st",0);
    public static MyWebElement duplicateBuilding_btn1_afterAddMultiBuilding = myWebelement("cssSelector:img#copyBuildingIcon","DuplicateBuilding_Button_AfterAddMultiBuilding _1st",0);
    public static MyWebElement duplicateFloor_btn1 = myWebelement("cssSelector:img#copyFloorIcon","DuplicateFloor_Button_1st",0);
    public static MyWebElement addUnit_btn1 = myWebelement("jQuerySelector:button[name=addUnitsBtn]", "AddUnits_Button_1st",0);

    public static MyWebElement buildingNameInUnitBlocks_title1 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1,1,1)[0];
    // second building, not in CAT1 scope
    public static MyWebElement buildingNameInUnitBlocks_title2 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(2, 1, 1)[1];
    public static MyWebElement floorNameInUnitBlocks_title1 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 1, 1)[1];
    public static MyWebElement floorNameInUnitBlocks_title2 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 2, 1)[1];
    public static MyWebElement removeBuildingFromUnitBlocks_btn1 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 1, 1)[2];
    public static MyWebElement removeBuildingFromUnitBlocks_btn2 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(2, 1, 1)[2];
    public static MyWebElement removeFloorFromUnitBlocks_btn1 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 1, 1)[3];
    public static MyWebElement removeFloorFromUnitBlocks_btn2 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 2, 1)[3];
    // fetch 2 unit lists from different floors
    public  static MyWebElement unitNameInUnitBlocks_list1 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1, 1, 1)[4];
    public  static MyWebElement unitNameInUnitBlocks_list2 = getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(1,2,1)[4];

    // fetch the column in each unit list
    public static MyWebElement unitNameInUnitBlocks_title1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,1);
    public static MyWebElement unitNameInUnitBlocks_status1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,2);
    public static MyWebElement unitNameInUnitBlocks_date1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,3);
    public static MyWebElement unitNameInUnitBlocks_rent1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,4);
    public static MyWebElement addLease_btn1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,5);
    public static MyWebElement viewLease_btn1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,5,"view");
    public static MyWebElement removeUnitFromUnitBlocks_btn1= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,1,1,6);
    public static MyWebElement unitNameInUnitBlocks_title2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,1);
    public static MyWebElement unitNameInUnitBlocks_status2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,2);
    public static MyWebElement unitNameInUnitBlocks_date2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,3);
    public static MyWebElement unitNameInUnitBlocks_rent2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,4);
    public static MyWebElement addLease_btn2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,5);
    public static MyWebElement viewLease_btn2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,5,"view");
    public static MyWebElement removeUnitFromUnitBlocks_btn2= getUnitListItem_FromUnitsFloorsForBuildingsTable(1,2,1,6);

    public static MyWebElement deleteConfirmPopUpBox_title = myWebelement("jQuerySelector:div#myModal div.modal-header h4","DeleteConfirmPopUpBox_Title");
    public static MyWebElement deleteConfirmPopUpBox_closeBtn = myWebelement("jQuerySelector:div#myModal div.modal-header button","DeleteConfirmPopUpBox_CloseButton");
    public static MyWebElement deleteConfirmPopUpBox_content = myWebelement("jQuerySelector:div#myModal div.modal-body ","DeleteConfirmPopUpBox_ContentMessage");
    public static MyWebElement deleteConfirmPopUpBox_no = myWebelement("jQuerySelector:div#myModal div.modal-footer button:contains('No')","DeleteConfirmPopUpBox_NoButton");
    public static MyWebElement deleteConfirmPopUpBox_yes = myWebelement("jQuerySelector:div#myModal div.modal-footer button:contains('Yes')","DeleteConfirmPopUpBox_YesButton");

    // page title
    public static String pageTitle = "PROPERTY DETAIL";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  all the label part in property info table in property detail page before click edit button
    public static MyWebElement [] propertyInfoTableElements_before_edit = {home_icon, propertyInfo_title, ownedBy_label_before_edit, managedBy_label_before_edit,
            propertyName_label_before_edit, propertyAddress1_label_before_edit, propertyAddress2_label,propertyCity_label_before_edit, propertyStateZip_label_before_edit,
            propertyTaxLot_label, arrowDownProperty_icon, editProperty_btn};
        //  all the label part in deposit info table in property detail page before click edit button
    public static MyWebElement [] depositInfoTableElements = {lock_icon, depositInfo_title,arrowDownDeposit_icon, bankName_label, interest_label, adminFee_label,
            paymentType_label, returnProcess_label, editDeposit_btn};
        //  all the label part, edit/remove/save button in add building table in property detail page
    public  static MyWebElement[] addBuildingsTableElements = {plusAddBuildings_icon, addBuildings_title, addBuildings_iconBtn, buildingTab_link1,
            buildingName_label, buildingType_label, editBuilding_iconBtn, removeBuilding_iconBtn};
        //  all the building, floor, unit(unoccupied) elements of building1,floor1,unit1 block in property detail page
    public  static MyWebElement[] addUnitsFloorsForBuildingsTable1_Elements = {filterBuildingName_selectBox, filterFloors_selectBox,filterUnits_selectBox, filterView_selectBox,
            unoccupied_label, occupied_label, leaseEndingSoon_label, buildingNameInUnitBlocks_title1, duplicateBuilding_btn1, addFloors_label1, selectFloor_selectBox1,
            addUnits_label1, inputUnits_textBox1, addUnit_btn1, removeBuildingFromUnitBlocks_btn1, floorNameInUnitBlocks_title1, duplicateFloor_btn1, removeFloorFromUnitBlocks_btn1,
            unitNameInUnitBlocks_list1, addLease_btn1, removeUnitFromUnitBlocks_btn1};
        //  all the building, floor, unit(occupied) elements of building1,floor1,unit1 block in property detail page
    public  static MyWebElement[] addUnitsFloorsForBuildingsTable1_Elements_afterUnitOccupied = {filterBuildingName_selectBox, filterFloors_selectBox,filterUnits_selectBox, filterView_selectBox,
            unoccupied_label, occupied_label, leaseEndingSoon_label, buildingNameInUnitBlocks_title1, duplicateBuilding_btn1, addFloors_label1, selectFloor_selectBox1,
            addUnits_label1, inputUnits_textBox1, addUnit_btn1, removeBuildingFromUnitBlocks_btn1,floorNameInUnitBlocks_title1, duplicateFloor_btn1, removeFloorFromUnitBlocks_btn1,
            unitNameInUnitBlocks_list1, removeUnitFromUnitBlocks_btn1};
        //  the elements listed in delete confirm pop up box
    public static MyWebElement[] deleteConfirmElements = { deleteConfirmPopUpBox_title, deleteConfirmPopUpBox_content,deleteConfirmPopUpBox_closeBtn, deleteConfirmPopUpBox_yes, deleteConfirmPopUpBox_no};
        //  all the display values displayed in property detail page's property info table, deposit info table, add building table, and building_floor_unit block
    public static MyWebElement[] propertyDetails_displayValues = {ownedBy_displayValue, managedBy_displayValue, propertyName_displayValue, propertyAddress1_displayValue,
            propertyAddress2_displayValue, propertyCity_displayValue, propertyStateZip_displayValue, propertyTaxLot_displayValue, bankName_displayValue, interest_displayValue,
            adminFee_displayValue, paymentType_displayValue, returnProcess_displayValue, buildingName_displayValue, buildingType_displayValue,
            buildingNameInUnitBlocks_title1, floorNameInUnitBlocks_title1,unitNameInUnitBlocks_title1};
        //  except for building_floor_unit blocks should list building1,floor2,unit2's display values, others same as propertyDetails_displayValues
    public static MyWebElement[] propertyDetails_displayValues2 = {ownedBy_displayValue, managedBy_displayValue, propertyName_displayValue, propertyAddress1_displayValue,
            propertyAddress2_displayValue, propertyCity_displayValue, propertyStateZip_displayValue, propertyTaxLot_displayValue, bankName_displayValue, interest_displayValue,
            adminFee_displayValue, paymentType_displayValue, returnProcess_displayValue, buildingName_displayValue, buildingType_displayValue,
            buildingNameInUnitBlocks_title1, floorNameInUnitBlocks_title2,unitNameInUnitBlocks_title2};
        //  all the text boxes,select boxes,etc. listed in property detail page's property info table after click edit button
    public static MyWebElement[] propertyInfoTable_editableObjects = {ownedBy_textBox, managedBy_textBox, propertyName_textBox, propertyAddress1_textBox, propertyAddress2_textBox,
            propertyCity_textBox, propertyState_selectBox, propertyZip_textBox, propertyTaxLot_textBox};
        //  all the text boxes,select boxes,radio buttons,etc. listed in property detail page's deposit info table after click edit button
    public static MyWebElement[] depositInfoTable_editableObjects = {bankName_textBox, interest_selectBox, adminFee_selectBox, paymentType_singleParty_radioBox,
            paymentType_singleParty_radioBox, returnProcess_textBox};

    // methods, used to get building,floor,unit elements by indexes
    // get 1.building name, 2.floor name, 3.remove building name, 4.remove floor name 5.unit list table from UnitsFloorsForBuildingsTable
    public static MyWebElement[] getBuildingName_Floor_UnitListFromUnitsFloorsForBuildingsTable(int building,int floor,int unit) {
        //System.out.println(" Get floor: #" + floor  + " from addUnitsFloorsForBuildingsTable" + building);
        int _building = building -1;
        int _floor = floor -1;
        int _unit = unit-1;
        MyWebElement buildingName_title = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.title b","BuildingNameInUnitBlocks_Title_" + building);
        MyWebElement floor_title = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.floor-content:eq(" + _floor + "):visible span:contains('FLOOR')","FloorNameInUnitBlocks_Title_" + floor);
        MyWebElement remove_building = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq(" + _building +
                ") div.title b + span button[name=removeBuildingBtn]","RemoveBuildingNameFromUnitBlocks_Button_" + building);
        MyWebElement remove_floor = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq(" + _building + ") div.floor-content:eq("
                + _floor + "):visible span:contains('FLOOR')+ span button[name=removeFloorBtn]","RemoveFloorFromUnitBlocks_Button_" + floor);
        MyWebElement unitList = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.floor-content:eq(" + _floor + "):visible  table.table-condensed tbody tr:eq(" + _unit + ")", "UnitNameInUnitBlocks_List"+ unit);
        MyWebElement[] BuildingName_Floor_UnitList = {buildingName_title, floor_title,remove_building, remove_floor, unitList};
        return BuildingName_Floor_UnitList;
    }

    // methods, used to get building,floor,unit elements by names
    // most of the times you need fetch the building, floor, unit, including the remove btn by their names.
    // 1.building name, 2.floor name, 3.remove building name, 4.remove floor name 5.unit list
    public static MyWebElement[] getBuilding_Floor_Unit_byName(String buildingName, String buildingType, String floor, String unit){
        String building =  buildingName + " - " + buildingType;
        MyWebElement building_name = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible div.title b:contains('" + building + "')","BuildingNameInUnitBlocks_Title_" + building);
        MyWebElement floor_name = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains('" + building + "') div.floor-content:visible span:contains('FLOOR " + floor + "')","FloorNameInUnitBlocks_Title_" + floor);
        MyWebElement remove_building = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains(" + building +
                ") div.title b + span button[name=removeBuildingBtn]","RemoveBuildingNameFromUnitBlocks_Button_" + building);
        MyWebElement remove_floor = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains(" + building + ") div.floor-content:contains("
                + floor + "):visible span:contains('FLOOR')+ span button[name=removeFloorBtn]","RemoveFloorFromUnitBlocks_Button_" + floor);
        MyWebElement unitList = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains("
                + building + ") div.floor-content:contains(" + floor + "):visible  table.table-condensed tbody tr:contains(" + unit + ")", "UnitNameInUnitBlocks_List"+ unit);
        MyWebElement[] BuildingName_Floor_Unit = {building_name, floor_name,remove_building, remove_floor, unitList};
        return BuildingName_Floor_Unit;
    }

    // methods, used to fetch the list items in List View of each units in building_floor_unit block by inputting building,floor,unit,and item indexes
    // get 1.unit name 2.unit status(if occupied, show tenant's first+last name), 3.start-end date, 4. rent amount, 5. add lease 6.remove unit from UnitsFloorsForBuildingsTable
    // the last param String 'addview' is a switch between add/ view button for different unit situations, by default, it is add lease button
    public static MyWebElement getUnitListItem_FromUnitsFloorsForBuildingsTable(int building,int floor,int unit,int col, String... addview) {
        String col_name;
        switch(col)  {
            case 1:
                col_name = "UnitName"; break;
            case 2:
                col_name = "UnitStatus_or_TenantName"; break;
            case 3:
                col_name = "StartEndDate"; break;
            case 4:
                col_name = "Rent_Amount"; break;
            case 5:
                col_name = "Add/ViewLease_Button"; break;
            case 6:
                col_name = "RemoveUnit_Button"; break;
            default:throw new IllegalStateException( "UnitList " + building + "_" + floor + "_" + unit + "only got 6 columns at all!");
        }
        //System.out.println(" Get " + col_name + " column from UnitList: " + unit);
        int _building = building -1;
        int _floor = floor -1;
        int _unit = unit-1;
        int _col = col -1;
        if (col < 5) {
        MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                + _building + ") div.floor-content:eq(" + _floor + "):visible  table.table-condensed tbody tr:eq(" + _unit + ") td:eq("
                + _col + ")", "UnitNameInUnitBlocks_" + col_name + building + "_" + floor + "_" + unit );
            return unit_item;
        } else if (col == 5 ){
            if(addview == null || addview.length == 0) {
                col_name = "AddLease_Button";
                MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                        + _building + ") div.floor-content:eq(" + _floor + "):visible  table.table-condensed tbody tr:eq(" + _unit + ") td:eq("
                        + _col + ") button[name=addLeaseBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
                return unit_item;
            }    else {
                col_name = "ViewLease_Button";
                MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                        + _building + ") div.floor-content:eq(" + _floor + "):visible  table.table-condensed tbody tr:eq(" + _unit + ") td:eq("
                        + _col + ") button[name=viewUnitBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
                return unit_item;
            }
        } else {
            MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:eq("
                    + _building + ") div.floor-content:eq(" + _floor + "):visible  table.table-condensed tbody tr:eq(" + _unit + ") td:eq("
                    + _col + ") button[name=removeUnitBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
            return unit_item;
        }
    }

    // methods, used to fetch the list items in List View of each units in building_floor_unit block by inputting building,floor,unit name and item indexes
    // most of the times you need fetch the building, floor, unit, including the remove btn by their names.
    // get 1.unit name 2.unit status(if occupied, show tenant's first+last name), 3.start-end date, 4. rent amount, 5. add lease 6.remove unit from UnitsFloorsForBuildingsTable
    public static MyWebElement getUnitListItem_byName (String buildingName, String buildingType, String floor, String unit,int col, String... addview) {
        String building =  buildingName + " - " + buildingType;
        String col_name;
        switch(col)  {
            case 1:
                col_name = "UnitName"; break;
            case 2:
                col_name = "UnitStatus_or_TenantName"; break;
            case 3:
                col_name = "StartEndDate"; break;
            case 4:
                col_name = "Rent_Amount"; break;
            case 5:
                col_name = "Add/ViewLease_Button"; break;
            case 6:
                col_name = "RemoveUnit_Button"; break;
            default:throw new IllegalStateException( "UnitList " + building + "_" + floor + "_" + unit + "only got 6 columns at all!");
        }
        //System.out.println(" Get " + col_name + " column from UnitList: " + unit);
        int _col = col -1;
        if (col < 5) {
            MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains("
                    + building + ") div.floor-content:contains(" + floor + "):visible  table.table-condensed tbody tr:contains(" + unit + ") td:eq("
                    + _col + ")", "UnitNameInUnitBlocks_" + col_name + building + "_" + floor + "_" + unit );
            return unit_item;
        } else if (col == 5 ){
            if(addview == null || addview.length == 0) {
                col_name = "AddLease_Button";
                MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains("
                        + building + ") div.floor-content:contains(" + floor + "):visible  table.table-condensed tbody tr:contains(" + unit + ") td:eq("
                        + _col + ") button[name=addLeaseBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
                return unit_item;
            }  else {
                col_name = "ViewLease_Button";
                MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains("
                        + building + ") div.floor-content:contains(" + floor + "):visible  table.table-condensed tbody tr:contains(" + unit + ") td:eq("
                        + _col + ") button[name=viewUnitBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
                return unit_item;
            }
        } else {
            MyWebElement unit_item = myWebelement("jQuerySelector:div#addUnitsBlock div.block-item:visible:contains("
                    + building + ") div.floor-content:contains(" + floor + "):visible  table.table-condensed tbody tr:contains(" + unit + ") td:eq("
                    + _col + ") button[name=removeUnitBtn]", col_name + "_" + building + "_" + floor + "_" + unit );
            return unit_item;
        }
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static PropertyDetailPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new PropertyDetailPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String propertyName) {
        verifyPageTitle(pageTitle);
        propertyDetail_title.verifyContains("PROPERTY DETAIL - " + propertyName);
        addLease_image.verifyElementPresent();
        propertyDetail_subTitle.verifyContainsIgnoreSpaces("To add a new lease, select the","button on an Unoccupied unit.");
    }

}
