package com.gcl.security.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcl.dental.core.model.security.Role;
import com.gcl.dental.core.repository.security.RoleRepository;



@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role edit(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public Page<Role> paginate(Map<String, String> filters) {

		ObjectMapper mapper = new ObjectMapper();
		Role pojo = mapper.convertValue(filters, Role.class);

		Integer page = filters.get("page") != null ? Integer.parseInt(filters.get("page")) : 0;

		Integer size = filters.get("size") != null ? Integer.parseInt(filters.get("size")) : 10;

		Integer sortOrder = filters.get("sortOrder") != null ? Integer.parseInt(filters.get("sortOrder")) : 1;

		String sortField = filters.get("sortField") != null ? filters.get("sortField") : "id";

		Sort sort = sortOrder == 1 ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(page, size, sort);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnoreNullValues()
				.withStringMatcher(StringMatcher.REGEX).withIgnoreCase(true)
				.withMatcher("nombre", GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.REGEX).ignoreCase());
		
		Example<Role> example = Example.of(pojo, matcher);

		return roleRepository.findAll(example, pageable);

	}
}
