package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class GetRateTaxesAndEstimatedTotalFromRentalPage implements Task {
  private static double baseRate;
  private static double feesAndTaxes;
  private static double estimatedTotal;

public GetRateTaxesAndEstimatedTotalFromRentalPage() {}

public static double getbaseRate() {
	return baseRate;
}

public static double getfeesAndTaxes() {
	return feesAndTaxes;
}

public static double getestimatedTotal() {
	return estimatedTotal;
}
public static Performable on() {
	return instrumented(GetRateTaxesAndEstimatedTotalFromRentalPage.class);
}

@Step("Get Taxes #vehicleName")
public <T extends Actor> void performAs(T actor){
	
	String baseRateLocator       = "//span[text()='Base Rate']//following-sibling::span[@class='pull-right']//span[2]";
	String feesAndTaxesLocator   = "//span[text()='Fees & Taxes']//following-sibling::span[@class='pull-right']//span[2]";
    String estimatedTotalLocator = "//span[text()='Estimated Total (Prepaid)']//following-sibling::span[@class='pull-right est-total']//span[2]";
    
    try {
	
	 baseRate       =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(baseRateLocator)).getText());
	 feesAndTaxes   =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(feesAndTaxesLocator)).getText());
	 estimatedTotal =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(estimatedTotalLocator)).getText());
    System.out.println(baseRate);
    System.out.println(feesAndTaxes);
    System.out.println(estimatedTotal);
    
	}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}



