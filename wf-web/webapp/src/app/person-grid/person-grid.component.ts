import {Component, OnInit} from '@angular/core';
import {PersonService} from '../shared/person.service';
import {HeaderMenuService} from '../shared/header-menu.service';
import {Person} from '../models/staff/person.model';
import {MenuItem} from '../models/menuItem.model';

@Component({
    selector: 'app-person-grid',
    templateUrl: './person-grid.component.html',
    styleUrls: ['./person-grid.component.css']
})
export class PersonGridComponent implements OnInit {

    title: string = "Работники";
    menuItems: MenuItem[] = [
        {path: 'person', title: 'Создать работника'}
    ];

    personList: any = [];

    constructor(
        public personService: PersonService,
        private headerMenuService: HeaderMenuService
    ) {
    }

    ngOnInit() {
        this.loadPersons();
        this.headerMenuService.setTitle(this.title);
        this.headerMenuService.setMenuItems(this.menuItems);
    }

    loadPersons() {
        return this.personService.getAll().subscribe((data: Person[]) => {
            this.personList = data;
        })
    }
}
