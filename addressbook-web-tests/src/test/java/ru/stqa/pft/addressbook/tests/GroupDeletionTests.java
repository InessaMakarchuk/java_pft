package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {
    app.navigationHelper.gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));
    }
    app.groupHelper.selectGroup();
    app.groupHelper.deleteSelectedGroups();
    app.groupHelper.returnToGroupPage();
  }

}
