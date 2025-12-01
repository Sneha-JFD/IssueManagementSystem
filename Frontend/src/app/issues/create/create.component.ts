import { Component } from '@angular/core';
import { IssueService } from '../issue.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent {

  issue: any = {
    title: '',
    description: '',
    priority: '',
    status: '',
    assignee: ''
  };

  assignees: string[] = ['Sneha', 'Test1', 'Test2', 'Amit', 'Priya'];

  showToast: boolean = false;

  constructor(private issueService: IssueService, public router: Router) {}

  createIssue() {
    this.issueService.createIssue(this.issue).subscribe(() => {

      this.showToast = true;

      setTimeout(() => {
        this.showToast = false;
        this.router.navigate(['/issues']);
      }, 1500);

    });
  }
}
