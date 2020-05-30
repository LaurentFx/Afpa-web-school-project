import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user';
import { ReservationDto } from '../model/reservationDto';


@Injectable({
  providedIn: 'root'
})
export class ReservationService {
 
  monUrl= 'http://localhost:8080/reservation'; 

  user: User[]; 
  reservationDto : ReservationDto;
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
  
  add(reservationDto: ReservationDto): Observable<object> {
    return this.http.post(this.monUrl,reservationDto);
  }

 /*  updateAdd(id: number,manifestation: ManifestationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/add/${id}`, manifestation);
  }

  updateSub(id: number,manifestation: ManifestationDto): Observable<Object> {
    return this.http.put(`${this.monUrl}/sub/${id}`, manifestation);
  } */

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

  deleteAll(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/manifestation/${id}`);
  }

}