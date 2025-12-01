import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IssueService } from '../issue.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  issue: any = {
    id: '',
    title: '',
    description: '',
    priority: '',
    status: '',
    assignee: ''
  };

  assignees: string[] = ['Sneha', 'Test1', 'Test2', 'User A', 'User B'];

  constructor(
    private route: ActivatedRoute,
    private issueService: IssueService,
    public router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];

    // ⭐ FIXED: now data comes correctly from API
    this.issueService.getIssueById(id).subscribe(res => {
      this.issue = res.data;
    });
  }

  updateIssue() {
    this.issueService.updateIssue(this.issue.id, this.issue).subscribe(() => {
      alert('Issue updated!');
      this.router.navigate(['/issues']);
    });
  }
}
