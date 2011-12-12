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
package org.opens.tanaguru.entity.dao.tgol.contract;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tanaguru.survey.view.data.factory.ContractResultFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSurveyContractDAOImpl extends AbstractJPADAO<Contract, Long>
        implements TanaguruSurveyContractDAO {

    private ContractResultFactory contractResultFactory;

    private static final String FIND_NUMBER_OF_CONTRACT_QUERY =
            "SELECT count(Id_Contract) FROM TGSI_CONTRACT as tc "
            + "INNER JOIN TGSI_USER as tu on (tu.Id_User=tc.USER_Id_User) "
            + "WHERE tu.Email1 like :prefix";

    private static final String FIND_TOP_N_CONTRACT_QUERY =
        "SELECT c.Label, c.Url, wrs.Raw_Mark, awr.WEB_RESOURCE_Id_Web_Resource FROM TGSI_USER AS u "
        +"INNER JOIN TGSI_CONTRACT AS c ON ( u.Id_User = c.USER_Id_User ) "
        +"INNER JOIN TGSI_ACT AS a ON "
        +"( c.Id_Contract = a.CONTRACT_Id_Contract AND a.End_Date = (SELECT End_Date FROM TGSI_ACT WHERE CONTRACT_Id_Contract = a.CONTRACT_Id_Contract ORDER BY End_Date DESC LIMIT 1 ) ) "
        +"INNER JOIN TGSI_ACT_WEB_RESOURCE awr ON ( a.Id_Act = awr.ACT_Id_Act ) "
        +"INNER JOIN WEB_RESOURCE_STATISTICS AS wrs ON ( awr.WEB_RESOURCE_Id_Web_Resource = wrs.Id_Web_Resource ) "
        +"WHERE u.Email1 like :listUser "
        +"ORDER BY wrs.Raw_Mark DESC, c.Label ASC "
        +"LIMIT :nbOfResults ";

    /**
     * Constructor
     * @param contractResultFactory
     */
    @Autowired
    public TanaguruSurveyContractDAOImpl(ContractResultFactory contractResultFactory) {
        super();
        this.contractResultFactory = contractResultFactory;
    }

    /**
     * Native sql query :
     * SELECT count(Id_Contract) FROM TGSI_CONTRACT as tc
     *       INNER JOIN TGSI_USER as tu on (tu.Id_User=tc.USER_Id_User)
     *       WHERE tu.Email1 like :prefix;
     * 
     * @param prefix
     * @return
     *      the number of contract for user whom Email1 field starts with prefix
     */
    @Override
    public int findNumberOfContractsFromPrefix(String prefix) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(FIND_NUMBER_OF_CONTRACT_QUERY);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("prefix", prefix+"%");
        Object result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            return Integer.valueOf(0);
        }
        if (result instanceof BigInteger) {
            return ((BigInteger)result).intValue();
        }
        else if(result instanceof Integer) {
            return ((Integer)result);
        } else {
            return Integer.valueOf(0);
        }
    }

    /**
     * Native sql query :
     * SELECT c.Label, c.Url, wrs.Raw_Mark FROM TGSI_USER AS u
     *       INNER JOIN TGSI_CONTRACT AS c ON ( u.Id_User = c.USER_Id_User )
     *       INNER JOIN TGSI_ACT AS a ON
     *       ( c.Id_Contract = a.CONTRACT_Id_Contract AND a.End_Date = (SELECT End_Date FROM TGSI_ACT WHERE CONTRACT_Id_Contract = a.CONTRACT_Id_Contract ORDER BY End_Date DESC LIMIT 1 ) )
     *       INNER JOIN TGSI_ACT_WEB_RESOURCE awr ON ( a.Id_Act = awr.ACT_Id_Act )
     *       INNER JOIN WEB_RESOURCE_STATISTICS AS wrs ON ( awr.WEB_RESOURCE_Id_Web_Resource = wrs.Id_Web_Resource )
     *       WHERE u.Email1 = :listUser
     *       ORDER BY wrs.Raw_Mark  DESC
     *       LIMIT :nbOfResults;
     *
     * @param listUser
     * @param nbOfResults
     * @return
     *      a collection of initialised ContractResult instances
     */
    @Override
    public Collection<ContractResult> findTopNContractsFromListUser(String listUser, int nbOfResults) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(FIND_TOP_N_CONTRACT_QUERY);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("listUser", listUser);
        query.setParameter("nbOfResults", nbOfResults);
        Collection<ContractResult> contractResultSet = new LinkedHashSet<ContractResult>();
        List<Object[]> result = null;
        try {
            result = (List<Object[]>)query.getResultList();
        } catch (NoResultException nre) {
            return contractResultSet;
        }
        for (Object[] obj : result) {
            ContractResult cr = contractResultFactory.createContractResult(
                    (String)obj[0], // label
                    (String)obj[1], //url
                    ((Float)obj[2]).intValue(), //mark
                    ((BigInteger)obj[3]).longValue()); //webResourceId
            contractResultSet.add(cr);
        }
        return contractResultSet;
    }

    @Override
    protected Class<? extends Contract> getEntityClass() {
        return ContractImpl.class;
    }

}