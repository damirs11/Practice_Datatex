import {Component, OnInit, Output} from '@angular/core';
import {PersonService} from '../shared/person.service';
import {HeaderMenuService} from '../header-menu/header-menu.service';

@Component({
    selector: 'app-person-grid',
    templateUrl: './person-grid.component.html',
    styleUrls: ['./person-grid.component.css']
})
export class PersonGridComponent implements OnInit {

    @Output() title: string = "Работники";

    personList: any = [];

    constructor(
        public personService: PersonService,
        private headerMenuService: HeaderMenuService
    ) {
    }

    ngOnInit() {
        this.loadPersons();
        this.headerMenuService.setTitle(this.title);
    }

    loadPersons() {
        return this.personService.getAll().subscribe((data: {}) => {
            this.personList = data;
        })
    }
}
