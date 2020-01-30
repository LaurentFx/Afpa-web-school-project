import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user';
import { ManifestationDto } from '../model/manifestationDto';


@Injectable({
  providedIn: 'root'
})
export class InvitationService {
 
  monUrl= 'http://localhost:8080/invitation'; 

  user: User[]; 
  manifestation : ManifestationDto [];
  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(user: User): Observable<object> {
    return this.http.post(this.monUrl,user);
  }
  
  getVips(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  getListVips(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/list/${id}`);
  } 

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 
  
  update(id: number,manifestation: ManifestationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, manifestation);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}