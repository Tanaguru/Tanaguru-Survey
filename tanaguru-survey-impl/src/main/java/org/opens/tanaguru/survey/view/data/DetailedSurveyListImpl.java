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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.opens.tgol.entity.contract.Contract;


/**
 *
 * @author jkowalczyk
 */
public final class DetailedSurveyListImpl extends SurveyListImpl implements DetailedSurveyList{

    private Collection<Contract> contractCollection = new ArrayList<Contract>();
    @Override
    public Collection<Contract> getContractCollection() {
        return contractCollection;
    }

    @Override
    public void setContractCollection(Collection<Contract> contractCollection) {
        if (contractCollection != null) {
            for (Contract contract : contractCollection) {
                if (contract.getUrl() != null && !contract.getUrl().isEmpty()) {
                    this.contractCollection.add(contract);
                }
            }
        }
    }

    private Collection<ContractResult> topContractResultCollection = new LinkedList<ContractResult>();
    @Override
    public Collection<ContractResult> getTopContractCollection() {
        return topContractResultCollection;
    }

    @Override
    public void setTopContractCollection(Collection<ContractResult> contractResultCollection) {
        if (contractResultCollection != null) {
            this.topContractResultCollection.addAll(contractResultCollection);
        }
    }

    /**
     * Default constructor
     */
    public DetailedSurveyListImpl() {}

    /**
     * 
     * @param id
     * @param name
     * @param label
     * @param description
     * @param contractCollection
     */
    public DetailedSurveyListImpl(
            Long id,
            String name,
            String label,
            Collection<Contract> contractCollection,
            Collection<ContractResult> topContractCollection,
            Integer surveyListAverage) {
        setId(id);
        setName(name);
        setLabel(label);
        setContractCollection(contractCollection);
        setTopContractCollection(topContractCollection);
        setSurveyListAverage(surveyListAverage);
    }

}