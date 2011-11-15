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
package org.opens.tanaguru.entity.decorator.tgol.contract.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.survey.test.util.EntityFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class ContractDataServiceMock extends AbstractGenericDataService<Contract, Long>
        implements ContractDataService {

    private List<Contract> contractList = new LinkedList<Contract>();
    
    public ContractDataServiceMock(){
        User user1 = EntityFactory.createUser(
                Long.valueOf(1),
                "user1@tanaguru.org",
                "user1",
                "user1FirstName",
                true);
        User user2 = EntityFactory.createUser(
                Long.valueOf(2),
                "user2@tanaguru.org",
                "user2",
                "user2FirstName",
                true);
        Product product1 = EntityFactory.createProduct(
                "MOCK_PRODUCT1",
                "mock product 1");
        contractList.add(EntityFactory.createContract(
                "http://www.mock-url1",
                "mock-label-data-service1",
                user1,
                product1));
        contractList.add(EntityFactory.createContract(
                "http://www.mock-url2",
                "mock-label-data-service2",
                user1,
                product1));
        contractList.add(EntityFactory.createContract(
                "http://www.mock-url3",
                "mock-label-data-service3",
                user2,
                product1));
        contractList.add(EntityFactory.createContract(
                "http://www.mock-url4",
                "mock-label-data-service4",
                user2,
                product1));
    }

    @Override
    public Collection<Contract> getAllContractsByUser(User user) {
        Collection<Contract> localContractList = new ArrayList<Contract>();
        for (Contract contract : contractList) {
            if (contract.getUser().getEmail1().equalsIgnoreCase(user.getEmail1())) {
                localContractList.add(contract);
            }
        }
        return localContractList;
    }

    @Override
    public Collection<Contract> getAllContractsByProduct(Product product) {
        Collection<Contract> localContractList = new ArrayList<Contract>();
        for (Contract contract : contractList) {
            if (contract.getProduct().getCode().equalsIgnoreCase(product.getCode())) {
                localContractList.add(contract);
            }
        }
        return localContractList;
    }

    @Override
    public Contract create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Contract> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract read(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract saveOrUpdate(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Contract> saveOrUpdate(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Contract, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Contract> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract update(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}