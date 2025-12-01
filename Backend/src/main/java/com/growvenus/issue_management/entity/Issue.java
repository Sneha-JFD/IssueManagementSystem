package com.growvenus.issue_management.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Please provide a title for the issue")
	@Size(max = 100, message = "Title cannot exceed 100 characters")
	private String title;
	
	@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Priority cannot be empty. Choose LOW, MEDIUM, or HIGH")
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is mandatory. Choose OPEN, IN_PROGRESS, RESOLVED, or CLOSED")
	private Status status;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	@Size(max = 100, message = "Assignee name cannot exceed 100 characters")
	private String assignee;
	
	public enum Priority{
		LOW,MEDIUM,HIGH
	}
	
	public enum Status{
		OPEN,IN_PROGRESS,RESOLVED,CLOSED
	}
}
