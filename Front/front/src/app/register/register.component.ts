import { Component, OnInit } from "@angular/core";
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
  AbstractControl,
  ValidatorFn,
  ValidationErrors
} from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "../environement/user.service";

type ERole = 'USER' | 'ADMIN' | 'MANAGER' | 'MODERATOR';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html"
})
export class RegisterComponent implements OnInit {
  userForm!: FormGroup;
  base64code: string = '';
  roles: ERole[] = ['USER', 'ADMIN', 'MANAGER', 'MODERATOR'];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: UserService
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      photo: [''], // Pas obligatoire pour test
      roles: this.fb.array([], this.minSelectedRoles(1)),
      salaire: [0, Validators.min(0)],
      hasCredit: [false],
      hasInsurance: [false],
      age: [18, [Validators.required, Validators.min(18)]],
      anciennete: [0, Validators.min(0)]
    });

    const rolesArray = this.userForm.get('roles') as FormArray;
    this.roles.forEach(() => rolesArray.push(new FormControl(false)));
  }

  private minSelectedRoles(min: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const formArray = control as FormArray;
      const selectedCount = formArray.controls.filter(c => c.value).length;
      return selectedCount >= min ? null : { required: true };
    };
  }

  get rolesControls() {
    return (this.userForm.get('roles') as FormArray).controls;
  }

  onFileChanged(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.base64code = reader.result as string;
        this.userForm.get('photo')?.setValue(this.base64code);
        this.userForm.get('photo')?.markAsTouched();
      };
    }
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const selectedRoles: ERole[] = this.roles.filter((_, index) =>
        (this.userForm.get('roles') as FormArray).at(index).value
      );

      const user = {
        username: this.userForm.get('username')?.value,
        firstName: this.userForm.get('firstName')?.value,
        lastName: this.userForm.get('lastName')?.value,
        email: this.userForm.get('email')?.value,
        password: this.userForm.get('password')?.value,
        photo: this.base64code || '',
        roles: selectedRoles,
        salaire: +this.userForm.get('salaire')?.value,
        hasCredit: this.userForm.get('hasCredit')?.value,
        hasInsurance: this.userForm.get('hasInsurance')?.value,
        age: +this.userForm.get('age')?.value,
        anciennete: +this.userForm.get('anciennete')?.value,
      };

      console.log("Payload envoyé:", JSON.stringify(user));

    this.service.createUser(user).subscribe({
  next: (response) => {
    console.log('Utilisateur créé avec succès', response);
    this.router.navigate(['/auth/login']);
  },
  error: (err) => {
    console.error('Erreur lors de la création utilisateur', err);
    if (err.error) {
      console.error('Détail backend:', err.error);
      alert('Erreur backend : ' + JSON.stringify(err.error));
    }
  }
});

    } else {
      console.log("Formulaire invalide");
      Object.keys(this.userForm.controls).forEach(key => {
        const control = this.userForm.get(key);
        if (control && control.invalid) {
          console.log(`- ${key} est invalide:`, control.errors);
        }
      });
    }
  }
}
