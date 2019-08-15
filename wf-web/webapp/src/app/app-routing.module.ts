import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PersonGridComponent} from "./person-grid/person-grid.component";
import {PersonFormComponent} from './person-form/person-form.component';

const routes: Routes = [
    {path: '', component: PersonGridComponent},
    {path: 'create', component: PersonFormComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
