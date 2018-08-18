import { Component, OnInit } from '@angular/core';
import {AuthorService} from "../shared";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css'],
  providers: [AuthorService]
})
export class AuthorListComponent implements OnInit {
  authors: Array<any>;

  constructor(private authorService: AuthorService) { }

  ngOnInit() {
    this.authorService.getAll().subscribe(
      data => {
        this.authors = data;
        console.log(data)
      },
      error => console.log(error)
    )
  }

}
