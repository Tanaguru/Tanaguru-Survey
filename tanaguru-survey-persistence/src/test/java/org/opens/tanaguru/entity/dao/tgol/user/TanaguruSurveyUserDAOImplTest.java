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
package org.opens.tanaguru.entity.dao.tgol.user;

import java.util.List;
import org.opens.tgol.entity.dao.test.AbstractDaoTestCase;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyUserDAOImplTest extends AbstractDaoTestCase {

    /**
     * Filename with data needed for the test
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private TanaguruSurveyUserDAO tanaguruSurveyUserDAO;

    public TanaguruSurveyUserDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        tanaguruSurveyUserDAO = (TanaguruSurveyUserDAO)
                springBeanFactory.getBean("tsUserDAO");
    }

    public void testFindUserList() {
        List<User> userList = tanaguruSurveyUserDAO.findListUser("list-");
        assertEquals(2, userList.size());
        userList = tanaguruSurveyUserDAO.findListUser("other-prefix");
        assertTrue(userList.isEmpty());
        assertNull(tanaguruSurveyUserDAO.findListUser(null));
    }

}
