package br.com.aurum.cases.repository.basic;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.google.cloud.datastore.Key;

import br.com.aurum.cases.model.Case;

public interface CaseRepositoryBasic extends DatastoreRepository<Case,Key> {

}
