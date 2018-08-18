import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from '@reactivex/rxjs/es6/Observable.js'

@Injectable()
export class AuthorService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/authors');
  }
}
