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
import junit.framework.TestCase;
import org.opens.tanaguru.survey.test.util.DecoratorFactory;
import org.opens.tanaguru.survey.test.util.EntityFactory;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class ContractDataServiceDecoratorImplTest extends TestCase {
    
    public ContractDataServiceDecoratorImplTest(String testName) {
        super(testName);
    }

    public void testGetNumberOfContractsFromPrefix() {
        ContractDataServiceDecorator udsd = DecoratorFactory.getContractDataServiceDecorator();
        assertEquals(4, udsd.getNumberOfContractsFromPrefix("mock-label-dao"));
        assertEquals(0, udsd.getNumberOfContractsFromPrefix("mock-label-data-service"));
    }

    public void testGetTopNContractsFromListUser() {
        ContractDataServiceDecorator udsd = DecoratorFactory.getContractDataServiceDecorator();
        Collection<ContractResult> contractResultCollection = udsd.getTopNContractsFromListUser("user1@tanaguru.org", 2);
        assertEquals(2, contractResultCollection.size());
        int i=1;
        for (ContractResult cr : contractResultCollection) {
            assertEquals("mock-label-dao"+i, cr.getLabel());
            i++;
        }
    }

    public void testGetAllContractsByUser() {
        ContractDataServiceDecorator udsd = DecoratorFactory.getContractDataServiceDecorator();
        User user = EntityFactory.createUser(
                Long.valueOf(1),
                "user1@tanaguru.org",
                "user1",
                "user1FirstName",
                true);
        Collection<Contract> contractCollection=udsd.getAllContractsByUser(user);
        assertEquals(2, contractCollection.size());
        int i=1;
        for (Contract cr : contractCollection) {
            assertEquals("mock-label-data-service"+i, cr.getLabel());
            i++;
        }
    }

    public void testGetAllContractsByProduct() {
        ContractDataServiceDecorator udsd = DecoratorFactory.getContractDataServiceDecorator();
        Product product = EntityFactory.createProduct(
                "MOCK_PRODUCT1",
                "mock product 1");
        Collection<Contract> contractCollection=udsd.getAllContractsByProduct(product);
        assertEquals(4, contractCollection.size());
        int i=1;
        for (Contract cr : contractCollection) {
            assertEquals("mock-label-data-service"+i, cr.getLabel());
            i++;
        }
    }

}
