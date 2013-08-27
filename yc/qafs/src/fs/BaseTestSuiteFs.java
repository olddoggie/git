package fs;

import myaccount.Account;

import org.testng.annotations.*;
import frameLib.listener.*;
import fs.pages.*;
import applications.*;
import rtb.*;
import static applications.BasePage.*;
import static fs.FsConstants.*;
import static myconstant.BizWork.*;
import static myconstant.General.EMAIL;

@SuppressWarnings("static-access")
public class BaseTestSuiteFs extends BaseTestSuite {
	protected static void go2AccountOverviewPage(String userName) {
		navigateTo(FS_URL);
		LoginPage.load().login(userName, FS_PASSWORD);
		PersonalImagePage.load().selectBasketImage();
	}

	protected static void register(String accountNumber, String userName) {
		Account account = new Account(accountNumber);
		navigateTo(FS_URL);
		LoginPage.load().registerLink.click();
		RegistrationPage.load().register(account.getRegisterInfo());
		RegistrationTermsPage.load().iAgreeButton.click();
		RegistrationPickCredentialsPage.load().submitForm(userName, EMAIL);
		
//		LoginPage.registerLink.click();
//		RegistrationPage.register(account.getRegisterInfo());
//		RegistrationTermsPage.iAgreeButton.click();
//		RegistrationPickCredentialsPage.submitForm(userName, EMAIL);
		
	}
	
	public static void removeAllEnrolledAccounts(){
		while(EAutopayManagePaymentAccountsPage.load().enrolledAccountsDiv.getTextNoNewLine().contains("Remove Account")){
			EAutopayManagePaymentAccountsPage.removeAccount1Link.click();
			EAutopayRemoveAccountPage.load().removeAccountButton.click();
			NavigationPage.managetPaymentAccountsSubLink.waitUntilElementPresent().click();
		}
	}
}
