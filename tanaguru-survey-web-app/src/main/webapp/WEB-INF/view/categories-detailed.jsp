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
        <fmt:message key="categories-detailed.pageTitle">
            <fmt:param>${detailedSurveyList.name}</fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="pageMetaDescription" scope="page">
        <fmt:message key="categories-detailed.meta">
            <fmt:param>${detailedSurveyList.name}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgs-categories-detailed">
        <%@include file="template/top-bar.jsp" %>
        <c:set var="surveyList" scope="page" value="${detailedSurveyList}"/>
        <div class="container">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/index.html"/>"><fmt:message key="index.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/categories.html"/>"><fmt:message key="categories.h1"/></a> <span class="divider"></span></li>
                <li class="active"><fmt:message key="categories-detailed.h1"><fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param></fmt:message></li>
            </ul>
            <div class="row">
                <div class="span13">
                    <h1>
                        <fmt:message key="categories-detailed.h1">
                            <fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param>
                        </fmt:message>
                    </h1>
                </div><!-- class="span14" -->
                <div class="span3 average-score">
                    <c:set var="mark" value="${detailedSurveyList.surveyListAverage}"/>
                    <c:set var="markSizeClass" value="size-4xl"/>
                    <fmt:message key="categories-detailed.averageScore"/> <%@include file="template/score.jsp" %>
                </div>
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <h2>
                        <fmt:message key="categories-detailed.top"/>
                    </h2>
                    <c:set var="captionClass" scope="page" value="visually-hidden"/>
                    <c:set var="snapshotWidth" scope="page" value="80"/>
                    <c:set var="snapshotHeigth" scope="page" value="60"/>
                    <c:set var="topContractCollection" scope="page" value="${detailedSurveyList.topContractCollection}"/>
                    <%@include file="template/top5-table.jsp" %>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <h2>
                        <fmt:message key="categories-detailed.list">
                            <fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param>
                        </fmt:message>
                    </h2>
                    <p><fmt:message key="categories-detailed.presentation">
                            <fmt:param>${fn:length(detailedSurveyList.contractCollection)}</fmt:param>
                        </fmt:message>
                    </p>
                    <ul>
                <c:forEach var="contract" items="${detailedSurveyList.contractCollection}" varStatus="pSurveyList">
                        <li><a href="${contract.url}">${contract.label}</a></li>
                </c:forEach>
                    </ul>
                    <p class="alert-message block-message warning">
                        <fmt:message key="categories-detailed.contact">
                            <fmt:param>${configProperties['contact-email']}</fmt:param>
                        </fmt:message>
                    </p>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>