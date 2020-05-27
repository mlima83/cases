package br.com.aurum.cases.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aurum.base.controller.DefaultController;
import br.com.aurum.base.mapper.DefaultEntityMapper;
import br.com.aurum.base.service.DefaultService;
import br.com.aurum.cases.dto.CaseDtoV1;
import br.com.aurum.cases.mapper.CaseMapper;
import br.com.aurum.cases.model.Access;
import br.com.aurum.cases.model.Case;
import br.com.aurum.cases.service.CaseService;

@RestController
@RequestMapping("cases")
@Validated
public class CaseController extends DefaultController<Case, CaseDtoV1> {
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private CaseMapper caseMapper;
	
    @GetMapping(path = "/search")
    public Page<CaseDtoV1> findByFilterPagination(
    		@RequestParam(required = false) String folder,
    		@RequestParam(required = false) String title,
    		@RequestParam(required = false) String description,
    		@RequestParam(required = false) String clients,
    		@RequestParam(required = false) String tag,
    		@RequestParam(required = false) Access access,
    		@RequestParam(required = false) Date dtCreated,
            @PageableDefault(page = 0, size = 10) Pageable pageable){
    	
		CaseDtoV1 example = new CaseDtoV1(folder, title, description, clients, tag, access, dtCreated);
		return caseMapper.convertPageEntityToPageDto(this.caseService.findByExample(example, pageable));
    }

	@Override
	public DefaultService<Case> getService() {
		return this.caseService;
	}

	@Override
	public DefaultEntityMapper<Case, CaseDtoV1> getMapper() {
		return this.caseMapper;
	}

}
