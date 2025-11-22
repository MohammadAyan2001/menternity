import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { Article } from './article.model';


@Component({
  selector: 'app-article-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <h2>Articles</h2>
    <div *ngFor="let article of articles" class="article-card" (click)="viewArticle(article.id)">
      <h3>{{ article.title }}</h3>
      <p><em>by {{ article.author }}</em></p>
    </div>
  `,
  styles: [`
    .article-card {
      border: 1px solid #ddd;
      padding: 12px;
      margin-bottom: 12px;
      border-radius: 8px;
      cursor: pointer;
      background-color: #f9f9f9;
    }
  `]
})
export class ArticleListComponent {
  constructor(private router: Router) {}

  articles: Article[] = [
    { id: 1, title: 'Intro to Java', content: '...', author: 'Ayan' },
    { id: 2, title: 'Understanding OOP', content: '...', author: 'Ayan Khan' },
    { id: 2, title: 'Understanding SOLID Principle', content: '...', author: 'Ayan Khan' }
  ];

  viewArticle(id: number) {
    this.router.navigate(['/learn-coding', 'article', id]);
  }
}
