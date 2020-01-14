import { Injectable } from '@angular/core';
import { ManifestationDto } from '../model/manifestationDto';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManifestationService {
 
  monUrl= 'http://localhost:8080/manifestation'; 

  manifestation: ManifestationDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(manifestation: ManifestationDto): Observable<object> {
    return this.http.post(this.monUrl,manifestation);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  update(id: number, manifestation: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, manifestation);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}
