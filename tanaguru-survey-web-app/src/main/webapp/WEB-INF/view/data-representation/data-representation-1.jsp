<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

                    <div class="row">
                        <div class="span15 offset1">
                            <p class="process-remarks">
                            <fmt:message  key="${remarkInfosItem.messageCode}">
                            <c:if test='${remarkInfosItem.remarkTarget != null}'>
                                <c:set var="remarkTarget">
                                    ${fn:escapeXml(fn:replace(remarkInfosItem.remarkTarget,"&", "&amp;"))}
                                </c:set>
                                <fmt:param value="${remarkTarget}"/>
                            </c:if>
                            </fmt:message>
                            </p>
                        </div><!-- class="span15 offset1" -->
                    </div><!-- class="row" -->
                    <c:if test='${not empty remarkInfosItem.evidenceElementList}'>
                    <div class="row">
                        <div class="span14 offset2">
                            <ul class="process-remarks">
                                <c:forEach var="childRemarkItem" items="${remarkInfosItem.evidenceElementList}">
                                    <li>
                                    <c:forEach var="evidenceElement" items="${childRemarkItem}">
                                        <c:choose>
                                            <c:when test="${evidenceElement.key == 'Line-Number'}">
                                                <a href="#line${evidenceElement.value}">
                                                    <fmt:message key="${evidenceElement.key}"/> ${evidenceElement.value}
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message key="${evidenceElement.key}"/> ${fn:escapeXml(fn:replace(evidenceElement.value, "&", "&amp;"))}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div><!-- class="span14 offset2" -->
                    </div><!-- class="row" -->
                    </c:if>