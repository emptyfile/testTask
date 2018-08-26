import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AuthorListComponent } from './author-list/author-list.component';
import {HttpClientModule} from "@angular/common/http";
import { BookListComponent } from './book-list/book-list.component';
import { BookPageComponent } from './book-page/book-page.component';
import { AuthorPageComponent } from './author-page/author-page.component';
import {FormsModule} from "@angular/forms";

const appRoutes: Routes = [
  { path: 'authors-list', component: AuthorListComponent },
  { path: 'book-list',      component: BookListComponent },
  { path: 'book-page',      component: BookPageComponent },
  { path: 'author-page',      component: AuthorPageComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    AuthorListComponent,
    BookListComponent,
    BookPageComponent,
    AuthorPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    ),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
