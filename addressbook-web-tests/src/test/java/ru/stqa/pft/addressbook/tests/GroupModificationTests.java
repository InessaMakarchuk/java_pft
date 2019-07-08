package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.navigationHelper.gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));
    }
    app.groupHelper.selectGroup();
    app.groupHelper.initGroupModification();
    app.groupHelper.fillGroupForm(new GroupData("123", "234", "345"));
    app.groupHelper.submitGroupModification();
    app.groupHelper.returnToGroupPage();
  }
}
