package genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities {

	/**
	 * @author Indrapal Singh File is base class of framework used to read
	 *         properties file configuration and contains methods to launch
	 *         browser
	 */

	public static WebDriver driver;
	static final String BROWSER = "browser";
	public static Properties prop;

	public BrowserUtilities() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./constant.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getBrowser() throws IOException {

		if (prop.getProperty(BROWSER).equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));

		}

		else if (prop.getProperty(BROWSER).equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));

		} else if (prop.getProperty(BROWSER).equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}

		return driver;

	}

	public static void getWindowHandles() throws InterruptedException {
		Thread.sleep(1000);
		String subWindowHandler = null;

		Set<String> handles = BrowserUtilities.driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
			BrowserUtilities.driver.switchTo().window(subWindowHandler);
		}

	}

	public static void closeBrowser() {

		driver.close();

	}
}
