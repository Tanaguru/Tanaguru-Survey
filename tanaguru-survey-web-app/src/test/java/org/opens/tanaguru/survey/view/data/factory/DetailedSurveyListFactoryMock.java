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
import java.util.Collection;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;
import org.opens.tanaguru.survey.view.data.SurveyList;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.user.User;


/**
 *
 * @author jkowalczyk
 */
public class DetailedSurveyListFactoryMock implements DetailedSurveyListFactory{

    @Override
    public DetailedSurveyList createDetailedSurveyList(String userName, boolean addContractList) throws ForbiddenUserException {
        return new DetailedSurveyList() {

            @Override
            public Collection<Contract> getContractCollection() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setContractCollection(Collection<Contract> contractCollection) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Collection<ContractResult> getTopContractCollection() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setTopContractCollection(Collection<ContractResult> contractCollection) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getId() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setId(String id) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getName() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setName(String surveyListName) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getLabel() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setLabel(String surveyListLabel) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getDescription() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setDescription(String surveyListDescription) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getNumberOfContracts() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setNumberOfContracts(int numberOfContracts) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public SurveyList createSurveyList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SurveyList createSurveyList(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<SurveyList> getSurveyListCollection() {
        return new ArrayList<SurveyList>();
    }

    @Override
    public String getUserListPrefix() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUserListPrefix(String userListPrefix) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}