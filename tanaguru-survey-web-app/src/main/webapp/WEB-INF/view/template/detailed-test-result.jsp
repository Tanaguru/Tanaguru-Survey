<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <c:set var="themeCode" value="${testResult.test.criterion.theme.code}"/>
                <div class="row">
                    <div class="span16">
                        <h2 class="theme2" id="theme${themeCode}"><fmt:message key="audit-result.theme"/> <fmt:message key="${themeCode}"/></h2>
                    </div><!-- class="span16" -->
                </div><!-- class="row" -->
                <div class="row row-top-spacing1">
                    <div class="span15 offset1">
                        <table id="test-result-${themeCode}" class="zebra-striped">
                            <tr>
                                <td class="rule-id">
                                    <h3>${testResult.testShortLabel}</h3>
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
                                </td><!-- class="rule-level" -->
                            </tr>
                        </table>
                    </div><!-- class="span1 offset1" -->
                </div><!-- class="row" -->
                <div class="row">
                    <c:set var="offset" value="11"/>
                    <c:if test='${0 != testResult.elementCounter}'>
                    <div class="span6 offset1">
                        <ul class="counter-remarks">
                            <li>
                                <strong>${testResult.elementCounter}</strong> <fmt:message key="resultPage.testedElements"/>
                            </li>
                            <c:if test='${testResult.resultCounter.failedCount > 0}'>
                            <li>
                            <c:choose>
                                <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                <a href="#${testResult.testCode}failed0"><strong>${testResult.resultCounter.failedCount}</strong> <fmt:message key="resultPage.failedElements"/></a>
                                </c:when>
                                <c:otherwise>
                                <strong>${testResult.resultCounter.failedCount}</strong> <fmt:message key="resultPage.failedElements"/>
                                </c:otherwise>
                            </c:choose>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.nmiCount > 0}'>
                            <li>
                            <c:choose>
                                <c:when test="${testResult.testRepresentation  != 'data-representation/data-representation-1.jsp'}">
                                <a href="#${testResult.testCode}nmi0"><strong>${testResult.resultCounter.nmiCount}</strong> <fmt:message key="resultPage.nmiElements"/></a>
                                </c:when>
                                <c:otherwise>
                                <strong>${testResult.resultCounter.nmiCount}</strong> <fmt:message key="resultPage.nmiElements"/>
                                </c:otherwise>
                            </c:choose>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.suspectedPassedCount > 0}'>
                            <li>
                                <strong>${testResult.resultCounter.suspectedPassedCount}</strong> <fmt:message key="resultPage.nmiSuspectedPassedElements"/>
                            </li>
                            </c:if>
                            <c:if test='${testResult.resultCounter.suspectedFailedCount > 0}'>
                            <li>
                                <strong>${testResult.resultCounter.suspectedFailedCount}</strong> <fmt:message key="resultPage.nmiSuspectedFailedElements"/>
                            </li>
                            </c:if>
                        </ul>
                    </div><!-- class="span6 offset1" -->
                    <c:set var="offset" value="4"/>
                    </c:if>
                    <div class="test-result-detailed span5 offset${offset}" id="r${testResult.testShortLabel}-detailed">
                        <p class="more-on-test">
                            <a href="<fmt:message key="${testResult.testCode}-url"/>">
                                <fmt:message key="resultPage.more"/> ${testResult.testShortLabel}
                            </a> <br/>
                            <a href="${testResult.ruleDesignUrl}">
                                <fmt:message key="resultPage.ruleDesignUrl"/> ${testResult.testShortLabel}
                            </a>
                        </p>
                    </div><!-- class="test-result-detailed span2 offset10" -->
                </div><!-- class="row" -->
                <c:if test='${not empty testResult.remarkInfosList}'>
                    <c:set var="nmiCounter" scope="request" value="0"/>
                    <c:set var="failedCounter" scope="request" value="0"/>
                    <c:forEach var="remarkInfosItem" items="${testResult.remarkInfosList}">
                        <c:set var="remarkInfosItem" scope="request" value="${remarkInfosItem}"/>
                        <c:set var="testCode" scope="request" value="${testResult.testCode}"/>
                        <c:import url="${testResult.testRepresentation}"/>
                        <c:if test="${remarkInfosItem.remarkResult == 'nmi'}">
                            <c:set var="nmiCounter" scope="request" value="${nmiCounter + 1}"/>
                        </c:if>
                        <c:if test="${remarkInfosItem.remarkResult == 'failed'}">
                            <c:set var="failedCounter" scope="request" value="${failedCounter + 1}"/>
                        </c:if>
                    </c:forEach>
                </c:if>