package com.qapitol.cribuzz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Cribuzz {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.cricbuzz.com/cricket-series/8525/womens-big-bash-league-2024/points-table");
        driver.findElement(By.xpath("//h3[text()='Womens Big Bash League 2024 - Points Table']")).isDisplayed();
        List<WebElement> points = driver.findElements(By.cssSelector(".cb-srs-pnts-td.text-bold"));
        List<WebElement> names = driver.findElements(By.cssSelector(".cb-col.cb-col-84"));
        int i =1;
        for (WebElement point : points) {
            String pointText = point.getText();
            if (Integer.parseInt(pointText) >= 9) {
                System.out.println("Points  = " + point.getText() + " Names = "+names.get(i).getText());
            }
            i ++;
        }
        driver.quit();
    }
}
