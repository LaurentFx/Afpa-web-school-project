import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavebarComponent } from './navebar/navebar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { TypeSalleModule } from './modules/typesalle/typesalle.module';
import { RoleModule } from './modules/role/role.module';
import { SalleModule } from './modules/salle/salle.module';
import { LoginModule } from './modules/login/login.module';
import { ManifestationModule } from './modules/manifestation/manifestation.module';

@NgModule({
  declarations: [
    AppComponent,

    NavebarComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    TypeSalleModule,
    RoleModule,
    SalleModule,
    LoginModule,
    ManifestationModule    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
