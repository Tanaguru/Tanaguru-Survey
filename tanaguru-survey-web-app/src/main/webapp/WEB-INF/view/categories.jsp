<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
        <fmt:message key="categories.pageTitle"/>
    </c:set>
    <c:set var="pageMetaDescription" scope="page">
        <fmt:message key="categories.meta"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgs-categories">
        <c:set var="categoriesPageSelected" scope="page" value="true"/>
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/index.html"/>"><fmt:message key="index.h1"/></a> <span class="divider"></span></li>
                <li class="active"><fmt:message key="categories.h1"/></li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1><fmt:message key="categories.h1"/></h1>
                    <p><fmt:message key="categories.presentation"/></p>
                </div>
            </div>
            <div class="row">
                <div class="span12 offset2">
                    <table class="tg-data-table zebra-striped" summary="<fmt:message key="categories.presentation"/>">
                    <caption class="visually-hidden"><fmt:message key="categories.presentation"/></caption>
                        <thead>
                            <tr>
                                <th scope="col" class="align-left"><fmt:message key="categories.name"/></th>
                                <th scope="col" class="align-right"><fmt:message key="categories.nbSites"/></th>
                                <th scope="col" class="align-right"><fmt:message key="categories.average"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="surveyList" items="${surveyList}" varStatus="pSurveyList">
                            <tr>
                            <c:set var="linkTitle" scope="page">
                                <fmt:message key="categories.detailsOn">
                                    <fmt:param>
                                        ${surveyList.name}
                                    </fmt:param>
                                </fmt:message>
                            </c:set>
                                <td class="align-left">
                                    <c:set var="surveyList" scope="page" value="${surveyList}"/>
                                    <a href="<c:url value="/categories-detailed.html?surveyList=${surveyList.id}"/>" title="${linkTitle}">
                                        <%@include file="template/survey-list-name.jsp" %>
                                    </a>
                                </td>
                                <td class="align-right">
                                    ${surveyList.numberOfContracts}
                                </td>
                                <td class="align-right">
                                    <c:set var="mark" scope="page" value="${surveyList.surveyListAverage}"/>
                                    <c:set var="markSizeClass" value="size-1xl"/>
                                    <%@include file="template/score.jsp" %>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div><!-- class="span16 -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>