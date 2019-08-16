package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    app.log().inAsUser(app.getProperty("web.adminLogin"));
    app.log().inWithPassword(app.getProperty("web.adminPassword"));
    app.goTo().manageUsers();
    Users users = app.db().users();
    UserData user = users.iterator().next();
    String password = "password";
    app.james().doesUserExist(user.getUsername());
    app.james().drainEmail(user.getUsername(), password);
    app.user().initModificationById(user.getId());
    app.user().resetPassword();
    app.log().out();
    List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    String resetPasswordLink = findResetPasswordLink(mailMessages, user.getEmail());
    String newPassword = "NewPassword";
    app.user().changePassword(resetPasswordLink, newPassword);
    assertTrue(app.newSession().login(user.getUsername(), newPassword));

  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
