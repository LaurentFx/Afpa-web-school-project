import { Injectable } from '@angular/core';
import { ManifestationDto } from '../model/manifestationDto';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManifestationService {
 
  monUrl1= 'http://localhost:8080/public/manifestation'; 
  monUrl2 = 'http://localhost:8080/manifestation'; 

  manifestation: ManifestationDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(`${this.monUrl1}/list`);
  }
  
  add(manifestation: ManifestationDto): Observable<any> {
    return this.http.post(`${this.monUrl2}/add`,manifestation);
  }
  
  getAvalaibility(manifestation: ManifestationDto): Observable<object> {
    return this.http.post(this.monUrl2+"/availability",manifestation);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl2}/show/${id}`);
  } 

  update(id: number, manifestation: Object): Observable<Object> {
    return this.http.put(`${this.monUrl2}/update/${id}`, manifestation);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl2}/delete/${id}`);
  }

}
