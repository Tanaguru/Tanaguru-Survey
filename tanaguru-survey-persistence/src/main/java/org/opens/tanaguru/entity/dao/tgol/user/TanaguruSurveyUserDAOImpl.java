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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyUserDAOImpl extends AbstractJPADAO<User, Long>
        implements TanaguruSurveyUserDAO {

    private static final String SELECT_KEY = "SELECT";
    private static final String FROM_KEY = " FROM ";
    private static final String WHERE_KEY = " WHERE";
    private static final String LIKE_KEY = " LIKE";
    private static final String USER_TARGET_KEY = " u";

    /**
     *
     * @param userListPrefix
     * @return
     */
    @Override
    public List<User> findListUser(String userListPrefix) {
        if (userListPrefix == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SELECT_KEY);
            sb.append(USER_TARGET_KEY);
            sb.append(FROM_KEY);
            sb.append(getEntityClass().getName());
            sb.append(USER_TARGET_KEY);
            sb.append(WHERE_KEY);
            sb.append(USER_TARGET_KEY);
            sb.append(".email");
            sb.append(LIKE_KEY);
            sb.append(" :key");
            Query query = entityManager.createQuery(sb.toString());
            query.setParameter("key", userListPrefix+"%");
            if (query.getResultList() != null) {
                return (List<User>)query.getResultList();
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return new ArrayList<User>();
        }
    }

    @Override
    protected Class<? extends User> getEntityClass() {
        return UserImpl.class;
    }

}