package com.company.referencebooks.web.screens.user;

import com.haulmont.cuba.gui.screen.*;
import com.company.referencebooks.entity.User;

@UiController("referencebooks_User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@LoadDataBeforeShow
public class UserBrowse extends StandardLookup<User> {
}