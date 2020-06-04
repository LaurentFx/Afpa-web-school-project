import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user';
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
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 
  
  getByManifestation(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/manifestation/${id}`);
  } 

  getByUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 
  
  getNewByUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/new/${id}`);
  } 

  add(invitationDto: InvitationDto): Observable<any> {
    return this.http.post(this.monUrl,invitationDto);
  }

 update(id: number,invitationDto: InvitationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, invitationDto);
  }  

  updateReponse(id: number,reponse: String): Observable<Object> {
    return this.http.put(`${this.monUrl}/reponse/${id}`, reponse);
  }  

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

  deleteAll(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/manifestation/${id}`);
  }

}