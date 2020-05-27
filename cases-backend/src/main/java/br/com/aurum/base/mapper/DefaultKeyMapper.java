package br.com.aurum.base.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

@Component
public class DefaultKeyMapper {
	
	@Value("${spring.cloud.gcp.datastore.project-id}")
	private String projectId;
	
	private KeyFactory keyFactory;

	public Key convertToKey(Long id, Class<?> entityClass) {
		if (id == null) {
			return null;
		}
		return getKeyFactory(entityClass).newKey(id);
	}

	private KeyFactory getKeyFactory(Class<?> entityClass) {
		if (this.keyFactory == null) {
			this.keyFactory = new KeyFactory(getProjectId()).setKind(getKind(entityClass));
		}
		return this.keyFactory;
	}

	private String getKind(Class<?> entityClass) {
		Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
		if (entityAnnotation != null 
				&& entityAnnotation.name() != null 
				&& !entityAnnotation.name().isEmpty()) {
			return entityAnnotation.name();
		}

		return entityClass.getSimpleName().toLowerCase();
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
