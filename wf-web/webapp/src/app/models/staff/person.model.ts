import {Staff} from './staff.model';

export class Person extends Staff {
    firstname: String;
    patronymic: String;
    surname: String;
    post: String;
    departmentId: number;
    photo: String;
}