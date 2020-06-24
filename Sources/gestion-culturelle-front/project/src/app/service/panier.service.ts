import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { ArticleDto } from '../model/articleDto';


@Injectable({
  providedIn: 'root'
})
export class PanierService {
 
  monUrl= 'http://localhost:8080/panier'; 

  article: ArticleDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(`${this.monUrl}/list`);
  }
  
  add(article: ArticleDto): Observable<object> {
    return this.http.post(`${this.monUrl}/add`,article);
  }
  
  getOne(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/show/${id}`);
  } 

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/user/${id}`);
  } 
  
  update(id: number, panier: Object): Observable<Object> {
    return this.http.put(`${this.monUrl}/update/${id}`, panier);
  }
 
 deleteArticles(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/deleteArticles/${id}`);
  }

  deletePanier(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/delete/${id}`);
  }

}