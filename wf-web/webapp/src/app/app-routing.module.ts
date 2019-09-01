import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PersonGridComponent} from "./person-grid/person-grid.component";
import {PersonFormComponent} from './person-form/person-form.component';
import {NotFoundComponent} from './not-found/not-found.component';

const routes: Routes = [
    {path: '', component: PersonGridComponent},
    {path: 'person/:id', component: PersonFormComponent},
    {path: 'person', component: PersonFormComponent},
    {path: '**', component: NotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
    declarations: []
})
export class AppRoutingModule {
}
