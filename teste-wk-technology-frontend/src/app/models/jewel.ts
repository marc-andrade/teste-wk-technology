import { CategoryDTO } from "./category";

export interface Jewel{
    id?: any;
    name: string;
    imgUrl: string;
    description: string;
    weight: number;
    size: string;
    price: number;
    category: CategoryDTO
}

  