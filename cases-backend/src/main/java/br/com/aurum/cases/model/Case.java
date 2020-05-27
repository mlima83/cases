package br.com.aurum.cases.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import br.com.aurum.base.model.DefaultEntity;

@Entity
public class Case extends DefaultEntity {

	@Length(max = 40)
	private String folder;
	
	private String folderCi;
	
	@NotEmpty
	private String clients;
	
	private String clientsCi;
	
	@NotEmpty
	private String title;
	
	private String titleCi;
	
	private List<String> tags;
	
	private String description;
	
	private String descriptionCi;
	
	private String comments;
	
	@NotEmpty
	private String responsible;
	
	private String responsibleCi;
	
	private Access access;

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolderCi() {
		return folderCi;
	}

	public void setFolderCi(String folderCi) {
		this.folderCi = folderCi;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getClientsCi() {
		return clientsCi;
	}

	public void setClientsCi(String clientsCi) {
		this.clientsCi = clientsCi;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleCi() {
		return titleCi;
	}

	public void setTitleCi(String titleCi) {
		this.titleCi = titleCi;
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

	public String getDescriptionCi() {
		return descriptionCi;
	}

	public void setDescriptionCi(String descriptionCi) {
		this.descriptionCi = descriptionCi;
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

	public String getResponsibleCi() {
		return responsibleCi;
	}

	public void setResponsibleCi(String responsibleCi) {
		this.responsibleCi = responsibleCi;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

}
