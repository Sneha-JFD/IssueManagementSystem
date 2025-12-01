import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  private baseUrl = 'http://localhost:8080/issues';

  constructor(private http: HttpClient) {}

  // Create Issue
  createIssue(issue: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, issue);
  }

  // Get All Issues
  getAllIssues(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  // Get Issue by ID
  getIssueById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // Update Issue
  updateIssue(id: number, issue: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, issue);
  }

  // Update Issue Status
  updateStatus(id: number, status: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}/status`, status);
  }

  // Update Full Issue
  updateFullIssue(id: number, issue: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}/full`, issue);
  }

  // Delete Issue
  deleteIssue(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  // Filter Issues
  filterIssues(priority?: string, status?: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/filter`, {
      params: { priority: priority || '', status: status || '' }
    });
  }

  // Search Issues
  searchIssues(title?: string, assignee?: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search`, {
      params: { title: title || '', assignee: assignee || '' }
    });
  }

  // Count Issues
  countIssues(): Observable<any> {
    return this.http.get(`${this.baseUrl}/count`);
  }
}
