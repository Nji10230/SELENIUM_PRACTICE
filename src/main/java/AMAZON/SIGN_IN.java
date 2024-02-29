package AMAZON;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SIGN_IN {
    static WebDriver driver;
    static Scanner sc;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        SIGN_IN obj=new SIGN_IN();
        obj.signin_details();

    }

    public void signin_details() throws InterruptedException {
        sc=new Scanner(System.in);
        driver.get("https://www.amazon.in/-/hi/ap/signin?openid.pape.max_auth_age=3600&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fspr%2Freturns%2Fgift&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=amzn_psr_desktop_in&openid.mode=checkid_setup&language=en_IN&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        System.out.println("initial authenaction ,please enter characters :-");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(sc.nextLine());
        driver.findElement(By.xpath("//button[text()='Continue shopping']")).submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement enterdetails = driver.findElement(By.xpath("//input[@id='ap_email']"));

        String user_interest = sc.nextLine();
        email_or_phoneno(enterdetails , user_interest);

    }

    private void email_or_phoneno(WebElement enterdetails , String user_interest) throws InterruptedException {
        List<String>details_encrpted=new ArrayList<>();
        if (user_interest.equals("email")) {
            System.out.println("please enter the email :- ");
            enterdetails.sendKeys(sc.nextLine());
            Thread.sleep(2000);

           // details_encrpted.add(enterdetails.getText());
            userpassword(details_encrpted);
        } else if (user_interest.equals("phoneno")) {
            enterdetails.sendKeys(sc.nextLine());
            userpassword(details_encrpted);
        }
    }

    private void userpassword(List<String>details_encrpted) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       WebElement password= driver.findElement(By.xpath("//input[@id='ap_password']"));
        System.out.println("please enter the password :- ");

        password.sendKeys(sc.nextLine());
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //details_encrpted.add(password.getText());
       // System.out.println("sigin details of user:- " +details_encrpted);
      //  password = driver.findElement(By.xpath("//input[@id='ap_password']"));

        otp();


    }

    private void otp() throws InterruptedException {
         WebElement otp_enter=driver.findElement(By.xpath("//input[@maxlength=6]"));
        System.out.println("user please enter the otp :- ");
          otp_enter.sendKeys(sc.nextLine());
          //otp_enter.click();
           Thread.sleep(3000);
          driver.findElement(By.xpath("//span[text()='Submit code']")).submit();

    }

}
