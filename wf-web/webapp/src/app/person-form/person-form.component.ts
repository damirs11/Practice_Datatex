import {Component, OnInit} from '@angular/core';
import {HeaderMenuService} from '../header-menu/header-menu.service';
import {ActivatedRoute} from '@angular/router';
import {PersonService} from '../shared/person.service';
import {Person} from '../models/staff/person.model';

@Component({
    selector: 'app-person-form',
    templateUrl: './person-form.component.html',
    styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {

    title: String = "Создание/Редактирование работника";
    person: Person;

    constructor(
        private headerMenuService: HeaderMenuService,
        private personService: PersonService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.headerMenuService.setTitle(this.title);
        this.loadPerson(this.activatedRoute.snapshot.params.id);
    }

    loadPerson(id: number) {
        return this.personService.getById(id).subscribe((data: Person) => {
            this.person = data;
        })
    }

}
