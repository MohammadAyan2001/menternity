// src/app/app.config.ts
import { Routes, provideRouter } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ResumeFormComponent } from './components/resume-form/resume-form.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'resume-form', component: ResumeFormComponent },
];

export const appConfig = {
  providers: [provideRouter(routes)],
};
