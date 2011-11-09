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

/**
 *
 * @author jkowalczyk
 */
public class SurveyListImpl implements SurveyList {

    private String id;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String surveyListName) {
        this.name = surveyListName;
    }

    private String label;
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String surveyListLabel) {
        this.label = surveyListLabel;
    }

    private String description;
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String surveyListDescription) {
        this.description = surveyListDescription;
    }

    /**
     * Default constructor
     */
    public SurveyListImpl() {}

    /**
     *
     * @param id
     * @param name
     * @param label
     * @param description
     */
    public SurveyListImpl(
            String id,
            String name,
            String label,
            String description) {
       this.id = id;
       this.name = name;
       this.label = label;
       this.description = description;
    }

}