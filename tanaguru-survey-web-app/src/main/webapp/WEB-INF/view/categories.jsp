<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
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
                    <ul>
                <c:forEach var="surveyList" items="${surveyList}" varStatus="pSurveyList">
                    <c:set var="linkTitle" scope="page">
                        <fmt:message key="categories.detailsOn">
                            <fmt:param>
                                ${surveyList.name}
                            </fmt:param>
                        </fmt:message>
                    </c:set>
                        <li>
                            <a href="<c:url value="/categories-detailed.html?surveyList=${surveyList.id}"/>" title="${linkTitle}">
                                ${surveyList.name}
                            </a>
                            (${surveyList.numberOfContracts} sites)
                        </li>
                </c:forEach>
                    </ul>
                </div><!-- class="span16 -->
            </div><!-- class="row" -->
        <%@include file="template/footer.jsp" %>
        </div><!-- class="container" -->
    </body>
</html>
</compress:html>