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
package org.opens.tanaguru.survey.view.data.factory;

import junit.framework.TestCase;
import org.opens.tanaguru.entity.decorator.tgol.contract.mock.ContractDataServiceMock;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecoratorImpl;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDAOMock;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDataServiceMock;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.view.data.SurveyList;
import org.opens.tanaguru.survey.view.data.SurveyListImpl;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class DetailedSurveyListFactoryImplTest extends TestCase {
    
    public DetailedSurveyListFactoryImplTest(String testName) {
        super(testName);
    }

    public void testCreateSurveyList() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        DetailedSurveyListFactoryImpl dslf = new DetailedSurveyListFactoryImpl();
        dslf.setUserListPrefix("user");
        dslf.setUserDataServiceDecorator(udsd);
        dslf.setContractDataService(new ContractDataServiceMock());
        SurveyList surveyList = dslf.createDetailedSurveyList("user1FromDataService@tanaguru.org");
        assertEquals("user1FromDataService", surveyList.getName());
        assertEquals("user1FirstNameFromDataService", surveyList.getLabel());
        assertNull(surveyList.getDescription());
        try {
            surveyList = dslf.createDetailedSurveyList("user3FromDataService@tanaguru.org");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user4FromDataService@tanaguru.org");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user5FromDataService@tanaguru.org");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user6FromDataService@tanaguru.org");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        dslf.setUserListPrefix("other-prefix");
        try {
            surveyList = dslf.createDetailedSurveyList("user1FromDataService@tanaguru.org");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("");
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList(null);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
    }

    private User createUser (String email, String name, String firstName)  {
        User user = new UserImpl();
        user.setEmail1(email);
        user.setName(name);
        user.setFirstName(firstName);
        return user;
    }

}