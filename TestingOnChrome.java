package eTrustedScript;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestingOnChrome {
	ChromeDriver driver;
	String url = "https://www.trustedshops.de/bewertung/info_X77B11C1B8A5ABA16DDEC0C30E7996C21.html";
      
        public void checkPageTitle() {
        	System.setProperty("webdriver.chrome.driver", "C:\\Users\\zain\\eclipse-workspace\\libs\\chromedriver.exe");
        	// Task 1: Check if the page title exists
        	String pageTitle = this.driver.getTitle();
            System.out.println("Page Title: " + pageTitle);
            driver.get(url);	 	
        }
        
        public void gradeCheck() {
        	// Task 2: Check if the grade is visible and above zero
            WebElement gradeElement = driver.findElement(By.className("ts-starrating-grade"));
            String gradeText = gradeElement.getText();
            double grade = Double.parseDouble(gradeText.split("/")[0]);
            if (grade > 0) {
                System.out.println("Grade: " + gradeText);
            } else {
                System.out.println("Grade not visible or below zero.");
            }
        	
        }
    
        public void linkWithAdditionaInfo() {
        // Task 3: Check if the "Wie berechnet sich die Note?" link opens the window with additional information
        WebElement infoLink = driver.findElement(By.linkText("Wie berechnet sich die Note?"));
        infoLink.click();

        // Switch to the new window
        String parentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Check if the provided information is relevant
        WebElement infoContent = driver.findElement(By.className("information-content"));
        System.out.println("Additional Information: " + infoContent.getText());

        // Close the additional information window and switch back to the original window
        driver.close();
        driver.switchTo().window(parentHandle);
        }
        
        public void filterReviews() {
        // Task 4: Click on "2 Stars" to filter all "two stars" reviews
        WebElement twoStarsFilter = driver.findElement(By.xpath("//a[contains(text(), '2 Stars')]"));
        twoStarsFilter.click();

        // Wait for the reviews to load (you may need to use explicit waits here)

        // Check if every review in the entire list has only two stars
        List<WebElement> reviewElements = driver.findElements(By.className("ts-rating-stars"));
        boolean allTwoStars = true;
        for (WebElement review : reviewElements) {
            String starsText = review.getAttribute("data-ts-rating");
            double stars = Double.parseDouble(starsText);
            if (stars != 2.0) {
                allTwoStars = false;
                break;
            }
        }
        System.out.println("All reviews have two stars: " + allTwoStars);
        
        }
        
        public void sumPercentage() {
        // Task 5: Create the sum of all star percentage values and ensure it's equal or below 100
        List<WebElement> starPercentageElements = driver.findElements(By.className("ts-rating-star-percentage"));
        int sum = 0;
        for (WebElement starPercentage : starPercentageElements) {
            String percentageText = starPercentage.getText().replaceAll("[^0-9]", "");
            int percentage = Integer.parseInt(percentageText);
            sum += percentage;
        }
        System.out.println("Sum of star percentages: " + sum);
        if (sum <= 100) {
            System.out.println("Sum is equal or below 100.");
        } else {
            System.out.println("Sum is above 100.");
            
        }
        
 }
        
    	public static void main(String[] args) {
    		// Set the path to chromedriver executable
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\zain\\eclipse-workspace\\libs\\chromedriver.exe");
            
            TestingOnChrome tc = new TestingOnChrome();

            // Create a new instance of ChromeDriver
            WebDriver driver = new ChromeDriver();
            // Open the profile page
            driver.get("https://www.trustedshops.de/bewertung/info_X77B11C1B8A5ABA16DDEC0C30E7996C21.html");
            
            tc.checkPageTitle();
            tc.gradeCheck();
            tc.linkWithAdditionaInfo();
            tc.filterReviews();
            tc.sumPercentage();

    	}
    	
}
        
