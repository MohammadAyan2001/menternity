import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-article-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Write New Article</h2>
    <form (ngSubmit)="submit()">
      <input [(ngModel)]="title" name="title" placeholder="Title" required />
      <input [(ngModel)]="author" name="author" placeholder="Author" required />
      <textarea [(ngModel)]="content" name="content" placeholder="Content" rows="8" required></textarea>
      <button type="submit">Publish</button>
    </form>
  `,
  styles: [`
    input, textarea {
      display: block;
      margin-bottom: 12px;
      width: 100%;
      padding: 8px;
    }
  `]
})
export class ArticleFormComponent {
  title = '';
  author = '';
  content = '';

  submit() {
    console.log('Submitted article:', { title: this.title, author: this.author, content: this.content });
    alert('Article published!');
  }
}
