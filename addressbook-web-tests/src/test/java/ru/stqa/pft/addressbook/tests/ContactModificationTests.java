package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("another group"));
      }
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("first").withLastName( "surname").withAddress("address").withHomePhone("88124561236")
              .withMobilePhone("79126789023").withWorkPhone("88123128547").withEmailAddress("first.surname@gmail.com")
              .withEmailAddress2("first.surname2@gmail.com").withEmailAddress3("first.surname3@gmail.com")
              .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Ivan").withLastName( "Petrov").withAddress("address test").withHomePhone("88121234567")
            .withMobilePhone("79129876543").withWorkPhone("88121478596").withEmailAddress("ivan.petrov@gmail.com")
            .withEmailAddress2("ipetrov@mail.ru").withEmailAddress3("ivan-petrov@yandex.ru");
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
