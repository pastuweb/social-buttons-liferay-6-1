package com.appuntivarinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javax.portlet.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.util.PortalUtil;


public class MySocialButtons extends GenericPortlet {
	
	public static Log log = LogFactory.getLog("MySocialButtons");
	
	/* initialize the default parameter of "portlet.xml" */
	protected String editJSP;
	protected String viewJSP;
	private String googUrl = "https://www.googleapis.com/urlshortener/v1/url?shortUrl=http://goo.gl/fbsS&key=AIzaSyCwkdNYB0IVPOaHfqTxb7XNcUOmYIpUkZ4";
	
	public void init() throws PortletException{
		editJSP = getInitParameter("edit-jsp");
		viewJSP = getInitParameter("view-jsp");
	}
	
	
	//set the Portlet's default View
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		PortletPreferences prefs = renderRequest.getPreferences();
		String accountFb = (String) prefs.getValue("urlAccountFb", "none");
		String accountLIn = (String) prefs.getValue("urlAccountLIn", "none");
		String accountGoo = (String) prefs.getValue("urlAccountGoo", "none");
		String accountTw = (String) prefs.getValue("urlAccountTw", "none");
		String accountGit = (String) prefs.getValue("urlAccountGit", "none");
		
		/* Default value */
		if(accountFb.equalsIgnoreCase("none")){
			accountFb = "https://www.facebook.com/francesco.pasturenzi";
		}
		if(accountLIn.equalsIgnoreCase("none")){
			accountLIn = "http://it.linkedin.com/in/francescopasturenzi";
		}
		if(accountGoo.equalsIgnoreCase("none")){
			accountGoo = "https://plus.google.com/u/0/115914974766338057909/about";
		}
		if(accountTw.equalsIgnoreCase("none")){
			accountTw = "https://twitter.com/pasturenzi";
		}
		if(accountGit.equalsIgnoreCase("none")){
			accountGit = "https://github.com/pastuweb";
		}
		
		String dominio = renderRequest.getServerName();
		String currentUrl = "http://" + dominio + "" +PortalUtil.getCurrentURL(renderRequest);
		
		/*Accedo alla API di Google: Shorter Url*/
		String currentUrlShort = shorten(currentUrl);
		
		renderRequest.setAttribute("urlAccountFacebook", accountFb);
		renderRequest.setAttribute("urlAccountLinkedIn", accountLIn);
		renderRequest.setAttribute("urlAccountGooglePlus", accountGoo);
		renderRequest.setAttribute("urlAccountTwitter", accountTw);
		renderRequest.setAttribute("urlAccountGitHub", accountGit);
		renderRequest.setAttribute("currentUrl", currentUrlShort);
		
		include(viewJSP, renderRequest, renderResponse);
	}
	
	
	/* special method: used to dispatch to right JSP */
	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);
		if(portletRequestDispatcher == null){
			log.info("path : "+path+" non e' valido.");
		}else{
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}
	
	/* set the Portlet's default Edit: it's a simple <form> */
	public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		
		renderResponse.setContentType("text/html");
		
		PortletPreferences prefs = renderRequest.getPreferences();
		String accountFb = (String) prefs.getValue("urlAccountFb", "none");
		String accountLIn = (String) prefs.getValue("urlAccountLIn", "none");
		String accountGoo = (String) prefs.getValue("urlAccountGoo", "none");
		String accountTw = (String) prefs.getValue("urlAccountTw", "none");
		String accountGit = (String) prefs.getValue("urlAccountGit", "none");
		
		/* Default value */
		if(accountFb.equalsIgnoreCase("none")){
			accountFb = "https://www.facebook.com/francesco.pasturenzi";
		}
		if(accountLIn.equalsIgnoreCase("none")){
			accountLIn = "http://it.linkedin.com/in/francescopasturenzi";
		}
		if(accountGoo.equalsIgnoreCase("none")){
			accountGoo = "https://plus.google.com/u/0/115914974766338057909/about";
		}
		if(accountTw.equalsIgnoreCase("none")){
			accountTw = "https://twitter.com/pasturenzi";
		}
		if(accountGit.equalsIgnoreCase("none")){
			accountGit = "https://github.com/pastuweb";
		}
		
		renderRequest.setAttribute("urlAccountFacebook", accountFb);
		renderRequest.setAttribute("urlAccountLinkedIn", accountLIn);
		renderRequest.setAttribute("urlAccountGooglePlus", accountGoo);
		renderRequest.setAttribute("urlAccountTwitter", accountTw);
		renderRequest.setAttribute("urlAccountGitHub", accountGit);
		
		PortletURL saveAccountsURL = renderResponse.createActionURL();
		saveAccountsURL.setParameter("saveAccounts", "saveAccounts");
		renderRequest.setAttribute("saveAccountsURL", saveAccountsURL.toString());
		
		/*You can add other ACTION URL for the EDIT JSP*/
		
		include(editJSP, renderRequest, renderResponse);
		
	}
	
	
	/* ACTION call from Portlet's <form> of EDIT JSP */
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException{
		
		String saveAccounts = actionRequest.getParameter("saveAccounts");
		/*You can add other getParameter of EDIT JSP*/
		
		if(saveAccounts != null){
			PortletPreferences prefs = actionRequest.getPreferences();
			prefs.setValue("urlAccountFb", actionRequest.getParameter("inUrlAccountFacebook"));
			prefs.setValue("urlAccountLIn", actionRequest.getParameter("inUrlAccountLinkedIn"));
			prefs.setValue("urlAccountGoo", actionRequest.getParameter("inUrlAccountGooglePlus"));
			prefs.setValue("urlAccountTw", actionRequest.getParameter("inUrlAccountTwitter"));
			prefs.setValue("urlAccountGit", actionRequest.getParameter("inUrlAccountGitHub"));
			
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		}
		
		/*You can test other getParameter of EDIT JSP*/
		
		
	}
	
	
	/*special method: it used to have the shorten url */
	private String shorten(String longUrl){
	    String shortUrl = "";

	    try{
	        URLConnection conn = new URL(googUrl).openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "application/json");
	        OutputStreamWriter wr = 
	                     new OutputStreamWriter(conn.getOutputStream());
	        wr.write("{\"longUrl\":\"" + longUrl + "\"}");
	        wr.flush();

	        // Get the response
	        BufferedReader rd = 
	                     new BufferedReader(
	                     new InputStreamReader(conn.getInputStream()));
	        String line;

	        while ((line = rd.readLine()) != null)
	        {
	            if (line.indexOf("id") > -1)
	            {
	                // I'm sure there's a more elegant way of parsing
	                // the JSON response, but this is quick/dirty =)
	                shortUrl = line.substring(8, line.length() - 2);
	                break;
	            }
	        }

	        wr.close();
	        rd.close();
	    }catch (MalformedURLException ex){
	        Logger.getLogger("MalformedUrl");
	    }catch (IOException ex){
	        Logger.getLogger("IoException");        
	    }

	    return shortUrl;
	}
	
	
}
