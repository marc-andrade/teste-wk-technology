import { Role } from "./role";

export interface User{
    id?: any;
    name: string;
    email: string;
    password: string;
    roles: Role[]
}

  