package com.growvenus.issue_management.service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growvenus.issue_management.dao.IssueDao;
import com.growvenus.issue_management.entity.Issue;
import com.growvenus.issue_management.entity.Issue.Priority;
import com.growvenus.issue_management.entity.Issue.Status;
import com.growvenus.issue_management.exception.IssueNotFoundException;

@Service
public class IssueService {

	@Autowired
	private IssueDao issueDao;
	
	@Transactional
	public Issue createIssue(Issue issue) {
		
	    if (issue.getStatus() == null || issue.getStatus().toString().isEmpty()) {
	        issue.setStatus(Status.OPEN);
	    }

	    issue.setCreatedDate(LocalDateTime.now());
	    issue.setUpdatedDate(LocalDateTime.now());

	    return issueDao.save(issue);
	}

	public List<Issue> getAllIssues() {
		return issueDao.getAllIssues();
	}

	public Issue getIssueById(Long id) {
		 Issue issue = issueDao.getIssueById(id);
	        if (issue == null) {
	            throw new IssueNotFoundException("Issue not found with id: " + id);
	        }
	        return issue;
	}

	@Transactional
	public Issue updateIssue(Long id, Issue updatedIssue) {
	    Issue existingIssue = issueDao.getIssueById(id);
	    if (existingIssue == null) {
	    	throw new IssueNotFoundException("Issue not found with id: " + id);
	    }

	    // Update fields
	    if (updatedIssue.getTitle() != null) existingIssue.setTitle(updatedIssue.getTitle());
	    if (updatedIssue.getDescription() != null) existingIssue.setDescription(updatedIssue.getDescription());
	    if (updatedIssue.getStatus() != null) existingIssue.setStatus(updatedIssue.getStatus());
	    if (updatedIssue.getPriority() != null) existingIssue.setPriority(updatedIssue.getPriority());
	    existingIssue.setUpdatedDate(LocalDateTime.now()); 

	    return issueDao.updateIssue(existingIssue);
	}

	@Transactional
	public Issue updateIssueStatus(Long id, Status status) {
	    Issue existingIssue = issueDao.getIssueById(id);
	    if (existingIssue == null) {
	    	throw new IssueNotFoundException("Issue not found with id: " + id);
	    }
	    existingIssue.setStatus(status);
	    existingIssue.setUpdatedDate(LocalDateTime.now());
	    return issueDao.updateIssue(existingIssue);
	}

	@Transactional
	public Issue updateFullIssue(Long id, Issue updatedIssue) {
		Issue existingIssue = issueDao.getIssueById(id);
	    if (existingIssue == null) {
	    	throw new IssueNotFoundException("Issue not found with id: " + id);
	    }
	    existingIssue.setTitle(updatedIssue.getTitle());
	    existingIssue.setDescription(updatedIssue.getDescription());
	    existingIssue.setStatus(updatedIssue.getStatus());
	    existingIssue.setPriority(updatedIssue.getPriority());
	    existingIssue.setAssignee(updatedIssue.getAssignee());
	    existingIssue.setUpdatedDate(LocalDateTime.now());
	    return issueDao.updateIssue(existingIssue);
	}
	
	  @Transactional
	    public void deleteIssue(Long id) {
	        Issue existingIssue = issueDao.getIssueById(id);
	        if (existingIssue == null) {
	        	throw new IssueNotFoundException("Issue not found with id: " + id); // issue not found
	        }
	        issueDao.deleteIssue(existingIssue);
	       
	  }
	  
	  public List<Issue> filterIssues(Priority priority, Status status) {
	        return issueDao.filterIssues(priority, status);
	    }
	  
	  public List<Issue> searchIssues(String title, String assignee) {
	        return issueDao.searchIssues(title, assignee);
		}
	  
	

	public Map<String, Long> countIssuesByStatus() {
		 List<Object[]> results = issueDao.countIssuesByStatus();
	        Map<String, Long> countMap = new HashMap<>();
	        for (Object[] row : results) {
	            countMap.put(row[0].toString(), (Long) row[1]);
	        }
	        return countMap;
	    }
	  
	  
}
