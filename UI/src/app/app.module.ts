import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AuthorListComponent } from './author-list/author-list.component';
import {HttpClientModule} from "@angular/common/http";
import { BookListComponent } from './book-list/book-list.component';

const appRoutes: Routes = [
  { path: 'authors-list', component: AuthorListComponent },
  { path: 'book-list',      component: BookListComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    AuthorListComponent,
    BookListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
