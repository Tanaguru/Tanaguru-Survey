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
import org.opens.tanaguru.survey.test.util.DecoratorFactory;
import org.opens.tanaguru.survey.test.util.EntityFactory;
import org.opens.tanaguru.survey.view.data.SurveyList;
import org.opens.tanaguru.survey.view.data.SurveyListImpl;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class SurveyListFactoryImplTest extends TestCase {
    
    public SurveyListFactoryImplTest(String testName) {
        super(testName);
    }

    public void testCreateSurveyList() {
        SurveyListFactory slf = new SurveyListFactoryImpl();
        Object object = slf.createSurveyList();
        assertTrue(object instanceof SurveyListImpl);
    }

    public void testCreateSurveyListWithUserParam() {
        SurveyListFactory slf = getInitialisedSurveyListFactory("user");
        User user = EntityFactory.createUser("user@tanaguru.org", "userName", "userFirstName", true);
        SurveyList surveyList = slf.createSurveyList(user);
        assertEquals("userName", surveyList.getName());
        assertEquals("userFirstName", surveyList.getLabel());
        assertNull(surveyList.getDescription());
    }

    public void testGetSurveyListCollection() {
        SurveyListFactory slf = getInitialisedSurveyListFactory("user");
        int i = 1;
        for (SurveyList surveyList : slf.getSurveyListCollection()) {
            assertEquals("user"+i+"FromDAO", surveyList.getName());
            assertEquals("user"+i+"FirstNameFromDAO", surveyList.getLabel());
            assertNull(surveyList.getDescription());
            i++;
        }
    }

    private SurveyListFactory getInitialisedSurveyListFactory(String userListPrefix) {
        UserDataServiceDecorator udsd = DecoratorFactory.getUserDataServiceDecorator();
        ContractDataServiceDecorator cdsd = DecoratorFactory.getContractDataServiceDecorator();
        SurveyListFactoryImpl slf = new SurveyListFactoryImpl();
        slf.setUserDataServiceDecorator(udsd);
        slf.setContractDataService(cdsd);
        slf.setUserListPrefix(userListPrefix);
        return slf;
    }

}