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

import java.io.Serializable;
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
            Long surveyListId,
            boolean addContractList) {
        if (surveyListId == null || surveyListId <= 0) {
            LOGGER.warn("try to create surveyList from null user.");
            throw new ForbiddenUserException();
        }
        User user = getUserDataServiceDecorator().read(surveyListId);
        if (user == null) {
            LOGGER.warn("try to create surveyList from null user.");
            throw new ForbiddenUserException();
        }
        if (!user.getEmail1().startsWith(getUserListPrefix())) {
            LOGGER.warn("try to create surveyList from forbidden user " +surveyListId +". Throws ForbiddenUserException." );
            throw new ForbiddenUserException(user.getEmail1());
        }
        if (isFieldNotEmpty(user.getName()) && isFieldNotEmpty(user.getFirstName()) ) {
            throw new ForbiddenUserException(user.getEmail1());
        }
        DetailedSurveyList detailedSurvey = new DetailedSurveyListImpl();
        detailedSurvey.setId(user.getId());
        detailedSurvey.setName(user.getName());
        detailedSurvey.setLabel(user.getFirstName());
        if (addContractList) {
            setOrderedContractCollection(detailedSurvey, user.getEmail1(), user);
        }
        setTopContractCollection(detailedSurvey, user.getEmail1());
        detailedSurvey.setSurveyListAverage(getUserDataServiceDecorator().getUserResultAverage(user.getId()));
        return detailedSurvey;
    }

    /**
     *
     * @param detailedSurvey
     * @param surveyListEmail
     * @param user
     */
    private void setOrderedContractCollection(
            DetailedSurveyList detailedSurvey,
            String surveyListEmail,
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
                " contracts for the user "  + surveyListEmail + " in " +(endProcessDate.getTime() - beginProcessDate.getTime()) + " ms");
        }
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        Collections.sort(contractCollection, new ContractComparator());
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

    /**
     * Test if a string field is null or empty
     * @param field
     * @return
     */
    private boolean isFieldNotEmpty(String field) {
        return (field == null || field.isEmpty());
    }

    /**
     * private class to sort contract regarding the label alphabetical order
     */
    private static class ContractComparator implements Comparator<Contract>, Serializable {
        private static final long serialVersionUID = -6498732084039369192L;
        @Override
        public int compare(Contract t1, Contract t2) {
            return t1.getLabel().compareTo(t2.getLabel());
        }
    }

}