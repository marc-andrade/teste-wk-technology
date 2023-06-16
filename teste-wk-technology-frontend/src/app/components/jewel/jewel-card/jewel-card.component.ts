import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Jewel } from 'src/app/models/jewel';
import { JewelService } from 'src/app/services/jewel.service';

@Component({
  selector: 'app-jewel-card',
  templateUrl: './jewel-card.component.html',
  styleUrls: ['./jewel-card.component.css'],

  
})
export class JewelCardComponent implements OnInit {

  jewels: Jewel[] = [];

  constructor(private service: JewelService) { }

  ngOnInit(): void {
    this.findAll();
    
  }

  
  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.jewels = resposta.content;
    })
  }

}
