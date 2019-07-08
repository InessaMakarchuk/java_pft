package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.navigationHelper.gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));
  }

}
