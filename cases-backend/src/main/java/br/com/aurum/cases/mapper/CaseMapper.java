package br.com.aurum.cases.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aurum.base.mapper.DefaultEntityMapper;
import br.com.aurum.base.mapper.DefaultKeyMapper;
import br.com.aurum.base.utils.Utils;
import br.com.aurum.cases.dto.CaseDtoV1;
import br.com.aurum.cases.model.Case;

@Component
public class CaseMapper extends DefaultEntityMapper<Case, CaseDtoV1>{
	
	@Autowired
	private DefaultKeyMapper defaultKeyMapper;
	
	@Autowired
	private Utils utils;

	@Override
	public DefaultKeyMapper getKeyMapper() {
		return defaultKeyMapper;
	}

	@Override
	public CaseDtoV1 convertEntityToDto(Case entity, CaseDtoV1 dto) {
		if (dto == null) {
			dto = new CaseDtoV1();
		}
		dto.setFolder(entity.getFolder());
		dto.setAccess(entity.getAccess());
		dto.setClients(entity.getClients());
		dto.setComments(entity.getComments());
		dto.setDescription(entity.getDescription());
		dto.setResponsible(entity.getResponsible());
		dto.setTags(entity.getTags());
		dto.setTitle(entity.getTitle());
		
		return (CaseDtoV1) convertDefaultEntityToDto(entity, dto);
	}

	@Override
	public Case convertDtoToEntity(CaseDtoV1 dto, Case entity) {
		if (entity == null) {
			entity = new Case();
		}
		entity.setFolder(dto.getFolder());
		if (dto.getFolder() != null && !dto.getFolder().isEmpty()) {
			entity.setFolderCi(utils.unaccent(dto.getFolder().toLowerCase()));
		}
		entity.setAccess(dto.getAccess());
		entity.setClients(dto.getClients());
		if (dto.getClients() != null && !dto.getClients().isEmpty()) {
			entity.setClientsCi(utils.unaccent(dto.getClients().toLowerCase()));
		}
		entity.setComments(dto.getComments());
		entity.setDescription(dto.getDescription());
		if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
			entity.setDescriptionCi(utils.unaccent(dto.getDescription().toLowerCase()));
		}
		entity.setResponsible(dto.getResponsible());
		if (dto.getResponsible() != null && !dto.getResponsible().isEmpty()) {
			entity.setResponsibleCi(utils.unaccent(dto.getResponsible().toLowerCase()));
		}
		entity.setTags(dto.getTags());
		entity.setTitle(dto.getTitle());
		if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
			entity.setTitleCi(utils.unaccent(dto.getTitle().toLowerCase()));
		}
		
		return (Case) convertDefaultDtoToEntity(dto, entity);
	}


}
