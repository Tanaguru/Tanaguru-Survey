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
import org.opens.tanaguru.survey.test.util.EntityFactory;

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
        detailedSurveyList.setContractCollection(EntityFactory.getContractCollection());
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

    public void testConstructor() {
        DetailedSurveyList detailedSurveyList = new DetailedSurveyListImpl(Long.valueOf(1), "Name1", "Label1", null, null);
        assertEquals(Long.valueOf(1), detailedSurveyList.getId());
        assertEquals("Label1", detailedSurveyList.getLabel());
        assertEquals("Name1", detailedSurveyList.getName());
        assertTrue(detailedSurveyList.getContractCollection().isEmpty());
        assertTrue(detailedSurveyList.getTopContractCollection().isEmpty());
        detailedSurveyList = new DetailedSurveyListImpl(Long.valueOf(2), "Name2", "Label2", EntityFactory.getContractCollection(), getContractInfoCollection());
        assertEquals(Long.valueOf(2), detailedSurveyList.getId());
        assertEquals("Label2", detailedSurveyList.getLabel());
        assertEquals("Name2", detailedSurveyList.getName());
        assertEquals(2,detailedSurveyList.getContractCollection().size());
        assertEquals(2,detailedSurveyList.getTopContractCollection().size());
    }

    private Collection<ContractResult> getContractInfoCollection() {
        ContractResult contractResult1 = new ContractResultImpl();
        contractResult1.setUrl("http://www.mock-url1.org/");
        contractResult1.setLabel("mock-label1");
        contractResult1.setMark(70);
        ContractResult contractResult2 = new ContractResultImpl();
        contractResult2.setUrl("http://www.mock-url2.org/");
        contractResult2.setLabel("mock-label2");
        contractResult2.setMark(70);
        Collection<ContractResult> ContractInfoSet = new HashSet<ContractResult>();
        ContractInfoSet.add(contractResult1);
        ContractInfoSet.add(contractResult2);
        return ContractInfoSet;
    }

}