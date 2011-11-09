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
package org.opens.tanaguru.entity.decorator.tgol.user;

import java.util.List;
import javax.persistence.NoResultException;
import junit.framework.TestCase;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDAOMock;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDataServiceMock;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class UserDataServiceDecoratorImplTest extends TestCase {
    
    public UserDataServiceDecoratorImplTest(String testName) {
        super(testName);
    }

    public void testGetListUser() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        List<User> userList = udsd.getListUser("user");
        assertEquals(6, userList.size());
        int i = 1;
        for (User user : userList) {
            assertEquals("user"+i+"FromDAO@tanaguru.org", user.getEmail1());
            i++;
        }
    }

    public void testGetUserFromEmail() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        User user = udsd.getUserFromEmail("user1FromDataService@tanaguru.org");
        assertEquals("user1FromDataService", user.getName());
        try {
            user = udsd.getUserFromEmail("user1FromDAO");
        } catch (NoResultException nre) {
            assertTrue(true);
        }
        try {
            user = udsd.getUserFromEmail(null);
        } catch (NoResultException nre) {
            assertTrue(true);
        }
    }

    public void testGetUserFromName() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        User user = udsd.getUserFromName("user2FromDataService");
        assertEquals("user2FromDataService@tanaguru.org", user.getEmail1());
        try {
            user = udsd.getUserFromName("user2FromDAO");
        } catch (NoResultException nre) {
            assertTrue(true);
        }
        try {
            user = udsd.getUserFromName(null);
        } catch (NoResultException nre) {
            assertTrue(true);
        }
    }

    public void testIsAccountActivated() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        assertTrue(udsd.isAccountActivated("user1FromDataService@tanaguru.org"));
        assertFalse(udsd.isAccountActivated("user1FromDAO@tanaguru.org"));
        assertFalse(udsd.isAccountActivated(null));
    }

}