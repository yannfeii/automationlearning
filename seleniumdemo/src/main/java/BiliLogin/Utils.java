package BiliLogin;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.RandomUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Utils {

   // WebDriver driver;

    private static String BASE_PATH = "C:\\Users\\yanfeit\\Downloads\\";
    //开始遍历处距离左边的距离
    private static final int GEETEST_WIDTH_START_POSTION = 60;

    private static ChromeDriver driver = null;
    //文档截图后图片大小
    private static Point imageFullScreenSize = null;
    //html 大小
    private static Point htmlFullScreenSize = null;

    public WebDriver initDriver(){
        System.setProperty("webdriver.chrome.driver", "D:\\SelfStudy\\SeleniumDemo\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }


    public void loginNext() throws InterruptedException {


        try {
            for(int i = 0; i < 1; i++){
                Actions actions = new Actions(driver);


                //图一
                BufferedImage image = getImageEle(driver.findElement(By.cssSelector("canvas[class='geetest_canvas_bg geetest_absolute']")));
                ImageIO.write(image, "png",  new File(BASE_PATH + "slider.png"));
                //设置原图可见
                driver.executeScript("document.getElementsByClassName(\"geetest_canvas_fullbg\")[0].setAttribute('style', 'display: block')\n");
                //图二x

                image = getImageEle(driver.findElement(By.cssSelector("canvas[class='geetest_canvas_fullbg geetest_fade geetest_absolute']")));
                ImageIO.write(image, "png",  new File(BASE_PATH + "original.png"));
                //隐藏原图
                driver.executeScript("document.getElementsByClassName(\"geetest_canvas_fullbg\")[0].setAttribute('style', 'display: none')\n");
                WebElement element = null;
                element = driver.findElement(By.className("geetest_slider_button"));
                actions.clickAndHold(element).perform();

                int moveDistance = calcMoveDistance();

                int d = 0;

                List<MoveEntity> list = getMoveEntity(moveDistance+6);

                for(MoveEntity moveEntity : list){
                    actions.moveByOffset(moveEntity.getX(), moveEntity.getY()).perform();
                    Thread.sleep(moveEntity.getSleepTime());
                }
                actions.release(element).perform();
                Thread.sleep(1 * 1000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            System.out.println("*************finlly*******************");

        }
    }
    public By InputElementType(String locatorType, String locatorValue){
        if(locatorType.equals("id")){
            return By.id(locatorValue);
        }else if(locatorType.equals("className")){
            return By.className(locatorValue);
        }else if(locatorType.equals("name")) {
            return By.name(locatorValue);
        }else {
            return By.xpath(locatorValue);
        }
    }

    public Boolean waitEle(String location, String locVaule){
        boolean go = false;
        By exec = this.InputElementType(location,locVaule);
        WebDriverWait process = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            //process.until(ExpectedConditions.presenceOfElementLocated(exec));
            //process.until(ExpectedConditions.visibilityOfElementLocated(exec));
            process.until(ExpectedConditions.elementToBeClickable(exec));
            go = true;
        }catch (TimeoutException t){
            System.out.println("*************Do not have such element*******************");
            System.out.println("*************Do not have such element*******************");
            System.out.println("*************Do not have such element*******************");
            System.out.println("*************Do not have such element*******************");
            System.out.println("*************Do not have such element*******************");
        }
        return go;
    }

    private  BufferedImage getImageEle(WebElement ele) {
        try {
            byte[] fullPage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            BufferedImage fullImg = ImageIO.read(new ByteArrayInputStream(fullPage));
            ImageIO.write(fullImg, "png",  new File(BASE_PATH + "full.png"));
            if (imageFullScreenSize == null){//1366,623
                imageFullScreenSize = new Point(fullImg.getWidth(), fullImg.getHeight());
            }
            WebElement element = driver.findElement(By.className("geetest_widget"));

            if(htmlFullScreenSize == null){
                //htmlFullScreenSize = new Point(1366, 623);
                System.out.println("*************element.getSize().getWidth()*******************"+element.getSize().getWidth());
                System.out.println("*************element.getSize().getHeight()*******************"+element.getSize().getHeight());
                htmlFullScreenSize = new Point(element.getSize().getWidth(), element.getSize().getHeight());
            }
            Point point = ele.getLocation();
            int eleWidth = (int)(ele.getSize().getWidth() / (float)element.getSize().width * (float)fullImg.getWidth());
            //int eleWidth = 260;
            int eleHeight = (int) (ele.getSize().getHeight() / (float)element.getSize().height * (float)fullImg.getHeight());
            //int eleHeight = 160;

            System.out.println("*************point.getX()*******************"+point.getX());
            System.out.println("*************point.getX()*******************"+point.getY());
            System.out.println("*************eleWidth*******************"+eleWidth);
            System.out.println("*************eleHeight*******************"+eleHeight);

            //BufferedImage eleScreenShot = fullImg.getSubimage((int)(point.getX() / (float)element.getSize().width * (float)fullImg.getWidth()), (int)(point.getY() / (float)element.getSize().height * (float)fullImg.getHeight()), eleWidth, eleHeight);
            BufferedImage eleScreenShot = fullImg.getSubimage(1045,point.getY(),260,160);
            return eleScreenShot;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<MoveEntity> getMoveEntity(int distance){
        List<MoveEntity> list = new ArrayList<MoveEntity>();
        int i = 0;
        do {
            MoveEntity moveEntity = new MoveEntity();
            int r = RandomUtils.nextInt(5, 8);
            moveEntity.setX(r);
            moveEntity.setY(RandomUtils.nextInt(0, 1)==1?RandomUtils.nextInt(0, 2):0-RandomUtils.nextInt(0, 2));
            int s = 0;
            if(i/Double.valueOf(distance)>0.05){
                if(i/Double.valueOf(distance)<0.85){
                    s = RandomUtils.nextInt(2, 5);
                }else {
                    s = RandomUtils.nextInt(10, 15);
                }
            }else{
                s = RandomUtils.nextInt(20, 30);
            }
            moveEntity.setSleepTime(s);
            list.add(moveEntity);
            i = i + r;
        } while (i <= distance+5);
        boolean cc= i>distance;
        for (int j = 0; j < Math.abs(distance-i); ) {
            int r = RandomUtils.nextInt(1, 3);
            MoveEntity moveEntity = new MoveEntity();
            moveEntity.setX(cc?-r:r);
            moveEntity.setY(0);
            moveEntity.setSleepTime(RandomUtils.nextInt(100, 200));
            list.add(moveEntity);
            j = j+r;
        }
        return list;
    }

    class MoveEntity{
        private int x;
        private int y;
        private int sleepTime;//毫秒

        public MoveEntity(){

        }

        public MoveEntity(int x, int y, int sleepTime) {
            this.x = x;
            this.y = y;
            this.sleepTime = sleepTime;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSleepTime() {
            return sleepTime;
        }

        public void setSleepTime(int sleepTime) {
            this.sleepTime = sleepTime;
        }
    }

    /**
     * 根据original.png和slider.png计算需要移动的距离
     * @return
     */

    private int calcMoveDistance() {
        //小方块距离左边界距离
        int START_DISTANCE = 6;
        int startWidth = (int)(GEETEST_WIDTH_START_POSTION * (imageFullScreenSize.x + 0.0f)/ htmlFullScreenSize.x);
        START_DISTANCE = (int)(START_DISTANCE * (imageFullScreenSize.x + 0.0f)/ htmlFullScreenSize.x);
        try {
            BufferedImage geetest1 = ImageIO.read(new File(BASE_PATH + "original.png"));
            BufferedImage geetest2 = ImageIO.read(new File(BASE_PATH + "slider.png"));
            for (int i = startWidth; i < geetest1.getWidth(); i++){
                for(int j = 0; j < geetest1.getHeight(); j++){
                    int[] fullRgb = new int[3];
                    fullRgb[0] = (geetest1.getRGB(i, j)  & 0xff0000) >> 16;
                    fullRgb[1] = (geetest1.getRGB(i, j)  & 0xff00) >> 8;
                    fullRgb[2] = (geetest1.getRGB(i, j)  & 0xff);

                    int[] bgRgb = new int[3];
                    bgRgb[0] = (geetest2.getRGB(i, j)  & 0xff0000) >> 16;
                    bgRgb[1] = (geetest2.getRGB(i, j)  & 0xff00) >> 8;
                    bgRgb[2] = (geetest2.getRGB(i, j)  & 0xff);
                    if(difference(fullRgb, bgRgb) > 255){
                        int moveDistance = (int)((i - START_DISTANCE) / ((imageFullScreenSize.x + 0.0f)/ htmlFullScreenSize.x));
                        return moveDistance;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("计算移动距离失败");
    }
    private int difference(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]);
    }
}
