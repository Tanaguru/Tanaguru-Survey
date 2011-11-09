<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="${pageContext.response.locale}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="top-list.pageTitle">
            <fmt:param>${detailedSurveyList.name}</fmt:param>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="ts-top-list" class="tgm">
        <div id="meta-border">
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <h1>
                        <fmt:message key="top-list.h1">
                            <fmt:param>${detailedSurveyList.name}</fmt:param>
                        </fmt:message>
                    </h1>
                </div><!-- class="yui3-u-1" -->
                <div class="yui3-u-1">
                <c:forEach var="surveyList" items="${surveyList}" varStatus="pSurveyList">
                    <div class="yui3-u-1">
                        <p><a href="<c:url value=""/>">${surveyList.name}</a> : ${surveyList.label}</p>
                    </div>
                </c:forEach>
                </div><!-- class="yui3-u-1" -->
            </div><!-- class="yui3-g" -->
        </div>
        <%@include file="template/footer.jsp" %>
    </body>
</html>