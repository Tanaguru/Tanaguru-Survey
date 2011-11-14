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
package org.opens.tanaguru.survey.view.data;

import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class SurveyListImplTest extends TestCase {
    
    public SurveyListImplTest(String testName) {
        super(testName);
    }

    public void testSetGetId() {
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setId("list-test");
        assertEquals("list-test", surveyList.getId());
    }

    public void testSetGetName() {
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setName("Name");
        assertEquals("Name", surveyList.getName());
    }

    public void testSetGetLabel() {
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setLabel("Label");
        assertEquals("Label", surveyList.getLabel());
    }

    public void testSetGetDescription() {
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setDescription("Description");
        assertEquals("Description", surveyList.getDescription());
    }

    public void testSetGetNumberOfContracts() {
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setNumberOfContracts(10);
        assertEquals(10, surveyList.getNumberOfContracts());
    }

    public void testConstructor() {
        SurveyList surveyList = new SurveyListImpl("list-test", "Name", "Label", "Description", 10);
        assertEquals("list-test", surveyList.getId());
        assertEquals("Description", surveyList.getDescription());
        assertEquals("Label", surveyList.getLabel());
        assertEquals("Name", surveyList.getName());
        assertEquals(10, surveyList.getNumberOfContracts());
    }
    
}