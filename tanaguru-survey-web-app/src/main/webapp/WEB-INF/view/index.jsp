<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="index.pageTitle"/>
    </c:set>
    <c:set var="pageMetaDescription" scope="page">
        <fmt:message key="index.meta"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgs-index">
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <div id="social-network-index" class="row">
                    <div class="span2 offset10">
                        <div class="fb-like"
                             data-href="https://www.facebook.com/observatoire.accessibilite"
                             data-send="false"
                             data-layout="button_count"
                             data-show-faces="false"
                             data-font="arial">
                        </div>
                    </div>
                    <div class="span2">
                        <a href="https://twitter.com/ObsAccess"
                           class="twitter-follow-button"
                           data-show-count="false"
                           data-size="medium"
                           data-lang="${lang}"
                           data-align="middle"
                           title="<fmt:message key="footer.follow"/> @ObsAccess <fmt:message key="footer.on"/> Twitter">
                            @ObsAccess
                        </a>
                    </div>
                </div>
                <h1><fmt:message key="index.h1"/></h1>
                <p><fmt:message key="index.mainText"/></p>
                <p><fmt:message key="index.numberOfSites"><fmt:param>${synthesisData.totalNumberOfMonitoredSite}</fmt:param></fmt:message></p>
            </div><!-- class="hero-unit" -->
            <div class="row">
                <div class="span8">
                    <h2><fmt:message key="index.spotlight"/></h2>
                </div><!-- class="span8" -->
                <div class="span8" id="allCategoriesBlock">
                    <a href="<c:url value="/categories.html"/>" class="awesome big magenta"><fmt:message key="index.allCategories"/></a>
                </div><!-- class="span8" -->
            </div><!-- class="row" -->
            <c:set var="pickedListSize" scope="page">
                ${fn:length(synthesisData.spotlightSurveyList)}
            </c:set>
            <c:forEach var="surveyList" items="${synthesisData.spotlightSurveyList}" varStatus="pSurveyList">
            <c:if test="${pSurveyList.index % 2 == 0}">
            <div class="row">
            </c:if>
                <div class="span8">
                    <c:set var="surveyList" scope="page" value="${surveyList}"/>
                    <h3><%@include file="template/survey-list-name.jsp" %></h3>
                    <c:set var="captionClass" scope="page" value="visually-hidden"/>
                    <c:set var="topContractCollection" scope="page" value="${surveyList.topContractCollection}"/>
                    <c:set var="snapshotWidth" scope="page" value="80"/>
                    <c:set var="snapshotHeigth" scope="page" value="60"/>
                    <%@include file="template/top5-table.jsp" %>
                    <p class="see-category-block-link">
                        <a class="btn" href="<c:url value="/categories-detailed.html?surveyList=${surveyList.id}"/>">
                            <fmt:message key="index.seeCategory"><fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param></fmt:message> &raquo;
                        </a>
                    </p>
                </div><!-- class="span8" -->
            <c:if test="${pSurveyList.index+1 == pickedListSize || pSurveyList.index % 2 == 1}">
            </div><!-- class="row" -->
            </c:if>
            </c:forEach>
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>