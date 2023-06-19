import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { QuantidadeDoadoresPorTipoSanguineoReceptorDTO } from 'src/app/models/quantidadeDoadoresPorTipoSanguineo';
import { DoadorService } from 'src/app/services/doador.service';

@Component({
  selector: 'app-quantidade-possiveis-doadores',
  templateUrl: './quantidade-possiveis-doadores.component.html',
  styleUrls: ['./quantidade-possiveis-doadores.component.css']
})
export class QuantidadePossiveisDoadoresComponent implements OnInit {

  ELEMENT_DATA: QuantidadeDoadoresPorTipoSanguineoReceptorDTO[] = [];

  displayedColumns: string[] = ['tipoSanguineoReceptor', 'quantidadeDoadores'];
  dataSource = new MatTableDataSource<QuantidadeDoadoresPorTipoSanguineoReceptorDTO>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: DoadorService) { }

  ngOnInit(): void {
    this.quantidadePossiveisDoadores();
  }

  quantidadePossiveisDoadores(){
    this.service.quantidadePossiveisDoadores().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<QuantidadeDoadoresPorTipoSanguineoReceptorDTO>(resposta);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
