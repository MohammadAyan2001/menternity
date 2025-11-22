import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-article-detail',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Article Detail</h2>
    <p>Article ID: {{ articleId }}</p>
    <!-- Ideally, fetch article by ID -->
  `
})
export class ArticleDetailComponent {
  articleId: number;

  constructor(private route: ActivatedRoute) {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));
  }
}
