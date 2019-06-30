package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("TestGroup", "test1", "test2"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
