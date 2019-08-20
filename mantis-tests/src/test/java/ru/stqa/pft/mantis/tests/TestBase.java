package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php" , "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  @BeforeMethod
  public void LogTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void LogTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = app.soap().getMantisConnect();
    IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
    String status = issue.getStatus().getName();
    if (status.equals("resolved")) {
      return false;
    } else {
      return true;
    }
  }

}

