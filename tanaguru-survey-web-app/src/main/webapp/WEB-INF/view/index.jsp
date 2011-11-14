<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="index.pageTitle"/>
    </c:set>
    <c:set var="pageMetaDescription" scope="page">
        <fmt:message key="index.meta"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgs-categories">
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1><fmt:message key="index.h1"/></h1>
                <p><fmt:message key="index.mainText"/></p>
                <p><fmt:message key="index.numberOfSites"><fmt:param>${synthesisData.totalNumberOfMonitoredSite}</fmt:param></fmt:message></p>
            </div><!-- class="hero-unit" -->
            <div class="row">
                <div class="span16">
                    <h2><fmt:message key="index.spotlight"/></h2>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <c:set var="pickedListSize" scope="page">
                ${fn:length(synthesisData.spotlightSurveyList)}
            </c:set>
            <c:forEach var="surveyList" items="${synthesisData.spotlightSurveyList}" varStatus="pSurveyList">
            <c:if test="${pSurveyList.index % 2 == 0}">
            <div class="row">
            </c:if>
                <div class="span8">
                    <h3>${surveyList.name}</h3>
                    <c:set var="captionClass" scope="page" value="visually-hidden"/>
                    <c:set var="topContractCollection" scope="page" value="${surveyList.topContractCollection}"/>
                    <c:set var="categoryName" scope="page" value="${surveyList.name}"/>
                    <%@include file="template/top5-table.jsp" %>
                    <p>
                        <a class="btn" href="<c:url value="/categories-detailed.html?surveyList=${surveyList.id}"/>">
                            <fmt:message key="index.seeCategory"><fmt:param>${surveyList.name}</fmt:param></fmt:message>&raquo;
                        </a>
                    </p>
                </div><!-- class="span8" -->
            <c:if test="${pSurveyList.index+1 == pickedListSize || pSurveyList.index % 2 == 1}">
            </div><!-- class="row" -->
            </c:if>
            </c:forEach>
            <%@include file="template/footer.jsp" %>
        </div><!-- class="container" -->
    </body>
</html>
</compress:html>