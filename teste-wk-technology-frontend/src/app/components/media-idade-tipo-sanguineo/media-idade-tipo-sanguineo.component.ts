import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MediaIdadePorTipoSanguineoDTO } from 'src/app/models/mediaIdadePorTipoSanguineo';
import { DoadorService } from 'src/app/services/doador.service';

@Component({
  selector: 'app-media-idade-tipo-sanguineo',
  templateUrl: './media-idade-tipo-sanguineo.component.html',
  styleUrls: ['./media-idade-tipo-sanguineo.component.css']
})
export class MediaIdadeTipoSanguineoComponent implements OnInit {

  ELEMENT_DATA: MediaIdadePorTipoSanguineoDTO[] = [];

  displayedColumns: string[] = ['tipoSanguineo', 'mediaIdade'];
  dataSource = new MatTableDataSource<MediaIdadePorTipoSanguineoDTO>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: DoadorService) { }

  ngOnInit(): void {
    this.mediaIdadePorTipoSanguineo();
  }

  mediaIdadePorTipoSanguineo(){
    this.service.mediaIdadePorTipoSanguineo().subscribe(resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<MediaIdadePorTipoSanguineoDTO>(resposta);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
