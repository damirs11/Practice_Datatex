import {Component, OnInit} from '@angular/core';
import {HeaderMenuService} from '../shared/header-menu.service';
import {MenuItem} from '../models/menuItem.model';

@Component({
    selector: 'app-header-menu',
    templateUrl: './header-menu.component.html',
    styleUrls: ['./header-menu.component.css']
})
export class HeaderMenuComponent implements OnInit {

    title: String;
    menuItems: MenuItem[];

    constructor(
        private headerMenuService: HeaderMenuService
    ) {
    }

    ngOnInit() {
        this.headerMenuService.changeTitle.subscribe((title: String) => {
            this.title = title;
        });
        this.headerMenuService.changeMenuItems.subscribe((menuItems: MenuItem[]) => {
            this.menuItems = menuItems;
        });
    }
}
