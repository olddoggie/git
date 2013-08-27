package fs.bat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import myaccount.Account;
import myaccount.AccountMCycle;
import myaccount.TestBed;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static applications.BasePage.*;
import frameLib.listener.*;
import fs.BaseTestSuiteFs;
import fs.pages.*;
import static applications.BasePage.openBrowser;
import static frameLib.FrameUtilities.creatDefaultPattern;
import static fs.FsConstants.*;
import static myconstant.AccountDefaultValues.*;
import static myconstant.TestAccounts.*;
import static myconstant.FsConfig.*;
import static myconstant.General.*;

@SuppressWarnings("static-access")
public class __FsBAT_Stg extends BaseTestSuiteFs {
	private AccountMCycle CCA_BAT = new AccountMCycle(CCA_BAT_ACCOUNT_NUMBER);
	private String batAccountUserName = "ccabat";
	private String ddaBatAccountUserName = "ddabat";
	private String batAccountNewPassword = "password1";


	@Parameters({ "browserType" })
	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod(String browserType) throws Exception {
		//CCA_BAT.deleteLock();
		openBrowser(browserType);
	}

	@AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		closeBrowser();
	}

	/** login */
	@Test
	public void tc_cca_login() {
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load().logOutLink.click();
		LogoutPage.load();

	}

