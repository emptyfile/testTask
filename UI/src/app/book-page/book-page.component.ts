import {Component, OnInit} from '@angular/core';
import {AuthorService, BookService} from "../shared";
import {forEach} from "@angular/router/src/utils/collection";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css'],
  providers: [BookService, AuthorService]
})
export class BookPageComponent implements OnInit {
  book: any;
  authors: Array<any>;
  selectedAuthors: Array<any>;
  numbers: Array<number>;
  smartWatch: string;

  constructor(private bookService: BookService, private authorService: AuthorService, private route: ActivatedRoute) {
    this.authors = [{"id": 0, "firstName": "", "lastName": ""}];
    this.book = {};
    this.selectedAuthors = [this.authors[0]];
    this.numbers = [0];
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params);
      let book_id = params["book_id"];
      this.authorService.getAllBase().subscribe(
        data => {
          this.authors = this.authors.concat(data);
        }, error => console.log(error)
      );
      if (book_id != undefined) {

        this.bookService.getById(book_id).subscribe(
          data => {
            this.book = data;
            console.log(data);
            this.selectedAuthors = this.book.authors;
            console.error(this.selectedAuthors)
            this.numbers = Array.from(new Array(this.selectedAuthors.length), (x,i)=>i)
          },
          error => console.log(error)
        )
      }
    })

  }

  public save() {
    this.book.authors = this.selectedAuthors.slice().filter(a => a.id != 0).filter((elem, pos, arr) => {
      return arr.indexOf(elem) == pos;
    });
    if (this.book.id == undefined) {
      this.bookService.save(this.book).subscribe(
        () => console.log("saved " + this.book)
      )
    } else {
      this.bookService.update(this.book).subscribe(
        () => console.log("updated" + this.book)
      )
    }

  }

  public log(event: any) {
    console.log(this.selectedAuthors)
  }

  public setAuthors(authorId: any, index: number) {
    if (authorId != 0) {
      this.selectedAuthors[index] = this.authors.filter(a => a.id == authorId)[0];
    } else {
      this.selectedAuthors[index] = null;
    }
    console.log(JSON.stringify(this.selectedAuthors));
  }

  public addAuthor() {
    this.numbers.push(this.numbers.length);
    this.selectedAuthors.push(null);
    console.log(JSON.stringify(this.selectedAuthors));
  }

  public string(obj:any):string {
    return JSON.stringify(obj);
  }

  public compareAuthor(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}
