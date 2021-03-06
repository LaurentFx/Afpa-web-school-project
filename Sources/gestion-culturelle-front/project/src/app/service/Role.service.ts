import { Injectable } from '@angular/core';
import { RoleDto } from '../model/roleDto';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  
  monUrl= 'http://localhost:8080/role'; 

  role: RoleDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(`${this.monUrl}/list`);
  }
  
  add(role: RoleDto): Observable<any> {
    return this.http.post(`${this.monUrl}/add`,role);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/show/${id}`);
  } 

  update(id: number, role: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/update/${id}`, role);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/delete/${id}`);
  }
}