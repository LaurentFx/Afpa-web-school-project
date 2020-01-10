import { Injectable } from '@angular/core';
import { TypeDeSalleModel } from '../model/type-de-salle';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypeSalleService {

  monUrl = 'http://localhost:8080/typesalle';

  typeSalles: TypeDeSalleModel[];

  subjectMiseAJour = new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.monUrl);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/${id}`);
  }

  add(typeSalle: string): Observable<any> {
    return this.http.post(this.monUrl, new TypeDeSalleModel(0, typeSalle));
  }

  update(id: number, label: any): Observable<Object> {
    return this.http.put(`${this.monUrl}/${id}`, label);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }

}
