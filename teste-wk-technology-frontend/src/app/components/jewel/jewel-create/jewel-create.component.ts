import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CategoryDTO } from 'src/app/models/category';
import { JewelInsertDTO } from 'src/app/models/jewelInsert';
import { CategoryService } from 'src/app/services/category.service';
import { JewelService } from 'src/app/services/jewel.service';

@Component({
  selector: 'app-jewel-create',
  templateUrl: './jewel-create.component.html',
  styleUrls: ['./jewel-create.component.css']
})
export class JewelCreateComponent implements OnInit {

  selectedCategory: number;

  jewel: JewelInsertDTO = {
    name: "",
    imgUrl: "",
    description: "",
    weight: undefined,
    size: "",
    price: undefined,
    categoryId: undefined
  }

  categories: CategoryDTO [] = [];

  name: FormControl =  new FormControl(null, Validators.minLength(3));
  imgUrl: FormControl =  new FormControl(null, Validators.minLength(3));
  description: FormControl =  new FormControl(null, Validators.minLength(3));
  weight: FormControl =  new FormControl(null, Validators.nullValidator(null));
  size: FormControl =  new FormControl(null, Validators.nullValidator(null));
  price: FormControl =  new FormControl(null, Validators.required);
  category: FormControl =  new FormControl(null, Validators.nullValidator(null));

  constructor(private service: JewelService,
    private categoryService: CategoryService,
    private toast: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.findAllcategories();
  }


create():void{

  this.service.create(this.jewel).subscribe(()=>{
    this.toast.success('Joia cadastrado com sucesso', 'Cadastro');
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

validaCampos(): boolean {
  return this.name.valid && 
  this.imgUrl.valid &&
  this.description.valid &&
  this.weight.valid &&
  this.size.valid &&
  this.price.valid &&
  this.category.valid;
}

}