import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/index'

@Injectable()
export class AuthorService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/authors');
  }

  getById(id: number): Observable<any> {
    return this.http.get("http://localhost:8080/authors", {
      params: new HttpParams().set('book_id', ""+id),
      observe: 'response'
    })
  }
}
