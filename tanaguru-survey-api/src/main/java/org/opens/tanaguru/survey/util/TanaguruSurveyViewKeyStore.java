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
package org.opens.tanaguru.survey.util;

/**
 *
 * @author jkowalczyk
 */
public final class TanaguruSurveyViewKeyStore {

    /**
     * Private constructor for utility class
     */
    private TanaguruSurveyViewKeyStore(){}

    public static final String INDEX_VIEW_NAME="index";
    public static final String CATEGORIES_VIEW_NAME="categories";
    public static final String CATEGORIES_DETAILED_VIEW_NAME="categories-detailed";
    public static final String URL_LIST_VIEW_NAME="url-list";
    public static final String TOP_LIST_VIEW_NAME="top-list";
    public static final String AUDIT_RESULT_VIEW_NAME="audit-result";
    public static final String TEST_RESULT_VIEW_NAME="test-result";
    public static final String ERROR_403_VIEW_NAME="error-403";
    public static final String ERROR_404_VIEW_NAME="error-404";

    public static final String INDEX_URL="/index";
    public static final String CATEGORIES_URL="/categories";
    public static final String CATEGORIES_DETAILED_URL="/categories-detailed";
    public static final String URL_LIST_URL="/url-list";
    public static final String TOP_LIST_URL="/top-list";
    public static final String AUDIT_RESULT_URL="/audit-result";
    public static final String TEST_RESULT_URL="/test-result";
    public static final String ERROR_403_URL="/error-403";
    public static final String ERROR_404_URL="/error-404";
    
}