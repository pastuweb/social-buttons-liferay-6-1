<%
/**
 * Copyright (c) Pasturenzi Francesco
 * This is the VIEW of the Portlet.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<jsp:useBean id="urlAccountFacebook" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountLinkedIn" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountGooglePlus" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountTwitter" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountGitHub" class="java.lang.String" scope="request" />
<jsp:useBean id="currentUrl" class="java.lang.String" scope="request" />

<portlet:defineObjects />

<div class="MySocialButtons-portlet-bordiRotondi" style="position:relative;margin:auto;margin-top:5px;margin-bottom:5px;width:90%;">
	
	<div style="position:absolute;bottom:3px;left:3px;">
		<a href="http://www.pastuweb.com" target="_blank"><img src="http://www.pastuweb.com/loghi_pw/icone/pastuweb.png" width="30" alt="Creato da pastuweb.com" title="Creato da pastuweb.com" /></a>
	</div>
	
	<div style="text-align:center;">
		<strong>Share My Page</strong>
	</div>
	<div style="text-align:center;margin:auto;">
		<!-- Social Buttons -->
		
		<a href="https://twitter.com/share" class="twitter-share-button" data-lang="it" data-url="<%=currentUrl %>">Tweet</a>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
		
		<div class="g-plusone" data-size="medium" data-href="<%=currentUrl %>"></div>
		
		<!-- Inserisci questo tag dopo l'ultimo tag di pulsante +1. -->
		<script type="text/javascript">
		  window.___gcfg = {lang: 'it'};
		
		  (function() {
		    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
		    po.src = 'https://apis.google.com/js/plusone.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
		  })();
		</script>
		
		<div id="fb-root"></div>
		<script>(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/it_IT/all.js#xfbml=1";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));</script>
		
		<div class="fb-like" data-href="<%=currentUrl %>" data-send="true" data-layout="button_count" data-width="200" data-show-faces="true"></div>
		
		<script src="//platform.linkedin.com/in.js" type="text/javascript">
		 lang: en_US
		</script>
		<script type="IN/Share" data-counter="right" data-url="<%=currentUrl %>"></script>
		
		<div style="clear:left;"></div>
							
	</div>
	<div style="text-align:center;">
		<strong>My Social Accounts</strong>
	</div>
	<div style="text-align:center;margin:auto;width:100%;">
		<a href="<%=urlAccountFacebook%>" target="_blank"><img src="<%=request.getContextPath() %>/images/facebook.png" width="30" title="Account FaceBook" alt="Account FaceBook" /></a>  
		<a href="<%=urlAccountLinkedIn%>" target="_blank"><img src="<%=request.getContextPath() %>/images/linkedin.png" width="30" title="Account LinkedIn" alt="Account LinkedIn" /></a> 
		<a href="<%=urlAccountTwitter%>" target="_blank"><img src="<%=request.getContextPath() %>/images/twitter.png" width="31" title="Account Twitter" alt="Account Twitter" /></a> 
		<a href="<%=urlAccountGooglePlus%>" target="_blank"><img src="<%=request.getContextPath() %>/images/googleplus.png" width="39" title="Account Google Plus" alt="Account Google Plus" /></a> 
		<a href="<%=urlAccountGitHub%>" target="_blank"><img src="<%=request.getContextPath() %>/images/github.png" width="30" title="Account Git Hub" alt="Account Git Hub" /></a> 
	</div>
		
</div>