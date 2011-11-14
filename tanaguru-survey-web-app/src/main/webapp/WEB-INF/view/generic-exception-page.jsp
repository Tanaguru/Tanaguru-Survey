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
        <fmt:message key="${exception.exceptionMsgKey}">
            <c:if test="${exception.userName != null}">
                <fmt:param>${exception.userName}</fmt:param>
            </c:if>
        </fmt:message>
    </c:set>
    <c:set var="pageMetaDescription" scope="page">
        <fmt:message key="${exception.exceptionMsgKey}">
            <c:if test="${exception.userName != null}">
                <fmt:param>${exception.userName}</fmt:param>
            </c:if>
        </fmt:message>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="ts-exception">
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <ul class="breadcrumb">
                <li><a href="<c:url value="/index.html"/>"><fmt:message key="index.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageTitle}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>${pageTitle}</h1>
                </div><!-- class="span16" -->
            </div><!-- class="row" -->
            <%@include file="template/footer.jsp" %>
        </div><!-- class="container" -->
    </body>
</html>
</compress:html>