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
package org.opens.tanaguru.entity.decorator.tgol.contract.mock;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.opens.tanaguru.entity.dao.tgol.contract.TanaguruSurveyContractDAO;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tanaguru.survey.view.data.factory.ContractResultFactory;
import org.opens.tanaguru.survey.view.data.factory.ContractResultFactoryImpl;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class ContractDAOMock extends AbstractJPADAO<Contract, Long>
        implements TanaguruSurveyContractDAO {

    private List<Contract> contractList = new LinkedList<Contract>();

    public ContractDAOMock() {
        User user1 = createUser(
                "user1@tanaguru.org",
                "user1",
                "user1FirstName",
                true);
        User user2 = createUser(
                "user2@tanaguru.org",
                "user2",
                "user2FirstName",
                true);
        contractList.add(createContract("http://www.mock-url1", "mock-label-dao1", user1));
        contractList.add(createContract("http://www.mock-url2", "mock-label-dao2", user1));
        contractList.add(createContract("http://www.mock-url3", "mock-label-dao3", user2));
        contractList.add(createContract("http://www.mock-url4", "mock-label-dao4", user2));
    }

    @Override
    public int findNumberOfContractsFromPrefix(String prefix) {
        int counter =0;
        if (prefix.isEmpty()) {
            return counter;
        }
        for (Contract contract : contractList) {
            if (contract.getLabel().startsWith(prefix)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Collection<ContractResult> findTopNContractsFromListUser(String listUser, int nbOfContracts) {
        int counter =0;
        Collection<ContractResult> contractResultCollection =
                new LinkedList<ContractResult>();
        ContractResultFactory crf = new ContractResultFactoryImpl();
        for (Contract contract : contractList) {
            if (contract.getUser().getEmail1().equalsIgnoreCase(listUser)) {
                contractResultCollection.add(crf.createContractResult(contract.getLabel(), contract.getUrl(), 70));
                counter++;
            }
        }
        return contractResultCollection;
    }

    @Override
    protected Class<? extends Contract> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private User createUser (String email, String name, String firstName, boolean isActivated)  {
        User user = new UserImpl();
        user.setEmail1(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setAccountActivation(isActivated);
        return user;
    }

    private Contract createContract (String url, String label, User user)  {
        Contract contract = new ContractImpl();
        contract.setLabel(label);
        contract.setUrl(url);
        contract.setUser(user);
        return contract;
    }

}