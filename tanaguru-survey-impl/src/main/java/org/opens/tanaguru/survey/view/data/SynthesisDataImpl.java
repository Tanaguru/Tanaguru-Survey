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
import java.util.LinkedList;

/**
 *
 * @author jkowalczyk
 */
public final class SynthesisDataImpl implements SynthesisData {

    int totalNumberOfMonitoredSite;
    @Override
    public int getTotalNumberOfMonitoredSite() {
        return totalNumberOfMonitoredSite;
    }

    @Override
    public void setTotalNumberOfMonitoredSite(int totalNumberOfMonitoredSite) {
        this.totalNumberOfMonitoredSite = totalNumberOfMonitoredSite;
    }

    private Collection<DetailedSurveyList> spotlightSurveyList = new LinkedList<DetailedSurveyList>();
    @Override
    public Collection<DetailedSurveyList> getSpotlightSurveyList() {
        return spotlightSurveyList;
    }

    @Override
    public void setSpotlightSurveyList(Collection<DetailedSurveyList> spotlightSurveyList) {
        if (spotlightSurveyList != null) {
            this.spotlightSurveyList.addAll(spotlightSurveyList);
        }
    }


    /**
     * Default constructor
     */
    public SynthesisDataImpl() {}

    /**
     * 
     * @param totalNumberOfMonitoredSite
     * @param spotlightSurveyList
     */
    public SynthesisDataImpl(
            int totalNumberOfMonitoredSite,
            Collection<DetailedSurveyList> spotlightSurveyList) {
        setTotalNumberOfMonitoredSite(totalNumberOfMonitoredSite);
        setSpotlightSurveyList(spotlightSurveyList);
    }

}