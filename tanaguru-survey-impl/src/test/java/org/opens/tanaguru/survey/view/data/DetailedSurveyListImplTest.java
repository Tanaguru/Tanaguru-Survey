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
package org.opens.tanaguru.survey.view.data;

import java.util.Collection;
import java.util.HashSet;
import junit.framework.TestCase;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;

/**
 *
 * @author jkowalczyk
 */
public class DetailedSurveyListImplTest extends TestCase {
    
    public DetailedSurveyListImplTest(String testName) {
        super(testName);
    }

    public void testSetGetContractCollection() {
        DetailedSurveyList detailedSurveyList = new DetailedSurveyListImpl();
        assertTrue(detailedSurveyList.getContractCollection().isEmpty());
        detailedSurveyList.setContractCollection(getContractCollection());
        assertEquals(2, detailedSurveyList.getContractCollection().size());
    }

    public void testSetGetName() {
        DetailedSurveyList DetailedSurveyList = new DetailedSurveyListImpl();
        DetailedSurveyList.setName("Name");
        assertEquals("Name", DetailedSurveyList.getName());
    }

    public void testSetGetLabel() {
        DetailedSurveyList DetailedSurveyList = new DetailedSurveyListImpl();
        DetailedSurveyList.setLabel("Label");
        assertEquals("Label", DetailedSurveyList.getLabel());
    }

    public void testSetGetDescription() {
        DetailedSurveyList DetailedSurveyList = new DetailedSurveyListImpl();
        DetailedSurveyList.setDescription("Description");
        assertEquals("Description", DetailedSurveyList.getDescription());
    }

    public void testConstructor() {
        DetailedSurveyList detailedSurveyList = new DetailedSurveyListImpl("list-test1", "Name1", "Label1", "Description1", null);
        assertEquals("list-test1", detailedSurveyList.getId());
        assertEquals("Description1", detailedSurveyList.getDescription());
        assertEquals("Label1", detailedSurveyList.getLabel());
        assertEquals("Name1", detailedSurveyList.getName());
        assertTrue(detailedSurveyList.getContractCollection().isEmpty());
        detailedSurveyList = new DetailedSurveyListImpl("list-test2", "Name2", "Label2", "Description2", getContractCollection());
        assertEquals("list-test2", detailedSurveyList.getId());
        assertEquals("Description2", detailedSurveyList.getDescription());
        assertEquals("Label2", detailedSurveyList.getLabel());
        assertEquals("Name2", detailedSurveyList.getName());
        assertEquals(2,detailedSurveyList.getContractCollection().size());
    }

    private Collection<Contract> getContractCollection() {
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