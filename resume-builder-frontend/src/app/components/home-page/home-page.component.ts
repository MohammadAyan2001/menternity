import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RouterModule],
  template: `
    <div class="home-container">
      <div class="main-content">
        <h1>Welcome to <span class="highlight">Career Companion</span></h1>
        <p class="description">
          Helping college graduates build standout resumes and ace their mock interviews. 
          Use our Resume Builder and Interview Scheduler to boost your career confidence.
        </p>

        <div class="actions">
          <button (click)="goToResume()" class="action-button">🎯 Create Resume</button>
          <button (click)="goToMock()" class="action-button">🎤 Book Mock Interview</button>
        </div>
      </div>

      <footer class="footer">
        <p>Created by <strong>Mohammad Ayan</strong> — Software Engineer</p>
      </footer>
    </div>
  `,
  styles: [`
    .home-container {
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      padding: 40px 20px;
      background: linear-gradient(to right, #f9fafb, #e0f2fe);
      font-family: 'Segoe UI', sans-serif;
      text-align: center;
    }

    .main-content {
      max-width: 800px;
      width: 100%;
    }

    .highlight {
      color: #1d4ed8;
    }

    h1 {
      font-size: 2.5rem;
      margin-bottom: 1rem;
      font-weight: 700;
    }

    .description {
      font-size: 1.25rem;
      color: #4b5563;
      margin-bottom: 2.5rem;
    }

    .actions {
      display: flex;
      justify-content: center;
      gap: 20px;
      flex-wrap: wrap;
    }

    .action-button {
      padding: 16px 32px;
      font-size: 1rem;
      font-weight: 600;
      border: none;
      border-radius: 10px;
      background-color: #2563eb;
      color: white;
      cursor: pointer;
      transition: all 0.2s ease;
    }

    .action-button:hover {
      background-color: #1e40af;
      transform: scale(1.05);
    }

    .footer {
      margin-top: 40px;
      font-size: 1rem;
      color: #6b7280;
    }

    @media (max-width: 600px) {
      h1 {
        font-size: 1.8rem;
      }

      .description {
        font-size: 1rem;
      }

      .action-button {
        width: 100%;
        font-size: 1rem;
      }

      .actions {
        flex-direction: column;
        gap: 16px;
      }
    }
  `]
})
export class HomePageComponent {
  private router = inject(Router);

  goToResume() {
    this.router.navigate(['/resume']);
  }

  goToMock() {
    this.router.navigate(['/mock-interview']);
  }
}
