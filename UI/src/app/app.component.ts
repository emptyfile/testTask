import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'UI';
  template: `
  <h1>Angular Router</h1>
  <nav>
    <a routerLink="/author-list" routerLinkActive="active">Authors</a>
    <a routerLink="/book-list" routerLinkActive="active">Books</a>
  </nav>
  <router-outlet></router-outlet>
`
}
