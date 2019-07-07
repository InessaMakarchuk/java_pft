package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.navigationHelper.gotoGroupPage();
    app.groupHelper.initGroupCreation();
    app.groupHelper.fillGroupForm(new GroupData("TestGroup", null, null));
    app.groupHelper.submitGroupCreation();
    app.groupHelper.returnToGroupPage();
  }

}
