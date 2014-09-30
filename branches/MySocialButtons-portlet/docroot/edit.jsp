<%
/**
 * Copyright (c) Pasturenzi Francesco
 * This is the form that you can see click on button "Preferences" of the Portlet
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<jsp:useBean id="saveAccountsURL" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountFacebook" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountLinkedIn" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountGooglePlus" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountTwitter" class="java.lang.String" scope="request" />
<jsp:useBean id="urlAccountGitHub" class="java.lang.String" scope="request" />
<jsp:useBean id="currentUrl" class="java.lang.String" scope="request" />

<portlet:defineObjects />


<form id="<portlet:namespace/>accountForm" action="<%=saveAccountsURL%>" method="POST">
	<span style="color:#000000;">URL Account <img src="<%=request.getContextPath() %>/images/facebook.png" width="30" title="Account FaceBook" alt="Account FaceBook" />:</span><br>
		<input type="text" name="inUrlAccountFacebook" size="50" value="<%=urlAccountFacebook%>"><br>
	<span style="color:#000000;">URL Account <img src="<%=request.getContextPath() %>/images/linkedin.png" width="30" title="Account LinkedIn" alt="Account LinkedIn" />:</span><br>
		<input type="text" name="inUrlAccountLinkedIn" size="50" value="<%=urlAccountLinkedIn%>"><br>
	<span style="color:#000000;">URL Account <img src="<%=request.getContextPath() %>/images/googleplus.png" width="39" title="Account Google Plus" alt="Account Google Plus" />:</span><br>
		<input type="text" name="inUrlAccountGooglePlus" size="50" value="<%=urlAccountGooglePlus%>"><br>
	<span style="color:#000000;">URL Account <img src="<%=request.getContextPath() %>/images/twitter.png" width="30" title="Account Twitter" alt="Account Twitter" />:</span><br>
		<input type="text" name="inUrlAccountTwitter" size="50" value="<%=urlAccountTwitter%>"><br>
	<span style="color:#000000;">URL Account <img src="<%=request.getContextPath() %>/images/github.png" width="30" title="Account GitHub" alt="Account GitHub" />:</span><br>
		<input type="text" name="inUrlAccountGitHub" size="50" value="<%=urlAccountGitHub%>"><br>
	<p style="text-align:right;">
	<input type="submit" id="inviaAccountsForm" title="Save" value="Save">
	</p>
	<br>
</form>