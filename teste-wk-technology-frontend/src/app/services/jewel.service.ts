import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Jewel } from '../models/jewel';
import { JewelInsertDTO } from '../models/jewelInsert';
import { Page } from '../models/Page';

@Injectable({
  providedIn: 'root'
})
export class JewelService {

  constructor(private http: HttpClient) { }

  findById(id:any): Observable<Jewel>{
    console.log(id)
    return this.http.get<Jewel>(`${API_CONFIG.baseUrl}/jewels/${id}`);
  }

  findAll(): Observable<Page<Jewel>> {
    return this.http.get<Page<Jewel>>(`${API_CONFIG.baseUrl}/jewels`);
  }

  create(jewel: JewelInsertDTO): Observable<JewelInsertDTO> {
    return this.http.post<JewelInsertDTO>(`${API_CONFIG.baseUrl}/jewels`, jewel);
  }

  update(jewel: JewelInsertDTO): Observable<JewelInsertDTO> {
    return this.http.put<JewelInsertDTO>(`${API_CONFIG.baseUrl}/jewels/${jewel.id}`, jewel);
  }

  delete(id: any): Observable<Jewel>{
    return this.http.delete<Jewel>(`${API_CONFIG.baseUrl}/jewels/${id}`);
  }

}
