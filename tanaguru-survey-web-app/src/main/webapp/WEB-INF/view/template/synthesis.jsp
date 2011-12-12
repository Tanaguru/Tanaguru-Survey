<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

                <c:if test="${hasSynthesisTitle == 'true'}">
                <div class="row">
                    <div class="span6">
                        <h2 id="synthesis" class="cmr cml"><fmt:message key="resultPage.synthesis"/></h2>
                    </div>
                </div>
                </c:if>
                <div class="row" id="synthesis-result">
                    <c:set var="snapshotWidth" scope="page" value="240"/>
                    <c:set var="snapshotHeigth" scope="page" value="160"/>
                    <div class="span4">
                        <div class="thumbnail" >
                            <img src="${configProperties['snapshot-server-url']}&amp;url=${statistics.url}&amp;w=${snapshotWidth}&amp;h=${snapshotHeigth}" alt=""/>
                        </div>
                    </div>
                    <div id="synthesis-meta-data" class="span6 offset3">
                        <c:set var="mark" scope="page" value="${statistics.rawMark}"/>
                        <c:set var="weightedMark" scope="page" value="${statistics.weightedMark}"/>
                        <c:set var="scoreClass" scope="page" value="audit-score"/>
                        <c:set var="weightedScoreClass" scope="page" value="audit-weighted-score"/>
                        <c:set var="displayWeightedMark" scope="page" value="true"/>
                        <c:set var="hasScoreFormulaLink" scope="page" value="true"/>
                        <%@include file="score_1.jsp" %>
                        <div class="project-url">
                    <c:choose>
                        <c:when test="${statistics.auditScope == 'GROUPOFFILES' || statistics.auditScope == 'FILE'}">
                            ${statistics.url}
                        </c:when>
                        <c:otherwise>
                            <a href="${statistics.url}">${statistics.url}</a>
                        </c:otherwise>
                    </c:choose>
                        </div><!-- class="project-url" -->
                        <div class="project-creation-date"><fmt:message key="contract.createdOn"/> : <fmt:formatDate type="date" value="${statistics.date}" dateStyle="long"/> <fmt:formatDate type="time" value="${statistics.date}"/></div>
                    </div>
                    <div class="span3">
                        <c:if test="${fn:length(statistics.parametersMap) != 0}">
                        <div class="project-parameters">
                            <p class="_toggle-master-display-parameters"><fmt:message key="auditSetUp.formTitle"/></p>
                            <ul>
                            <c:forEach var="entry" items="${statistics.parametersMap}">
                                <li class="_toggle-display-parameters project-parameters-label"><spring:message code="${entry.key}"/> :
                                <span class="project-parameters-value">
                                <c:catch var="jspTagException" >
                                    <spring:message code="${entry.value}"/>
                                </c:catch>
                                <c:if test = "${jspTagException != null}">
                                    ${entry.value}
                                </c:if>
                                </span>
                                </li>
                            </c:forEach>
                            </ul>
                        </div><!-- class="project-parameters" -->
                        </c:if>
                    </div><!-- class="span2 offset1" -->
                </div> <!-- class="row" id="synthesis-result" -->
                <div class="row" id="graphical-synthesis-result">
                    <c:set var="counter" scope="request" value="${statistics.resultCounter}"/>
                    <div class="span3">
                        <div id="tgm-piechart">
                            <c:set var="hasTableAlternative" scope="request" value="false"/>
                            <c:set var="width" scope="request" value="100"/>
                            <c:set var="height" scope="request" value="134"/>
                            <c:import url="graph/pie-representation.jsp"/>
                        </div>
                        <div>
                            <table summary="<fmt:message key="graph.resultRepartitionSummaryAndCaption"/>" id="result-synthetized-text">
                                <caption class="visually-hidden"><fmt:message key="graph.resultRepartitionSummaryAndCaption"/></caption>
                                <tr>
                                    <th class="col01 passed" scope="row">Passed</th>
                                    <td class="col02">${counter.passedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 failed" scope="row">Failed</th>
                                    <td class="col02">${counter.failedCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 nmi" scope="row">
                                    <abbr title="Need More Information">NMI</abbr>
                                </th>
                                    <td class="col02">${counter.nmiCount}</td>
                                </tr>
                                <tr>
                                    <th class="col01 na" scope="row">
                                        <abbr title="Not Applicable">NA</abbr>
                                    </th>
                                    <td class="col02">${counter.naCount}</td>
                                </tr>
                            </table>
                        </div>
                    </div><!---div class="span2"-->

                <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
                <c:set var="width" scope="request" value="690"/>
                <c:set var="height" scope="request" value="230"/>
                <c:set var="xLabel" scope="request" value="Themes"/>
                <c:set var="yLabel" scope="request" value="Count"/>
                    <div class="span11 offset1">
                        <div id="barChartRepresentation">
                        <c:import url="graph/bar-chart-representation.jsp"/>
                        </div>
                    </div>
                </div><!--div class="row" id="graphical-synthesis-result"-->
