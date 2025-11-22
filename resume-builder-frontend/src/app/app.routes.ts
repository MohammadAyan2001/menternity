import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ResumeFormComponent } from './components/resume-form/resume-form.component';
import { MockInterviewBookingComponent } from './components/mock-interview-booking/mock-interview-booking.component';
import { LearnCodingComponent } from './components/learn-coding/learn-coding.component';
import { ArticleListComponent } from './components/learn-coding/article-list.component';
import { ArticleFormComponent } from './components/learn-coding/article-form.component';
import { ArticleDetailComponent } from './components/learn-coding/article-detail.component';
import { AboutComponent } from './components/about/about.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },                  // Default route - login
  { path: 'home', component: HomePageComponent },           // Home
  { path: 'resume', component: ResumeFormComponent },       // Resume form
  { path: 'mock-interview', component: MockInterviewBookingComponent }, // Interview
    { path: 'about', component: AboutComponent },

  // ✅ Learn Coding Section with child routes
  {
    path: 'learn-coding',
    component: LearnCodingComponent,
    children: [
      { path: '', component: ArticleListComponent },
      { path: 'write', component: ArticleFormComponent },
      { path: 'article/:id', component: ArticleDetailComponent }
    ]
  },

  // Catch-all route
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
