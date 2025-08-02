import { Component, OnInit } from '@angular/core';

interface User {
  id: number;
  username: string;
  email: string;
  roles: string[];
  age: number;
  salaire: number;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
})
export class AdminDashboardComponent implements OnInit {
  users: User[] = [];

  ngOnInit(): void {
    // Simuler la récupération de données (à remplacer par appel API réel)
    this.users = [
      { id: 1, username: 'sahar.gaiche', email: 'sahar@example.com', roles: ['ADMIN', 'USER'], age: 26, salaire: 2500 },
      { id: 2, username: 'john.doe', email: 'john.doe@mail.com', roles: ['USER'], age: 30, salaire: 1800 },
      { id: 3, username: 'alice.smith', email: 'alice@mail.com', roles: ['USER'], age: 22, salaire: 1500 },
    ];
  }

  onEdit(user: User): void {
    alert(`Modifier l'utilisateur: ${user.username} (fonction à implémenter)`);
    // Exemple: redirection vers formulaire d'édition avec l'id de l'utilisateur
  }

  onDelete(user: User): void {
    if (confirm(`Voulez-vous vraiment supprimer l'utilisateur ${user.username} ?`)) {
      // Supprimer l'utilisateur localement (dans un vrai projet, appeler l'API)
      this.users = this.users.filter(u => u.id !== user.id);
      alert(`Utilisateur ${user.username} supprimé.`);
    }
  }
}
