import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TypeSalleListComponent } from './TypeSalle-list/TypeSalle-list.component';
import { TypeSalleDetailComponent } from './TypeSalle-detail/TypeSalle-detail.component';
import { TypeSalleAddComponent } from './TypeSalle-add/TypeSalle-add.component';
import { NavebarComponent } from './navebar/navebar.component';

@NgModule({
  declarations: [
    AppComponent,
    TypeSalleListComponent,
    TypeSalleDetailComponent,
    TypeSalleAddComponent,
    NavebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
