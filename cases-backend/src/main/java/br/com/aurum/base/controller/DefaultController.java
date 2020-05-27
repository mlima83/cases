package br.com.aurum.base.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.aurum.base.exception.BusinessException;
import br.com.aurum.base.mapper.DefaultEntityMapper;
import br.com.aurum.base.model.DefaultEntity;
import br.com.aurum.base.service.DefaultService;

public abstract class DefaultController<T extends DefaultEntity, DTO>  {

    public abstract DefaultService<T> getService();
    
    public abstract DefaultEntityMapper<T, DTO> getMapper();

	@GetMapping
    public List<DTO> findAll() {
    	return getMapper().convertListEntityToListDto(getService().findAll());
    }

    @GetMapping(path = "/pagination")
    public Page<DTO> findAllPagination(
            @PageableDefault(sort = "dtCreated", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
        return getMapper().convertPageEntityToPageDto(getService().findAllPagination(pageable));
    }

    @GetMapping("{id}")
    public DTO findById(@PathVariable("id") Long id) {
        return getMapper().convertEntityToDto(getService().findById(id), null);
    }

    @GetMapping("count")
    public long count() {
        return getService().count();
    }

    @PostMapping
    public DTO save(@RequestBody @Valid DTO dto) throws BusinessException {
		return getMapper().convertEntityToDto(
				getService().save(getMapper().convertDtoToEntity(dto, null)), null);
    }

    @PostMapping("saveall")
    public List<DTO> saveAll(@NotEmpty @RequestBody List<@Valid DTO> listOfAll) {
        return getMapper().convertListEntityToListDto(
        		getService().saveAll(getMapper().convertListDtoToListEntity(listOfAll)));
    }

    @DeleteMapping
    public void deleteAll() {
        getService().deleteAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        getService().deleteById(id);
    }



}
