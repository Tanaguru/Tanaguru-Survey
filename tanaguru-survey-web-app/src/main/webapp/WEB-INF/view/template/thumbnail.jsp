<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <div class="span${proportion} offset1">
                    <div class="thumbnail ${cml}" >
                        <a href="${contractResult.url}"><img src="${configProperties['snapshot-server-url']}&amp;url=${url}&amp;q=90&amp;w=${snapshotWidth}&amp;h=${snapshotHeigth}"/></a>
                    </div>
                </div>