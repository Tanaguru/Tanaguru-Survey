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
package org.opens.tanaguru.survey.test.util;

import org.opens.tanaguru.entity.decorator.tgol.contract.ContractDataServiceDecorator;
import org.opens.tanaguru.entity.decorator.tgol.contract.ContractDataServiceDecoratorImpl;
import org.opens.tanaguru.entity.decorator.tgol.contract.mock.ContractDAOMock;
import org.opens.tanaguru.entity.decorator.tgol.contract.mock.ContractDataServiceMock;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecorator;
import org.opens.tanaguru.entity.decorator.tgol.user.UserDataServiceDecoratorImpl;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDAOMock;
import org.opens.tanaguru.entity.decorator.tgol.user.mock.UserDataServiceMock;

/**
 *
 * @author jkowalczyk
 */
public final class DecoratorFactory {

    public static UserDataServiceDecorator getUserDataServiceDecorator() {
        UserDataServiceDecoratorImpl udsd = new UserDataServiceDecoratorImpl();
        udsd.setEntityDao(new UserDAOMock());
        udsd.setUserDataService(new UserDataServiceMock());
        return udsd;
    }

    public static ContractDataServiceDecorator getContractDataServiceDecorator() {
        ContractDataServiceDecoratorImpl cdsd = new ContractDataServiceDecoratorImpl();
        cdsd.setEntityDao(new ContractDAOMock());
        cdsd.setContractDataService(new ContractDataServiceMock());
        return cdsd;
    }
    
}