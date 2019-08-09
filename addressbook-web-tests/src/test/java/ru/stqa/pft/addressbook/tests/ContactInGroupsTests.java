package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;


public class ContactInGroupsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("another group"));
      }
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/23234.jpg");
      app.contact().create(new ContactData()
              .withFirstName("first").withLastName("surname").withAddress("address").withHomePhone("88124561236")
              .withMobilePhone("79126789023").withWorkPhone("88123128547").withEmailAddress("first.surname@gmail.com")
              .withEmailAddress2("first.surname2@gmail.com").withEmailAddress3("first.surname3@gmail.com")
              .withPhoto(photo).inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != groups.size()) {
        app.contact().selectContactById(contact.getId());
        app.contact().selectGroup(contact);
        app.contact().addToSelectedCroups();
        app.contact().showInSelectedGroup();
        Contacts contactsInGroup = app.contact().all();
        Contacts thisContact = new Contacts(contactsInGroup.stream()
                .map((g) -> new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
                        .withLastName(contact.getLastName()).withAddress(contact.getAddress())
                        .withAllPhones(Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                                .stream().filter((s) -> !s.equals(""))
                                .collect(Collectors.joining("\n")))
                        .withAllEmailAddresses(Arrays.asList(contact.getEmailAddress(), contact.getEmailAddress2(), contact.getEmailAddress3())
                                .stream().filter((s) -> !s.equals(""))
                                .map(ContactInformationTests::cleaned)
                                .collect(Collectors.joining("\n"))))
                .collect(Collectors.toList()));
        Assert.assertTrue(contactsInGroup.containsAll(thisContact));
        app.contact().showAllGroups();
      }
    }
  }


  @Test
  public void testRemoveContactFromGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != 0) {
        app.contact().filterByGroup(contact);
        app.contact().selectContactById(contact.getId());
        app.contact().removeFromSelectedGroups();
        app.contact().showInSelectedGroup();
        Contacts contactsInGroup = app.contact().all();
        Contacts thisContact = new Contacts(contactsInGroup.stream()
                .map((g) -> new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName())
                        .withLastName(contact.getLastName()).withAddress(contact.getAddress())
                        .withAllPhones(Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                                .stream().filter((s) -> !s.equals(""))
                                .collect(Collectors.joining("\n")))
                        .withAllEmailAddresses(Arrays.asList(contact.getEmailAddress(), contact.getEmailAddress2(), contact.getEmailAddress3())
                                .stream().filter((s) -> !s.equals(""))
                                .map(ContactInformationTests::cleaned)
                                .collect(Collectors.joining("\n"))))
                .collect(Collectors.toList()));
        Assert.assertFalse(contactsInGroup.containsAll(thisContact));
        app.contact().showAllGroups();
      }
    }
  }
}