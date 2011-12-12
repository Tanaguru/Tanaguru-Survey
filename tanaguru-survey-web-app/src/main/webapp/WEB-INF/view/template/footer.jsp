<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <footer>
                <div id="footer-upper">
                    <div>
                        <div class="row">
                            <div class="span4">
                                <h2><fmt:message key="footer.survey"/></h2>
                                <ul class="unstyled">
                                    <li><a href="${configProperties['methodology-url']}"><fmt:message key="footer.methodology"/></a></li>
                                    <li><a href="${configProperties['why-a-survey-url']}"><fmt:message key="footer.whyASurvey"/></a></li>
                                    <li><a href="${configProperties['github-url']}"><fmt:message key="footer.surveyOnGithub"/></a></li>
                                </ul>
                            </div><!-- class="span4" -->
                            <div class="span4">
                                <h2 id="about"><fmt:message key="footer.about"/></h2>
                                <ul class="unstyled">
                                    <li><a href="${configProperties['blog-url']}"><fmt:message key="footer.blog"/></a></li>
                                    <li><a href="${configProperties['who-we-are-url']}"><fmt:message key="footer.whoAreWe"/></a></li>
                                    <li><a href="${configProperties['contact-us-url']}"><fmt:message key="footer.contactUs"/></a></li>
                                </ul>
                            </div><!-- class="span4" -->
                            <div class="span4">
                                <h2><fmt:message key="footer.joinUs"/></h2>
                                <ul class="unstyled">
                                    <li class="social-network-footer">
                                        <div class="fb-like"
                                             data-href="https://www.facebook.com/observatoire.accessibilite"
                                             data-send="false"
                                             data-layout="button_count"
                                             data-width="30"
                                             data-show-faces="false"
                                             data-font="arial">
                                        </div>
                                    </li>
                                    <li class="social-network-footer">
                                        <a href="https://twitter.com/ObsAccess"
                                           class="twitter-follow-button"
                                           data-show-count="false"
                                           data-size="small"
                                           data-lang="${lang}"
                                           title="<fmt:message key="footer.follow"/> @ObsAccess <fmt:message key="footer.on"/> Twitter">
                                           @ObsAccess
                                        </a>
                                    </li>
                                </ul>
                            </div><!-- class="span4" -->
                            <div class="span4">
                                <h2><fmt:message key="footer.supporters"/></h2>
                                <div class="row">
                                    <div class="span2 partner-logo">
                                        <a href="http://acs-horizons.fr/" title="ACS Horizons">
                                            <img src="http://asset.open-s.com/Logo-autres/logo-acs-horizons-bgTransp-w40px-h40px.png" alt="ACS Horizons">
                                        </a>
                                    </div><!-- class="span2" -->
                                    <div class="span2 partner-logo">
                                        <a href="http://adullact.org/">
                                            <abbr title="Association des Développeurs et Utilisateurs de Logiciels Libres dans les Administrations et Collectivités Territoriales">
                                                <img src="http://asset.open-s.com/Logo-autres/logo-adullact-bgTransp-w90px-h29px.png" alt="Adullact">
                                            </abbr>
                                        </a>
                                    </div><!-- class="span2" -->
                                </div><!-- class="row" -->
                            </div><!-- class="span4" -->
                        </div><!-- class="row" -->
                    </div>
                </div><!-- id="footer-up" -->
                <div id="footer-down">
                    <div>
                        <div class="row">
                            <div class="span16">
                                <fmt:message key="footer.main">
                                    <fmt:param>
                                        ${configProperties['tanaguru-version']}
                                    </fmt:param>
                                </fmt:message>
                            </div>
                        </div>
                        <div class="row">
                            <div class="span16">
                                &copy; Open-S 2011
                            </div>
                        </div>
                        <div class="row row-top-spacing1 row-bottom-spacing2">
                            <div class="span4 offset4">
                                <a href="http://www.open-s.com" title="<fmt:message key="footer.opens"/>">
                                    <img src="<c:url value="/Images/logo-Open-S-small.png"/>" alt="<fmt:message key="footer.opens"/>" class="logo"/>
                                </a>
                            </div>
                            <div class="span4">
                                <a href="http://tanaguru.com" title="<fmt:message key="footer.tanaguru"/>">
                                    <img src="<c:url value="/Images/Logo-tanaguru-small.png"/>" alt="<fmt:message key="footer.tanaguru"/>" class="logo"/>
                                </a>
                            </div>
                        </div>
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
            <div id="fb-root"></div>
            <c:choose>
                <c:when test="${lang == 'fr'}">
                    <c:set var="fbLang" value="fr_FR"/>
                </c:when>
                <c:otherwise>
                    <c:set var="fbLang" value="en_GB"/>
                </c:otherwise>
            </c:choose>
            <script>
                (function(d, s, id) {
                  var js, fjs = d.getElementsByTagName(s)[0];
                  if (d.getElementById(id)) return;
                  js = d.createElement(s); js.id = id;
                  js.src = "//connect.facebook.net/${fbLang}/all.js#xfbml=1";
                  fjs.parentNode.insertBefore(js, fjs);
                }(document, 'script', 'facebook-jssdk'));
            </script>
            <script>
                !function(d,s,id){
                var js,fjs=d.getElementsByTagName(s)[0];
                if(!d.getElementById(id)){js=d.createElement(s);
                    js.id=id;js.src="//platform.twitter.com/widgets.js";
                    fjs.parentNode.insertBefore(js,fjs);
                }}(document,"script","twitter-wjs");
            </script>