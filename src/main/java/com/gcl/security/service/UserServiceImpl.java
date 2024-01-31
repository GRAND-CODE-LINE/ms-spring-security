package com.gcl.security.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;

import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcl.dental.core.model.security.User;
import com.gcl.dental.core.repository.security.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) throws Exception {
		System.out.println("Hola, save!");
		List<User> userSearch = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

		if (!userSearch.isEmpty()) {
			throw new Exception("Usuario o email ya registrado.");
		}
		return userRepository.save(user);
	}

	@Override
	public User edit(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {

		userRepository.delete(user);

	}

	@Override
	public Optional<User> getById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Page<User> paginate(Map<String, String> filters) {

		ObjectMapper mapper = new ObjectMapper();
		User pojo = mapper.convertValue(filters, User.class);

		Integer page = filters.get("page") != null ? Integer.parseInt(filters.get("page")) : 0;

		Integer size = filters.get("size") != null ? Integer.parseInt(filters.get("size")) : 10;

		Integer sortOrder = filters.get("sortOrder") != null ? Integer.parseInt(filters.get("sortOrder")) : 1;

		String sortField = filters.get("sortField") != null ? filters.get("sortField") : "id";

		Sort sort = sortOrder == 1 ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(page, size, sort);

		ExampleMatcher matcher = ExampleMatcher.matching()

				.withIgnoreNullValues().withIgnorePaths("roles");

		Example<User> example = Example.of(pojo, matcher);
		Page<User> list = userRepository.findAll(example, pageable);
		return userRepository.findAll(example, pageable);

	}
}
