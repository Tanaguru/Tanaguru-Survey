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
import org.opens.tanaguru.survey.test.util.DecoratorFactory;
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
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        List<User> userList = udsd.getListUser("user");
        assertEquals(6, userList.size());
        int i = 1;
        for (User user : userList) {
            assertEquals("user"+i+"FromDAO", user.getName());
            i++;
            // the 2 first user of UserDAOMock have a name
            if (i==2) {
                break;
            }
        }
    }

    public void testGetUserFromEmail() {
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        User user = udsd.getUserFromEmail("user1@tanaguru.org");
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
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        User user = udsd.getUserFromName("user2FromDataService");
        assertEquals("user2FirstNameFromDataService", user.getFirstName());
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
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        assertTrue(udsd.isAccountActivated("callFromDataService"));
        assertFalse(udsd.isAccountActivated("notCalledFromDAO"));
    }

}