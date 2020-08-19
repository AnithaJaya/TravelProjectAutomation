package com.automation.actions;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class MaximizeTheWindow implements Task{
	
	public static MaximizeTheWindow toMaxWindow() {
		return Instrumented.instanceOf(MaximizeTheWindow.class).withProperties();
		
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		boolean win = false;
		BrowseTheWeb.as(actor).getDriver().manage().window().maximize();
		Dimension winSize = BrowseTheWeb.as(actor).getDriver().manage().window().getSize();
		System.out.println("The Original Window Size is :"+ winSize);
		System.out.println("Window is Maximized");
		win =true;
		if(!win) {
			
			String errorMessage = String.format("The Window is not maximized");
			throw new NoSuchElementException(errorMessage);
			
			
		}
		
		
		
	}

	
	
}
