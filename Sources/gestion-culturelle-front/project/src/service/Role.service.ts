import { Injectable } from '@angular/core';
import { RoleModel } from '../model/Role';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  monUrl= 'http://localhost:8080/role'; 

  roles: RoleModel[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(role: string): Observable<any> {
    return this.http.post(this.monUrl,new RoleModel(0,role));
  }

  suppRole(id: number): Observable<any> {
    return this.http.delete(this.monUrl +'/' + id);
  }

}
