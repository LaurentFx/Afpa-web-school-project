import { Injectable } from '@angular/core';
import { SalleDto } from '../model/salleDto';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalleService {
 
  monUrl= 'http://localhost:8080/salle'; 

  salle: SalleDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(salle: SalleDto): Observable<object> {
    return this.http.post(this.monUrl,salle);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  update(id: number, salle: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, salle);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}
