import { Injectable } from '@angular/core';
import { SalleModel } from '../model/Salle';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalleService {

  monUrl= 'http://localhost:8080/salle'; 

  salles: SalleModel[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(salle: string): Observable<any> {
    return this.http.post(this.monUrl,new SalleModel(0,this.salle));
  }

  suppSalle(id: number): Observable<any> {
    return this.http.delete(this.monUrl +'/' + id);
  }

}
