<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <footer>
                <div id="footer-upper">
                    <div>
                        <div class="row">
                            <div class="span-one-third">
                                <h2><fmt:message key="footer.survey"/></h2>
                                <ul class="unstyled">
                                    <li><a href="#"><fmt:message key="footer.methodology"/></a></li>
                                    <li><a href="#"><fmt:message key="footer.whyASurvey"/></a></li>
                                    <li><a href="https://github.com/mfaure/Tanaguru-Survey"><fmt:message key="footer.surveyOnGithub"/></a></li>
                                </ul>
                            </div><!-- class="span-one-third" -->
                            <div class="span-one-third">
                                <h2><fmt:message key="footer.accessibility"/></h2>
                                <ul class="unstyled">
                                    <li><a href="#"><abbr lang="en" title="Web Content Accessibility Guidelines">WCAG</abbr>2.0</a></li>
                                    <li><a href="#">AccessiWeb 2.1</a></li>
                                    <li><a href="#"><abbr title="Référentiel Générale d'Accessibilité des Administrations">RGAA</abbr>2.2.1</a></li>
                                </ul>
                            </div><!-- class="span-one-third" -->
                            <div class="span-one-third">
                                <h2><fmt:message key="footer.about"/></h2>
                                <ul class="unstyled">
                                    <li><a href="#"><fmt:message key="footer.whoAreWe"/></a></li>
                                </ul>
                            </div><!-- class="span-one-third" -->
                        </div><!-- class="row" -->
                    </div>
                </div><!-- id="footer-up" -->
                <div id="footer-down">
                    <div>
                        <div class="row">
                            <div class="span16">
                                <p>&copy; 2011 <a href="http://www.Open-S.com/">Open-S</a> &amp; <a href="http://www.atalan.fr/">Atalan</a></p>
                            </div><!-- class="span16" -->
                        </div><!-- class="row" -->
                    </div>
                </div><!-- id="footer-down" -->
            </footer>
                                
            <c:if test="${not empty configProperties['google-analytics-code']}">
            <script type="text/javascript">
                var _gaq = _gaq || [];
                _gaq.push(['_setAccount', '${configProperties['google-analytics-code']}']);
                _gaq.push(['_trackPageview']);
                (function() {
                    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
                })();
            </script>
            </c:if>                                