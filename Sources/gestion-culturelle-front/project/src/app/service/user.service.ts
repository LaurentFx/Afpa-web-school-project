import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  monUrl= 'http://localhost:8080/users'; 

  user: User [];
  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  getByRole(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/role/${id}`);
  } 

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  add(user: User): Observable<any> {
    return this.http.post(this.monUrl,user);
  }
  update(id: number, user: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, user);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}
