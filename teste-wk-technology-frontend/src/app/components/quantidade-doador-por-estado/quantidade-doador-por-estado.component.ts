import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { QuantidaePorEstadoDTO } from 'src/app/models/quantidadePorEstado';
import { DoadorService } from 'src/app/services/doador.service';

@Component({
  selector: 'app-quantidade-doador-por-estado',
  templateUrl: './quantidade-doador-por-estado.component.html',
  styleUrls: ['./quantidade-doador-por-estado.component.css']
})
export class QuantidadeDoadorPorEstadoComponent implements OnInit {

  ELEMENT_DATA: QuantidaePorEstadoDTO[] = [];

  displayedColumns: string[] = ['estado', 'quantidade'];
  dataSource = new MatTableDataSource<QuantidaePorEstadoDTO>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator; 

  constructor(private service: DoadorService) { }

  ngOnInit(): void {
    this.quantidadePorEstado();
  }

  quantidadePorEstado(){
    this.service.quantidadePorEstado().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<QuantidaePorEstadoDTO>(resposta);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
