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
package org.opens.tanaguru.entity.decorator.tgol.contract;

import java.util.Collection;
import org.opens.tanaguru.entity.dao.tgol.contract.TanaguruSurveyContractDAO;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ContractDataServiceDecoratorImpl extends AbstractGenericDataService<Contract, Long>
        implements ContractDataServiceDecorator{

    private ContractDataService decoratedContractDataService; // the contractDataService instance being decorated

    /**
     * Constructor
     * @param contractDataService
     */
    @Autowired
    public ContractDataServiceDecoratorImpl (ContractDataService contractDataService) {
        super();
        this.decoratedContractDataService = contractDataService;
    }

    @Override
    public int getNumberOfContractsFromPrefix(String prefix) {
        return ((TanaguruSurveyContractDAO)entityDao).findNumberOfContractsFromPrefix(prefix);
    }

    @Override
    public Collection<ContractResult> getTopNContractsFromListUser(String listUser, int nbOfContracts) {
        return ((TanaguruSurveyContractDAO)entityDao).findTopNContractsFromListUser(listUser, nbOfContracts);
    }

    @Override
    public Collection<Contract> getAllContractsByUser(User user) {
        return decoratedContractDataService.getAllContractsByUser(user);
    }

    @Override
    public Collection<Contract> getAllContractsByProduct(Product prdct) {
        return decoratedContractDataService.getAllContractsByProduct(prdct);
    }

}