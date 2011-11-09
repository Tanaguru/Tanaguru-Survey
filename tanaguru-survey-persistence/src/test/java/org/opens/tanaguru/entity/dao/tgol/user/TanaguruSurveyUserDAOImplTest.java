/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.tgol.user;

import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tgol.entity.dao.test.AbstractDaoTestCase;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyUserDAOImplTest extends AbstractDaoTestCase {

    private static final Logger LOGGER = Logger.getLogger(TanaguruSurveyUserDAOImplTest.class);

    /**
     * Filename with data needed for the test
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private TanaguruSurveyUserDAO tanaguruSurveyUserDAO;

    public TanaguruSurveyUserDAOImplTest(String testName) {
        super(testName);
        LOGGER.info("1");
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        LOGGER.info("2");
        tanaguruSurveyUserDAO = (TanaguruSurveyUserDAO)
                springBeanFactory.getBean("tsUserDAO");
        LOGGER.info("3");
    }

    public void testFindUserList() {
        List<User> userList = tanaguruSurveyUserDAO.findListUser("list-");
        assertEquals(2, userList.size());
        userList = tanaguruSurveyUserDAO.findListUser("other-prefix");
        assertTrue(userList.isEmpty());
        assertNull(tanaguruSurveyUserDAO.findListUser(null));
    }

}
