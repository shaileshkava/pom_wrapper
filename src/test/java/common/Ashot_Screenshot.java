package common;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Ashot_Screenshot extends ReusableMethods{
	
	Screenshot screenshot;
	
	/**This method is used to capture entire page screenshot
	 * 
	 * @param imageName
	 * @param driver
	 * @throws IOException
	 * @author shakava
	 */
	public void captureScreenshot(String imageName, WebDriver driver) throws IOException{
		System.out.println("Capturing screenshot");
		screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File("./Screenshot/"+imageName+".jpg"));
	}
	
	/**This method is used to capture highlighted screenshot of given element
	 * 
	 * @param imageName
	 * @param driver
	 * @param ele
	 * @throws IOException
	 * @author shakava
	 */
	public void captureScreenshot(String imageName, WebDriver driver, WebElement ele) throws IOException{
		
		highlightWebElement(ele, driver);
		screenshot = new AShot().takeScreenshot(driver, ele);
		ImageIO.write(screenshot.getImage(), "png", new File("./Screenshot/"+imageName+".png"));
	}
}
