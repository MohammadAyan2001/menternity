import { Component, inject } from '@angular/core';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

import {
  FormBuilder,
  FormGroup,
  FormControl,
  FormArray,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-resume-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule],
  templateUrl: './resume-form.component.html',
  styleUrls: ['./resume-form.component.scss'],
})
export class ResumeFormComponent {
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  submitted = false;
  resumeId: string | null = null;  // Store created resume ID

  resumeForm = this.fb.group({
    fullName: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    city: [''],        // Added city field
    state: [''],       // Added state field
     linkedin: [''],   // <-- LinkedIn link
    
     github: [''],     // <-- GitHub link
    summary: [''],
    skills: this.fb.array([this.fb.control('')]),
    softSkills: this.fb.array([this.fb.control('')]),  // Added softSkills array
    achievements: this.fb.array([this.fb.control('')]),
    education: this.fb.array([this.createEducationGroup()]),
    experience: this.fb.array([this.createExperienceGroup()]),
    projects: this.fb.array([this.createProjectGroup()]),
    template: ['classic'],  // Default template selection
  });

  // Getters for FormArrays and controls
  get skills() {
    return this.resumeForm.get('skills') as FormArray;
  }
  get softSkills() {
    return this.resumeForm.get('softSkills') as FormArray;
  }
  get achievements() {
    return this.resumeForm.get('achievements') as FormArray;
  }
  get education() {
    return this.resumeForm.get('education') as FormArray;
  }
  get experience() {
    return this.resumeForm.get('experience') as FormArray;
  }
  get projects() {
    return this.resumeForm.get('projects') as FormArray;
  }

  responsibilities(index: number) {
    return (this.experience.at(index) as FormGroup).get('responsibilities') as FormArray;
  }

  technologies(index: number) {
    return (this.projects.at(index) as FormGroup).get('technologies') as FormArray;
  }

  // Create group methods for nested FormGroups
  createEducationGroup(): FormGroup {
    return this.fb.group({
      degree: [''],
      fieldOfStudy: [''],
      institution: [''],
      startDate: [''],
      endDate: [''],
      grade: [''],
    });
  }

  createExperienceGroup(): FormGroup {
    return this.fb.group({
      jobTitle: [''],
      companyName: [''],
      location: [''],
      startDate: [''],
      endDate: [''],
      responsibilities: this.fb.array([this.fb.control('')]),
    });
  }

  createProjectGroup(): FormGroup {
    return this.fb.group({
      title: [''],
      description: [''],
      technologies: this.fb.array([this.fb.control('')]),
      link: [''],
    });
  }

  // Add/remove methods for skills
  addSkill() {
    this.skills.push(this.fb.control(''));
  }
  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  // Add/remove methods for softSkills
  addSoftSkill() {
    this.softSkills.push(this.fb.control(''));
  }
  removeSoftSkill(index: number) {
    this.softSkills.removeAt(index);
  }

  // Add/remove methods for achievements
  addAchievement() {
    this.achievements.push(this.fb.control(''));
  }
  removeAchievement(index: number) {
    this.achievements.removeAt(index);
  }

  // Add/remove methods for education
  addEducation() {
    this.education.push(this.createEducationGroup());
  }
  removeEducation(index: number) {
    this.education.removeAt(index);
  }

  // Add/remove methods for experience
  addExperience() {
    this.experience.push(this.createExperienceGroup());
  }
  removeExperience(index: number) {
    this.experience.removeAt(index);
  }

  // Responsibilities inside experience
  addResponsibility(expIndex: number) {
    this.responsibilities(expIndex).push(this.fb.control(''));
  }
  removeResponsibility(expIndex: number, resIndex: number) {
    this.responsibilities(expIndex).removeAt(resIndex);
  }

  // Add/remove methods for projects
  addProject() {
    this.projects.push(this.createProjectGroup());
  }
  removeProject(index: number) {
    this.projects.removeAt(index);
  }

  // Technologies inside projects
  addTechnology(projIndex: number) {
    this.technologies(projIndex).push(this.fb.control(''));
  }
  removeTechnology(projIndex: number, techIndex: number) {
    this.technologies(projIndex).removeAt(techIndex);
  }

  onSubmit() {
    this.submitted = true;
    if (this.resumeForm.invalid) return;

    this.http.post<any>('http://localhost:8080/api/resume', this.resumeForm.value).subscribe({
      next: (response) => {
        alert('Resume Created!');
        this.resumeId = response.id; // Ensure backend returns { id: "uuid" }
      },
      error: (err) => alert('Error: ' + err.message),
    });
  }

  downloadPdf() {
    if (!this.resumeId) return;
    window.open(`http://localhost:8080/api/resume/${this.resumeId}/pdf`, '_blank');
  }
downloadAsSinglePagePdf() {
  const resumeElement = document.getElementById('resume-content');
  if (!resumeElement) return;

  html2canvas(resumeElement, { scale: 2, scrollY: -window.scrollY }).then(canvas => {
    const imgData = canvas.toDataURL('image/png');

    const pdf = new jsPDF({
      orientation: 'portrait',
      unit: 'px',
      format: [794, 1123], // A4 dimensions in px
    });

    pdf.addImage(imgData, 'PNG', 0, 0, 794, 1123);
    pdf.save('resume.pdf');
  });
}


}
