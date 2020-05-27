package br.com.aurum.cases.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.aurum.cases.dto.CaseDtoV1;
import br.com.aurum.cases.model.Case;

public interface CaseRepositoryCustom {
	
	public Page<Case> findByExample(CaseDtoV1 caseExample, Pageable pageable);
}
