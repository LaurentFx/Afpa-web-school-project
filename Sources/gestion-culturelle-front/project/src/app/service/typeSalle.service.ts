import { Injectable } from '@angular/core';
import { TypeSalleModel } from '../model/TypeSalle';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypeSalleService {

  monUrl= 'http://localhost:8080/typesalle'; 

  typeSalles: TypeSalleModel[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }
  
  add(typeSalle: string): Observable<any> {
    return this.http.post(this.monUrl,new TypeSalleModel(0,typeSalle));
  }

  update(id:number,typeSalle:string): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, typeSalle);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }
  
}
