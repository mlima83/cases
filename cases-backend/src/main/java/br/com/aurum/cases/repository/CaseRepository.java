package br.com.aurum.cases.repository;

import br.com.aurum.cases.repository.basic.CaseRepositoryBasic;
import br.com.aurum.cases.repository.custom.CaseRepositoryCustom;

public interface CaseRepository extends CaseRepositoryCustom, CaseRepositoryBasic {

//	@Query("SELECT * FROM case WHERE folder >= :folder AND folder <= :folder ")
//	public Page<Case> findByFolder(@Param("folder") String folder, Pageable pageable);
}
