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

  getByBookId(id: number): Observable<any> {
    return this.http.get("http://localhost:8080/authors", {
      params: new HttpParams().set('book_id', "" + id),
      observe: 'response'
    })
  }

  delete(authorId: any): Observable<any> {
    let url = "http://localhost:8080/authors/" + authorId;
    return this.http.delete(url);
  }

  save(author: any):Observable<any> {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/authors', JSON.stringify(author),{headers: {'Content-Type':'application/json'}});
  }

  getAllBase():Observable<any> {
    return this.http.get('http://localhost:8080/authors/base');
  }

  getBaseById(authorId:number):Observable<any> {
    let url = "http://localhost:8080/authors/base/" + authorId;
    return this.http.get(url);
  }

  update(author: any) :Observable<any> {
    let url = "http://localhost:8080/authors/" + author.id;
    return this.http.put(url, JSON.stringify(author),{headers: {'Content-Type':'application/json'}});
  }
}
