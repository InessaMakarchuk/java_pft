package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first", "surname",
              "88124561236", "79126789023", "88123128547",
              "first.surname@gmail.com", "123"), true);
      app.navigationHelper.returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.contactHelper.selectContact(before.size() - 1);
    app.contactHelper.deleteSelectedContacts();
    app.contactHelper.acceptAlert();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
