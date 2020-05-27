package br.com.aurum.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.aurum.base.exception.BusinessException;
import br.com.aurum.base.mapper.DefaultKeyMapper;
import br.com.aurum.cases.model.Case;
import br.com.aurum.cases.repository.CaseRepository;
import br.com.aurum.cases.service.CaseService;

@Import(ValidationAutoConfiguration.class)
public class CaseServiceTest {
	
	@Mock
	private CaseRepository caseRepository;
	
	@Mock
	private DefaultKeyMapper defaultKeyMapper;
	
	@InjectMocks
	private CaseService caseService;
	
	private String projectId = "cases-278101";
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test(expected = BusinessException.class)
	public void saveTest() throws BusinessException {
		
		caseService.save(new Case());
	}
	
	@Test
	public void findAllTest() throws BusinessException {
		List<Case> cases = new ArrayList<Case>();
		cases.add(new Case());
		when(caseRepository.findAll()).thenReturn(cases);
		
		assertEquals(caseService.findAll().size(), cases.size());
	}
	
	@Test
	public void findAllPaginationTest() throws BusinessException {
		List<Case> cases = new ArrayList<Case>();
		cases.add(new Case());
		Pageable pageable = new PageRequest(0, 10);
		Page<Case> page = new PageImpl<Case>(cases);
		when(caseRepository.findAll(pageable)).thenReturn(page);
		
		assertEquals(caseService.findAllPagination(pageable).getContent(), cases);
	}
	

}
