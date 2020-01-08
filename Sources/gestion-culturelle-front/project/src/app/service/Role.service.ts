import { Injectable } from '@angular/core';
import { Role } from '../model/role';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  monUrl= 'http://localhost:8080/role'; 

  roles: Role[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(role: string): Observable<any> {
    return this.http.post(this.monUrl,new Role(0,role));
  }

  suppRole(id: number): Observable<any> {
    return this.http.delete(this.monUrl +'/' + id);
  }

}
