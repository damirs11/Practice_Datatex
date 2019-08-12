import {Component, OnInit} from '@angular/core';
import {PersonService} from '../shared/person.service';

@Component({
    selector: 'app-person-grid',
    templateUrl: './person-grid.component.html',
    styleUrls: ['./person-grid.component.css']
})
export class PersonGridComponent implements OnInit {

    personList: any = [];

    constructor(
        public personService: PersonService
    ) {
    }

    ngOnInit() {
        this.loadPersons();
    }

    loadPersons() {
        return this.personService.getAll().subscribe((data: {}) => {
            this.personList = data;
        })
    }
}
