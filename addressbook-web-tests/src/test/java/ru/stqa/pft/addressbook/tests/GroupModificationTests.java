package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.navigationHelper.gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.groupHelper.selectGroup(before.size() - 1);
    app.groupHelper.initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"123", "234", "345");
    app.groupHelper.fillGroupForm(group);
    app.groupHelper.submitGroupModification();
    app.groupHelper.returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
