<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <c:if test="${testResultMap != null and not empty testResultMap}">
                <div class="row">
                    <div class="span16">
                        <h2 id="work-done">
                    <c:choose>
                        <c:when test="${scope == 'page'}">
                    <fmt:message key="resultPage.detailedResultPage"/>
                        </c:when>
                        <c:when test="${scope == 'site'}">
                    <fmt:message key="resultPage.detailedResultSite"/>
                        </c:when>
                    </c:choose>
                        </h2>
                    </div>
                </div>
                </c:if>

                <c:forEach var="entry" items="${testResultMap}" varStatus="pResultMap">
                <div class="row">
                    <div class="span16">
                        <h3 class="theme" id="theme${entry.key.rank}"><fmt:message key="audit-result.theme"/> ${entry.key.rank} : <fmt:message key="${entry.key.code}"/></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="span15 offset1">
                        <table id="result-table-theme${pResultMap.index}" class="zebra-striped">
                        <c:forEach var="testResult" items="${entry.value}" varStatus="pTestResultList">
                            <tr>
                                <td class="rule-id">
                                    <h4>${testResult.testShortLabel}</h4>
                                </td>
                                <td class="rule-result ${testResult.resultCode}">
                                <c:choose>
                                    <c:when test="${testResult.resultCode == 'failed' && testResult.resultCounter.failedCount > 0}">
                                    <fmt:message key="${testResult.resultCode}"/> (<acronym title="${testResult.resultCounter.failedCount} <fmt:message key="resultPage.occurrences"/>">${testResult.resultCounter.failedCount}</acronym>)
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="${testResult.resultCode}"/>
                                    </c:otherwise>
                                </c:choose>
                                </td>
                                <td class="rule-label">
                                    <fmt:message key="${testResult.testCode}"/>
                                </td>
                                <td class="rule-level">
                                    <fmt:message key="${testResult.levelCode}"/>
                                </td>
                                <td class="rule-details">
                                    <a href="<c:url value="/test-result.html?surveyList=${detailedSurveyList.id}&amp;wr=${param.wr}&amp;test=${testResult.testCode}"/>" title="<fmt:message key="resultPage.detailsOn"/> ${testResult.testShortLabel}"><fmt:message key="resultPage.details"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
                </div>
                </c:forEach>