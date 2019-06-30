package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.navigationHelper.gotoCreateContactPage();
    app.contactHelper.fillContactForm(new ContactData("first", "surname", "88124561236", "79126789023", "88123128547", "first.surname@gmail.com"));
    app.contactHelper.submitContactCreation();
    app.contactHelper.returtToHomePage();
  }

}
