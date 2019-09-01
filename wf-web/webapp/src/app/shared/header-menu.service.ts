import {EventEmitter, Injectable, Output} from '@angular/core'
import {MenuItem} from '../models/menuItem.model';

@Injectable()
export class HeaderMenuService {

    private title: String = 'NULL';
    private menuItems: MenuItem[] = [];

    @Output() changeTitle: EventEmitter<String> = new EventEmitter();
    @Output() changeMenuItems: EventEmitter<MenuItem[]> = new EventEmitter();

    setTitle(title: String) {
        this.title = title;
        this.changeTitle.emit(this.title);
    }

    setMenuItems(menuItems: MenuItem[]) {
        this.menuItems = menuItems;
        this.changeMenuItems.emit(this.menuItems);
    }
}