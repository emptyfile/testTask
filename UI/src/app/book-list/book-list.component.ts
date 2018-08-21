import {Component, OnInit} from '@angular/core';
import {BookService} from "../shared";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  providers: [BookService]
})
export class BookListComponent implements OnInit {
  books: Array<any>;

  constructor(private bookService: BookService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
        console.log(params);
        let author_id = params["author_id"];
        if (author_id == undefined) {
          this.bookService.getAll().subscribe(
            data => {
              this.books = data;
              console.log(data);
            },
            error => console.log(error)
          )
        } else {
          this.bookService.getById(author_id).subscribe(
            data => {
              this.books = data.body;
              console.log(data);
            },
            error => console.log(error)
          )
        }
      }
    )


  }

  filterBooks() {

  }

}
