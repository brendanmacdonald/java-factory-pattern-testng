//package com.factory.pattern.utils;
//
//import io.qameta.allure.Attachment;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import java.io.IOException;
//
//public class ScreenShotOnFailure implements MethodRule {
//
//    private WebDriver driver;
//
//    public ScreenShotOnFailure(WebDriver driver){
//        this.driver = driver;
//    }
//
//    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
//        return new Statement() {
//            @Override
//            public void evaluate() throws Throwable {
//                try {
//                    statement.evaluate();
//                } catch (Throwable t) {
//                    captureScreenShot(frameworkMethod.getName());
//                    throw t; // rethrow to allow the failure to be reported to JUnit
//                }
//            }
//
//            @Attachment(value = "Screenshot", type = "image/png")
//            public byte[] captureScreenShot(String fileName) throws IOException {
//                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            }
//        };
//    }
//}
