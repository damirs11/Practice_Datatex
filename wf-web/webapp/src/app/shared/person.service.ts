import {Injectable} from "@angular/core";
import {Person} from './../models/staff/person.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PersonService {

    //baseUrl: String = location.href.replace(location.hash, "") + 'rest/';
    baseUrl: String = "http://localhost:3000/rest/";

    // Http Headers
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient) {
    }

    getAll(): Observable<Person> {
        return this.http.get<Person>(this.baseUrl + 'ecm/employees')
            .pipe(
                retry(1),
                catchError(this.errorHandle)
            )
    }

    getById(id: number): Observable<Person> {
        return this.http.get<Person>(this.baseUrl + 'ecm/employees/' + id)
            .pipe(
                retry(1),
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