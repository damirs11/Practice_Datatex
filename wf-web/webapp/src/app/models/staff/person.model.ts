import {Staff} from './staff.model';

export class Person extends Staff {
    firstname: string;
    patronymic: string;
    surname: string;
    post: string;
    departmentId: number;
    photo: string;

    constructor() {
        super();
        this.id = 0;
        this.firstname = '';
        this.surname = '';
        this.patronymic = '';
        this.departmentId = 0;
        this.post = '';
        this.photo = '';
    }
}