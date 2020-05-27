package br.com.aurum.cases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.cloud.datastore.Key;

import br.com.aurum.base.dto.ErrorItemDtoV1;
import br.com.aurum.base.exception.BusinessException;
import br.com.aurum.base.mapper.DefaultKeyMapper;
import br.com.aurum.base.service.DefaultService;
import br.com.aurum.cases.dto.CaseDtoV1;
import br.com.aurum.cases.model.Case;
import br.com.aurum.cases.repository.CaseRepository;

@Service
@Validated
public class CaseService extends DefaultService<Case>{

	@Autowired
	private CaseRepository caseRepository;
	
	@Autowired
	private DefaultKeyMapper defaultKeyMapper;
	
    public Page<Case> findByExample(CaseDtoV1 caseExample, Pageable pageable){
    	return this.caseRepository.findByExample(caseExample, pageable);
    }
    
    @Override
    public void validate(Case t) throws BusinessException {
    	if (t == null) {
    		throw new BusinessException("global", "Case must not be empty ");
    	}
    	List<ErrorItemDtoV1> errors = new ArrayList<ErrorItemDtoV1>();
    	if (t.getClients() == null || t.getClients().isEmpty()) {
    		errors.add(new ErrorItemDtoV1("clients", "Clients must not be empty"));
    	}
    	if (t.getTitle() == null || t.getTitle().isEmpty()) {
    		errors.add(new ErrorItemDtoV1("title", "Title must not be empty"));
    	}
    	if (t.getResponsible() == null || t.getResponsible().isEmpty()) {
    		errors.add(new ErrorItemDtoV1("responsible", "Responsible must not be empty"));
    	}
     	if (t.getFolder() != null && t.getFolder().length() > 40) {
    		errors.add(new ErrorItemDtoV1("folder", "Folder maximum characters 40"));
    	}
     	if (!errors.isEmpty()) {
     		throw new BusinessException(errors);
     	}
    }
    
	@Override
	public DatastoreRepository<Case, Key> getRepository() {
		return this.caseRepository;
	}

	@Override
	public DefaultKeyMapper getKeyMapper() {
		return this.defaultKeyMapper;
	}

}
