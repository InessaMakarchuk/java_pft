package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("first").withLastName( "surname").withAddress("address").withHomePhone("88124561236").withMobilePhone("79126789023")
              .withWorkPhone("88123128547").withEmailAddress("first.surname@gmail.com").withEmailAddress2("first.surname2@gmail.com")
              .withEmailAddress3("first.surname3@gmail.com").withGroup("123"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/23234.jpg");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("first").withLastName( "surname").withAddress("address").withHomePhone("88124561236")
            .withMobilePhone("79126789023").withWorkPhone("88123128547").withEmailAddress("first.surname@gmail.com")
            .withEmailAddress2("first.surname2@gmail.com").withEmailAddress3("first.surname3@gmail.com").withPhoto(photo).withGroup("123");
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
