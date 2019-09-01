import {Component, OnInit} from '@angular/core';
import {HeaderMenuService} from '../shared/header-menu.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PersonService} from '../shared/person.service';
import {Person} from '../models/staff/person.model';
import {MenuItem} from '../models/menuItem.model';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
    selector: 'app-person-form',
    templateUrl: './person-form.component.html',
    styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {

    private title: String = "Создание/Редактирование работника";
    private idParam: number = this.activatedRoute.snapshot.params.id;

    createBool = false;
    person: Person = new Person();
    menuItems: MenuItem[] = [
        {path: '', title: 'Назад'}
    ];

    constructor(
        public _DomSanitizer: DomSanitizer,
        private headerMenuService: HeaderMenuService,
        private personService: PersonService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {
    }

    ngOnInit() {
        if (this.idParam) {
            this.loadPerson(this.idParam);
        } else {
            this.createBool = !this.idParam;
        }
        
        this.headerMenuService.setTitle(this.title);
        this.headerMenuService.setMenuItems(this.menuItems);
    }

    loadPerson(id: number) {
        return this.personService.getById(id).subscribe((data: Person) => {
            this.person = data;
        })
    }

    onSubmit() {
        if (!this.createBool) {
            this.personService.update(this.idParam, this.person).subscribe((response) => {
                console.log(response);

                this.router.navigate([
                    this.menuItems[0].path
                ]);
            });
        } else {
            this.personService.create(this.person).subscribe((response) => {
                console.log(response);

                this.router.navigate([
                    this.menuItems[0].path
                ]);
            });
        }
    }

    delete() {
        this.personService.deleteById(this.person.id).subscribe((response) => {
            console.log(response);

            this.router.navigate([
                this.menuItems[0].path
            ]);
        });
    }

    getPhoto() {
        return this._DomSanitizer.bypassSecurityTrustResourceUrl("data:image/png;base64," + this.person.photo);
    }

    fileProgress(fileInput: any) {
        var reader = new FileReader();
        reader.readAsBinaryString(fileInput.target.files[0]);
        reader.onload = (_event) => {
            this.person.photo = btoa(reader.result as string);
        }
    }

}
