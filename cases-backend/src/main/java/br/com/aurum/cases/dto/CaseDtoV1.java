package br.com.aurum.cases.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.aurum.base.dto.DefaultEntityDto;
import br.com.aurum.cases.model.Access;

public class CaseDtoV1 extends DefaultEntityDto{
	
	@Length(max = 40)
	private String folder;
	
	@NotEmpty
	private String clients;
	
	@NotEmpty
	private String title;
	
	private List<String> tags;
	
	private String description;
	
	private String comments;
	
	@NotEmpty
	private String responsible;
	
	private Access access;

	public CaseDtoV1() {}
	
	public CaseDtoV1(String folder, String title, String description, String clients, String tag, Access access,
			Date dtCreated) {
		this.folder = folder;
		this.description = description;
		this.title = title;
		this.clients = clients;
		if (tag != null && !tag.isEmpty()) {
			this.tags = Arrays.asList(tag);
		}
		this.access = access;
		setDtCreated(dtCreated);
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

}
