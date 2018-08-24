import { Component, OnInit } from '@angular/core';
import {AuthorService, BookService} from "../shared";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css'],
  providers: [BookService, AuthorService]
})
export class BookPageComponent implements OnInit {
  book:any;
  authors: Array<any>;

  constructor(private bookService: BookService, private authorService:AuthorService) {
    this.book = {};
  }

  ngOnInit() {
    this.authorService.getAll().subscribe(
      data=> {
        this.authors = data
      },  error => console.log(error)
    )
  }

  public save() {
    this.bookService.save(this.book).subscribe(
      () => console.log("saved " + this.book)
    )
  }

  public log() {
    console.log(this.book)
  }

  public setAuthors(author:any) {
    console.log(JSON.stringify(author));
    this.book.authors = [{'id':this.authors[0].id, 'firstName':this.authors[0].firstName, 'lastName':this.authors[0].lastName}];
    console.log(this.book.authors);
  }

}
