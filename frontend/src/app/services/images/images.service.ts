import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {

  constructor(private httpClient: HttpClient) { }

  getInverted(file: any) {
    const headers = new HttpHeaders();
    //headers.responseType = "arraybuffer";
    return this.httpClient.post<Blob>("http://localhost:8080/api/v1/images/inverted", file, {responseType: 'blob' as 'json'});
  }
}
