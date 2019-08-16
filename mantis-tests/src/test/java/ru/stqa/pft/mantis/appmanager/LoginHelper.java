package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends BaseHelper {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void inAsUser(String username) {
    type(By.name("username"), username);
    wd.findElement(By.id("login-form")).submit();
  }

  public void inWithPassword(String password) {
    type(By.name("password"), password);
    wd.findElement(By.id("login-form")).submit();
  }

  public void out() {
    wd.get(app.getProperty("web.baseUrl") + "/logout_page.php");
  }
}
