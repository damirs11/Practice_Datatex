import {Staff} from './staff.model';

export class Person extends Staff {
    surname: String;
    firstname: String;
    patronymic: String;
    post: String;
    departmentId: number;
    photo: String;
}