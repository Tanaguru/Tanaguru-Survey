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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.decorator.tgol.contract.ContractDataServiceDecorator;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecorator;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;
import org.opens.tanaguru.survey.view.data.SynthesisData;
import org.opens.tanaguru.survey.view.data.SynthesisDataImpl;
import org.opens.tgol.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class SynthesisDataFactoryImpl implements SynthesisDataFactory {

    private static final Logger LOGGER = Logger.getLogger(SynthesisDataFactoryImpl.class);

    private String userListPrefix = "";
    public void setUserListPrefix(String userListPrefix) {
        if (userListPrefix != null) {
            this.userListPrefix = userListPrefix;
        }
    }

    private Collection<String> spotlightList = new HashSet<String>();
    public void setSpotlightList (String spotlightList) {
        if (spotlightList != null) {
            this.spotlightList.addAll(Arrays.asList(spotlightList.split(";")));
        }
    }

    private DetailedSurveyListFactory detailedSurveyListFactory;
    @Autowired
    public void setDetailedSurveyListFactory(DetailedSurveyListFactory detailedSurveyListFactory) {
        this.detailedSurveyListFactory = detailedSurveyListFactory;
    }

    private ContractDataServiceDecorator contractDataServiceDecorator;
    @Autowired
    public void setContractDataServiceDecorator(ContractDataServiceDecorator contractDataServiceDecorator) {
        this.contractDataServiceDecorator = contractDataServiceDecorator;
    }
    
    private UserDataServiceDecorator userDataServiceDecorator;
    @Autowired
    public void setUserDataServiceDecorator(UserDataServiceDecorator userDataServiceDecorator) {
        this.userDataServiceDecorator = userDataServiceDecorator;
    }

    @Override
    public SynthesisData createSynthesisData() {
        SynthesisData synthesisData = new SynthesisDataImpl();
        Date beginProcessDate = null;
        Date endProcessDate = null;
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        synthesisData.setTotalNumberOfMonitoredSite(
                contractDataServiceDecorator.getNumberOfContractsFromPrefix(userListPrefix));
        if (LOGGER.isDebugEnabled()) {
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Calculating total number of sites took " + (endProcessDate.getTime() - beginProcessDate.getTime()) + " ms");
        }
        List<DetailedSurveyList> detailedSurveyListList =
                new LinkedList<DetailedSurveyList>();
        for (String listUser : spotlightList) {
            LOGGER.debug("Creating DetailedSurveyList instance for " + listUser + " as spotlighted list");
            User user = userDataServiceDecorator.getUserFromEmail(listUser);
            if (user != null)
            detailedSurveyListList.add(
                    detailedSurveyListFactory.createDetailedSurveyList(
                    user.getId(),
                    false));
        }
        synthesisData.setSpotlightSurveyList(detailedSurveyListList);
        return synthesisData;
    }

}