package com.growvenus.issue_management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growvenus.issue_management.entity.Issue;
import com.growvenus.issue_management.response.ApiResponse;
import com.growvenus.issue_management.service.IssueService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

	private final IssueService issueService;
	
	//create issue
	@PostMapping
	  public ResponseEntity<ApiResponse> createIssue(@Valid @RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return new ResponseEntity<>(new ApiResponse("Issue created successfully", createdIssue),HttpStatus.CREATED);
	}
	
	//get all issues
	 @GetMapping
	public ResponseEntity<ApiResponse> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return ResponseEntity.ok(new ApiResponse("All issues fetched", issues));
    }
	
	//get issue by id
	@GetMapping("/{id}")
	 public ResponseEntity<ApiResponse> getIssueById(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        return ResponseEntity.ok(new ApiResponse("Issue fetched successfully", issue));
	}
	
	//Update Issue by id
	@PutMapping("/{id}")
	 public ResponseEntity<ApiResponse> updateIssue(@PathVariable Long id,@Valid @RequestBody Issue updatedIssue) {
		Issue issue = issueService.updateIssue(id, updatedIssue);
		return ResponseEntity.ok(new ApiResponse("Issue updated successfully", issue));
}
	
	//Update Issue Status
	@PutMapping("/{id}/status")
	public ResponseEntity<ApiResponse> updateIssueStatus(@PathVariable Long id,@Valid @RequestBody Issue updatedIssue) {
		Issue issue = issueService.updateIssueStatus(id, updatedIssue.getStatus());
		return ResponseEntity.ok(new ApiResponse("Issue status updated successfully", issue));
	}
	
	//Update Full Issue
	@PutMapping("/{id}/full")
	public ResponseEntity<ApiResponse> updateFullIssue(@PathVariable Long id,@Valid @RequestBody Issue updatedIssue) {
		Issue issue = issueService.updateFullIssue(id, updatedIssue);
		return ResponseEntity.ok(new ApiResponse("Issue fully updated", issue));
	}
	
	//Delete Issue
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok(new ApiResponse("Issue deleted successfully", null));
    }
	
	//Filter by Priority or Status
	@GetMapping("/filter")
	public ResponseEntity<ApiResponse> filterIssues(
            @RequestParam(required = false) Issue.Priority priority,
            @RequestParam(required = false) Issue.Status status) {

        List<Issue> issues = issueService.filterIssues(priority, status);
        return ResponseEntity.ok(new ApiResponse("Filtered issues", issues));
    }
	
	// Search Issues by title or assignee
	@GetMapping("/search")
	 public ResponseEntity<ApiResponse> searchIssues(
	            @RequestParam(required = false) String title,
	            @RequestParam(required = false) String assignee) {

	        List<Issue> issues = issueService.searchIssues(title, assignee);
	        return ResponseEntity.ok(new ApiResponse("Search results", issues));
	    }
	
	//Count Issues by Status or Priority
	@GetMapping("/count")
	public ResponseEntity<ApiResponse> countIssuesByStatus() {
        Map<String, Long> countMap = issueService.countIssuesByStatus();
        return ResponseEntity.ok(new ApiResponse("Issue counts", countMap));
    }
}
