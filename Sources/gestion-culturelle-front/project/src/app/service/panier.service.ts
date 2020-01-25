import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { CommandeDto } from '../model/commandeDto';


@Injectable({
  providedIn: 'root'
})
export class PanierService {
 
  monUrl= 'http://localhost:8080/panier'; 

  commande: CommandeDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(commande: CommandeDto): Observable<object> {
    return this.http.post(this.monUrl,commande);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  } 

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 
  
  update(id: number, commande: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, commande);
  }

 delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}