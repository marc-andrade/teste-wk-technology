import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuantidaePorEstadoDTO } from '../models/quantidadePorEstado';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { IMCMedioPorFaixaIdadeDTO } from '../models/imcMedioPorFaixa';
import { PercentualObesosGeneroDTO } from '../models/percentualObesos';
import { MediaIdadePorTipoSanguineoDTO } from '../models/mediaIdadePorTipoSanguineo';
import { QuantidadeDoadoresPorTipoSanguineoReceptorDTO } from '../models/quantidadeDoadoresPorTipoSanguineo';

@Injectable({
  providedIn: 'root'
})
export class DoadorService {

  constructor(private http: HttpClient) { } 

  quantidadePorEstado(): Observable<QuantidaePorEstadoDTO[]> {
    return this.http.get<QuantidaePorEstadoDTO[]>(`${API_CONFIG.baseUrl}/doadores/quantidade-por-estado`);
  }

  imcMedioPorFaixaIdade(): Observable<IMCMedioPorFaixaIdadeDTO[]> {
    return this.http.get<IMCMedioPorFaixaIdadeDTO[]>(`${API_CONFIG.baseUrl}/doadores/imc-medio-por-faixa-idade`);
  }

  percentualObesosGenero(): Observable<PercentualObesosGeneroDTO[]> {
    return this.http.get<PercentualObesosGeneroDTO[]>(`${API_CONFIG.baseUrl}/doadores/percentual-obesos-genero`);
  }

  mediaIdadePorTipoSanguineo(): Observable<MediaIdadePorTipoSanguineoDTO[]> {
    return this.http.get<MediaIdadePorTipoSanguineoDTO[]>(`${API_CONFIG.baseUrl}/doadores/media-idade-por-tipo-sanguineo`);
  }

  quantidadePossiveisDoadores(): Observable<QuantidadeDoadoresPorTipoSanguineoReceptorDTO[]> {
    return this.http.get<QuantidadeDoadoresPorTipoSanguineoReceptorDTO[]>(`${API_CONFIG.baseUrl}/doadores/quantidade-possiveis-doadores`);
  }

}
