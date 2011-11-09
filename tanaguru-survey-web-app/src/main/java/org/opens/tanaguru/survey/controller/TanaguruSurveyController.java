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

import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.util.TanaguruSurveyParamKeyStore;
import org.opens.tanaguru.survey.util.TanaguruSurveyViewKeyStore;
import org.opens.tanaguru.survey.view.data.factory.DetailedSurveyListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class TanaguruSurveyController {

    private DetailedSurveyListFactory detailedSurveyListFactory;
    @Autowired
    public void setDetailedSurveyListFactory(DetailedSurveyListFactory detailedSurveyListFactory) {
        this.detailedSurveyListFactory = detailedSurveyListFactory;
    }

    public TanaguruSurveyController() {
        super();
    }

    @RequestMapping(value=TanaguruSurveyViewKeyStore.INDEX_URL, method=RequestMethod.GET)
    public String displayIndexPage(Model model) {
        model.addAttribute(
                TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY,
                detailedSurveyListFactory.getSurveyListCollection());
        return TanaguruSurveyViewKeyStore.INDEX_VIEW_NAME;
    }

    @RequestMapping(value=TanaguruSurveyViewKeyStore.URL_LIST_URL, method=RequestMethod.GET)
    public String displayUrlListPage(
            @RequestParam(TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY) String listUser,
            Model model) throws ForbiddenUserException {
        model.addAttribute(
                TanaguruSurveyParamKeyStore.DETAILED_SURVEY_LIST_KEY,
                detailedSurveyListFactory.createDetailedSurveyList(listUser));
        return TanaguruSurveyViewKeyStore.URL_LIST_VIEW_NAME;
    }
    
    @RequestMapping(value=TanaguruSurveyViewKeyStore.TOP_LIST_URL, method=RequestMethod.GET)
    public String displayTopListPage(Model model) {
//        model.addAttribute(
//                TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY,
//                surveyListFactory.getSurveyListCollection());
        return TanaguruSurveyViewKeyStore.TOP_LIST_VIEW_NAME;
    }

}