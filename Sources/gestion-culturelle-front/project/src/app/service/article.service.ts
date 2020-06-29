/* 
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { ArticleDto } from '../model/articleDto';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
 
  monUrl= 'http://localhost:8080/article'; 

  article: ArticleDto[]; 

  subjectMiseAJour= new Subject<number>();

  constructor(private http: HttpClient) { }

  getArticles(id: number): Observable<any> {
    return this.http.get(`${this.monUrl}/panier/${id}`);
  } 

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.monUrl}/${id}`);
  }


 
}

 */