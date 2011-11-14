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

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class SynthesisDataImplTest extends TestCase {
    
    public SynthesisDataImplTest(String testName) {
        super(testName);
    }

    public void testSetGetDescription() {
        SynthesisData synthesisData = new SynthesisDataImpl();
        synthesisData.setSpotlightSurveyList(null);
        assertTrue(synthesisData.getSpotlightSurveyList().isEmpty());
        DetailedSurveyList dsl1 = new DetailedSurveyListImpl();
        DetailedSurveyList dsl2 = new DetailedSurveyListImpl();
        List<DetailedSurveyList> dslCollection = new ArrayList<DetailedSurveyList>();
        dslCollection.add(dsl1);
        dslCollection.add(dsl2);
        synthesisData.setSpotlightSurveyList(dslCollection);
        assertEquals(2,synthesisData.getSpotlightSurveyList().size());
    }

    public void testSetGetTotalNumberOfMonitoredSite() {
        SynthesisData synthesisData = new SynthesisDataImpl();
        synthesisData.setTotalNumberOfMonitoredSite(10);
        assertEquals(10, synthesisData.getTotalNumberOfMonitoredSite());
    }

    public void testConstructor() {
        SynthesisData synthesisData = new SynthesisDataImpl(10, null);
        assertEquals(10, synthesisData.getTotalNumberOfMonitoredSite());
        assertTrue(synthesisData.getSpotlightSurveyList().isEmpty());
        DetailedSurveyList dsl1 = new DetailedSurveyListImpl();
        DetailedSurveyList dsl2 = new DetailedSurveyListImpl();
        List<DetailedSurveyList> dslCollection = new ArrayList<DetailedSurveyList>();
        dslCollection.add(dsl1);
        dslCollection.add(dsl2);
        synthesisData = new SynthesisDataImpl(10, dslCollection);
        assertEquals(2,synthesisData.getSpotlightSurveyList().size());
    }

}
