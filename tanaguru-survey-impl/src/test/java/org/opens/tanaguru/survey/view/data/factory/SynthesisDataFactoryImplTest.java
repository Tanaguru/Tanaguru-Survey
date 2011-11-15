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
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.test.util.DecoratorFactory;
import org.opens.tanaguru.survey.view.data.SynthesisData;

/**
 *
 * @author jkowalczyk
 */
public class SynthesisDataFactoryImplTest extends TestCase {
    
    public SynthesisDataFactoryImplTest(String testName) {
        super(testName);
    }

    public void testCreateContractResult() {
        SynthesisDataFactoryImpl sdfi = new SynthesisDataFactoryImpl();
        sdfi.setContractDataServiceDecorator(DecoratorFactory.getContractDataServiceDecorator());
        sdfi.setUserDataServiceDecorator(DecoratorFactory.getUserDataServiceDecorator());
        sdfi.setDetailedSurveyListFactory(DetailedSurveyListFactoryImplTest.getInitialisedSurveyListFactory(null));
        sdfi.setSpotlightList(null);
        sdfi.setUserListPrefix("mock-label");
        SynthesisData idp = sdfi.createSynthesisData();
        assertEquals(4, idp.getTotalNumberOfMonitoredSite());
        assertTrue(idp.getSpotlightSurveyList().isEmpty());
        sdfi.setUserListPrefix(null);
        idp = sdfi.createSynthesisData();
        assertEquals(4, idp.getTotalNumberOfMonitoredSite());
        sdfi.setUserListPrefix("other-label");
        idp = sdfi.createSynthesisData();
        assertEquals(0, idp.getTotalNumberOfMonitoredSite());
        sdfi.setSpotlightList("user1@tanaguru.org");
        idp = sdfi.createSynthesisData();
        assertEquals(1, idp.getSpotlightSurveyList().size());
        sdfi.setSpotlightList("user1@tanaguru.org;user2@tanaguru.org");
        idp = sdfi.createSynthesisData();
        assertEquals(2, idp.getSpotlightSurveyList().size());
        sdfi.setSpotlightList("user3@tanaguru.org");
        try {
            idp = sdfi.createSynthesisData();
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
    }

}
