package com.automation.features;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import com.automation.actions.WaitAllElementsLoadedInPage;
import com.automation.budget.commonutilities.Click_Button_UsingVisibleText;
import com.automation.budget.commonutilities.GetRateTaxesAndEstimatedTotalFromRentalPage;
import com.automation.budget.commonutilities.SelectVehiclesUsingVehicleNameFromGrid;
import com.automation.budget.commonutilities.ValidateLocationAddress;
import com.automation.budget.commonutilities.ValidateLocationAddressInPayPage;
import com.automation.budget.commonutilities.ValidateRateEstimatedTotalAndTaxesInInformationPage;
import com.automation.budget.commonutilities.ValidateVehicleNameInPayScreen;
import com.automation.questions.Application;
import com.automation.tasks.EnterTravelDetails;
import com.automation.tasks.OpenTheApplication;
import com.automation.tasks.SelectVehiclewithNameAndNoOfSeats;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;

/* Author : Anitha Prakash
 * Description : Book Flight For Travel
 * Date : 16th Aug 2020
 */


@RunWith(SerenityRunner.class)
public class TravelBudget extends PageObject {
    Actor user = Actor.named("User");
	@Managed
	WebDriver browser;
	
	public static final EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
	public static final String applicationTitle        = variables.getProperty("Application.title");
	private static String travelLocation   = "Austin Bergstrom Intl Airport";
	private static String submitText       = "Select My Car";
	private static String vehicleName      = "SUV";
	private String pickUpTravel = "Pick-Up";
	private String returnTravel = "Return";

	@Test
	public void bookCar(){
		
		user.can(BrowseTheWeb.with(browser));
		
		when(user).wasAbleTo(
				OpenTheApplication.toTheLoginPage());
		
		then(user).should(seeThat(Application.title(),containsString(applicationTitle)));
		
		//Enter the Travel Details
		(user).attemptsTo(
				EnterTravelDetails.enterandselect(travelLocation),
				Click_Button_UsingVisibleText.on(submitText),
				WaitAllElementsLoadedInPage.on()
				);
		
		
		//validate the entered Travel Details
		(user).attemptsTo(
		        ValidateLocationAddress.on(pickUpTravel, travelLocation),
		        ValidateLocationAddress.on(returnTravel, travelLocation)
		        );
		
		
		//Select Vehicle using VehicleName,Seats,Door
		(user).attemptsTo(
		SelectVehiclewithNameAndNoOfSeats.on(vehicleName),
		SelectVehiclesUsingVehicleNameFromGrid.on(vehicleName)
		        );
		
		
		//Validate Travel Details in Pay Screen
		(user).attemptsTo(
				ValidateLocationAddressInPayPage.on(pickUpTravel, travelLocation),
				ValidateLocationAddressInPayPage.on(returnTravel, travelLocation),
				ValidateVehicleNameInPayScreen.on(SelectVehiclesUsingVehicleNameFromGrid.getSelectedVehicleName())
		        );
		
		(user).attemptsTo(
				GetRateTaxesAndEstimatedTotalFromRentalPage.on(),
				Click_Button_UsingVisibleText.on("Continue")
				);
		
		//Validate Travel Details in Rental Page
		(user).attemptsTo(
		ValidateRateEstimatedTotalAndTaxesInInformationPage.validate(
				GetRateTaxesAndEstimatedTotalFromRentalPage.getbaseRate(),
				GetRateTaxesAndEstimatedTotalFromRentalPage.getfeesAndTaxes(),
				GetRateTaxesAndEstimatedTotalFromRentalPage.getestimatedTotal())
		       );
		
		
      	}
	
   }
