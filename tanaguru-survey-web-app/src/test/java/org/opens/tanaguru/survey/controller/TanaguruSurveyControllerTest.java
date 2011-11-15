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
package org.opens.tanaguru.survey.controller;

import junit.framework.TestCase;
import org.opens.tanaguru.survey.util.TanaguruSurveyViewKeyStore;
import org.opens.tanaguru.survey.view.data.factory.DetailedSurveyListFactoryMock;
import org.opens.tanaguru.survey.view.data.factory.SynthesisDataFactoryMock;
import org.springframework.ui.ExtendedModelMap;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyControllerTest extends TestCase {
    
    public TanaguruSurveyControllerTest(String testName) {
        super(testName);
    }

    public void testDisplayCategoriesPage() {
        TanaguruSurveyController tsc = getInitialisedTanaguruSurveyController();
        assertEquals(TanaguruSurveyViewKeyStore.CATEGORIES_VIEW_NAME, tsc.displayCategoriesPage(new ExtendedModelMap()));
    }

    public void testDisplayCategoriesDetailedPage () {
        TanaguruSurveyController tsc = getInitialisedTanaguruSurveyController();
        assertEquals(TanaguruSurveyViewKeyStore.CATEGORIES_DETAILED_VIEW_NAME, tsc.displayCategoriesDetailedPage(Long.valueOf(1),new ExtendedModelMap()));
    }

    public void testDisplayIndexPage() {
        TanaguruSurveyController tsc = getInitialisedTanaguruSurveyController();
        assertEquals(TanaguruSurveyViewKeyStore.INDEX_VIEW_NAME, tsc.displayIndexPage(new ExtendedModelMap()));
    }

    private TanaguruSurveyController getInitialisedTanaguruSurveyController() {
        return new TanaguruSurveyController(
                new SynthesisDataFactoryMock(),
                new DetailedSurveyListFactoryMock());
    }

}
