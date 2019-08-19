import {EventEmitter, Injectable, Output} from '@angular/core'

@Injectable()
export class HeaderMenuService {

    private title: String = 'NULL';

    @Output() change: EventEmitter<String> = new EventEmitter();

    setTitle(title: String) {
        this.title = title;
        this.change.emit(this.title);
    }

}