import {Injectable} from "@angular/core";
import {Person} from '../models/staff/person.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PersonService {

    baseUrl: String = 'http://windows-ueebum4:8080/rest/ecm/';
    //baseUrl: String = "http://localhost:3000/rest/"; //only for json-server tests

    // Http Headers
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient) {
    }

    getAll(): Observable<Person[]> {
        console.log('Getting Persons');
        return this.http.get<Person[]>(this.baseUrl + 'employees')
            .pipe(
                map((data: Person[]) => {
                    return data
                }),
                catchError(this.errorHandle)
            )
    }

    getById(id: number): Observable<Person> {
        console.log('Getting Person id = ' + id);
        return this.http.get<Person>(this.baseUrl + 'employees/' + id)
            .pipe(
                map((data: Person) => {
                    return data
                }),
                catchError(this.errorHandle)
            )
    }

    update(id: number, person: Person): Observable<Person> {
        console.log('Updating Person id = ' + id);
        return this.http.put<Person>(this.baseUrl + 'employees/' + id, person, this.httpOptions)
            .pipe(
                map(data => data),
                catchError(this.errorHandle)
            )
    }

    create(person: Person): Observable<Person> {
        console.log('Creating Person');
        return this.http.post<Person>(this.baseUrl + 'employees', person, this.httpOptions)
            .pipe(
                map(data => data),
                catchError(this.errorHandle)
            )
    }

    deleteById(id: number): Observable<Person> {
        console.log('Deleteing Person id = ' + id);
        return this.http.delete<Person>(this.baseUrl + 'employees/' + id)
            .pipe(
                map((data: Person) => {
                    return data
                }),
                catchError(this.errorHandle)
            )
    }

    // Error handling
    errorHandle(error) {
        let errorMessage = '';

        if (error.error instanceof ErrorEvent) {
            // Get client-side error
            errorMessage = error.error.message;
        } else {
            // Get server-side error
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        console.log(errorMessage);

        return throwError(errorMessage);
    }
}