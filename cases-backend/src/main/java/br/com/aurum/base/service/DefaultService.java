package br.com.aurum.base.service;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.cloud.datastore.Key;

import br.com.aurum.base.exception.BusinessException;
import br.com.aurum.base.mapper.DefaultKeyMapper;
import br.com.aurum.base.model.DefaultEntity;

public abstract class DefaultService<T extends DefaultEntity> {
	
	public abstract DefaultKeyMapper getKeyMapper();

	public abstract DatastoreRepository<T, Key> getRepository();

	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}

	public Page<T> findAllPagination(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	public T findById(Long id) {
		Optional<T> retorno = getRepository().findById(convertKey(id));
		if (retorno.isPresent()) {
			return retorno.get();
		} else {
			return null;
		}
	}

	public long count() {
		return getRepository().count();
	}

	public T save(@Valid T t) throws BusinessException {
		validate(t);
		t.setDtUpdated(new Date());
		if (t.getId() == null) {
			t.setDtCreated(new Date());
		}
		return (T) getRepository().save(t);
	}

	public void validate(T t) throws BusinessException {}

	public List<T> saveAll(@NotEmpty @RequestBody List<@Valid T> listOfAll) {
		return (List<T>) getRepository().saveAll(listOfAll);
	}

	public void deleteAll() {
		getRepository().deleteAll();
	}

	public void deleteById(Long id) {
		getRepository().deleteById(convertKey(id));
	}

	private Key convertKey(Long id) {
		return getKeyMapper().convertToKey(id, getTypeKind());
	}

	@SuppressWarnings({ "unchecked" })
	private Class<T> getTypeKind() {
		return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

}
