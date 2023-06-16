import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Role } from 'src/app/models/role';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  user: User = {
    id: '',
    name: '',
    email: '',
    password: '',
    roles: []
  }


  name: FormControl =  new FormControl(null, Validators.minLength(3));
  email: FormControl =        new FormControl(null, Validators.email);
  password: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(private service: UserService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.user.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.user.id).subscribe(resposta => {
      resposta.roles = [];
      this.user = resposta;
    });
  }
  
  update(): void {
    this.service.update(this.user).subscribe(() => {
      this.toast.success('Usuário atualizado com sucesso', 'Update');
      this.router.navigate(['users'])
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

  addPerfil(role: Role): void {

    if(this.user.roles.includes(role)) {
      this.user.roles.splice(this.user.roles.indexOf(role), 1);
      console.log(this.user.roles)
    } else {
      this.user.roles.push(role);
      console.log(this.user.roles)
    }
    
  }

  validaCampos(): boolean {
    return this.name.valid 
    && this.email.valid 
    && this.password.valid
  }
}
