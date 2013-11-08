package com.ge.dsp.event.subscriber.core.fakes;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ge.dsp.commons.rest.PropertiesWrapper;
import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.dsi.notification.exception.DupsException;

public class FakeDupsService implements IDups {

	private List<IPreference> preferenceList;

	
	@POST
	@Path("createUserPreference")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public boolean createUserPreference(@FormParam("uuid") String uuid,
			@FormParam("dupsContext") String dupsContext,
			@FormParam("key") String key, @FormParam("value") String value)
			throws DupsUserNotFoundException, DupsException {
		// TODO Auto-generated method stub
		return false;
	}

	
	@POST
	@Path("createUserPreferences")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public boolean createUserPreferences(@FormParam("uuid") String uuid,
			@FormParam("dupsContext") String dupsContext,
			@FormParam("props") PropertiesWrapper props)
			throws DupsUserNotFoundException, DupsException {
		// TODO Auto-generated method stub
		return false;
	}

	
	@GET
	@Path("getUserPreference")
	@Consumes("text/plain")
	@Produces("application/json")
	public List<IPreference> getUserPreference(@QueryParam("uuid") String uuid,
			@QueryParam("dupsContext") String dupsContext,
			@QueryParam("key") String key) throws DupsUserNotFoundException {
		// TODO Auto-generated method stub
		return preferenceList;
	}

	
	@GET
	@Path("getUserPreferenceAll")
	@Consumes("text/plain")
	@Produces("application/json")
	public List<IPreference> getUserPreferenceAll(
			@QueryParam("dupsContext") String dupsContext)
			throws DupsUserNotFoundException {
		// TODO Auto-generated method stub
		return preferenceList;
	}

	
	@GET
	@Path("getUserPreferences")
	@Consumes("text/plain")
	@Produces({ "application/json", "application/xml" })
	public PropertiesWrapper getUserPreferences(
			@QueryParam("uuid") String uuid,
			@QueryParam("dupsContext") String dupsContext)
			throws DupsUserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@POST
	@Path("updateUserPreference")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public boolean updateUserPreference(@FormParam("uuid") String uuid,
			@FormParam("dupsContext") String dupsContext,
			@FormParam("key") String key, @FormParam("value") String value)
			throws DupsUserNotFoundException, DupsException {
		// TODO Auto-generated method stub
		return false;
	}

	
	@POST
	@Path("updateUserPreferences")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public boolean updateUserPreferences(@FormParam("uuid") String uuid,
			@FormParam("dupsContext") String dupsContext,
			@FormParam("props") PropertiesWrapper props)
			throws DupsUserNotFoundException, DupsException {
		// TODO Auto-generated method stub
		return false;
	}

	
	@GET
	@Path("validateUserInIdentityStore")
	@Consumes("text/plain")
	@Produces("text/plain")
	public boolean validateUserInIdentityStore(@QueryParam("uuid") String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setPreferenceList(List<IPreference> preferenceList) {
		this.preferenceList = preferenceList;
		
	}

}
