import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  monUrl= 'http://localhost:8080/public/inscription'; 

  user: User [];
  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  
  add(user: User): Observable<any> {
    return this.http.post(this.monUrl,user);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

}
