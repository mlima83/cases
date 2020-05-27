package br.com.aurum.cases.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.DatastoreReaderWriter;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.GqlQuery;
import com.google.cloud.datastore.GqlQuery.Builder;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.Query.ResultType;
import com.google.common.collect.Iterators;

import br.com.aurum.base.utils.Utils;
import br.com.aurum.cases.dto.CaseDtoV1;
import br.com.aurum.cases.model.Case;
import br.com.aurum.cases.repository.custom.CaseRepositoryCustom;

public class CaseRepositoryImpl implements CaseRepositoryCustom {
	
	@Autowired
	private DatastoreTemplate datastoreTemplate;
	
	@Autowired
	private DatastoreReaderWriter datastore;
	
	@Autowired
	private Utils utils; 
	
	@Override
	public Page<Case> findByExample(CaseDtoV1 caseExample, Pageable pageable) {
		StringBuilder gql = new StringBuilder("SELECT %s FROM case WHERE active = @active ");
		if (caseExample.getFolder() != null && !caseExample.getFolder().isEmpty()) {
			gql.append(" AND folderCi = @folderCi ");
		}
		if (caseExample.getDescription() != null && !caseExample.getDescription().isEmpty()) {
			gql.append(" AND descriptionCi = @descriptionCi ");
		}
		if (caseExample.getAccess() != null) {
			gql.append(" AND access = @access ");
		}
		if (caseExample.getClients() != null && !caseExample.getClients().isEmpty()) {
			gql.append(" AND clientsCi = @clientsCi ");
		}
		if (caseExample.getDtCreated() != null) {
			gql.append(" AND dtCreated = @dtCreated ");
		}
		if (caseExample.getTags() != null && !caseExample.getTags().isEmpty()) {
			gql.append(" AND tags contains @tags ");
		}
		if (caseExample.getTitle() != null && !caseExample.getTitle().isEmpty()) {
			gql.append(" AND titleCi = @titleCi ");
		}
		
		
		Builder<Key> countQueryBuilder = Query.newGqlQueryBuilder(ResultType.KEY, String.format(gql.toString(), "__key__"))
			    .setBinding("active", true);
		
		gql.append(" LIMIT @Limit OFFSET @Offset ");
		
		Builder<Entity> entityQueryBuilder = Query.newGqlQueryBuilder(ResultType.ENTITY, String.format(gql.toString(), "*"))
			    .setBinding("active", true)
				.setBinding("Limit", pageable.getPageSize())
				.setBinding("Offset", pageable.getPageNumber() * pageable.getPageSize());
		
		if (caseExample.getFolder() != null && !caseExample.getFolder().isEmpty()) {
			countQueryBuilder.setBinding("folderCi",  utils.unaccent(caseExample.getFolder().toLowerCase()));
			entityQueryBuilder.setBinding("folderCi",  utils.unaccent(caseExample.getFolder().toLowerCase()));
		}
		if (caseExample.getDescription() != null && !caseExample.getDescription().isEmpty()) {
			countQueryBuilder.setBinding("descriptionCi",  utils.unaccent(caseExample.getDescription().toLowerCase()));
			entityQueryBuilder.setBinding("descriptionCi",  utils.unaccent(caseExample.getDescription().toLowerCase()));
		}
		if (caseExample.getAccess() != null) {
			countQueryBuilder.setBinding("access", caseExample.getAccess().name());
			entityQueryBuilder.setBinding("access",  caseExample.getAccess().name());
		}
		if (caseExample.getClients() != null && !caseExample.getClients().isEmpty()) {
			countQueryBuilder.setBinding("clientsCi",  utils.unaccent(caseExample.getClients().toLowerCase()));
			entityQueryBuilder.setBinding("clientsCi",  utils.unaccent(caseExample.getClients().toLowerCase()));
		}
		if (caseExample.getDtCreated() != null) {
			countQueryBuilder.setBinding("dtCreated", Timestamp.of(caseExample.getDtCreated()));
			entityQueryBuilder.setBinding("dtCreated",  Timestamp.of(caseExample.getDtCreated()));
		}
		if (caseExample.getTags() != null && !caseExample.getTags().isEmpty()) {
			countQueryBuilder.setBinding("tags",  caseExample.getTags().toArray(new String[] {}));
			entityQueryBuilder.setBinding("tags",  caseExample.getTags().toArray(new String[] {}));
		}
		if (caseExample.getTitle() != null && !caseExample.getTitle().isEmpty()) {
			countQueryBuilder.setBinding("titleCi",  utils.unaccent(caseExample.getTitle().toLowerCase()));
			entityQueryBuilder.setBinding("titleCi",  utils.unaccent(caseExample.getTitle().toLowerCase()));
		}
		
		GqlQuery<Key> queryKey = countQueryBuilder.build();
		Long count = Long.valueOf(Iterators.size(datastore.run(queryKey)));
		
		Query<Entity> query = entityQueryBuilder.build();
		return new PageImpl<Case>((List<Case>) this.datastoreTemplate.query(query, Case.class), pageable, count);
	}
	
//	@Override
//	public Page<Case> findByExample(CaseDtoV1 caseExample, Pageable pageable) {
//		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//		if (caseExample.getFolder() != null && !caseExample.getFolder().isEmpty()) {
//			filters.add(PropertyFilter.ge("folderCi", utils.unaccent(caseExample.getFolder().toLowerCase())));
//		}
//		if (caseExample.getAccess() != null) {
//			filters.add(PropertyFilter.eq("access", caseExample.getAccess().name()));
//		}
//		if (caseExample.getClients() != null && !caseExample.getClients().isEmpty()) {
//			filters.add(PropertyFilter.ge("clientsCi", utils.unaccent(caseExample.getClients().toLowerCase())));
//		}
//		if (caseExample.getDescription() != null && !caseExample.getDescription().isEmpty()) {
//			filters.add(PropertyFilter.ge("descriptionCi", utils.unaccent(caseExample.getDescription().toLowerCase())));
//		}
//		if (caseExample.getDtCreated() != null) {
//			filters.add(PropertyFilter.eq("dtCreated", Timestamp.of(caseExample.getDtCreated())));
//		}
//		if (caseExample.getTags() != null && !caseExample.getTags().isEmpty()) {
//			caseExample.getTags().forEach(tag -> {
//				filters.add(PropertyFilter.eq("tags", tag));
//			});
//		}
//		if (caseExample.getTitle() != null && !caseExample.getTitle().isEmpty()) {
//			filters.add(PropertyFilter.ge("titleCi", utils.unaccent(caseExample.getTitle().toLowerCase())));
//		}
//		
//		Query<Entity> query =
//			    Query.newEntityQueryBuilder().setKind("case")
//			    .setFilter(
//			    		CompositeFilter.and(
//			    				PropertyFilter.eq("active", true),
//			    				filters.toArray(new PropertyFilter[] {})))
//			    .setLimit(pageable.getPageSize())
//			    .setOffset(pageable.getPageNumber() * pageable.getPageSize())
////			    .setOrderBy(OrderBy.asc("folderCi"))
//			    .build();
//		Query<Key> queryKey =
//			    Query.newKeyQueryBuilder().setKind("case")
//			    .setFilter(
//			    		CompositeFilter.and(
//			    				PropertyFilter.eq("active", true),
//			    				filters.toArray(new PropertyFilter[] {})))
//			    .build();
//		Long count = StreamSupport.stream(this.datastoreTemplate.queryKeys(queryKey).spliterator(), false).count();
//		return new PageImpl<Case>((List<Case>) this.datastoreTemplate.query(query, Case.class), pageable, count);
//	}
	

}
