import { Injectable } from '@angular/core';
import { TypeSalle } from '../model/TypeSalle';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypeSalleService {

  monUrl= 'http://localhost:8080/typesalle'; 

  typeSalles: TypeSalle[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(typeSalle: string): Observable<any> {
    return this.http.post(this.monUrl,new TypeSalle(0,typeSalle));
  }
}
