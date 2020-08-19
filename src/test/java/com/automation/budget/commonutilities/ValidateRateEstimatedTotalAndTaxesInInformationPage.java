package com.automation.budget.commonutilities;


import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.junit.Assert;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class ValidateRateEstimatedTotalAndTaxesInInformationPage implements Task {
  private  double expBaseRate;
  private  double expFeesAndTaxes;
  private  double expEstimatedTotal;

public ValidateRateEstimatedTotalAndTaxesInInformationPage() {}

public  ValidateRateEstimatedTotalAndTaxesInInformationPage(double expBaseRate,double expFeesAndTaxes,double expEstimatedTotal) {
	this.expBaseRate= expBaseRate;
	this.expFeesAndTaxes= expFeesAndTaxes;
	this.expEstimatedTotal = expEstimatedTotal;
	
}


public static Performable validate(double baseRate,double feesAndTaxes,double estimatedTotal) {
	return instrumented(ValidateRateEstimatedTotalAndTaxesInInformationPage.class,baseRate,feesAndTaxes,estimatedTotal);
}

@Step("Get Taxes #vehicleName")
public <T extends Actor> void performAs(T actor){
	
	String baseRateLocator = "//span[text()='Base Rate']//following-sibling::span[@class='pull-right']//span[2]";
	String feesAndTaxesLocator  = "//span[text()='Fees & Taxes']//following-sibling::span[@class='pull-right']//span[2]";
    String estimatedTotalLocator = "//span[text()='Estimated Total (Prepaid)']//following-sibling::span[@class='pull-right est-total']//span[2]";
    
	try {   
		
     double actualBaseRate      =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(baseRateLocator)).getText());
     double actualFeesAndTaxes =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(feesAndTaxesLocator)).getText());
     double actualEstimatedTotal =  Double.parseDouble(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(estimatedTotalLocator)).getText());
     
     if(actualBaseRate==expBaseRate && actualFeesAndTaxes==expFeesAndTaxes && actualEstimatedTotal== expEstimatedTotal) {
    	 
    	 System.out.println("Expected Base Rate           : "+expBaseRate);
    	 System.out.println("Actual Base Rate             : "+actualBaseRate + "\n");
    	 System.out.println("Expected Fees AndTaxes Rate  : "+expFeesAndTaxes);
    	 System.out.println("Actual Fees AndTaxes Rate    : "+actualFeesAndTaxes + "\n");
    	 System.out.println("Expected Estimated Total     : "+expEstimatedTotal);
    	 System.out.println("Actual Estimated Total       : "+actualEstimatedTotal+"\n");
    	 System.out.println("Test Status                  :  PASS ");
    	 
     }
     else {
    	 System.out.println("Expected Base Rate           : "+expBaseRate);
    	 System.out.println("Actual Base Rate             : "+actualBaseRate + "\n");
    	 System.out.println("Expected Fees AndTaxes Rate  : "+expFeesAndTaxes);
    	 System.out.println("Actual Fees AndTaxes Rate    : "+actualFeesAndTaxes + "\n");
    	 System.out.println("Expected Estimated Total     : "+expEstimatedTotal);
    	 System.out.println("Actual Estimated Total       : "+actualEstimatedTotal+"\n");
    	 System.out.println("Test Status                  :  FAIL ");
    	 Assert.assertTrue(actualBaseRate==expBaseRate && actualFeesAndTaxes==expFeesAndTaxes && actualEstimatedTotal== expEstimatedTotal);
    	 
     }
    
	}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}



