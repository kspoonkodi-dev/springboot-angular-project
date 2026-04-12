import { Role } from './role.enum';

export interface User {
  userId: number;
  username: string;
  password: string;
  email: string;
  role: Role[];
  status: string;
  accountCreatedDate: Date;
}
