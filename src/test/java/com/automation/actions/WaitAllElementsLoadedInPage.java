package com.automation.actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;

public class WaitAllElementsLoadedInPage implements Task {

@Override
@Step("Waiting until All elements are loaded in the page")
public <T extends Actor> void performAs(T actor){
  
	try {
		BrowseTheWeb.as(actor).waitFor(ExpectedConditions.jsReturnsValue("return document.readyState"));
	    BrowseTheWeb.as(actor).evaluateJavascript("return document.readyState");
	    if(BrowseTheWeb.as(actor).evaluateJavascript("return document.readyState").toString().equals("complete"))
		    {
			   System.out.println("All elements are loaded in the page");
		    }
	    else{
		       System.out.println("Elements are not  loaded completely"); 
	        }   
		   
	    }
		
	catch(Exception e){
		e.printStackTrace();
	}
	
 }

public static Performable on () {
	return instrumented(WaitAllElementsLoadedInPage.class);
	}
  
 }
