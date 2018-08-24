import { Component, OnInit } from '@angular/core';
import {AuthorService, BookService} from "../shared";

@Component({
  selector: 'app-author-page',
  templateUrl: './author-page.component.html',
  styleUrls: ['./author-page.component.css'],
  providers: [AuthorService]
})
export class AuthorPageComponent implements OnInit {
  author:any;

  constructor(private authorService:AuthorService) {
    this.author = {};
  }

  ngOnInit() {
  }

  public save() {
    this.authorService.save(this.author).subscribe(
      () => console.log("saved " + this.author)
    )
  }
}
