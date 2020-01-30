package common;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Ashot_Screenshot{
	
	Screenshot screenshot;
	
	public void captureScreenshot(String imageName, WebDriver driver) throws IOException{
		screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File("./Screenshot/"+imageName+".jpg"));
	}
	
	public void captureScreenshot(String imageName, WebDriver driver, WebElement ele) throws IOException{
		screenshot = new AShot().takeScreenshot(driver, ele);
		ImageIO.write(screenshot.getImage(), "png", new File("./Screenshot/"+imageName+".png"));
	}
}
