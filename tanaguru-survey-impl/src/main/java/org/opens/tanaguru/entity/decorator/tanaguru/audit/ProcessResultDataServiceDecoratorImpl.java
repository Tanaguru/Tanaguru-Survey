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
package org.opens.tanaguru.entity.decorator.tanaguru.audit;

import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.tanaguru.audit.TanaguruSurveyProcessResultDAO;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ProcessResultDataServiceDecoratorImpl extends AbstractGenericDataService<ProcessResult, Long>
        implements ProcessResultDataServiceDecorator {

    private ProcessResultDataService decoratedProcessResultDataService; // the processResultDataService instance being decorated

    /**
     * Constructor
     * @param processResultDataService
     */
    @Autowired
    public ProcessResultDataServiceDecoratorImpl (ProcessResultDataService processResultDataService) {
        super();
        this.decoratedProcessResultDataService = processResultDataService;
    }

    @Override
    public ProcessResult getNetResultFromAuditAndTest(Audit audit, Test test) {
        return ((TanaguruSurveyProcessResultDAO)entityDao).findNetResultFromAuditAndTest(audit, test);
    }

    @Override
    public int getResultByThemeCount(WebResource wr, TestSolution ts, Theme theme) {
        return decoratedProcessResultDataService.getResultByThemeCount(wr, ts, theme);
    }

    @Override
    public Collection<ProcessResult> getResultByScopeList(WebResource wr, Scope scope) {
        return decoratedProcessResultDataService.getResultByScopeList(wr, scope);
    }

    @Override
    public Long getNumberOfGrossResultFromAudit(Audit audit) {
        return decoratedProcessResultDataService.getNumberOfGrossResultFromAudit(audit);
    }

    @Override
    public Long getNumberOfNetResultFromAudit(Audit audit) {
        return decoratedProcessResultDataService.getNumberOfNetResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getGrossResultFromAudit(Audit audit) {
        return decoratedProcessResultDataService.getGrossResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test) {
        return decoratedProcessResultDataService.getGrossResultFromAuditAndTest(audit, test);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAudit(Audit audit) {
        return decoratedProcessResultDataService.getNetResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource wr) {
        return decoratedProcessResultDataService.getNetResultFromAuditAndWebResource(audit, wr);
    }

}