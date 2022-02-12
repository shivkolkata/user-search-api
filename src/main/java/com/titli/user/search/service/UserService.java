package com.titli.user.search.service;

import java.util.List;

import com.titli.user.search.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titli.user.search.entity.User;
import com.titli.user.search.repo.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepo userRepo;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllUser() {
		return (List<User>)userRepo.findAll();
	}

	@Override
	public List<User> searchUser(final List<SearchCriteria> params) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<User> query = builder.createQuery(User.class);
		final Root r = query.from(User.class);

		Predicate predicate = builder.conjunction();
		UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
		params.stream().forEach(searchConsumer);
		predicate = searchConsumer.getPredicate();
		query.where(predicate);

		return entityManager.createQuery(query).getResultList();
	}

}
