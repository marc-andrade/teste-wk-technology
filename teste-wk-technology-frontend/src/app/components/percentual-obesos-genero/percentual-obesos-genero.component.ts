import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { PercentualObesosGeneroDTO } from 'src/app/models/percentualObesos';
import { DoadorService } from 'src/app/services/doador.service';

@Component({
  selector: 'app-percentual-obesos-genero',
  templateUrl: './percentual-obesos-genero.component.html',
  styleUrls: ['./percentual-obesos-genero.component.css']
})
export class PercentualObesosGeneroComponent implements OnInit {

  ELEMENT_DATA: PercentualObesosGeneroDTO[] = [];

  displayedColumns: string[] = ['genero', 'percentual']; 
  dataSource = new MatTableDataSource<PercentualObesosGeneroDTO>(this.ELEMENT_DATA); 

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: DoadorService) { }

  ngOnInit(): void {
    this.imcMedioPorFaixaIdade();
  }

  imcMedioPorFaixaIdade(){
    this.service.percentualObesosGenero().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<PercentualObesosGeneroDTO>(resposta);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
