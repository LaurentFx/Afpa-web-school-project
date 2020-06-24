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
    return this.http.get(`${this.monUrl}/list`);
  }
  
  getByRole(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/role/${id}`);
  } 

  getInvites(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/invites/${id}`);
  } 

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/show/${id}`);
  } 

  add(user: User): Observable<any> {
    return this.http.post(this.monUrl+"/add",user);
  }
  
  addClient(user: User): Observable<any> {
    return this.http.post(this.monUrl+"/new",user);
  }

  update(id: number, user: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/update/${id}`, user);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/delete/${id}`);
  }

}
