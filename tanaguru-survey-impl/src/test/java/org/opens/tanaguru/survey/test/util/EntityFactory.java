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

import java.util.Collection;
import java.util.HashSet;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.ProductImpl;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public final class EntityFactory {

    /**
     *
     * @param email
     * @param name
     * @param firstName
     * @param isActivated
     * @return
     */
    public static User createUser (String email, String name, String firstName, boolean isActivated)  {
        User user = new UserImpl();
        user.setEmail1(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setAccountActivation(isActivated);
        return user;
    }

    /**
     *
     * @param url
     * @param label
     * @param user
     * @param product
     * @return
     */
    public static Contract createContract (String url, String label, User user, Product product)  {
        Contract contract = new ContractImpl();
        contract.setLabel(label);
        contract.setUrl(url);
        contract.setUser(user);
        contract.setProduct(product);
        return contract;
    }

    /**
     * 
     * @param code
     * @param label
     * @return
     */
    public static Product createProduct (String code, String label)  {
        Product contract = new ProductImpl();
        contract.setLabel(label);
        contract.setCode(code);
        return contract;
    }

    public static Collection<Contract> getContractCollection() {
        Contract contract1 = new ContractImpl();
        contract1.setUrl("http://www.mock-url1.org/");
        Contract contract2 = new ContractImpl();
        contract2.setUrl("http://www.mock-url2.org/");
        Contract contract3 = new ContractImpl();
        contract3.setUrl(null);
        Contract contract4 = new ContractImpl();
        contract4.setUrl("");
        Collection<Contract> contractSet = new HashSet<Contract>();
        contractSet.add(contract1);
        contractSet.add(contract2);
        contractSet.add(contract3);
        contractSet.add(contract4);
        return contractSet;
    }
    
}