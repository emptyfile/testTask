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


  getById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/books', {
      params: new HttpParams().set("author_id", ""+id),
      observe: 'response'
    })

  }

  delete(bookId: any): Observable<any> {
    let url = "http://localhost:8080/books/" + bookId;
    console.log(url);
    return this.http.delete(url);

  }

  save(book: any):Observable<any> {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/books', JSON.stringify(book),{headers: {'Content-Type':'application/json'}});
  }
}
