package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.automation.exception.InvalidLocatorRuntimeException;
import com.automation.exception.MandatoryFieldValueRuntimeException;
import org.junit.Assert;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.BlurScreenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.screenshots.BlurLevel;

public class Click_Button_UsingVisibleText implements Task {
  private String buttonName;
  
public Click_Button_UsingVisibleText(String buttonName) {
 this.buttonName = buttonName;
}

public static Performable on(String buttonName) {
 return instrumented(Click_Button_UsingVisibleText.class,buttonName);
}

@Step("Click the Button  #buttonName")
@BlurScreenshots(BlurLevel.HEAVY)
public <T extends Actor> void performAs(T actor){
	
    String buttonNameLocator = "//button[text()='"+buttonName+"']";
	if(!buttonNameLocator.trim().isEmpty()) {
		
	try {
	  WebElement buttonElement =  BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(buttonNameLocator));
	  boolean isButtonEnabled = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(buttonNameLocator)).isEnabled();
	  if(isButtonEnabled){
       BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",buttonElement);
	    }
	  else{
	   System.out.println("Button : " + buttonName + " is not enabled to Click" );
	   Assert.assertTrue(isButtonEnabled);
	      }
	    }
	
    catch(NoSuchElementException e){
     	throw new InvalidLocatorRuntimeException(buttonName);
       }
	
	catch(Exception e){
		e.printStackTrace();
	  }
	}
	else{
	   throw new MandatoryFieldValueRuntimeException(buttonName);
		}
     }
}

