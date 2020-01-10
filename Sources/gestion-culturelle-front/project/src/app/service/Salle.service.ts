import { Injectable } from '@angular/core';
import { SalleModel } from '../model/salle';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalleService {
 // delete(id: number) {
    //throw new Error("Method not implemented.");
  //}

  monUrl= 'http://localhost:8080/salle'; 

  salle: SalleModel[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(salle: SalleModel): Observable<object> {
    return this.http.post(this.monUrl,salle);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  update(id: number, label: any): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, label);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}
