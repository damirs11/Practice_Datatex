import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderMenuComponent} from './header-menu/header-menu.component';
import {PersonGridComponent} from './person-grid/person-grid.component';
import {PersonService} from './shared/person.service';
import {PersonFormComponent} from "./person-form/person-form.component";

@NgModule({
    declarations: [
        AppComponent,
        HeaderMenuComponent,
        PersonGridComponent,
        PersonFormComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        PersonService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
