package com.company.referencebooks.web.screens.user;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.User;

@UiController("referencebooks_User.edit")
@UiDescriptor("user-edit.xml")
@EditedEntityContainer("userDc")
@LoadDataBeforeShow
public class UserEdit extends StandardEditor<User> {
}