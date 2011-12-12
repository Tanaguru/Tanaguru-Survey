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
        <fmt:message key="error-403.pageTitle"/>
    </c:set>
    <%@include file="template/head.jsp" %>
    <body id="tgs-categories">
        <%@include file="template/top-bar.jsp" %>
        <div class="container">
            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1><fmt:message key="error-403.h1"/></h1>
            </div><!-- class="hero-unit" -->
            <div class="row">
                <div class="span16 main-logo">
                    <img src="<c:url value="/Images/error_403.jpg"/>" alt=""/>
                </div><!-- class="span8 offset3" -->
            </div><!-- class="row" -->
            <div class="row">
                <div class="span4 offset9">
                    <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                        <img src="<c:url value="/Images/creative_common_logo.png"/>" alt="License"/>
                    </a>
                    <a title="Flickr: Galerie de VanEstelfeen" href="http://www.flickr.com/photos/59349507@N06/">VanEstelfeen</a>
                </div><!-- class="span2 offset8" -->
            </div><!-- class="row" -->
        </div><!-- class="container" -->
        <%@include file="template/footer.jsp" %>
    </body>
</html>
</compress:html>