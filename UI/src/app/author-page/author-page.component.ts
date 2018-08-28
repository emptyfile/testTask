import { Component, OnInit } from '@angular/core';
import {AuthorService, BookService} from "../shared";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-author-page',
  templateUrl: './author-page.component.html',
  styleUrls: ['./author-page.component.css'],
  providers: [AuthorService]
})
export class AuthorPageComponent implements OnInit {
  author:any;

  constructor(private authorService:AuthorService, private route: ActivatedRoute, private router: Router) {
    this.author = {};
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params);
      let author_id = params["author_id"];
      if (author_id != undefined) {
        this.authorService.getBaseById(author_id).subscribe(
          data => {
            this.author = data;
            console.log(data);
          },
          error => console.log(error)
        )
      }
    })
  }

  public save() {
    if (this.author.id == undefined) {
      this.authorService.save(this.author).subscribe(
        () => this.router.navigate(["/authors-list"])
      )
    } else {
      this.authorService.update(this.author).subscribe(
        () => this.router.navigate(["/authors-list"])
      )
    }
  }
}
