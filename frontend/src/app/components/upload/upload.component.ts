import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UploadService, UploadedFile } from '../../services/upload.service';
import { AuthService } from '../../services/auth.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-upload',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
})
export class UploadComponent implements OnInit {
  private uploadService = inject(UploadService);
  private auth = inject(AuthService);

  user = this.auth.currentUser();
  uploadedFiles: UploadedFile[] = [];
  loading = false;

  ngOnInit() {
    this.loadFiles();
  }

  loadFiles() {
    if (!this.user) return;
    this.loading = true;

    this.uploadService.getUserFiles(this.user.id).subscribe({
      next: (files) => {
        this.uploadedFiles = files;
        this.loading = false;
      },
      error: (err) => {
        console.error('Greska pri ucitavanju fajlova:', err);
        this.loading = false;
      },
    });
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0 || !this.user) return;

    Array.from(input.files).forEach((file) => {
      this.uploadService.upload(this.user!.id, file).subscribe({
        next: (uploaded) => {
          this.uploadedFiles.push(uploaded);
        },
        error: () => {
          alert('Greška pri uploadu fajla.');
        },
      });
    });

    input.value = '';
  }

  deleteFile(fileId: string, index: number) {
    this.uploadService.deleteFile(fileId).subscribe({
      next: () => {
        this.uploadedFiles.splice(index, 1);
      },
      error: () => {
        alert('Greška pri brisanju fajla.');
      },
    });
  }

  getFileUrl(file: UploadedFile): string {
    return `${environment.apiUrl}/${file.filePath}`;
  }
}
