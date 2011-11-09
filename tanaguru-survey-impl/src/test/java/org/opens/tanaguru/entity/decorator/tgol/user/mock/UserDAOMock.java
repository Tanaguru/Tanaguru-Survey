/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.decorator.tgol.user.mock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.opens.tanaguru.entity.dao.tgol.user.TanaguruSurveyUserDAO;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class UserDAOMock extends AbstractJPADAO<User, Long>
        implements TanaguruSurveyUserDAO {

    private List<User> userList = new LinkedList<User>();

    public UserDAOMock() {
        userList.add(createUser(
                "user1FromDAO@tanaguru.org",
                "user1FromDAO",
                "user1FirstNameFromDAO",
                true));
        userList.add(createUser(
                "user2FromDAO@tanaguru.org",
                "user2FromDAO",
                "user2FirstNameFromDAO",
                false));
        userList.add(createUser(
                "user3FromDAO@tanaguru.org",
                "",
                null,
                false));
        userList.add(createUser(
                "user4FromDAO@tanaguru.org",
                null,
                "",
                false));
        userList.add(createUser(
                "user5FromDAO@tanaguru.org",
                null,
                null,
                false));
        userList.add(createUser(
                "user6FromDAO@tanaguru.org",
                "",
                "",
                false));
    }

    @Override
    protected Class<? extends User> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findListUser(String userListPrefix) {
        List<User> localUserList = new ArrayList<User>();
        for (User user : userList) {
            if (user.getEmail1().startsWith(userListPrefix)) {
                localUserList.add(user);
            }
        }
        return localUserList;
    }

    private User createUser (String email, String name, String firstName,boolean isActivated)  {
        User user = new UserImpl();
        user.setEmail1(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setAccountActivation(isActivated);
        return user;
    }

}