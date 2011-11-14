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
package org.opens.tanaguru.entity.decorator.tgol.user;

import java.util.List;
import org.opens.tanaguru.entity.dao.tgol.user.TanaguruSurveyUserDAO;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class UserDataServiceDecoratorImpl extends AbstractGenericDataService<User, Long>
        implements UserDataServiceDecorator{

    private UserDataService decoratedUserDataService; // the userDataService instance being decorated

    /**
     * Constructor
     * @param userDataService
     */
    @Autowired
    public UserDataServiceDecoratorImpl (UserDataService userDataService) {
        super();
        this.decoratedUserDataService = userDataService;
    }

    @Override
    public List<User> getListUser(String userListPrefix) {
        return ((TanaguruSurveyUserDAO)entityDao).findListUser(userListPrefix);
    }

    @Override
    public User getUserFromEmail(String email) {
        return decoratedUserDataService.getUserFromEmail(email);
    }

    @Override
    public User getUserFromName(String name) {
        return decoratedUserDataService.getUserFromName(name);
    }

    @Override
    public boolean isAccountActivated(String email) {
        return decoratedUserDataService.isAccountActivated(email);
    }

}