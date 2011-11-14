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
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.decorator.tgol.contract.ContractDataServiceDecorator;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecorator;
import org.opens.tanaguru.survey.view.data.SurveyList;
import org.opens.tanaguru.survey.view.data.SurveyListImpl;
import org.opens.tgol.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class SurveyListFactoryImpl implements SurveyListFactory {

    private static final Logger LOGGER = Logger.getLogger(SurveyListFactoryImpl.class);

    private String userListPrefix = "";
    @Override
    public String getUserListPrefix() {
        return userListPrefix;
    }

    @Override
    public void setUserListPrefix(String userListPrefix) {
        if (userListPrefix != null) {
            this.userListPrefix = userListPrefix;
        }
    }

    private UserDataServiceDecorator userDataServiceDecorator;
    public UserDataServiceDecorator getUserDataServiceDecorator() {
        return this.userDataServiceDecorator;
    }

    @Autowired
    public void setUserDataServiceDecorator(UserDataServiceDecorator userDataServiceDecorator) {
        this.userDataServiceDecorator = userDataServiceDecorator;
    }

    private ContractDataServiceDecorator contractDataService;
    public ContractDataServiceDecorator getContractDataService() {
        return contractDataService;
    }
    
    @Autowired
    public void setContractDataService(ContractDataServiceDecorator contractDataService) {
        this.contractDataService = contractDataService;
    }

    @Override
    public SurveyList createSurveyList() {
        return new SurveyListImpl();
    }

    @Override
    public SurveyList createSurveyList(User user) {
        if ((user.getName() == null || user.getName().isEmpty()) &&
                (user.getFirstName() == null || user.getFirstName().isEmpty())) {
            LOGGER.debug("Creating SurveyList from an empty user " + user.getEmail1());
            return null;
        }
        SurveyList surveyList = new SurveyListImpl();
        surveyList.setId(user.getEmail1());
        surveyList.setName(user.getName());
        surveyList.setLabel(user.getFirstName());
        surveyList.setNumberOfContracts(contractDataService.getAllContractsByUser(user).size());
        return surveyList;
    }

    @Override
    public Collection<SurveyList> getSurveyListCollection() {
        List<User> userList = userDataServiceDecorator.getListUser(userListPrefix);
        LOGGER.debug("Retrieved " + userList.size() +" users with the prefix " + userListPrefix);
        List<SurveyList> surveyListList = new ArrayList<SurveyList>();
        for (User user : userList){
            SurveyList surveyList = createSurveyList(user);
            if (surveyList != null) {
                surveyListList.add(surveyList);
                LOGGER.debug("Added SurveyList instance "+ surveyList.getName());
            }
        }
        return surveyListList;
    }

}