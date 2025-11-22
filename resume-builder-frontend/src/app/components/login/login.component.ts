import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="login-wrapper" [ngStyle]="{'background-image': 'url(assets/login.png)'}">
      <div class="form-container">
        <h2>Login</h2>
        <form (ngSubmit)="login()" #loginForm="ngForm">
          <div class="form-group">
            <input
              type="email"
              placeholder="Email"
              [(ngModel)]="email"
              name="email"
              required
              #emailField="ngModel"
            />
            <div *ngIf="emailField.invalid && emailField.touched" class="error">
              Email is required
            </div>
          </div>

          <div class="form-group">
            <input
              type="password"
              placeholder="Password"
              [(ngModel)]="password"
              name="password"
              required
              #passwordField="ngModel"
            />
            <div *ngIf="passwordField.invalid && passwordField.touched" class="error">
              Password is required
            </div>
          </div>

          <button type="submit" [disabled]="!loginForm.valid">Login</button>
        </form>
      </div>
    </div>
  `,
  styles: [`
    html, body {
      height: 100%;
      margin: 0;
      font-family: Arial, sans-serif;
    }

    .login-wrapper {
      display: flex;
      height: 100vh;
      width: 100vw;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      justify-content: center;
      align-items: center;
      /* Optional debug border */
      /* border: 3px solid red; */
    }

    .form-container {
      background-color: rgba(255, 255, 255, 0.9);
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
      max-width: 400px;
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    h2 {
      margin-bottom: 30px;
      color: #1e3a8a;
      font-weight: 700;
      font-size: 32px;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
      width: 100%;
      max-width: 320px;
    }

    input {
      padding: 14px 12px;
      width: 100%;
      border-radius: 6px;
      border: 1px solid #ccc;
      font-size: 16px;
      box-sizing: border-box;
      transition: border-color 0.3s ease;
    }

    input:focus {
      border-color: #1e3a8a;
      outline: none;
    }

    button {
      padding: 14px 40px;
      border: none;
      border-radius: 6px;
      background-color: #1e3a8a;
      color: white;
      cursor: pointer;
      font-size: 18px;
      font-weight: 600;
      transition: background-color 0.3s ease;
      width: 100%;
      max-width: 320px;
    }

    button:disabled {
      background-color: #94a3b8;
      cursor: not-allowed;
    }

    button:hover:not(:disabled) {
      background-color: #1452a3;
    }

    .error {
      color: #d32f2f;
      font-size: 14px;
      margin-top: 6px;
      text-align: left;
    }
  `]
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';
  private router = inject(Router);
  private authService = inject(AuthService);

  ngOnInit() {
    console.log('LoginComponent initialized.');
  }

  login() {
    if (this.email === 'admin@example.com' && this.password === 'admin') {
      this.authService.login();
      this.router.navigate(['/home']);
    } else {
      alert('Invalid credentials');
    }
  }
}
