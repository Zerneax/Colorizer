import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {

  constructor(private httpClient: HttpClient) { }

  getInverted(file: any) {
    return this.httpClient.post<Blob>("http://localhost:8080/api/v1/images/inverted", file, {responseType: 'blob' as 'json'});
  }

  getBlackAndWhite(file: any) {
    return this.httpClient.post<Blob>("http://localhost:8080/api/v1/images/black_and_white", file, {responseType: 'blob' as 'json'});
  }

  getSepia(file: any) {
    return this.httpClient.post<Blob>("http://localhost:8080/api/v1/images/sepia", file, {responseType: 'blob' as 'json'});
  }

  getShake(file: any) {
    return this.httpClient.post<Blob>("http://localhost:8080/api/v1/images/shake", file, {responseType: 'blob' as 'json'});
  }
}
