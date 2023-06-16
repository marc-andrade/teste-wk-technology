import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Jewel } from 'src/app/models/jewel';
import { JewelService } from 'src/app/services/jewel.service';

@Component({
  selector: 'app-jewel-list',
  templateUrl: './jewel-list.component.html',
  styleUrls: ['./jewel-list.component.css']
})
export class JewelListComponent implements OnInit {

  ELEMENT_DATA: Jewel[] = [];

  displayedColumns: string[] = ['id', 'nome','weight','size','price',
  'category','acoes'];
  dataSource = new MatTableDataSource<Jewel>(this.ELEMENT_DATA);
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: JewelService) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.ELEMENT_DATA = resposta.content;
      
      this.dataSource = new MatTableDataSource<Jewel>(resposta.content);
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  
}
