package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactInGroupsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group 1"));
        app.group().create(new GroupData().withName("group 2"));
      }
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("first").withLastName("surname").withAddress("address").withHomePhone("88124561236")
              .withMobilePhone("79126789023").withWorkPhone("88123128547").withEmailAddress("first.surname@gmail.com")
              .withEmailAddress2("first.surname2@gmail.com").withEmailAddress3("first.surname3@gmail.com")
              .inGroup(groups.iterator().next()), true);
    } else {
      for (ContactData contact : app.db().contacts()) {
        if (contact.getGroups().size() == app.db().groups().size()) {
          app.goTo().groupPage();
          app.group().create(new GroupData().withName("new group"));
          app.goTo().homePage();
        } else if (contact.getGroups().size() == 0){
          Groups groups = app.db().groups();
          app.contact().create(new ContactData()
                  .withFirstName("Ivan").withLastName("Petrov").withAddress("address test").withHomePhone("88121234567")
                  .withMobilePhone("79129876543").withWorkPhone("88121478596").withEmailAddress("ivan.petrov@gmail.com")
                  .withEmailAddress2("ipetrov@mail.ru").withEmailAddress3("ivan-petrov@yandex.ru")
                  .inGroup(groups.iterator().next()), true);
        } break;
      }
    }
  }


  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != groups.size()) {
        Groups groupsBefore = contact.getGroups();
        app.contact().addToGroup(contact);
        Contacts updatedContacts = app.db().contacts();
        for (ContactData updatedContact : updatedContacts) {
          if (updatedContact.getId() == contact.getId()) {
            Groups groupsAfter = updatedContact.getGroups();
            assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
            Groups groupsWithAdded = new Groups(groupsAfter);
            groupsWithAdded.removeAll(groupsBefore);
            assertThat(groupsAfter, equalTo(groupsBefore.withAdded(groupsWithAdded.iterator().next())));
          }
        }
        app.contact().showAllGroups();
      }
    }
  }


  @Test
  public void testRemoveContactFromGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != 0) {
        Groups groupsBefore = contact.getGroups();
        app.contact().removeFromGroup(contact);
        Contacts updatedContacts = app.db().contacts();
        for (ContactData updatedContact : updatedContacts) {
          if (updatedContact.getId() == contact.getId()) {
            Groups groupsAfter = updatedContact.getGroups();
            assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
            groupsBefore.removeAll(groupsAfter);
            assertThat(groupsAfter, equalTo(contact.getGroups().without(groupsBefore.iterator().next())));
          }
        }
        app.contact().showAllGroups();
      }
    }
  }


  /* Проверка для UI, нужно чистить кэш при добавлении контакта в группу, требуется доработать
  @Test
  public void testAddContactToGroupVerifyInUI() {
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
  */

}




