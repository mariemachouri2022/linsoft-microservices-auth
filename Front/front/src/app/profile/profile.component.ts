import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  user: any;

  ngOnInit(): void {
    // Exemple de données récupérées depuis un service ou le localStorage
    this.user = {
      username: '',
      email: '',
      roles: ['ADMIN', 'USER'],
      salaire: 50000,
      hasCredit: true,
      hasInsurance: false,
      age:6,
      anciennete: 3
    };
  }
}
