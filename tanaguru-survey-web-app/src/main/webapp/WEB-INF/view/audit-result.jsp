<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <fmt:message key="resultPage.pageTitle">
            <fmt:param>
                ${statistics.url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addWebSnapr" scope="page" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgs-result-page">
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <c:set var="pageNameBc" scope="page">
                <fmt:message key="audit-result.h1">
                    <fmt:param>
                        ${statistics.url}
                    </fmt:param>
                </fmt:message>
            </c:set>
            <c:set var="pageName" scope="page">
                <fmt:message key="audit-result.h1">
                    <fmt:param>
                        <a href="${statistics.url}">${statistics.url}</a>
                    </fmt:param>
                </fmt:message>
            </c:set>
            <c:set var="surveyList" scope="page" value="${detailedSurveyList}"/>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/index.html"/>"><fmt:message key="index.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/categories.html"/>"><fmt:message key="categories.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/categories-detailed.html?surveyList=${detailedSurveyList.id}"/>"><fmt:message key="categories-detailed.h1"><fmt:param><%@include file="template/survey-list-name.jsp" %></fmt:param></fmt:message></a> <span class="divider"></span></li>
                <li class="active">${pageNameBc}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div>
                <c:set var="showLegend" scope="request" value="true"/>
                <c:set var="showAxisLabel" scope="request" value="true"/>
                <c:set var="hasBarChartLink" scope="request" value="true"/>
                <c:set var="hasGraphics" scope="request" value="true"/>
                <c:set var="hasSynthesisTitle" scope="request" value="true"/>
                <c:set var="hasPageCounter" scope="request" value="false"/>
                <c:set var="hasPagesListLink" scope="request" value="false"/>
                <c:set var="hasResultDispatchTitle" scope="request" value="false"/>
                <c:import url="template/synthesis.jsp" />
                <c:set var="displayAlgorithm" scope="request" value="true"/>
                <c:set var="scope" scope="request" value="page"/>
                <c:import url="template/detailed-result.jsp" />
        </div><!-- id="container"-->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>