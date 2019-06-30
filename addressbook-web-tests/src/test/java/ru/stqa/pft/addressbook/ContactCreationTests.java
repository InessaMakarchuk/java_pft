package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    gotoCreateContact();
    fillContactForm(new ContactData("first", "surname", "88124561236", "79126789023", "88123128547", "first.surname@gmail.com"));
    submitContactCreation();
    returtToHomePage();
  }

}
