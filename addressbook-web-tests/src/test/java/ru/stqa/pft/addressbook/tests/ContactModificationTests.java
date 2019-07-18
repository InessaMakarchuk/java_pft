package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first", "surname",
              "88124561236", "79126789023", "88123128547",
              "first.surname@gmail.com", "123"), true);
      app.navigationHelper.returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.contactHelper.selectContact(before.size() - 1);
    app.contactHelper.initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"first", "surname",
            "88124561236", "79126789023", "88123128547",
            "first.surname@gmail.com", null);
    app.contactHelper.fillContactForm(contact, false);
    app.contactHelper.submitContactModification();
    app.navigationHelper.returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
