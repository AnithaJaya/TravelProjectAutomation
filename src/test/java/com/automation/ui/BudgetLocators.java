package com.automation.ui;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class BudgetLocators extends PageObject{

public static final Target selectMyCar = Target.the("Car Selection").
                         located(By.xpath("//div[@class='reservation-progress-bar']"));
}	                                  