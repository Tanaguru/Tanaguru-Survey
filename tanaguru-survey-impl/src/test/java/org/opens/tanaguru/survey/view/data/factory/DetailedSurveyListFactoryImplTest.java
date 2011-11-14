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
import org.opens.tanaguru.entity.decorator.tgol.contract.ContractDataServiceDecorator;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecorator;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.test.util.DecoratorFactory;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;

/**
 *
 * @author jkowalczyk
 */
public class DetailedSurveyListFactoryImplTest extends TestCase {
    
    public DetailedSurveyListFactoryImplTest(String testName) {
        super(testName);
    }

    public void testCreateSurveyList() {
        DetailedSurveyListFactory dslf = getInitialisedSurveyListFactory("user");
        DetailedSurveyList surveyList = dslf.createDetailedSurveyList("user1@tanaguru.org", true);
        assertEquals("user1FromDataService", surveyList.getName());
        assertEquals("user1FirstNameFromDataService", surveyList.getLabel());
        assertEquals(2, surveyList.getContractCollection().size());
        assertNull(surveyList.getDescription());
        surveyList = dslf.createDetailedSurveyList("user1@tanaguru.org", false);
        assertEquals("user1FromDataService", surveyList.getName());
        assertEquals("user1FirstNameFromDataService", surveyList.getLabel());
        assertEquals(0, surveyList.getContractCollection().size());
        assertNull(surveyList.getDescription());
        try {
            surveyList = dslf.createDetailedSurveyList("user3@tanaguru.org", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user4@tanaguru.org", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user5@tanaguru.org", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("user6@tanaguru.org", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        dslf.setUserListPrefix("other-prefix");
        try {
            surveyList = dslf.createDetailedSurveyList("user1@tanaguru.org", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList("", true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        try {
            surveyList = dslf.createDetailedSurveyList(null, true);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
    }

    public static DetailedSurveyListFactory getInitialisedSurveyListFactory(String userListPrefix) {
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        ContractDataServiceDecorator cdsd = DecoratorFactory.getContractDataServiceDecorator();
        DetailedSurveyListFactoryImpl slf = new DetailedSurveyListFactoryImpl();
        slf.setUserDataServiceDecorator(udsd);
        slf.setContractDataService(cdsd);
        slf.setUserListPrefix(userListPrefix);
        return slf;
    }

}