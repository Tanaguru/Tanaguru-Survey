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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class UserDataServiceMock implements UserDataService{

    private List<User> userList = new ArrayList<User>();

    public UserDataServiceMock(){
        userList.add(createUser("user1FromDataService@tanaguru.org", "user1FromDataService", "user1FirstNameFromDataService", true));
        userList.add(createUser("user2FromDataService@tanaguru.org", "user2FromDataService", "user2FirstNameFromDataService", false));
        userList.add(createUser("user3FromDataService@tanaguru.org", "", "", true));
        userList.add(createUser("user4FromDataService@tanaguru.org", null, null, false));
        userList.add(createUser("user5FromDataService@tanaguru.org", "", null, false));
        userList.add(createUser("user6FromDataService@tanaguru.org", null, "", false));
    }

    @Override
    public User getUserFromEmail(String email) {
        for (User user : userList) {
            if (user.getEmail1().equalsIgnoreCase(email)) {
                return user;
            }
        }
        throw new NoResultException();
    }

    @Override
    public User getUserFromName(String name) {
        for (User user : userList) {
            if (user.getName()!=null && user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        throw new NoResultException();
    }

    @Override
    public boolean isAccountActivated(String email) {
        for (User user : userList) {
            if (user.getEmail1().equalsIgnoreCase(email)) {
                return user.isAccountActivated();
            }
        }
        return false;
    }

    private User createUser (String email, String name, String firstName, boolean isActivated)  {
        User user = new UserImpl();
        user.setEmail1(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setAccountActivation(isActivated);
        return user;
    }

    @Override
    public User create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<User> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User read(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User saveOrUpdate(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<User> saveOrUpdate(Set<User> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<User, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<User> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User update(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}