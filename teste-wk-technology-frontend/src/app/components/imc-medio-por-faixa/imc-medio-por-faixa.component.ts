import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { IMCMedioPorFaixaIdadeDTO } from 'src/app/models/imcMedioPorFaixa';
import { DoadorService } from 'src/app/services/doador.service';

@Component({
  selector: 'app-imc-medio-por-faixa',
  templateUrl: './imc-medio-por-faixa.component.html',
  styleUrls: ['./imc-medio-por-faixa.component.css']
})
export class ImcMedioPorFaixaComponent implements OnInit {

  ELEMENT_DATA: IMCMedioPorFaixaIdadeDTO[] = [];

  displayedColumns: string[] = ['faixaIdade', 'imc']; 
  dataSource = new MatTableDataSource<IMCMedioPorFaixaIdadeDTO>(this.ELEMENT_DATA); 

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: DoadorService) { }

  ngOnInit(): void {
    this.imcMedioPorFaixaIdade();
  }

  imcMedioPorFaixaIdade(){
    this.service.imcMedioPorFaixaIdade().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<IMCMedioPorFaixaIdadeDTO>(resposta);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
