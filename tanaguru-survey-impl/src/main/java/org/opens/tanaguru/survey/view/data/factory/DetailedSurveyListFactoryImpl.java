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

import java.util.Collection;
import org.apache.log4j.Logger;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;
import org.opens.tanaguru.survey.view.data.DetailedSurveyListImpl;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class DetailedSurveyListFactoryImpl extends SurveyListFactoryImpl implements DetailedSurveyListFactory {

    private static final Logger LOGGER = Logger.getLogger(DetailedSurveyListFactoryImpl.class);

    private ContractDataService contractDataService;
    @Autowired
    public void setContractDataService(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }

    @Override
    public DetailedSurveyList createDetailedSurveyList(String surveyListId) throws ForbiddenUserException {
        if (surveyListId == null || surveyListId.isEmpty()) {
            LOGGER.warn("try to create surveyList from null user.");
            throw new ForbiddenUserException();
        }
        if (!surveyListId.startsWith(getUserListPrefix())) {
            LOGGER.warn("try to create surveyList from forbidden user " +surveyListId +". Throws ForbiddenUserException." );
            throw new ForbiddenUserException(surveyListId);
        }
        User user = getUserDataServiceDecorator().getUserFromEmail(surveyListId);
        if (user == null || ((user.getName() == null || user.getName().isEmpty()) &&
                (user.getFirstName() == null || user.getFirstName().isEmpty()))) {
            throw new ForbiddenUserException(surveyListId);
        }
        DetailedSurveyList detailedSurvey = new DetailedSurveyListImpl();
        detailedSurvey.setName(user.getName());
        detailedSurvey.setLabel(user.getFirstName());
        Collection<Contract> contractCollection = contractDataService.getAllContractsByUser(user);
        LOGGER.debug("Retrieved " +contractCollection.size() +" contracts for the user "  + surveyListId);
        detailedSurvey.setContractCollection(contractCollection);
        return detailedSurvey;
    }

}