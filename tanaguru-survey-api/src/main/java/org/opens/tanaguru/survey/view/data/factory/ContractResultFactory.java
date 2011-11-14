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
package org.opens.tanaguru.survey.view.data.factory;

import org.opens.tanaguru.survey.view.data.ContractResult;

/**
 *
 * @author jkowalczyk
 */
public interface ContractResultFactory {

    /**
     * 
     * @return
     */
    ContractResult createContractResult();

    /**
     *
     * @param contractLabel
     * @param contractUrl
     * @param mark
     * @return
     */
    ContractResult createContractResult(String contractLabel, String contractUrl, int mark);

}