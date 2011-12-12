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
import javax.persistence.NoResultException;
import org.opens.tanaguru.entity.dao.tgol.user.TanaguruSurveyUserDAO;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.survey.test.util.EntityFactory;
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
        userList.add(EntityFactory.createUser(
                Long.valueOf(1),
                "user1@tanaguru.org",
                "user1FromDAO",
                "user1FirstNameFromDAO",
                true));
        userList.add(EntityFactory.createUser(
                Long.valueOf(2),
                "user2@tanaguru.org",
                "user2FromDAO",
                "user2FirstNameFromDAO",
                false));
        userList.add(EntityFactory.createUser(
                Long.valueOf(3),
                "user3@tanaguru.org",
                "",
                null,
                false));
        userList.add(EntityFactory.createUser(
                Long.valueOf(4),
                "user4@tanaguru.org",
                null,
                "",
                false));
        userList.add(EntityFactory.createUser(
                Long.valueOf(5),
                "user5@tanaguru.org",
                null,
                null,
                false));
        userList.add(EntityFactory.createUser(
                Long.valueOf(6),
                "user6@tanaguru.org",
                "",
                "",
                false));
    }

    @Override
    protected Class<? extends User> getEntityClass() {
        return UserImpl.class;
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

    @Override
    public User read (Long key) {
        for (User user : userList) {
            if (user.getId()!=null && user.getId().equals(key)) {
                return user;
            }
        }
        throw new NoResultException();
    }

    @Override
    public Integer findUserResultAverage(Long idUser) {
        return 50;
    }

}