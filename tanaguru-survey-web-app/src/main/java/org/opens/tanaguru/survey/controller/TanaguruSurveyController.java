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
package org.opens.tanaguru.survey.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.decorator.tanaguru.audit.ProcessResultDataServiceDecorator;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.reference.ScopeDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.survey.exception.ForbiddenPageException;
import org.opens.tanaguru.survey.exception.ForbiddenUserException;
import org.opens.tanaguru.survey.util.TanaguruSurveyParamKeyStore;
import org.opens.tanaguru.survey.util.TanaguruSurveyViewKeyStore;
import org.opens.tanaguru.survey.view.data.ContractResult;
import org.opens.tanaguru.survey.view.data.DetailedSurveyList;
import org.opens.tanaguru.survey.view.data.factory.DetailedSurveyListFactory;
import org.opens.tanaguru.survey.view.data.factory.SynthesisDataFactory;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.factory.AuditStatisticsFactory;
import org.opens.tgol.presentation.factory.TestResultFactory;
import org.opens.tgol.util.TgolHighlighter;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class TanaguruSurveyController {

    private static final Logger LOGGER = Logger.getLogger(TanaguruSurveyController.class);

    private boolean hasSourceCodeWithDoctype  = false;
    private LocaleResolver localeResolver;
    private WebResourceDataServiceDecorator webResourceDataService;
    private ContentDataService contentDataService;
    private TestDataService testDataService;
    private ProcessResultDataServiceDecorator processResultDataService;
    private DetailedSurveyListFactory detailedSurveyListFactory;
    private SynthesisDataFactory synthesisDataFactory;
    private Map<String, String> parametersToDisplay = new LinkedHashMap<String, String>();
    public Map<String, String> getParametersToDisplay() {
        return parametersToDisplay;
    }

    public void setParametersToDisplay(Map<String, String> parametersToDisplay) {
        this.parametersToDisplay.putAll(parametersToDisplay);
    }

    private Scope pageScope;
    public Scope getPageScope() {
        return pageScope;
    }

    private int pageScopeId = 1;
    public void setPageScopeId(int pageScopeId) {
        this.pageScopeId = pageScopeId;
    }

    /**
     * 
     * @param synthesisDataFactory
     * @param detailedSurveyListFactory
     * @param webResourceDataService
     * @param contentDataService
     * @param processResultDataService
     * @param testDataService
     * @param localeResolver
     */
    @Autowired
    public TanaguruSurveyController(
            SynthesisDataFactory synthesisDataFactory,
            DetailedSurveyListFactory detailedSurveyListFactory,
            WebResourceDataServiceDecorator webResourceDataService,
            ContentDataService contentDataService,
            ScopeDataService scopeDataService,
            ProcessResultDataServiceDecorator processResultDataService,
            TestDataService testDataService,
            LocaleResolver localeResolver) {
        super();
        this.detailedSurveyListFactory = detailedSurveyListFactory;
        this.synthesisDataFactory = synthesisDataFactory;
        this.webResourceDataService = webResourceDataService;
        this.contentDataService = contentDataService;
        this.processResultDataService = processResultDataService;
        this.testDataService = testDataService;
        this.localeResolver = localeResolver;
        pageScope = scopeDataService.read(Long.valueOf(pageScopeId));
    }

    /**
     *
     * @param model
     * @return
     *      the categories view name
     */
    @RequestMapping(value=TanaguruSurveyViewKeyStore.CATEGORIES_URL, method=RequestMethod.GET)
    public String displayCategoriesPage(Model model) {
        model.addAttribute(
                TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY,
                detailedSurveyListFactory.getSurveyListCollection());
        return TanaguruSurveyViewKeyStore.CATEGORIES_VIEW_NAME;
    }

    /**
     *
     * @param listUser
     * @param model
     * @return
     *      the categories-details view name
     * @throws ForbiddenUserException
     */
    @RequestMapping(value=TanaguruSurveyViewKeyStore.CATEGORIES_DETAILED_URL, method=RequestMethod.GET)
    public String displayCategoriesDetailedPage(
            @RequestParam(TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY) Long listUserId,
            Model model) {
        model.addAttribute(
                TanaguruSurveyParamKeyStore.DETAILED_SURVEY_LIST_KEY,
                detailedSurveyListFactory.createDetailedSurveyList(listUserId, true));
        return TanaguruSurveyViewKeyStore.CATEGORIES_DETAILED_VIEW_NAME;
    }

    /**
     *
     * @param model
     * @return
     *      the index view name
     */
    @RequestMapping(value=TanaguruSurveyViewKeyStore.INDEX_URL, method=RequestMethod.GET)
    public String displayIndexPage(Model model) {
        model.addAttribute(
                TanaguruSurveyParamKeyStore.SYNTHESIS_DATA_KEY,
                synthesisDataFactory.createSynthesisData());
        return TanaguruSurveyViewKeyStore.INDEX_VIEW_NAME;
    }
    
    /**
     *
     * @param model
     * @return
     *      the audit-result view name
     */
    @RequestMapping(value=TanaguruSurveyViewKeyStore.AUDIT_RESULT_URL, method=RequestMethod.GET)
    public String displayAuditResult(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            @RequestParam(TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY) Long listUserId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long wrId = Long.valueOf(webresourceId);
        WebResource webResource = webResourceDataService.ligthRead(wrId);
        if (webResource != null) {
            DetailedSurveyList dsl =
                detailedSurveyListFactory.createDetailedSurveyList(listUserId, true);
            if (isAuditResultAllowedToBeDisplayed(webResource, dsl)) {
                model.addAttribute(TanaguruSurveyParamKeyStore.DETAILED_SURVEY_LIST_KEY, dsl);
                // If the Id given in argument correspond to a webResource,
                // data are retrieved to be prepared and displayed
                return prepareSuccessfullAuditData(
                        webResource,
                        model,
                        localeResolver.resolveLocale(request));
            } else {
                throw new ForbiddenPageException();
            }
        } else {
            throw new ForbiddenPageException();
        }
    }

    /**
     *
     * @param model
     * @return
     *      the test-result view name
     */
    @RequestMapping(value=TanaguruSurveyViewKeyStore.TEST_RESULT_URL, method=RequestMethod.GET)
    public String displayTestResult(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            @RequestParam(TanaguruSurveyParamKeyStore.SURVEY_LIST_KEY) Long listUserId,
            @RequestParam(TanaguruSurveyParamKeyStore.TEST_KEY) String testId,
            Model model) {
        Long wrId = Long.valueOf(webresourceId);
        WebResource webResource = webResourceDataService.ligthRead(wrId);
        if (webResource != null && webResource instanceof Page)  {
            DetailedSurveyList dsl =
                    detailedSurveyListFactory.createDetailedSurveyList(listUserId, true);
            if (isAuditResultAllowedToBeDisplayed(webResource, dsl)) {
                WebResource fullWebResource =
                    webResourceDataService.deepRead(webResource.getId());
                Test test;
                try {
                   test = testDataService.findAllByCode(new String[]{testId}).iterator().next();
                } catch (NoSuchElementException nsee) {
                    throw new ForbiddenPageException();
                }
                ProcessResult pr = processResultDataService.getNetResultFromAuditAndTest(
                        fullWebResource.getAudit(), test);
                model.addAttribute(TanaguruSurveyParamKeyStore.DETAILED_SURVEY_LIST_KEY, dsl);
                model.addAttribute(TanaguruSurveyParamKeyStore.URL_KEY, fullWebResource.getURL());
                return prepareSuccessfullPageData((Page)webResource, model, pr);
            } else {
                throw new ForbiddenPageException();
            }
        } else {
            throw new ForbiddenPageException();
        }
    }

    /**
     *
     * @param webResource
     * @param model
     * @param locale
     * @param exportFormat
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullAuditData(
            WebResource webResource,
            Model model,
            Locale locale) {
        model.addAttribute(TgolKeyStore.LOCALE_KEY,locale);

        if (webResource instanceof Site) {
            throw new ForbiddenUserException();
        } else if (webResource instanceof Page) {
            addAuditStatisticsToModel(webResource, model);
            //In case of display page result page, we need all data related with
            // the page, that's why a deepRead is needed
            WebResource fullWebResource =
                    webResourceDataService.deepRead(webResource.getId());
            return prepareSuccessfullPageData((Page)fullWebResource, model, null);
        }
        return TgolKeyStore.OUPS_VIEW_NAME;
    }

    /**
     *
     * @param page
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullPageData(Page page, Model model, ProcessResult pr) {
        Audit audit = page.getAudit();
        if (audit == null && page.getParent() != null) {
            audit = page.getParent().getAudit();
        }
        if (audit == null) {
            return TgolKeyStore.OUPS_VIEW_REDIRECT_NAME;
        }
        hasSourceCodeWithDoctype = false;
        boolean hasSSP = true;
        // The source code has to be hightlighted before the processResult are
        // computed. We need to know if a doctype is present in the page. If true,
        // the line of each process remark has to be increased by 1.
        SSP ssp = null;
        try {
            ssp = contentDataService.findSSP(page, page.getURL());
        } catch (NoResultException nre) {
            hasSSP = false;
        }

        if (hasSSP) {
            model.addAttribute(TgolKeyStore.SOURCE_CODE_KEY,highlightSourceCode(ssp));
            if (pr == null) {
                model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance().getTestResultSortedByThemeMap(page, getPageScope(), hasSourceCodeWithDoctype, false));
            } else {
                model.addAttribute(TanaguruSurveyParamKeyStore.TEST_RESULT_KEY,
                    TestResultFactory.getInstance().getTestResult(pr, hasSourceCodeWithDoctype, true));
            }
        }
        if (pr == null) {
            return TanaguruSurveyViewKeyStore.AUDIT_RESULT_VIEW_NAME;
        } else {
            return TanaguruSurveyViewKeyStore.TEST_RESULT_VIEW_NAME;
        }
    }

    /**
     * This methods makes a distant call to the highlighter service and returns
     * the highlighted code.
     * @param ssp
     * @return
     * @throws IOException
     */
    private String highlightSourceCode(SSP ssp) {
        if (ssp.getDoctype() != null && !ssp.getDoctype().trim().isEmpty()) {
            hasSourceCodeWithDoctype = true;
        } else {
            hasSourceCodeWithDoctype = false;
        }
        try {
            return TgolHighlighter.getInstance().
                    highlightSourceCode(ssp.getDoctype(), ssp.getAdaptedContent());
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
            return "";
        }
    }

    /**
     * Add a populated auditStatistics instance to the model
     *
     * @param webResource
     * @param model
     * @param hasResultAction
     */
    protected void addAuditStatisticsToModel(WebResource webResource, Model model) {
        model.addAttribute(
                TgolKeyStore.STATISTICS_KEY,
                getAuditStatistics(webResource, model));
    }

    /**
     *
     * @param webResource
     * @param model
     * @param hasResultAction
     * @return
     */
    protected AuditStatistics getAuditStatistics(WebResource webResource, Model model){
        return AuditStatisticsFactory.getInstance().getAuditStatistics(webResource, getParametersToDisplay());
    }

    /**
     * 
     * @param webResource
     * @param webResource
     * @return
     */
    protected boolean isAuditResultAllowedToBeDisplayed(WebResource webResource, DetailedSurveyList dsl) {
        for (ContractResult cr: dsl.getTopContractCollection()) {
            if (cr.getWebResourceId().equals(webResource.getId())) {
                return true;
            }
        }
        return false;
    }

}