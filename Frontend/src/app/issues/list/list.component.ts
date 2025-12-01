import { Component, OnInit } from '@angular/core';
import { IssueService } from '../issue.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  issues: any[] = [];
  issueCounts: any = {};

  // Pagination
  currentPage: number = 1;
  pageSize: number = 5;
  totalPages: number = 1;

  // Filters & Search Variables
  priorityFilter: string = '';
  statusFilter: string = '';
  titleSearch: string = '';
  assigneeSearch: string = '';
  sortOption: string = '';

  constructor(private issueService: IssueService) {}

  ngOnInit(): void {
    this.loadIssues();
    this.loadCounts();
  }

  loadIssues() {
    this.issueService.getAllIssues().subscribe((res: any) => {
      this.issues = res.data;

      // Update pagination
      this.totalPages = Math.ceil(this.issues.length / this.pageSize);
    });
  }

  applyFilters() {
    this.issueService
      .filterIssues(this.priorityFilter, this.statusFilter)
      .subscribe((res: any) => {
        this.issues = res.data;

         this.totalPages = Math.ceil(this.issues.length / this.pageSize);
         this.currentPage = 1;
      });
  }

  searchByTitle() {
    this.issueService.searchIssues(this.titleSearch, '').subscribe((res: any) => {
      this.issues = res.data;

       this.totalPages = Math.ceil(this.issues.length / this.pageSize);
    this.currentPage = 1;
    });
  }

  searchByAssignee() {
    this.issueService.searchIssues('', this.assigneeSearch).subscribe((res: any) => {
      this.issues = res.data;

       this.totalPages = Math.ceil(this.issues.length / this.pageSize);
    this.currentPage = 1;
    });
  }

  loadCounts() {
    this.issueService.countIssues().subscribe((res: any) => {
      this.issueCounts = res.data;
    });
  }

  applySorting() {
    console.log('applySorting() called');

    if (!this.sortOption) return;

    switch (this.sortOption) {
      case 'titleAsc':
        this.issues.sort((a, b) => a.title.localeCompare(b.title));
        break;

      case 'titleDesc':
        this.issues.sort((a, b) => b.title.localeCompare(a.title));
        break;

      case 'priorityHigh':
        const high: { [key: string]: number } = {
          HIGH: 1,
          MEDIUM: 2,
          LOW: 3
        };
        this.issues.sort((a, b) => high[a.priority] - high[b.priority]);
        break;

      case 'priorityLow':
        const low: { [key: string]: number } = {
          LOW: 1,
          MEDIUM: 2,
          HIGH: 3
        };
        this.issues.sort((a, b) => low[a.priority] - low[b.priority]);
        break;

      case 'dateNew':
        this.issues.sort(
          (a, b) =>
            new Date(b.createdDate).getTime() -
            new Date(a.createdDate).getTime()
        );
        break;

      case 'dateOld':
        this.issues.sort(
          (a, b) =>
            new Date(a.createdDate).getTime() -
            new Date(b.createdDate).getTime()
        );
        break;
    }
  }

  resetFilters() {
    this.priorityFilter = '';
    this.statusFilter = '';
    this.titleSearch = '';
    this.assigneeSearch = '';

    this.loadIssues();
  }

  deleteIssue(id: number) {
    this.issueService.deleteIssue(id).subscribe(() => {
      alert('Issue deleted');
      this.loadIssues();
      this.loadCounts();
    });
  }

  prevPage() {
  if (this.currentPage > 1) {
    this.currentPage--;
  }
}

nextPage() {
  if (this.currentPage < this.totalPages) {
    this.currentPage++;
  }
}

}
