import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  template: `
    <nav *ngIf="authService.isLoggedIn$ | async" class="navbar">
      <div class="logo">🚀 Menternity</div>
      <ul class="nav-links">
        <li><a routerLink="/home" routerLinkActive="active-link" [routerLinkActiveOptions]="{exact:true}">Home</a></li>
        <li><a routerLink="/resume" routerLinkActive="active-link">Resume</a></li>
        <li><a routerLink="/mock-interview" routerLinkActive="active-link">Mock</a></li>
         <li><a routerLink="/learn-coding" routerLinkActive="active-link">Learn Coding</a></li>  <!-- NEW LINK -->
        <li><a routerLink="/about" routerLinkActive="active-link">About Us</a></li>
      </ul>
    </nav>
  `,
  styles: [`
    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #1e3a8a;
      color: white;
      padding: 16px 32px;
    }
    .logo {
      font-size: 24px;
      font-weight: bold;
      cursor: pointer;
    }
    .nav-links {
      display: flex;
      list-style: none;
      gap: 24px;
      margin: 0;
      padding: 0;
    }
    .nav-links li a {
      text-decoration: none;
      color: white;
      font-weight: 500;
      font-size: 16px;
      transition: color 0.2s ease;
    }
    .nav-links li a:hover,
    .nav-links li a.active-link {
      color: #facc15;
    }
  `]
})
export class NavbarComponent {
  authService = inject(AuthService);
}
