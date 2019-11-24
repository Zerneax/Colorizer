import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ImagesComponent } from './pages/images/images.component';
import { ImagesModule } from './pages/images/images.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ImagesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
