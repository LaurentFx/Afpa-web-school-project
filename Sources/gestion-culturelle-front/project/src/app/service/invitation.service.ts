import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user';
import { ManifestationDto } from '../model/manifestationDto';
import { InvitationDto } from '../model/invitationDto';


@Injectable({
  providedIn: 'root'
})
export class InvitationService {
 
  monUrl= 'http://localhost:8080/invitation'; 

  user: User[]; 
  //manifestation : ManifestationDto [];
  invitationDto : InvitationDto;
  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(invitationDto: InvitationDto): Observable<object> {
    return this.http.post(this.monUrl,invitationDto);
  }
  
  getByManifestation(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/manifestation/${id}`);
  } 

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 
  
  updateAdd(id: number,manifestation: ManifestationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/add/${id}`, manifestation);
  }

  updateSub(id: number,manifestation: ManifestationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/sub/${id}`, manifestation);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

  deleteAll(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/manifestation/${id}`);
  }

}