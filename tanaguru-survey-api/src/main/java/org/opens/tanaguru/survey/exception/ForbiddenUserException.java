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
package org.opens.tanaguru.survey.exception;

/**
 *
 * @author jkowalczyk
 */
public class ForbiddenUserException extends RuntimeException{

    private String userName = null;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static final String FORBIDDEN_USER_EXCEPTION_MSG_KEY = "forbidden-user-exception-msg-key";
    private static final String NULL_USER_EXCEPTION_MSG_KEY = "null-user-exception-msg-key";

    public String getExceptionMsgKey() {
        if (userName != null) {
            return FORBIDDEN_USER_EXCEPTION_MSG_KEY;
        }
        return NULL_USER_EXCEPTION_MSG_KEY;
    }

    private static final long serialVersionUID = 6868141361082941720L;

    public ForbiddenUserException(String userName) {
        this.userName = userName;
    }

    public ForbiddenUserException() {}

}