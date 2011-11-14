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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author jkowalczyk
 */
public class ContractResultImplTest extends TestCase {
    
    public ContractResultImplTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ContractResultImplTest.class);
        return suite;
    }

    public void testSetGetUrl() {
        ContractResult contractResult = new ContractResultImpl();
        contractResult.setUrl("url");
        assertEquals("url", contractResult.getUrl());
        assertEquals(0,contractResult.getMark());
        assertNull(contractResult.getLabel());
    }
    
    public void testSetGetLabel() {
        ContractResult contractResult = new ContractResultImpl();
        contractResult.setLabel("label");
        assertEquals("label", contractResult.getLabel());
        assertEquals(0,contractResult.getMark());
        assertNull(contractResult.getUrl());
    }

    public void testSetGetMark() {
        ContractResult contractResult = new ContractResultImpl();
        contractResult.setMark(70);
        assertEquals(70, contractResult.getMark());
        assertNull(contractResult.getLabel());
        assertNull(contractResult.getUrl());
    }

    public void testConstructor() {
        ContractResult contractResult = new ContractResultImpl("url", "label", 70);
        assertEquals("label", contractResult.getLabel());
        assertEquals("url", contractResult.getUrl());
        assertEquals(70, contractResult.getMark());
        contractResult = new ContractResultImpl(null, null, 0);
        assertNull(contractResult.getLabel());
        assertNull(contractResult.getUrl());
    }

}
