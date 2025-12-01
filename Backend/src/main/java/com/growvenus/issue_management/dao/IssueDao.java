package com.growvenus.issue_management.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.growvenus.issue_management.entity.Issue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class IssueDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	//save new issue
	public Issue save(Issue issue) {
		entityManager.persist(issue);
		return issue;
	}

	public List<Issue> getAllIssues() {
		TypedQuery<Issue> query=entityManager.createQuery("SELECT i FROM Issue i",Issue.class);
		return query.getResultList();
	}

	public Issue getIssueById(Long id) {
		 return entityManager.find(Issue.class, id);
	}

	public Issue updateIssue(Issue existingIssue) {
	     return entityManager.merge(existingIssue); // merge returns managed entity
	}

	 public void deleteIssue(Issue issue) {
	        entityManager.remove(issue);
	 }
	
	 public List<Issue> filterIssues(Issue.Priority priority, Issue.Status status) {

		 	String jpql = "SELECT i FROM Issue i WHERE 1=1";

		 	 if (priority != null) jpql += " AND i.priority = :priority";
		        if (status != null) jpql += " AND i.status = :status";

		        TypedQuery<Issue> query = entityManager.createQuery(jpql, Issue.class);
		        if (priority != null) query.setParameter("priority", priority);
		        if (status != null) query.setParameter("status", status);

		        return query.getResultList();
		}
	
	 public List<Issue> searchIssues(String title, String assignee) {
		 String jpql = "SELECT i FROM Issue i WHERE 1=1";
	        if (title != null && !title.isEmpty()) jpql += " AND i.title LIKE :title";
	        if (assignee != null && !assignee.isEmpty()) jpql += " AND i.assignee LIKE :assignee";

	        TypedQuery<Issue> query = entityManager.createQuery(jpql, Issue.class);
	        if (title != null && !title.isEmpty()) query.setParameter("title", "%" + title + "%");
	        if (assignee != null && !assignee.isEmpty()) query.setParameter("assignee", "%" + assignee + "%");

	        return query.getResultList();
		}

	 public List<Object[]> countIssuesByStatus() {
		 String jpql = "SELECT i.status, COUNT(i) FROM Issue i GROUP BY i.status";
	        return entityManager.createQuery(jpql).getResultList();
		}

}