//	/** register */
//	@Test
//	public void tc_register() {
//		CCA_BAT.unRegister();
//		register(CCA_BAT.getAccountNumber(), batAccountUserName);
//		AccountOverviewPage.load();
//	}

	/** DDA verification */
	@Test
	public void tc_dda_login() {
		go2AccountOverviewPage(ddaBatAccountUserName);
		AccountOverviewPage.logOutLink.waitUntilElementPresent().click();
		LogoutPage.load();
		// // if the user has not been registered
		// register(DDA_BAT_ACCOUNT_NUMBER, "ddabat");
	}

	/** make a payment */
	// update this test case to take 5:00PM into consideration.
	@Test
	public void tc_make_payment() {
		CCA_BAT.deleteAutopay();
		CCA_BAT.deleteAllEnrolledDdas();
		CCA_BAT.enrollDda(ABA_NUMBER_DEFAULT);
		CCA_BAT.consentDdaLegalTerm();
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();
		String paymentDate = common.Tools.getPaymentDate("MM/dd/yyyy", TIME_ZONE);
		String paymentAmount = "1.23";
		EAutopayMakeAPaymentPage.otherAmountRadioButton.click();
		EAutopayMakeAPaymentPage.otherAmountEditBox.sendKeys(paymentAmount);
		EAutopayMakeAPaymentPage.continueButton.click();
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		EAutopayPaymentSuccessPage.load().paymentInfoList
				.verifyMatchesByLowerCase(creatDefaultPattern("\\d+",
						paymentAmount, paymentDate));
	}

	/** enroll DDA */
	@Test
	public void tc_enroll_dda() {
		String externalDDANumber = "8832";
		//CCA_BAT.deleteEnrolledDdasByAccountNumber(externalDDANumber);
		go2AccountOverviewPage(batAccountUserName);
		//set precondtion -- remove all enrolled payment account
		NavigationPage.eAutopayLink.waitUntilElementPresent().click();
		NavigationPage.managetPaymentAccountsSubLink.waitUntilElementPresent().click();
		removeAllEnrolledAccounts();
		
		NavigationPage.accountsLink.waitUntilElementPresent().click();
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();		
		EAutopayHomeNotEnrolledPage.load().enrollNowForEAutopayImg.click();
		EAutopayEnrollAccountPage.load().enrollAccount(ABA_NUMBER_DEFAULT,externalDDANumber);
		EAutopayConfirmEnrollmentInformationPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();
		EAutopayEnrollSuccessPage.load().makeAPaymentButton.click();
		EAutopayMakeAPaymentPage.load();
	}

	/** edit DDA payment info */
	@Test
	public void tc_update_payment_info() {
		String ABA_NUMBER_2 = "011000138";
		String CHECKING_ACCOUNT_NUMBER_2 = "10002";
		CCA_BAT.deleteAllEnrolledDdas();
		CCA_BAT.enrollDda(ABA_NUMBER_DEFAULT);
		// update bank info
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();
		EAutopayMakeAPaymentPage.load().editAccountLink.click();
		EAutopayEditBankInformationPage.load().editBankInfo(ABA_NUMBER_2,
				CHECKING_ACCOUNT_NUMBER_2);
		EAutopayConfirmEditBankInformationPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();
		// update personal inf
		EAutopayMakeAPaymentPage.load().editAddressLink.click();
		EAutopayEditNameAndAddressPage.load().editPersonInfo(
				"950 tanbark street", "san rafael", "94903");
		EAutopayConfirmEditNameAndAddressPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();
		EAutopayMakeAPaymentPage.load();
		NavigationPage.managetPaymentAccountsSubLink.waitUntilElementPresent()
				.click();
		// verify bank and personal info updates
		EAutopayManagePaymentAccountsPage.load().firstAccoutInfo
				.verifyMatchesByLowerCase(creatDefaultPattern(
						ABA_NUMBER_2.substring(ABA_NUMBER_2.length() - 4),
						CHECKING_ACCOUNT_NUMBER_2
								.substring(CHECKING_ACCOUNT_NUMBER_2.length() - 4)));
		EAutopayManagePaymentAccountsPage.firstAccoutInfo
				.verifyMatchesByLowerCase(creatDefaultPattern(
						"950 tanbark street", "san rafael", "94903"));
	}

	/** update CCA personal info */
	@Test
	public void tc_update_personal_info() {
		CCA_BAT.deleteAllEnrolledDdas();
		CCA_BAT.enrollDda(ABA_NUMBER_DEFAULT);
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load();
		SideBar.myPersonalInfoLink.waitUntilElementPresent().click();
		MyPersonalInformationHomePage.load().selectThisAccountLink.click();
		MyPersonalInformationFormPage.load().submitForm();
		MyPersonalInformationConfirmPage.load().confirmButton.click();
		MyPersonalInformationThankYouPage.load().youRequestHasScript
				.verifyMatchesByLowerCase(creatDefaultPattern("\\d+"));

	}

	/** check foot links */
	@Test
	public void tc_footer_links() {
		navigateTo(FS_URL);
		FootLinks.careersLink.waitUntilElementPresent().click();
		waitUntilWindowPresentByPageSource("Building Momentum Through Teamwork");
		driver.close();
		waitUntilWindowPresentByPageSource("1FBUSA Online Services provides secure");
		LoginPage.load();
		FootLinks.termsLink.click();
		TermsPage.load();
		FootLinks.privacyandSecurityCenterLink.click();
		PrivacyAndSecurityCenterPage.load();
		FootLinks.frequentlyAskedQuestionsLink.click();
		FrequentlyAskedQuestionsPage.load();
		FootLinks.creditCardAgreementsLink.click();
		CreditCardAgreementsPage.load();
		FootLinks.contactUsLink.click();
		FrequentlyAskedQuestionsPage.load();
		FootLinks.homeLink.click();
		LoginPage.load();
	}

	/** text banking */
	@Test
	public void tc_text_banking() {
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load();
		NavigationPage.textBankingLink.click();
		//waitUntilPageReady();
		if (driver.getTitle().equalsIgnoreCase("MobilePay Service Enrolled")) {
			MobilePayServiceEnrolledPage.load().cancelMobilePayServiceButton
					.click();
			MobilePayServiceConfirmEditPage.load().yesButton.click();
		}
		MobilePayServiceCanclledPage.load().addButton.click();
		MobilePayServiceAddFormPage.load().submitForm();
		MobilePayServiceEnrolledPage.load().contentBlock.verifyContains(
				"(xxx) xxx-1325", "Sprint");

	}

	/** update paperless */
	@Test
	public void tc_paperless() {
		go2AccountOverviewPage(batAccountUserName);
		NavigationPage.serviceLink.waitUntilElementPresent().click();
		CustomerServiceHomePage.load().accountPaperlessSettingLink.click();
		String deliverSetting = AccountPaperlessSettingPage.load().deliverySettingList
				.getFirstSelectedOption().getText();
		if (deliverSetting.equalsIgnoreCase("Online Only")) {
			AccountPaperlessSettingPage.change2USMail();
		} else {
			AccountPaperlessSettingPage.change2OnLineOnly();
		}
		// if(CCA_BAT.IsPaperless()){
		// SideBar.learnMoreLink.waitUntilElementPresent().click();
		// AccountPaperlessSettingPage.change2USMail();
		// }else{
		// NavigationPage.serviceLink.waitUntilElementPresent().click();
		// CustomerServiceHomePage.load().accountPaperlessSettingLink.click();
		// AccountPaperlessSettingPage.load().change2OnLineOnly();
		// }
	}

	/** view legal disclosure */
	@Test
	public void tc_legal_disclosures() {
		go2AccountOverviewPage(batAccountUserName);
		NavigationPage.legalDisclosuresLink.waitUntilElementPresent().click();
		LegalDisclosuresPage.load().firstFinancialBankUSAPrivacyNoticeLink
				.waitUntilElementPresent();
		LegalDisclosuresPage.creditCardAccountBillingRightsStatementLink
				.waitUntilElementPresent();
	}

	/** Verify statement links displayed */
	@Test
	public void tc_statements() {
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load().viewOnlineStatementsButton.click();
		AccountOnlineStatementsPage.load().ViewStatement1Link
				.waitUntilElementPresent();

	}

	/** Verify some transaction records displayed */
	@Test
	public void tc_activities() {
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load();
		NavigationPage.activitySubLink.waitUntilElementPresent().click();
		AccountActivityPage.load();
		// find an account with activity
	}

	/** Verify at least one mail exist */
	@Test
	public void tc_mail() {
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load();
		NavigationPage.mailLink.waitUntilElementPresent().click();
		MailInboxPage.testMessageLink.waitUntilElementPresent();
	}

	/** Verify forgot password */
	@Test
	public void tc_forgot_password() {
		CCA_BAT.resetFsPassword(FS_PASSWORD);
		navigateTo(FS_URL);
		LoginPage.load().forgotLink.click();
		ForgottenPasswordHomePage.load().submitForm(batAccountUserName,
				CCA_BAT.getRegisterInfo());
		ForgottenPasswordPickCredentialsPage.load().submitForm(
				batAccountNewPassword);
		ForgottenPasswordSuccessPage.load();
		AccountOverviewPage.load().logOutLink.click();
	}

	/** Verify change password */
	@Test
	public void tc_change_password() {
		CCA_BAT.resetFsPassword(FS_PASSWORD);
		go2AccountOverviewPage(batAccountUserName);
		AccountOverviewPage.load();
		NavigationPage.serviceLink.waitUntilElementPresent().click();
		CustomerServiceHomePage.load().changePasswordLink.click();
		CustomerServiceChangePasswordPage.load().submitForm(batAccountUserName,
				FS_PASSWORD, batAccountNewPassword);
		CustomerServiceChangePasswordChangeImagePage.load().submitForm();
		AccountOverviewPage.load().logOutLink.click();
	}

	/** Verify logout */
	@Test
	public void tc_logout() {
		//version 1.4;
		// version 1.6

	}
}
