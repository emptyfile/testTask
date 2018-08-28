import {Component, OnInit} from '@angular/core';
import {AuthorService} from "../shared";
import {ActivatedRoute} from "@angular/router"

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css'],
  providers: [AuthorService]
})
export class AuthorListComponent implements OnInit {
  authors: Array<any>;

  constructor(private authorService: AuthorService, private route: ActivatedRoute) {
  }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      console.log(params);
      let book_id = params["book_id"];
      if (book_id == undefined) {
        this.authorService.getAll().subscribe(
          data => {
            this.authors = data;
            console.log(data)
          },
          error => console.log(error)
        )
      } else {
        this.authorService.getByBookId(book_id).subscribe(
          data => {
            this.authors = data.body;
            console.log(data)
          },
          error => console.log(error)
        )
      }
    })
  }

  public delete(event, authorId) {
    this.authorService.delete(authorId).subscribe(()=> location.reload());
  }

}
