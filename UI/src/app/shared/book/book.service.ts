import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/books');
  }


  getByAuthorId(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/books', {
      params: new HttpParams().set("author_id", ""+id),
      observe: 'response'
    })

  }

  getById(id:number):Observable<any> {
    let url = "http://localhost:8080/books/" + id;
    return this.http.get(url);
  }

  delete(bookId: any): Observable<any> {
    let url = "http://localhost:8080/books/" + bookId;
    return this.http.delete(url);
  }

  update(book: any):Observable<any> {
    let url = "http://localhost:8080/books/" + book.id;
    return this.http.put(url, JSON.stringify(book),{headers: {'Content-Type':'application/json'}});
  }

  save(book: any):Observable<any> {
    return this.http.post('http://localhost:8080/books', JSON.stringify(book),{headers: {'Content-Type':'application/json'}});
  }
}
