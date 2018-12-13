package genericlibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;

/**
 * @author Indrapal Singh
 * @getScreenShot 
 *         method to capture screenshot for failed test
 */

public class ScreenshotUtilities extends BrowserUtilities{

	static final String ERRORMESSAGESCREENSHOT = "Screenshot not getting captured";
	
	public ScreenshotUtilities() {
		super();
	}

	public static void getScreenShotForFailed(WebDriver ldriver, ITestNGMethod method) {

		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
		
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src,
					new File(prop.getProperty("FailedScreenshotpath") + System.currentTimeMillis() + ".png"));
		}
		catch (IOException e)
		{
			LogUtilities.error(ERRORMESSAGESCREENSHOT);
		}
	}

	
	// Take screenshot and store 	
	public static void getScreenShotForPass() {

		File src = ((TakesScreenshot) BrowserUtilities.driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

	FileUtils.copyFile(src,new File(prop.getProperty("PassScreenshotpath") + System.currentTimeMillis() + ".png"));
		}
		catch (IOException e)
		{
			LogUtilities.error(ERRORMESSAGESCREENSHOT);
		}
	}

	/*public static void captureAndroidScreenShot() {

		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ApplicationSetup.driver).getScreenshotAs(OutputType.FILE);
		try {
		// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src,
					new File(prop.getProperty("Screenshotpath") + System.currentTimeMillis() + ".png"));
		}
		catch (IOException e)
		{
			LogUtilities.error(ERRORMESSAGESCREENSHOT);
		}

	}*/
}
