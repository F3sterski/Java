package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class TelemanSteps {
	
	private final Pages pages;

	public TelemanSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
 
    @When("user opens Registration link")
    public void userClicksOnRegistrationLink(){        
        pages.home().findElement(By.xpath("//*[@id=\"welcome\"]/div/div/div/p[1]/a")).click();
    }
    @When("user type login $a")
    public void userFillLogin(String login){        
        pages.registration().findElement(By.xpath("//*[@id=\"user_login\"]")).sendKeys(login);;
    }
    @When("user type password $a")
    public void userFillRegister(String pass){        
        pages.registration().findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(pass);
    }
    @When("user retype password $a")
    public void userRefillRegister(String pass){        
        pages.registration().findElement(By.xpath("//*[@id=\"user_password_confirmation\"]")).sendKeys(pass);
    }
    @When("user type email $a")
    public void userFillEmail(String email){        
        pages.registration().findElement(By.xpath("//*[@id=\"user_email\"]")).sendKeys(email);
    }
    @When("user click register")
    public void userClicksOnSportLink(){        
        pages.registration().findElement(By.xpath("/html/body/div/div/div/div[2]/form/input")).click();
    }
 
    @Then("Registration page is shown")
    public void RegistrationPageIsShown(){
       assertEquals("Bracketize! - sign up", pages.registration().getTitle());
    }	

}
