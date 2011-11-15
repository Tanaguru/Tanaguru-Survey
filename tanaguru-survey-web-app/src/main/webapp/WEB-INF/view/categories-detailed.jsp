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
                <div class="span16">
                    <h1>
                        <fmt:message key="categories-detailed.h1">
                            <fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param>
                        </fmt:message>
                    </h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span16">
                    <h2>
                        <fmt:message key="categories-detailed.top"/>
                    </h2>
                    <c:set var="captionClass" scope="page" value=""/>
                    <c:set var="topContractCollection" scope="page" value="${detailedSurveyList.topContractCollection}"/>
                    <c:set var="categoryName" scope="page" value="${detailedSurveyList.name}"/>
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
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>