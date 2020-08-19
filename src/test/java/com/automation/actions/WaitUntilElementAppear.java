package com.automation.actions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.StaleElementReferenceException;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class WaitUntilElementAppear implements Task {
  private final Target target;
  private final int timeOut;

  
  public WaitUntilElementAppear(Target target,int timeOut) {
	  this.target = target;
	  this.timeOut = timeOut;
	//  this.timeUnit = timeUnit;
	  
  }
  
  
@Override
@Step("wait until element appear on Screen")
public <T extends Actor> void performAs(T actor) {
   System.out.println("a");
	WebElementFacade webelement = target.resolveFor(actor);
	System.out.println("b");
	int temp=0,timeIntervalInSeconds = 3;
	System.out.println("c");
	try {
		System.out.println("d");
		while(webelement.isPresent())
			
		{
			System.out.println("e");
			if(temp>=timeOut) {
				System.out.println("f");
				break;
			}
			System.out.println("k");
			TimeUnit.SECONDS.sleep(timeIntervalInSeconds);
			temp = temp + timeIntervalInSeconds;
		}
	}
	
	catch(InterruptedException e) {
		
		e.printStackTrace();
	}
	catch(StaleElementReferenceException sre) {
		
		try {
			TimeUnit.SECONDS.sleep(timeIntervalInSeconds);		
		}
		catch(InterruptedException e) {}
		
		
		
	}
			
}

public static Performable on (Target target,int timeOut) {
	return instrumented(WaitUntilElementAppear.class,target,timeOut);
	
}
  
  
}
