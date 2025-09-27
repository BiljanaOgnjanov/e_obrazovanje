import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface UploadedFile {
  id: string;
  userId: string;
  fileName: string;
  fileType: string;
  filePath: string;
  createdAt: string;
  updatedAt: string;
}

@Injectable({
  providedIn: 'root',
})
export class UploadService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/documents`;

  upload(userId: string, file: File): Observable<UploadedFile> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('userId', userId);

    return this.http.post<UploadedFile>(`${this.apiUrl}`, formData);
  }

  getUserFiles(userId: string): Observable<UploadedFile[]> {
    return this.http.get<UploadedFile[]>(`${this.apiUrl}/user/${userId}`);
  }

  deleteFile(fileId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${fileId}`);
  }
}
