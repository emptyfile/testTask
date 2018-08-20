import { Component, OnInit } from '@angular/core';
import {BookService} from "../shared";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  providers: [BookService]
})
export class BookListComponent implements OnInit {
  books: Array<any>;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getAll().subscribe(
      data => {
        this.books = data;
        console.log(data);
      },
      error => console.log(error)
    )
  }

  filterBooks() {

  }

}
