export interface User {
  id?: number;  // rendu optionnel avec le `?`
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  photo: string;
  roles: ERole[];
  salaire: number;
  hasCredit: boolean;
  hasInsurance: boolean;
  age: number;
  anciennete: number;
}


export type ERole = 'USER' | 'ADMIN' | 'MANAGER' | 'MODERATOR'; // À adapter selon ton enum Java
