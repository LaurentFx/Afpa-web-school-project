import { Injectable } from '@angular/core';
import { PanierDto } from '../model/panierDto';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { ManifestationDto } from '../model/manifestationDto';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
 
  monUrl= 'http://localhost:8080/panier'; 

  panier: PanierDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(panier: PanierDto): Observable<object> {
    return this.http.post(this.monUrl,panier);
  }
  
  addPanier(manifestation: ManifestationDto): Observable<object> {
    return this.http.put(this.monUrl,manifestation);
  }
  

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 

  update(id: number, panier: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, panier);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}

