import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoryDTO } from 'src/app/models/category';
import { JewelInsertDTO } from 'src/app/models/jewelInsert';
import { CategoryService } from 'src/app/services/category.service';
import { JewelService } from 'src/app/services/jewel.service';

@Component({
  selector: 'app-jewel-delete',
  templateUrl: './jewel-delete.component.html',
  styleUrls: ['./jewel-delete.component.css']
})
export class JewelDeleteComponent implements OnInit {

  selectedCategory: number;

  jewel: JewelInsertDTO = {
    id: '',
    name: '',
    imgUrl: '',
    description: '',
    weight: undefined,
    size: '',
    price: undefined,
    categoryId: undefined
  }

  categories: CategoryDTO [] = [];

  constructor(private service: JewelService,
    private categoryService: CategoryService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.jewel.id = this.route.snapshot.paramMap.get('id');
    this.findById();
    this.findAllcategories();
  }


  findById(): void {
    this.service.findById(this.jewel.id).subscribe(resposta => {
      this.jewel = resposta;
    });
  }
  
  delete(): void {
    this.service.delete(this.jewel.id).subscribe(() => {
      this.toast.success('Joia deletada com sucesso', 'Delete');
      this.router.navigate(['jewels'])
    }, ex => {
      if(ex.error.errors) {
        ex.error.errors.forEach((element: { message: string; }) => {
          this.toast.error(element.message);
        });
      } else {
        this.toast.error(ex.error.message);
      }
    })
  }

  findAllcategories(){
    this.categoryService.findAll().subscribe(resposta => {
      this.categories = resposta.content;
    })
  }

}