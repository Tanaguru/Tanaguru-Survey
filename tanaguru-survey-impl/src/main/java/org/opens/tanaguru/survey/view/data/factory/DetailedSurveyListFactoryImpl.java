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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;
import org.opens.tanaguru.survey.view.data.DetailedSurveyListImpl;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public final class DetailedSurveyListFactoryImpl extends SurveyListFactoryImpl implements DetailedSurveyListFactory {

    private static final Logger LOGGER = Logger.getLogger(DetailedSurveyListFactoryImpl.class);

    private int nbOfTopElements = 0;
    public void setNbOfTopElements(int nbOfTopElements) {
        this.nbOfTopElements = nbOfTopElements;
    }

    @Override
    public DetailedSurveyList createDetailedSurveyList(
            String surveyListId,
            boolean addContractList) throws ForbiddenUserException {
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
        detailedSurvey.setId(user.getEmail1());
        detailedSurvey.setName(user.getName());
        detailedSurvey.setLabel(user.getFirstName());
        if (addContractList) {
            setOrderedContractCollection(detailedSurvey, surveyListId, user);
        }
        setTopContractCollection(detailedSurvey, user.getEmail1());
        return detailedSurvey;
    }

    /**
     *
     * @param detailedSurvey
     * @param surveyListId
     * @param user
     */
    private void setOrderedContractCollection(
            DetailedSurveyList detailedSurvey,
            String surveyListId,
            User user) {
        Date beginProcessDate = null;
        Date endProcessDate = null;
        List<Contract> contractCollection = new ArrayList<Contract>();
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        contractCollection.addAll(getContractDataService().getAllContractsByUser(user));
        if (LOGGER.isDebugEnabled()) {
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Retrieved " +contractCollection.size() +
                " contracts for the user "  + surveyListId + " in " +(endProcessDate.getTime() - beginProcessDate.getTime()) + " ms");
        }
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        Collections.sort(contractCollection, new Comparator<Contract>() {
            @Override
            public int compare(Contract t1, Contract t2) {
                return t1.getLabel().compareTo(t2.getLabel());
            }
        });
        if (LOGGER.isDebugEnabled()) {
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Sorting contract collection by alphabetical order took " + (endProcessDate.getTime() - beginProcessDate.getTime()) + " ms");
        }
        detailedSurvey.setContractCollection(contractCollection);
        
    }

    /**
     * 
     * @param detailedSurvey
     * @param userId
     */
    private void setTopContractCollection(
            DetailedSurveyList detailedSurvey,
            String userId){
        Date beginProcessDate = null;
        Date endProcessDate = null;
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        detailedSurvey.setTopContractCollection(getContractDataService().getTopNContractsFromListUser(userId, nbOfTopElements));
        if (LOGGER.isDebugEnabled()) {
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Retrieving top contractResults for  " +userId + " took "+ (endProcessDate.getTime() - beginProcessDate.getTime()) + " ms");
        }
    }

}