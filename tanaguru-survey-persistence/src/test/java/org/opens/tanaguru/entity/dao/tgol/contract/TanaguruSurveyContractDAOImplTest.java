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
package org.opens.tanaguru.entity.dao.tgol.contract;

import java.util.Collection;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tgol.entity.dao.test.AbstractDaoTestCase;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyContractDAOImplTest extends AbstractDaoTestCase {

    /**
     * Filename with data needed for the test
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private TanaguruSurveyContractDAO tanaguruSurveyContractDAO;

    public TanaguruSurveyContractDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        tanaguruSurveyContractDAO = (TanaguruSurveyContractDAO)
                springBeanFactory.getBean("tsContractDAO");
    }

    public void testFindNumberOfContractsFromPrefix() {
        assertEquals(2,tanaguruSurveyContractDAO.findNumberOfContractsFromPrefix("list-user"));
        assertEquals(0,tanaguruSurveyContractDAO.findNumberOfContractsFromPrefix("fake-list"));
        assertEquals(0,tanaguruSurveyContractDAO.findNumberOfContractsFromPrefix("common"));
        assertEquals(0,tanaguruSurveyContractDAO.findNumberOfContractsFromPrefix("not-in-db-prefix"));
    }
    
    public void testFindTopNContractsFromListUser() {
        assertTrue(tanaguruSurveyContractDAO.findTopNContractsFromListUser("list-user2", 2).isEmpty());
        assertTrue(tanaguruSurveyContractDAO.findTopNContractsFromListUser("fake-list-user", 2).isEmpty());
        assertTrue(tanaguruSurveyContractDAO.findTopNContractsFromListUser("common-user", 2).isEmpty());
        Collection<ContractResult> contractResultCollection = tanaguruSurveyContractDAO.findTopNContractsFromListUser("list-user1", 2);
        assertEquals(2, contractResultCollection.size());
        for (ContractResult cr : contractResultCollection) {
            if (cr.getLabel().equals("Contract1")) {
                assertEquals(50, cr.getMark());
            } else if (cr.getLabel().equals("Contract2")) {
                assertEquals(80, cr.getMark());
            }
        }
        contractResultCollection = tanaguruSurveyContractDAO.findTopNContractsFromListUser("list-user1", 1);
        assertEquals(1, contractResultCollection.size());
        for (ContractResult cr : contractResultCollection) {
            assertEquals(80, cr.getMark());
        }
        contractResultCollection = tanaguruSurveyContractDAO.findTopNContractsFromListUser("list-user1", 5);
        assertEquals(2, contractResultCollection.size());
        for (ContractResult cr : contractResultCollection) {
            if (cr.getLabel().equals("Contract1")) {
                assertEquals(50, cr.getMark());
            } else if (cr.getLabel().equals("Contract2")) {
                assertEquals(80, cr.getMark());
            }
        }
    }

}