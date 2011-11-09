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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class ContractDataServiceMock implements ContractDataService {

    public ContractDataServiceMock(){}

    @Override
    public Collection<Contract> getAllContractsByUser(User user) {
        return new HashSet<Contract>();
    }

    @Override
    public Collection<Contract> getAllContractsByProduct(Product product) {
        return new HashSet<Contract>();
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