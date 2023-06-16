import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { CategoryDTO } from '../models/category';
import { Page } from '../models/Page';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }
  
  findAll(): Observable<Page<CategoryDTO>> {
    return this.http.get<Page<CategoryDTO>>(`${API_CONFIG.baseUrl}/categories`);
  }

}
