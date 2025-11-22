import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

interface Slot {
  date: string;       // e.g. "2025-05-31"
  startTime: string;  // e.g. "14:00:00"
}

@Component({
  selector: 'app-mock-interview-booking',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './mock-interview-booking.component.html',
  styleUrls: ['./mock-interview-booking.component.scss']
})
export class MockInterviewBookingComponent {
  formData = {
    email: '',
    stack: '',      // JAVA or MERN
    level: '',      // LEVEL_1 or LEVEL_2
    date: '',       // yyyy-MM-dd
    time: '',       // HH:mm (from input type="time")
    slot: ''        // full slot string "yyyy-MM-dd HH:mm"
  };

  availableSlots: string[] = [];

  constructor(private http: HttpClient) {}

  onStackChange() {
    if (!this.formData.stack) {
      this.availableSlots = [];
      this.formData.slot = '';
      this.formData.date = '';
      this.formData.time = '';
      return;
    }

    const apiUrl = 'http://localhost:8081/api/availableSlots';

    this.http.post<Slot[]>(apiUrl, { role: this.formData.stack }).subscribe({
      next: (slots) => {
        // Map Slot objects to "yyyy-MM-dd HH:mm" string for dropdown
        this.availableSlots = slots.map(slot => {
          // Convert "HH:mm:ss" to "HH:mm" for user-friendly display
          const time = slot.startTime.substring(0, 5);
          return `${slot.date} ${time}`;
        });
        // Clear selection on role change
        this.formData.slot = '';
        this.formData.date = '';
        this.formData.time = '';
      },
      error: (err) => {
        console.error('Error fetching slots:', err);
        this.availableSlots = [];
      }
    });
  }

  onSlotChange() {
    if (!this.formData.slot) {
      this.formData.date = '';
      this.formData.time = '';
      return;
    }

    const [date, time] = this.formData.slot.split(' ');
    this.formData.date = date;
    this.formData.time = time;
  }

  bookInterview() {
    const { email, stack, level, date, time } = this.formData;

    if (!email || !stack || !level || !date || !time) {
      alert('Please fill all the fields');
      return;
    }

    // Convert time from HH:mm to HH:mm:ss for backend
    const formattedTime = time.length === 5 ? time + ':00' : time;

    const payload = {
      studentEmail: email,
      developerRole: stack,
      interviewLevel: level,
      date: date,
      preferredStartTime: formattedTime
    };

    const apiUrl = 'http://localhost:8081/api/bookings';

    this.http.post(apiUrl, payload).subscribe({
      next: () => {
        alert('Interview booked successfully!');
        this.formData = { email: '', stack: '', level: '', date: '', time: '', slot: '' };
        this.availableSlots = [];
      },
      error: (error) => {
        alert('Failed to book interview.');
        console.error(error);
      }
    });
  }
}
