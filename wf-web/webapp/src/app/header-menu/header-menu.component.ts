import {Component, OnInit} from '@angular/core';
import {HeaderMenuService} from './header-menu.service';

@Component({
    selector: 'app-header-menu',
    templateUrl: './header-menu.component.html',
    styleUrls: ['./header-menu.component.css']
})
export class HeaderMenuComponent implements OnInit {

    title: String;

    constructor(
        private headerMenuService: HeaderMenuService
    ) {
    }

    ngOnInit() {
        this.headerMenuService.change.subscribe((title: String) => {
            this.title = title;
        });
    }
}
