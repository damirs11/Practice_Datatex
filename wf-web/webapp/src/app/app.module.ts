import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms'

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderMenuComponent} from './header-menu/header-menu.component';
import {PersonGridComponent} from './person-grid/person-grid.component';
import {PersonService} from './shared/person.service';
import {PersonFormComponent} from "./person-form/person-form.component";
import {HeaderMenuService} from './header-menu/header-menu.service';

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
        HttpClientModule,
        FormsModule
    ],
    providers: [
        PersonService,
        HeaderMenuService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
