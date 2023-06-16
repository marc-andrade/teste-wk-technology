import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  findById(id:any): Observable<User>{
    return this.http.get<User>(`${API_CONFIG.baseUrl}/users/${id}`);
  }

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${API_CONFIG.baseUrl}/users`);
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(`${API_CONFIG.baseUrl}/users`, user);
  }

  update(user: User): Observable<User> {
    return this.http.put<User>(`${API_CONFIG.baseUrl}/users/${user.id}`, user);
  }

  delete(id: any): Observable<User>{
    return this.http.delete<User>(`${API_CONFIG.baseUrl}/users/${id}`);
  }
}
