<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

                    <table class="tg-data-table zebra-striped" summary="<fmt:message key="top5-table.summary"><fmt:param>${surveyList.name}</fmt:param></fmt:message>">
                        <caption class="${captionClass}"><fmt:message key="top5-table.caption"><fmt:param><%@include file="survey-list-name.jsp" %></fmt:param></fmt:message></caption>
                        <thead>
                            <tr>
                                <th scope="col" class="align-center"><fmt:message key="top5-table.rank"/></th>
                                <th scope="col"><fmt:message key="top5-table.site"/></th>
                                <th scope="col" class="align-right"><fmt:message key="top5-table.score"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contractResult" items="${topContractCollection}" begin="0" end="4" varStatus="pContractResult">
                            <tr>
                                <td class="align-center top5-rank${pContractResult.index+1}">${pContractResult.index+1}</td>
                                <td><a href="${contractResult.url}">${contractResult.label}</a></td>
                                <td class="align-right"><%@include file="score.jsp" %></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>